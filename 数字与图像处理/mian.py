import pywt
import cv2
import numpy as np
import base64


origin_img=cv2.imread("./origin.jpg")
origin_img=cv2.cvtColor(origin_img,cv2.COLOR_BGR2GRAY)
watermark_img=cv2.imread("./watermark.jpg")
watermark_img=cv2.cvtColor(watermark_img,cv2.COLOR_BGR2GRAY)
water_origin_size=watermark_img.shape

ca,(ch,cv,cd)=pywt.dwt2(origin_img,"haar")
watermark_img=cv2.resize(watermark_img,(ca.shape[1],ca.shape[0]))
for i in range(ca.shape[0]):
    for j in range(ca.shape[1]):
        if watermark_img[i][j]==0:
            a=-1
        else:
            a=1
        ca[i][j]=ca[i][j]*(1+a*0.03)
im=pywt.idwt2((ca,(ch,cv,cd)),"haar")
cv2.imwrite("result.jpg",im)
cv2.imshow("test",im)
cv2.waitKey(0)

marked_img=cv2.imread("./result.jpg")
marked_img=cv2.cvtColor(marked_img,cv2.COLOR_BGR2GRAY)
ca,(ch,cv,cd)=pywt.dwt2(marked_img,"haar")
caa,(chh,cvv,cdd)=pywt.dwt2(ca,"haar")
caa=cv2.resize(caa,(ca.shape[1],ca.shape[0]))
res=np.zeros((ca.shape[0],ca.shape[1]))
for i in range(ca.shape[0]):
    for j in range(ca.shape[1]):
        a=caa[i][j]/(ca[i][j])-1
        a=watermark_img[i][j]
        if a==0:
            res[i][j]=0
        else:
            res[i][j]=255

# res=watermark_img
cv2.imshow("test",res)
cv2.waitKey(0)
res=cv2.resize(res,water_origin_size)
cv2.imwrite("reswater.jpg",res)

def arnold(img):
    n=10
    a=3
    b=5
    width=img.shape[0]
    height=img.shape[1]
    ret=np.zeros((width,height))
    for i in range(n):
        for x in range(width):
            for y in range(height):
                xx=(x+b*y)%width
                yy=((a*x)+(a*b+1)*y)%height
                ret[xx][yy]=img[x][y]
    return ret

cv2.imwrite("encoded_origin.jpg",arnold(origin_img))
cv2.imwrite("encoded_marked.jpg",arnold(im))
    