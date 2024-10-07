import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {
    private String filePath;
    private String fileFormat;
    private BufferedReader reader;

    public Object loadData() throws IOException {
        if (reader == null) {
            throw new IOException("File is not open.");
        }
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        return parseData(content.toString());
    }

    public void openFile(String filePath) throws IOException {
        this.filePath = filePath;
        reader = new BufferedReader(new FileReader(filePath));
    }

    public void closeFile() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }

    public Object parseData(String content) {
        // Implement your data parsing logic here
        return content;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
