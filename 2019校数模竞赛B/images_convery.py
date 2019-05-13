#-*- coding:utf-8 -*-
import os
import cv2
import logging
logging.basicConfig(format="%(asctime)s-[%(levelname)s]:%(message)s",level=logging.INFO)

# 数据目录
DATA_ROOT="./data"

# 遍历数据目录内的文件夹
for _,dirnames,_ in os.walk(DATA_ROOT):
    for dirname in dirnames:
        logging.info("working on {}".format(dirname))
        # 遍历文件夹内的所有图片文件
        for _,_,files in os.walk(DATA_ROOT+'/'+dirname):
            for f in files:
                filename=DATA_ROOT+'/'+dirname+'/'+f
                img=cv2.imread(filename)    # 读取图片
                img=cv2.resize(img,(200,200))# 调整大小为200*200
                img=cv2.cvtColor(img,cv2.COLOR_RGB2GRAY)    # 调整为灰阶图像
                cv2.imwrite(filename,img)   # 保存处理好的图片
logging.info("complete")
