import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 次のメソッドを作成しなさい。
 * public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until)
 * このメソッドは、until関数が受け入れる値を生成するまで、actionを非同期に繰り返します。
 * until関数も非同期に実行されるべきです。
 * コンソールからjava.net.PasswordAuthenticationを読み込む関数、および、１秒間スリープすることで
 * 正当性検査をシミュレートし、パスワードが"secret"であるかを検査する関数を用いてテストしなさい。
 * ヒント：再帰を使用します。
 */

class Util {
	private static Scanner in = new Scanner(System.in);

	public static String getInput(String prompt) {
		System.out.print(prompt + ": ");
		return in.nextLine();
	}

	public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync( (T t) -> {
					return until.test(t) ? CompletableFuture.completedFuture(t) : repeat(action, until);
				});
	}
}

public class UrlLink {
	private static boolean login(PasswordAuthentication input) {
		//正当性検証のシミュレート
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.equals(input.getPassword(), "secret".toCharArray());
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<PasswordAuthentication> cfpa = Util.repeat(
				() -> Util.getInput("ID"), s -> s.length() > 1
			).thenApplyAsync( (idsring) -> {
					CompletableFuture<String> pass = Util.repeat(
						() -> Util.getInput("PASS"), s -> s.length() > 1
					);
					char[] passchar = null;
					try {
						passchar = pass.get().toCharArray();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return new PasswordAuthentication(idsring, passchar);
				});
		boolean success = login(cfpa.get());
		System.out.println("login "+  (success ? "ok" : "ng"));

		System.out.println("exiting");
	}
}
