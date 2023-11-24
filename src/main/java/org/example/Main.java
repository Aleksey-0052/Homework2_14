package org.example;

import org.example.service.IntegerList;
import org.example.service.IntegerListImpl;

import java.util.Arrays;

public class Main {

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }
    public static void main(String[] args) {

//        Integer[] arr = generateRandomArray();
//
//        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
//
//        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
//
//        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
//
//        long start1 = System.currentTimeMillis();
//        sortBubble(arr1);
//        System.out.println(System.currentTimeMillis() - start1);
//
//        long start2 = System.currentTimeMillis();
//        sortSelection(arr2);
//        System.out.println(System.currentTimeMillis() - start2);
//
//        long start3 = System.currentTimeMillis();
//        sortInsertion(arr3);
//        System.out.println(System.currentTimeMillis() - start3);

        IntegerList integerList = new IntegerListImpl();

        integerList.add(67);
        integerList.add(89);
        integerList.add(54);
        integerList.add(23);
        integerList.add(88);
        integerList.add(5);
        integerList.add(7);
        integerList.add(99);
        integerList.add(4);
        integerList.add(46);
        System.out.println(Arrays.toString(integerList.getStorage()));
        System.out.println("_______________________________________");

        System.out.println(integerList.contains(3));
        System.out.println(integerList.contains(99));


    }
    
    // Пузырьковая сортировка

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    // Сортировка выбором

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    // Сортировка вставкой

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

}