import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ユーザーにURLを問い合わせて、そのURLのウェブページを読み込み、すべてのリンクを表示するプログラムを作成しなさい。
 * 各ステップに対して、CompletableFutureを使用しなさい。getを呼び出さないこと。
 * プログラムの処理が終わる前に終了しないようにするために、次の呼び出しを行いなさい。
 * ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
 */

class Util {
	public static String getPage(String urlString) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new URL(urlString).openStream());
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine());
				builder.append("\n");
			}
			return builder.toString();
		} catch (IOException ex) {
			RuntimeException rex = new RuntimeException();
			rex.initCause(ex);
			throw rex;
		} finally {
			scanner.close();
		}
	}

	public static List<String> matches(String input, String patternString) {
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		List<String> result = new ArrayList<>();

		while (matcher.find())
			result.add(matcher.group(1));
		return result;
	}

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
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		String hrefPattern = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
		CompletableFuture<String> f = Util.repeat( () -> Util.getInput("URL"),
				s -> s.startsWith("http://")).thenApplyAsync((String url) -> Util.getPage(url));
		CompletableFuture<List<String>> links = f.thenApply(c -> Util.matches(c, hrefPattern));
		links.thenAccept(System.out::println);
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
		System.out.println("exiting");
	}
}
