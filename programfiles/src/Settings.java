/**
 * The Settings class defines properties for notifications, 
 * light mode, text-to-speech, and font size for a user.
 * @author Tony Lin
 */
public class Settings {
  private boolean notifications;
  private boolean lightMode;
  private boolean textToSpeech;
  private int fontSize;

  /**
   * Default constructor where a users default settings are
   * set to no notifications, light mode on, no text to speech,
   * and font size of 10.
   */
  public Settings() {
    this.notifications = false;
    this.lightMode = true;
    this.textToSpeech = false;
    this.fontSize = 10;
  }

  
  /** Changes the notification setting to the boolean argument passed
   * in
   * @param on the new setting for notifications
   */
  public void setNotifications(boolean on) {
    this.notifications = on;
  }

  
  /** Changes the light mode setting to the boolean argument passed
   * in
   * @param on the new setting for light mode
   */
  public void setLightMode(boolean on) {
    this.lightMode = on;
  }

  
  /** Changes the text-to-speech setting to the boolean argument 
   * passed in
   * @param on the new setting for text-to-speech
   */
  public void setTextToSpeech(boolean on) {
    this.textToSpeech = on;
  }

  
  /** Changes the font size setting to the boolean argument passed in
   * @param size the new setting for font size
   */
  public void setFontSize(int size) {
    if(size > 0)
      this.fontSize = size;
  }

  
  /** Returns a string of all of a user's current settings
   * in formatted manner
   * @return String of all datamembers of settings class
   */
  public String toString() {
    return "Settings:\nNotifications: " + String.valueOf(this.notifications)
            + "\nLight Mode: " + String.valueOf(this.lightMode) + 
            "\nText-To-Speech: " + String.valueOf(this.textToSpeech) +
            "\nFont Size: " + String.valueOf(this.fontSize);
  }
}
