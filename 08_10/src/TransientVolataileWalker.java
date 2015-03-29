import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class TransientVolataileWalker {

	public static void main(String[] args) throws IOException {
		String jdkpath = "C:\\Program Files\\Java\\jdk1.8.0_11\\src";
		try(Stream<Path> stream = Files.walk(Paths.get(jdkpath), Integer.MAX_VALUE)){
			  stream.forEach(path -> {
			    String contents = null;
				try {
					contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
				} catch (Exception e) {
					// AccessDeniedExceptionが出るが、、、握りつぶす
				}
				if (contents != null) {
					int transientIndex = contents.indexOf("transient");
					if (transientIndex != -1) {
						System.out.println(path);
					} else {
						int volatileIndex = contents.indexOf("volatile");
						if (volatileIndex != -1) {
							System.out.println(path);
						}
					}
				}
				
			  });
			}

	}

}
