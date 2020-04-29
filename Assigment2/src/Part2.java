import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Part2 {
    public static Scanner scanner=new Scanner(System.in);
    public static Random random=new Random();

    public static int[] create_array(int size){
        int[] ret=new int[size];
        for (int i=0;i<size;i++) ret[i]= random.nextInt();
        return ret;
    }

    public static void out(int[] a){
        for (int i=0;i<a.length;i++) {
            if (i%5==0) System.out.println();
            System.out.print(a[i]+"\t");
        }
        System.out.println();
    }

    public static void QuickSort(int[] a,int left,int right){
        if (left>=right) return;

        int key=a[left];
        int i=left;
        int j=right;
        while (i<j){
            while (a[j]>=key && i<j) j--;
            while (a[i]<=key && i<j) i++;

            if (i<j){
                int temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        }
        a[left]=a[i];
        a[i]=key;
        QuickSort(a,left,i-1);
        QuickSort(a,i+1,right);
    }

    public static void merge(int[] a,int left,int mid,int right){
        int[] temp=new int[a.length];
        int p1=left,p2=mid+1,k=left;

        while (p1<=mid && p2<=right){
            if (a[p1]<=a[p2]) temp[k++]=a[p1++];
            else temp[k++]=a[p2++];
        }

        while (p1<=mid) temp[k++]=a[p1++];
        while (p2<=right) temp[k++]=a[p2++];

        for (int i=left;i<=right;i++)
            a[i]=temp[i];
    }

    public static void MergeSort(int[] a,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            MergeSort(a,start,mid);
            MergeSort(a,mid+1,end);
            merge(a,start,mid,end);
        }
    }

    public static void main(String[] args) {
        System.out.print("Please input the length of random array:");
        int n=scanner.nextInt();
        System.out.println("\n\nCreate array:");
        int[] a=create_array(n);
        int[] b=new int[a.length];
        for (int i=0;i<a.length;i++) b[i]=a[i];
        out(a);


        long time0=System.nanoTime();
        QuickSort(a,0,a.length-1);
        long time1=System.nanoTime();
        System.out.println("\n\nQuick Sort result:");
        out(a);
        System.out.println("time:"+(time1-time0)+"ns");

        time0=System.nanoTime();
        MergeSort(b,0,b.length-1);
        time1=System.nanoTime();
        System.out.println("\n\nMerge Sort result:");
        out(b);
        System.out.println("time:"+(time1-time0)+"ns");

    }
}
