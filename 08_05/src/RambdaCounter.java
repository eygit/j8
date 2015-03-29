import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class RambdaCounter {

	public static void main(String[] args) throws IOException, InterruptedException {
        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        Iterator<String> ite = words.iterator();
        AtomicInteger c = new AtomicInteger();
        ite.forEachRemaining( (t) -> {
        		if(t.length() > 12) {
        			c.incrementAndGet();
        		}
        	});
        System.out.println("rambda counter :" + c);
       
        // 長いリストに対しては、線形探索しかできない上記ラムダの方が操作が遅い。
 	}
}
