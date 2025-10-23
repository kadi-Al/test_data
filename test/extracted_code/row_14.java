public class ArrayScreener {
    public static void main(String[] args) {
        int[] arr = {4, 2, 10, 15, 8, 7};
        int K = 9;
        screenArray(arr, K);
    }

    public static void screenArray(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            System.out.println("The array is empty.");
            return;
        }
        
        if (arr.length == 1 && arr[0] % K != 0) {
            System.out.println("The array has only one element and it does not satisfy the condition.");
            return;
        }
        
        boolean found = false;
        for (int num : arr) {
            if (num % K == 0) {
                System.out.println(num + " satisfies the condition.");
                found = true;
            } else {
                System.out.println(num + " does not satisfy the condition.");
            }
        }
        
        if (!found && arr.length > 1) {
            System.out.println("No element in the array satisfies the condition.");
        }
    }
}
4 does not satisfy the condition.
2 does not satisfy the condition.
10 satisfies the condition.
15 does not satisfy the condition.
8 does not satisfy the condition.
7 does not satisfy the condition.
No element in the array satisfies the condition.