#-*- coding:utf-8 -*-
import torch
import torch.nn as nn
import torch.nn.functional as F
import torchvision
import torchvision.transforms as transforms
from torch.utils.data import DataLoader


# 数据目录
DATA_ROOT="./data"

# 网络模型
class CNN(nn.Module):
    def __init__(self):
        super(CNN,self).__init__()
        self.conv1=nn.Sequential(   # 输入大小(3,200,200)
            nn.Conv2d(in_channels=3,out_channels=8,kernel_size=10,stride=1,padding=4),  # 卷积层 输出大小(8,200,200)
            nn.ReLU(),  # 激活函数
            nn.MaxPool2d(kernel_size=2) # 池化层 输出大小 (8,99,99)
        )   
        self.conv2=nn.Sequential( # 输入大小 (8,99,99)
            nn.Conv2d(8,16,10,1,4), # 卷积层 输出大小 (16,99,99)
            nn.ReLU(),
            nn.MaxPool2d(2) # 池化层 输出大小(16,49,49)
        )
        self.out=nn.Linear(16*49*49,100)    # 线性回归，100类


    def forward(self,x):
        x=self.conv1(x)
        # print(x.size())
        x=self.conv2(x)
        # print(x.size())
        x=x.view(x.size(0),-1)   # 展平多维的卷积图 16*49*49
        output=self.out(x)
        return output


# 训练数据读取
t=transforms.Compose([transforms.ToTensor()]) # ToTensor() 转为向量
data=torchvision.datasets.ImageFolder(root=DATA_ROOT,transform=t) #设置数据目录
loader=DataLoader(data,batch_size=8) 


cnn=CNN()   # 网络初始化
cnn.cuda()  # 将网络转至gpu
# print(cnn)
loss_func=nn.CrossEntropyLoss() # 设置损失函数 交叉熵损失函数
optimizer=torch.optim.Adam(cnn.parameters(),lr=0.0001)  
EPOCH=100   # 设定训练轮数
n=len(loader)   # 训练数据集大小


for epoch in range(EPOCH):
    cnn.train() # 将网络设置为训练模式
    for step,(b_x,b_y) in enumerate(loader):
        b_x=b_x.cuda()
        b_y=b_y.cuda()  # 从数据集中的读取数据并转移至gpu内存
        output=cnn(b_x) # 正向传播
        loss=loss_func(output,b_y)  # 计算损失
        cnn.zero_grad() # 梯度置零
        loss.backward() # 反向传播
        optimizer.step()# 步进

    # 每五轮检验一次模型
    if epoch%5==0:
        cnn.eval()  # 设置模型为评估模式
        accuracy=0  
        for i,(test_x,test_y) in enumerate(loader):
            # 从训练数据中每隔5抽取一组样本作为测试数据
            if i % 5==0:
                test_x=test_x.cuda()
                test_y=test_y.cuda() # 读取数据将其转至gpu内存
                preds=cnn(test_x)       # 模型预测结果
                pred=torch.max(preds,1)[1].cuda().data.squeeze()    # 处理预测结果
                print("test_y:",test_y)
                print("pred:",pred)
                if torch.equal(pred,test_y):  # 判断预测结果是否正确
                    accuracy+=1

        acc=float(accuracy)/float(n/5)  # 计算准确率
        # 打印结果
        # loss 损失率
        # acc 准确率
        # acc_num:预测正确次数-预测总次数
        print ('Epoch[{}/{}],loss:{:.6f},acc:{:.6f},acc_num:{}-{}'.format(EPOCH,epoch+1,loss,acc,accuracy,n/5))
    if epoch%10==0:
        torch.save(cnn,"./leaves.model")
        print("Epoch:{} saved".format(epoch))
