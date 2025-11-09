xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-compress</artifactId>
    <version>1.21</version>
</dependency>
import java.io.File;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchiveExtractor {
    public static void main(String[] args) {
        // Define the path to the ZIP archive and the extraction directory
        String zipFilePath = "/path/to/archive.zip";
        String extractDirPath = "/tmp/unpack";

        try (ZipArchiveInputStream in = new ZipArchiveInputStream(new File(zipFilePath))) {
            ArchiveEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                // Get the full path for the file to be extracted
                String filePath = extractDirPath + File.separator + entry.getName();
                
                if (!entry.isDirectory()) {
                    // Ensure the directory structure is created before writing the file
                    new File(filePath).getParentFile().mkdirs();
                    
                    // Write the content of the ZIP entry to a file
                    try (FileOutputStream out = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                } else {
                    // Create the directory if it doesn't exist
                    new File(filePath).mkdirs();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}