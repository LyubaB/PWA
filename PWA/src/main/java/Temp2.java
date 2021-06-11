import java.util.ArrayList;
import java.util.List;

public class Temp2 {
        public static void main(String[] args) {
            int[] array= {5,7,4,3,8};
            int[] array1= {2,10,3,7,9,4,6,12,8};
            for (int v:quickSort(array1)){
                System.out.print(v+" ");

            }}
        static int[] quickSort(int[] arr) {
            int n=arr.length;
            int p=arr[0];
            List<Integer> arr1 = new ArrayList<>();
            List<Integer> arr2 = new ArrayList<>();
            for(int i=1; i<n; i++){
                if (p>arr[i]){
                    arr1.add(arr[i]);
                }
                else{
                    arr2.add(arr[i]);;
                }}
            int[] newArr = new int[n];
            int i=0;
            for (int v:arr1){
                newArr[i] =v;
                i++;}
            newArr[i]=p;
            i++;
            for (int v:arr2){
                newArr[i]=v;
                i++;
            }return newArr;

        }
//    static int[] quickSort(int[] arr) {
//    int n=arr.length;
//    int p=arr[0];
//    int m=n-1;
//     int[] arr1= new int [m/2];
//     int[] arr2=new int [m/2];
//        int j=0;
//        int y=0;
//        for(int i=1; i<n; i++){
//        if (p>arr[i]){
//            arr1[j]=arr[i];
//        j++;}
//        else{
//            arr2[y]=arr[i];
//            y++;
//        }}
//        int[] newArr = new int[n];
//        int i=0;
//        for (int v:arr1){
//            newArr[i] =v;
//        i++;}
//        newArr[i]=p;
//        i++;
//        for (int v:arr2){
//            newArr[i]=v;
//            i++;
//        }return newArr;
//
//    }

    }

