public class Album {
  private String name;
  private String url;
  private String image;
  private String artist;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  public void setArtist(String artist) {
    this.artist = artist;
  }
  
  public String getName() {
    return name;
  }
  
  public String getUrl() {
    return url;
  }
  
  public String getImage() {
    return image;
  }
  
  public String getArtist() {
    return artist;
  }
  
  public String toString() {
    return name + " " + artist + "\n";
  }
}