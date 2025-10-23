public class BinarySearch {
    public static void main(String[] args) {
        int[] sortedArray = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int target = 12; // Change this to test other values

        BinarySearch bs = new BinarySearch();
        int resultIndex = bs.binarySearch(sortedArray, target);
        
        if (resultIndex != -1) {
            System.out.println("Target " + target + " found at index: " + resultIndex);
            double sqrt = Math.sqrt(target);
            System.out.println("Square root of " + target + " is: " + sqrt);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }
}