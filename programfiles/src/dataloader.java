import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class DataLoader {
    private String filePath;
    private BufferedReader reader;
    private List<User> userList;

    public DataLoader() {
        userList = new ArrayList<>();
    }

    public List<User> loadData() throws IOException {
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

    public List<User> parseData(String content) {
        Gson gson = new Gson();
        try {
            User[] users = gson.fromJson(content, User[].class);
            for (User user : users) {
                userList.add(user);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<User> getUserList() {
        return userList;
    }

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        try {
            dataLoader.openFile(DataConstants.DEFAULT_FILE_PATH);
            List<User> users = dataLoader.loadData();
            dataLoader.closeFile();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
