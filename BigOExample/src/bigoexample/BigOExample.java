/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigoexample;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Dan
 */
public class BigOExample {

    private static final int N = 100000;
    static Random r = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Integer[] arr = new Integer[N];
        initial(arr);
        Integer[] arr2 = arr.clone();

        //detect time duration
        long begin = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Duration of merge sort: " + (System.currentTimeMillis() - begin) + " millisecond");
        

        long begin2 = System.currentTimeMillis();
        Arrays.sort(arr2);
        System.out.println("Duration of Java Sort: " + (System.currentTimeMillis() - begin2) + " millisecond");
        

    }

    private static void initial(Integer[] a1) {
        for (int i = 0; i < a1.length; i++) {
            a1[i] = r.nextInt(N);
        }
    }


    public static void mergeSort(Integer[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    static void merge(Integer[] array, int left, int mid, int right) {
        // calculating lengths
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // creating temporary subarrays
        int leftArray[] = new int[lengthLeft];
        int rightArray[] = new int[lengthRight];

        // copying our sorted subarrays into temporaries
        for (int i = 0; i < lengthLeft; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < lengthRight; i++) {
            rightArray[i] = array[mid + i + 1];
        }

        // iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // copying from leftArray and rightArray back into array
        for (int i = left; i < right + 1; i++) {
            // if there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } // if all the elements have been copied from rightArray, copy the rest of leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } // if all the elements have been copied from leftArray, copy the rest of rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
