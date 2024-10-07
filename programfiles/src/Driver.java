
public class Driver {
  public static void main(String[] args) {
    DataLoader loader = new DataLoader();
    try {
      loader.openFile();
      String data = (String) loader.loadData();
      loader.closeFile();
      System.out.println(data);
    } catch (Exception e) {
      System.out.println("File not found");
    }
  }
}
