import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BiConsumerException {
	// 3つ目のパラメータは利用側スレッドに例外結果を渡すために必要。
	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> handler) {
      Thread t = new Thread() {
            public void run() {
            	T result = null;
            	Throwable throwable = null;
               try {
                  result = first.get();
               } catch (Throwable t) {
            	   throwable = t;
               }
               try {
            	   second.accept(result, throwable);
               } catch (Throwable t) {
            	   handler.accept(t);
               }
            }
         };
      t.start();
   }

   public static void main(String[] args) {
	  Supplier<String> supplier = () -> {
		  return "Hi";
	  };
	  Supplier<String> supplierException = () -> {
		  throw new RuntimeException("fist exception");
	  };

	  BiConsumer<String, Throwable> biConsumer = (prev, e) -> {
		  if (e != null) {
			  throw new RuntimeException(e);
		  } else {
			  System.out.println(prev);
		  }
	  };
	  BiConsumer<String, Throwable> biConsumerException = (prev, e) -> {
		  if (e != null) {
			  throw new RuntimeException(e);
		  } else {
			  throw new RuntimeException("second exception");
		  }
	  };

	  Consumer<Throwable> handler = e -> System.out.println(e);

	  // うまい？ユースケース。
      doInOrderAsync(supplier, biConsumer, handler);
      doInOrderAsync(supplierException, biConsumer, handler);
      doInOrderAsync(supplier, biConsumerException, handler);
      doInOrderAsync(supplierException, biConsumerException, handler);
   }
}
