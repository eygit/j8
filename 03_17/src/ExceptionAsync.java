import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ExceptionAsync {
   public static void doInParallelOrder(Runnable first, Runnable second, Consumer<Throwable> handler) {
	   try {
		   ExecutorService pool = Executors.newCachedThreadPool();
		   pool.submit(() -> first.run());
		   pool.submit(() -> second.run());
	       pool.shutdown();
	       pool.awaitTermination(1, TimeUnit.HOURS);
	   } catch (Throwable ex) {
		   handler.accept(ex);
	   }
   }

   public static void main(String[] args) {
	   //例外なし
//      doInParallelOrder(
//         () -> System.out.println("first: " + Thread.currentThread()),
//         () -> System.out.println("second: " + Thread.currentThread()),
//         (e) -> System.out.println("exception: "  + Thread.currentThread() + ":: " + e));
      
      // firstが例外スロー キャッチできない・・・。要件等
      doInParallelOrder(
    	         () -> {throw new RuntimeException("fist exception");},
    	         () -> System.out.println("second: " + Thread.currentThread()),
    	         (e) -> System.out.println("exception: "  + Thread.currentThread() + ":: " + e));      

   }
}
