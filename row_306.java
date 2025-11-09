import java.nio.ByteBuffer;
import java.util.Scanner;

public class MemoryAllocation {
    public static void main(String[] args) {
        byte[] buf = allocateAndReadBuffer();
        byte[] buf1 = allocateAndReadBuffer();

        // Print the contents of the buffers for verification
        System.out.println("Contents of buf: " + new String(buf));
        System.out.println("Contents of buf1: " + new String(buf1));
    }

    public static byte[] allocateAndReadBuffer() {
        // Allocate a large buffer (up to 1024 bytes)
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // Use fgets-like functionality to read up to 100 characters from stdin
        Scanner scanner = new Scanner(System.in);
        byte[] inputBytes = new byte[100];
        int bytesRead = 0;
        while (bytesRead < 100 && scanner.hasNext()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length() && bytesRead < 100; i++) {
                inputBytes[bytesRead++] = (byte) line.charAt(i);
            }
        }

        // Copy the read bytes into the buffer
        System.arraycopy(inputBytes, 0, buffer.array(), 0, Math.min(100, buffer.capacity()));

        return buffer.array();
    }
}