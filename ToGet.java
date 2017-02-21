import java.util.Scanner;
import java.util.List;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import com.google.gson.*;
//import com.google.gson.reflect.*;


public class ToGet {
  
  final private static String apiKey = "cceab0d33eb5783119b5aae9c9c814ef";
  
  public static void main(String[] args) {
     Scanner input = new Scanner(System.in);
    
     while (input.hasNextLine()) {
       String searchKeywords = input.nextLine();
       retrieveAlbums(searchKeywords);
     }
  }
  
  public static Album[] retrieveAlbums(String artist) {
    try {
      /*Call to the last.fm API requesting the most popular albums of the given artist.*/
      final String urlString = "http://ws.audioscrobbler.com/2.0/?method=artist." +
        "gettopalbums&artist=" + artist + "&api_key=" + apiKey + "&format=json";
        
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader in = new BufferedReader(new 
                                               InputStreamReader(connection.getInputStream()));
      
      final String json = in.readLine();
      JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
      jsonObject = jsonObject.get("topalbums").getAsJsonObject();
      final JsonArray jsonAlbums = jsonObject.get("album").getAsJsonArray();
      final Album[] albums = new Album[jsonAlbums.size()];
      
      for (int i = 0; i < albums.length; i++) {
        jsonObject = (JsonObject) jsonAlbums.get(i);
        
        /*Extract the name of each of the top albums.*/
        final String name = jsonObject.get("name").getAsString();
        
        /*Extract a Last.fm link for each of the top albums.*/ 
        final String albumUrl = jsonObject.get("url").getAsString();

        /*Extract the URL for the second largest image from each of the top albums.*/
        final JsonArray jsonImages = jsonObject.get("image").getAsJsonArray();
        jsonObject = (JsonObject) jsonImages.get(2);
        final String image = jsonObject.get("#text").getAsString();
        
        /*Add all of the extracted values to an album object, which is added to an array.*/
        Album album = new Album();
        album.setName(name);
        album.setUrl(albumUrl);
        album.setImage(image);
        album.setArtist(artist);
        albums[i] = (album);
      }
      
      in.close();
      return albums;
    } catch (Exception e) {
      e.printStackTrace(); 
    }
    return null;
  }
}