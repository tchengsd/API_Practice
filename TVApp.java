import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;

public class TVApp {
	public static void main(String[] args) throws IOException {
		String show = JOptionPane.showInputDialog("Enter a show.");
		int id = getShowID(show);
		URL site = new URL("https://api.tvmaze.com/shows/"+id+"/seasons");
		HttpURLConnection connection = (HttpURLConnection) site.openConnection();
		InputStream input = connection.getInputStream();
		JsonReader reader = Json.createReader(input);
		JsonArray arr = reader.readArray();
		int totalSeasons = arr.size();
		for(int i = 0; i < totalSeasons; i++) {
			JsonObject obj = arr.getJsonObject(i);
			System.out.println(obj.getInt("episodeOrder"));
		}
	}
	static int getShowID(String show) throws IOException {
		URL site = new URL("https://api.tvmaze.com/singlesearch/shows?q="+show);
		HttpURLConnection connection = (HttpURLConnection) site.openConnection();
		InputStream input = connection.getInputStream();
		JsonReader reader = Json.createReader(input);
		JsonObject object = reader.readObject();
		input.close();
		int id = object.getInt("id");
		return id;
	}
}
