#-*- coding:utf-8 -*-
import torch
import torch.nn as nn
import torch.nn.functional as F
import torchvision
import torchvision.transforms as transforms
from torch.utils.data import DataLoader


import time

DATA_ROOT="./data"

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

cnn=torch.load("./m.model")
cnn.cuda()
cnn.eval()

ac=0
n=len(loader)
start=time.clock()
for i,(x,y) in enumerate(loader):
    x=x.cuda()
    y=y.cuda()
    preds=cnn(x)
    pred=torch.max(preds,1)[1].cuda().data.squeeze()
    if torch.equal(pred,y):  # 判断预测结果是否正确
        ac+=1
end=time.clock()
print("ac:{} total:{} acc:{} time:{}".format(ac,n,float(ac)/float(n),end-start))