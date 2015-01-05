import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConcurrentWordCollector2 {

	public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        int ntasks = 2;
        List<String> filenames = new ArrayList<>(ntasks);
        filenames.add("./alice.txt");
        filenames.add("./war-and-peace.txt");

        ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<String, Set<File>>();

        for (int t = 0; t < ntasks; t++) {
        	final int fixedt = t;
        	pool.submit(() -> {
                String contents = null;
				try {
					contents = new String(Files.readAllBytes(
					        Paths.get(filenames.get(fixedt))), StandardCharsets.UTF_8);
				} catch (Exception e) {
					e.printStackTrace();
				}
                List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
                Set<File> fileSet = new HashSet<>();
                fileSet.add(new File(filenames.get(fixedt))); // 問題指定のクラスに合わせるためFileオブジェクトにしているが冗長。
                for (String word : words) {
                	// computeIfAbsentが良い点は、ほとんどの利用ケースでvalueの計算回数が最小限になるところ。
                	// (使いにくい点は、既存のvalueとの畳み込んで新valueを作れないこと(computeIfPresentが必要になってしまうと思われる)）。
                	map.computeIfAbsent(word, s -> fileSet);
                	map.computeIfPresent(word, (key, existingSet) -> {
                		Set<File> newSet = new HashSet<>(existingSet); // Set要素の組み合わせが有限なので、newの回数を更に減らすこともできる。
                		newSet.addAll(fileSet);
                		return newSet;
                	});
                }
		    });
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println(map.entrySet());
	}

}
