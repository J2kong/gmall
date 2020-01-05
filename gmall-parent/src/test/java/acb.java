/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/16 14:32
 **/
public class acb {

    public static  void  quickSort(int[] index,int low,int high){
        int i,j,temp,in;
        i=low;
        j=high;
        in = index[i];

        while(i<j){
            while (i<j&&index[j]>=in)
                j--;
            while (i<j&&index[i]<in)
                i++;
            if (i<j){
                temp = index[i];
                index[i]=index[j];
                index[j]=index[i];
            }
            index[low]=index[i];
            index[i]=in;
            quickSort(index,low,j-1);
            quickSort(index,i+1,high);

        }

    }
}
