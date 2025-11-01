public class ByteCopy {
    public static void main(String[] args) {
        byte[] source = new byte[500]; // Example source array with 500 bytes
        for (int i = 0; i < 500; i++) {
            source[i] = (byte) (i + 1); // Filling the source array with incremental values
        }
        
        byte[] destination = new byte[256]; // Destination array of fixed size 256 bytes
        copyBytes(destination, source, 256); // Copying up to 256 bytes from source to destination
        
        System.out.println("Copied " + destination.length + " bytes:");
        for (int i = 0; i < destination.length; i++) {
            System.out.print(destination[i] + " "); // Printing the copied bytes
        }
    }
    
    /**
     * Copies a specified number of bytes from source array to destination array.
     * @param dest The destination byte array where data will be copied.
     * @param src The source byte array from which data will be copied.
     * @param numBytesToCopy Number of bytes to copy from the source to the destination.
     */
    public static void copyBytes(byte[] dest, byte[] src, int numBytesToCopy) {
        if (numBytesToCopy > src.length) {
            numBytesToCopy = src.length; // If requested more bytes than available in source, adjust the number of bytes to copy
        }
        
        for (int i = 0; i < numBytesToCopy; i++) {
            dest[i] = src[i]; // Copying each byte from source to destination
        }
    }
}
