import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい。
 * AtomicReferenceと適切な累積関数を使用しなさい。
 */

public class LongestString {
	private static AtomicReference<Integer> largest = new AtomicReference<Integer>(0);

	public static void main(String[] args) throws InterruptedException, IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

    	int ntasks = 4;
		AtomicInteger next = new AtomicInteger(0);
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int t = 0; t < ntasks; t++) {
			pool.submit( () -> {
				final int task = next.incrementAndGet();
				for (int i = task*words.size()/ntasks; i < (task+1)*words.size()/ntasks; i++) {
					largest.accumulateAndGet(words.get(i).length(), Math::max);
					// これだと最大文字列長の確認プログラムだが、最大長の文字を全てリスト化する方が題意に沿う可能性もある。
				}
			} );
		}

		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(largest);
	}

}
