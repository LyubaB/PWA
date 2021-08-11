import java.util.Arrays;

public class Temp {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 9, 13, 22, 27, 35, 46, 51, 55, 83, 87, 1};
        insertionSort1(14, arr);
//        System.out.println(reverseString("Hello!"));
//        printPrimeNumbers(100);
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
                }
                break;
            }
        }
            if (Arrays.asList(arr).contains(c)==false) {
                arr[0] = c;
                for (int v : arr) {
                    System.out.print(v + " ");
                }

//        for (int v : arr) {
//            if (v == c) {
//                arr[0] = c;
//            }
//            for (int y : arr) {
//                System.out.print(y + " ");
//            }
        }
    }

    public static String reverse(String str) {
        char[] arr = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            result.append(arr[i]);
        }
        return result.toString();
    }

    public static String reverseString(String str) {
        char[] arr = str.toCharArray();
        String result = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            result += arr[i];
        }
        return result;
    }

    public static String reverseSentance(String s) {
        String[] words = s.split(" ");
        String result = "";
        for (String v : words) {
            result += reverseString(v);
        }
        return result;
    }

    public static void printPrimeNumbers(int limit) {
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

