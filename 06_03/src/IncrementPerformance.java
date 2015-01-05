import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;


/**
 * 1,000個のスレッドを生成し、各スレッドは、ある１つのカウンターを100,000回だけ
 * 1ずつ増加させます。AtomicLongとLongAdderを使用した場合の性能を比較しなさい。
 */
public class IncrementPerformance {

	   public static void main(String[] args) throws InterruptedException {
		  int ntasks = 1000;
		  int iterations = 100000;


	      ExecutorService pool = Executors.newFixedThreadPool(ntasks);
		  AtomicLong atmicLong = new AtomicLong();
	      Instant start = Instant.now();
	      for (int t = 0; t < ntasks; t++)
	         pool.submit(() -> {
	               for (int i = 0;  i < iterations; i++) {
	                  atmicLong.incrementAndGet();
	               }
	            });
	      pool.shutdown();
	      pool.awaitTermination(10, TimeUnit.SECONDS);
	      Instant end = Instant.now();
	      System.out.println("AtmicLong:: " + Duration.between(start, end).toMillis() + " msec");


	      pool = Executors.newFixedThreadPool(ntasks);
		  LongAdder longAdder = new LongAdder();
	      start = Instant.now();
	      for (int t = 0; t < ntasks; t++)
	         pool.submit(() -> {
	               for (int i = 0;  i < iterations; i++) {
	            	   longAdder.increment();
	               }
	            });
	      pool.shutdown();
	      pool.awaitTermination(10, TimeUnit.SECONDS);
	      end = Instant.now();
	      System.out.println("LongAdder:: " + Duration.between(start, end).toMillis() + " msec");

	   }

}
