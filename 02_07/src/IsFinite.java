import java.util.stream.Stream;


public class IsFinite {
	
	/**
	 * 無限の要素数を持たなければfalseを返し、そうでなければ停止しない良くないメソッド。
	 * @param stream 無限か判定したい対象ストリーム
	 * @return 無限の要素数を持たなければfalse、そうでなければこの関数は永遠に戻らない。
	 */
	public static <T> boolean isFinit (Stream<T> stream) {
		stream.count();
		return false;
	}
	
	public static void main(String[] args) {
		Stream<Double> randoms = Stream.generate(Math::random);
		boolean answer = isFinit(randoms);
		System.out.println(answer);
	}

}
