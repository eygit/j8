import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class ParallelForLoop {

	public static void main(String[] args) throws IOException, InterruptedException {

        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = 0;
        long before = 0;
        before = System.nanoTime();
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println("alice.txt: normal:\t" + (System.nanoTime() - before) + "\tcount:" + count);
        before = System.nanoTime();
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println("alice.txt: parallel:\t" + (System.nanoTime() - before) + "\tcount:" + count);
        // 小さなドキュメントでは通常ストリームの方がパラレルストリームより速い。


        contents = new String(Files.readAllBytes(
                Paths.get("./war-and-peace.txt")), StandardCharsets.UTF_8);
        words = Arrays.asList(contents.split("[\\P{L}]+"));
        before = System.nanoTime();
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println("war-and-peace.txt: normal:\t" + (System.nanoTime() - before) + "\tcount:" + count);
        before = System.nanoTime();
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println("war-and-peace.txt: parallel:\t" + (System.nanoTime() - before) + "\tcount:" + count);
        // 大きなドキュメントではパラレルストリームの方が通常ストリームより若干速い。

	}
}
