import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class app {
	static JFrame frame;
	public static void main(String[] args) throws IOException {
		JFrame bFrame = new JFrame();
		JButton button = new JButton("Click Here!");
		bFrame.add(button);
		button.addActionListener((e)->{
			try {
				getPic();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bFrame.pack();
		bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bFrame.setVisible(true);
	}
	
	static void getPic() throws IOException {
		if(frame != null) {
			frame.dispose();
		}
		URL site = new URL("https://dog.ceo/api/breeds/image/random");
		HttpURLConnection connection = (HttpURLConnection) site.openConnection();
		InputStream input = connection.getInputStream();
		JsonReader reader = Json.createReader(input);
		JsonObject object = reader.readObject();
		input.close();
		String dogPic = object.getString("message");
		
		frame = new JFrame();
		JLabel pic = new JLabel();
		pic.setIcon(new ImageIcon(new URL(dogPic)));
		frame.add(pic);
		frame.pack();
		frame.setVisible(true);
	}
}
