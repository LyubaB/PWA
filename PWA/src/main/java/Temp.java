import java.util.Arrays;

public class Temp {
    public static void main(String[] args) {
        int[] arr = {1,3,5,9,13,22,27,35,46,51,55,83,87,23};
        insertionSort1(14, arr);


    }

    static void insertionSort1(int n, int[] arr) {
        int c = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (c < arr[i]) {
                arr[i + 1] = arr[i];
                for (int v : arr) {
                    System.out.print(v + " ");
                }
                System.out.println(" ");
            }
            if (c >= arr[i]) {
                arr[i + 1] = c;
                for (int v : arr) {
                    System.out.print(v + " ");
                }break;
                }
//            }if (Arrays.asList(arr).contains(c)==false){
//            arr[0] = c;
//                for (int v : arr) {
//                    System.out.print(v + " ");
        }for (int v:arr){
            if(v==c){
                arr[0] = c;}
                for (int y : arr) {
                    System.out.print(y + " ");
            }
    }
}}

