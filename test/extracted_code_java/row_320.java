public class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        
        try {
            int value = getValueFromArray(numbers, 2);
            System.out.println("Value at index 2: " + value); // Should print 30
            
            int outOfBoundsValue = getValueFromArray(numbers, 10); // This will throw an exception
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught IndexOutOfBoundsException: " + e.getMessage());
        }
    }

    public static int getValueFromArray(int[] array, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
        return array[index];
    }
}