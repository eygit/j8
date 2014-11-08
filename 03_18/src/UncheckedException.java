import java.util.function.Function;

interface Translatable<T, U>  {
	U translate(T t) throws Exception;
}

public class UncheckedException {

   public static <T,U> Function<T, U> unchecked(Translatable<T,U> trans) {
	      return (element) -> {
	         try {
	            return trans.translate(element);
	         }
	         catch (Exception e) {
	            throw new RuntimeException(e);
	         }
	         catch (Throwable t) {
	            throw t;
	         }
	      };
	   }

   
   public static void main(String[] args) {
	   // 例外を発生する型の変換
	   Function<String, CharSequence> f = unchecked((elm) -> elm.subSequence(-1,4));
   }
}

