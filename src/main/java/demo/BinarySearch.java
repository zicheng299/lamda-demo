package demo;

import java.util.Arrays;

public class BinarySearch {


    public static void main(String[] args) {
        System.out.println(5 >>> 1);

        int[] nums = {1, 2, 5, 6, 78, 99};
//        int target = binarySearch(nums, 99);
        int target = Arrays.binarySearch(nums, 78);

        System.out.println(target);

    }


    private static int binarySearch(int[] numbers, int num2Find) {
        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int middlePosition = (low + high) / 2;
            if (num2Find == numbers[middlePosition]) {
                return middlePosition;
            }
            if (num2Find > middlePosition) {
                low = middlePosition + 1;
            } else {
                high = middlePosition - 1;
            }
        }
        return -1;
    }
}
