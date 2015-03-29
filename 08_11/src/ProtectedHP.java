import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class ProtectedHP {

	public static void main(String[] args) throws IOException {
		URL url = new URL("hoge.html");
		URLConnection connection = url.openConnection();
		String idpass = "username:password";
		String b64idpass = Base64.getEncoder().encodeToString(idpass.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic" + b64idpass);
		connection.connect();
		InputStream ins = connection.getInputStream();
		// InputStreamの処理が続く。
	}

}
