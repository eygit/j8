import java.util.stream.IntStream;
import java.util.stream.Stream;


public class IsFinite {

	/**
	 * 無限の要素数を持たなければfalseを返し、そうでなければ停止しない良くないメソッド。
	 * @param stream 無限か判定したい対象ストリーム
	 * @return 無限の要素数を持たなければfalse、そうでなければこの関数は永遠に戻らない。
	 */
	public static <T> boolean isFinit (Stream<T> stream) {
		stream.count(); // 無限ストリームの場合、永久にカウントし続けて答えが得られない。
		return false;
	}

	public static void main(String[] args) {
		// 有限ストリームの場合
		String s ="abcdefghijklmnopqrstuvwxyz";
		Stream<Integer> is = IntStream.range(0, s.length()).boxed();
		System.out.println(isFinit(is));

		// 無限ストリームの場合
		Stream<Double> randoms = Stream.generate(Math::random);
		System.out.println(isFinit(randoms));
	}

}
