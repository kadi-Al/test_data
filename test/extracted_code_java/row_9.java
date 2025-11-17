\nimport java.text.ParseException;\nimport java.text.SimpleDateFormat;\nimport java.util.Date;\n\npublic class Main{\n    public static void main(String[] args){\n        try{\n            String oldDateString = \"5 January, 2020\";\n            SimpleDateFormat oldDateFormat = new SimpleDateFormat(\"dd MM yyyy\");\n            Date oldDate = oldDateFormat.parse(oldDateString);\n            \n            SimpleDateFormat newDateFormat = new SimpleDateFormat(\"MM/dd/yyyy\");\n            String newDateString = newDateFormat.format(oldDate);\n            \n            System.out.println(newDateString);\n        }catch(ParseException e){\n            e.printStackTrace();\n        }\n   }\n}\n
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        try {
            String oldDateString = "5 January 2020";
            SimpleDateFormat oldDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH); // Corrected format
            Date oldDate = oldDateFormat.parse(oldDateString);
            
            SimpleDateFormat newDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String newDateString = newDateFormat.format(oldDate);
            
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
01/05/2020