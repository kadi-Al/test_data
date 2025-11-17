public class StringScrambler {
    public static void main(String[] args) {
        String original = "Hello, World!";
        String scrambled = scrambleString(original);
        System.out.println("Original: " + original);
        System.out.println("Scrambled: " + scrambled);
    }

    public static String scrambleString(String str) {
        StringBuilder sb = new StringBuilder(str);
        int n = sb.length();
        
        for (int i = 0; i < n - 1; i++) {
            // Generate a predictable rotation index
            int shift = (i * 37) % n; // Using a simple formula to generate the shift value
            
            // Swap characters at positions i and shift
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(shift));
            sb.setCharAt(shift, temp);
        }
        
        return sb.toString();
    }
}