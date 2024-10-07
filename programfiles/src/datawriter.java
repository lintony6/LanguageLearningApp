import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
    private String filePath;
    private String fileFormat;
    private BufferedWriter writer;

    public void writeData(Object data) throws IOException {
        if (writer == null) {
            throw new IOException("File is not open.");
        }
        String formattedData = formatData(data);
        writer.write(formattedData);
        writer.newLine();
    }

    public void openFile(String filePath) throws IOException {
        this.filePath = filePath;
        writer = new BufferedWriter(new FileWriter(filePath));
    }

    public void closeFile() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public String formatData(Object data) {
        // Implement your data formatting logic here
        return data.toString();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
