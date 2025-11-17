public class TempFilenameGenerator {
    public static String generateTempFilename() {
        long currentTimeMillis = System.currentTimeMillis();
        return "temp_" + currentTimeMillis;
    }

    public static void main(String[] args) {
        String tempFilename = generateTempFilename();
        System.out.println("Generated temporary filename: " + tempFilename);
    }
}
Generated temporary filename: temp_1672502400000