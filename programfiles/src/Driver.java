
public class Driver {
  public static void main(String[] args) {
    DataLoader loader = new DataLoader();
    DataWriter writer = new DataWriter();
    try {
      loader.openFile();
      String data = (String) loader.loadData();
      loader.closeFile();
      System.out.println(data);
      writer.openFile();
      writer.writeData("This is testing the data writer");
      writer.closeFile();
    } catch (Exception e) {
      System.out.println("File not found");
    }
  }
}
