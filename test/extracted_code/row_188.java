import java.util.Scanner;

public class CsvBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for inputs
        System.out.println("Enter the number of fields you want to add:");
        int numFields = Integer.parseInt(scanner.nextLine());
        
        StringBuilder csvLineBuilder = new StringBuilder();
        
        for (int i = 0; i < numFields; i++) {
            System.out.println("Enter field " + (i+1) + ":");
            String field = scanner.nextLine();
            
            // Append the field to the CSV line, ensuring each entry is properly quoted if needed
            if (field.contains(",") || field.contains("\"")) {
                // Escape double quotes and commas in the field
                field = "\"" + field.replace("\"", "\"\"") + "\"";
            }
            
            if (csvLineBuilder.length() > 0) {
                csvLineBuilder.append(",");
            }
            csvLineBuilder.append(field);
        }
        
        String csvLine = csvLineBuilder.toString();
        System.out.println("Your CSV line is:");
        System.out.println(csvLine);
        
        scanner.close();
    }
}