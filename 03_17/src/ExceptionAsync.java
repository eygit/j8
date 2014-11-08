import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ExceptionAsync {
   public static void doInParallelOrder(Runnable first, Runnable second, Consumer<Throwable> handler) {
	   try {
		   ExecutorService pool = Executors.newCachedThreadPool();
		   Future<?> ff = pool.submit(() -> first.run());
		   Future<?> fs = pool.submit(() -> second.run());
	       pool.shutdown();
	       pool.awaitTermination(1, TimeUnit.HOURS);
	       ff.get();
	       fs.get();
	   } catch (Throwable ex) {
		   handler.accept(ex);
	   }
   }

   public static void main(String[] args) {
	   //例外なし
      doInParallelOrder(
         () -> System.out.println("first: " + Thread.currentThread()),
         () -> System.out.println("second: " + Thread.currentThread()),
         (e) -> System.out.println("hndle exception: "  + Thread.currentThread() + ":: " + e));
      
      System.out.println("=============");
      // firstが例外スロー
      doInParallelOrder(
			() -> {throw new RuntimeException("fist exception");},
			() -> System.out.println("second: " + Thread.currentThread()),
			(e) -> System.out.println("hndle exception: "  + Thread.currentThread() + ":: " + e));      
      System.out.println("=============");
      
      // secondが例外スロー
      doInParallelOrder(
			() -> System.out.println("fist: " + Thread.currentThread()),
			() -> {throw new RuntimeException("second exception");},
			(e) -> System.out.println("hndle exception: "  + Thread.currentThread() + ":: " + e));  

      // fist, second両方が例外のときは、今回の実装では、fistのみ出力となる
      
 
   }
}
