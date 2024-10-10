import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class DataWriter {
    private String filePath;
    private BufferedWriter writer;
    private Gson gson;

    public DataWriter() {
        gson = new Gson();
    }

    public void writeData(User user) throws IOException {
        if (writer == null) {
            throw new IOException("File is not open.");
        }
        String jsonData = gson.toJson(user);
        writer.write(jsonData);
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        DataWriter dataWriter = new DataWriter();
        User user = new User("John Doe", 30, "john.doe@example.com");
        try {
            dataWriter.openFile(DataConstants.DEFAULT_FILE_PATH);
            dataWriter.writeData(user);
            dataWriter.closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
