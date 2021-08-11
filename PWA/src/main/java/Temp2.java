import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Temp2 {
    public static void main(String[] args) {
        int[] array = {5, 7, 4, 3, 8};
        int[] array1 = {2, 10, 3, 7, 9, 4, 6, 12, 8, 5, 1};
        Integer[] array2 = {5, 7, 4, 3, 8};
        for (int v : quickSort(array1)) {
            System.out.print(v + " ");
            }
//        System.out.print("\n");
//        sort(array);
//        System.out.print("\n");
//        bubbleSort(array1);
//        System.out.print("\n");
//        sortReverse(array2);
    }
//    doesn't work
    public static int[] quickSort(int[] arr) {
        int n = arr.length;
        int p = arr[0];
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (p > arr[i]) {
                arr1.add(arr[i]);
            } else {
                arr2.add(arr[i]);

            }
        }
        int[] newArr = new int[n];
        int i = 0;
        for (int v : arr1) {
            newArr[i] = v;
            i++;
        }
        newArr[i] = p;
        i++;
        for (int v : arr2) {
            newArr[i] = v;
            i++;
        }
        return newArr;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        for (int v : arr) {
            System.out.print(v + " ");
        }
    }


    public static void sort(int[] arr) {
        Arrays.sort(arr);
        for (int v : arr) {
            System.out.print(v + " ");
        }
    }

//    public static void sortReverse(Integer[] arr) {
//        Arrays.sort(arr, Collections.reverseOrder());
//        for (int v : arr) {
//            System.out.print(v + " ");
//        }
//    }

}

