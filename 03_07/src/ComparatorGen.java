import java.util.Arrays;
import java.util.Comparator;

/**
 * [コメント]
 * ”カスタマイズされた引数”の指定方法が不適切かもしれない。
 * テキストの該当部の記述から、UnaryOperatorを利用して連鎖的にComparatorを適用する作りにすべきと思うが・・・うまくいっていない。
 */


public class ComparatorGen {

	private static String sensitiveString(String in, boolean caseSensitiveOrder, boolean spaceSensitiveOrder) {
		String out = in;
		if (! caseSensitiveOrder) out = out.toUpperCase();
		if (! spaceSensitiveOrder)out = out.replaceAll(" ", "");
		return out;
	}

	/**
	 * 指定のコンパレータを返す。
	 * @param naturalOrder trueなら自然な順序、そうでないなら逆順
	 * @param caseSensitiveOrder trueなら大文字小文字を区別する
	 * @param spaceSensitiveOrder trueなら空白を区別する
	 * @return 指定のコンパレータ
	 */
	public static Comparator<String> comparatorGenerator(boolean naturalOrder, boolean caseSensitiveOrder, boolean spaceSensitiveOrder) {

		Comparator<String> c = (s1, s2) -> {
			String ss1 = sensitiveString(s1, caseSensitiveOrder, spaceSensitiveOrder);
			String ss2 = sensitiveString(s2, caseSensitiveOrder, spaceSensitiveOrder);
			return ss1.compareTo(ss2);
		};

		return naturalOrder ? c : c.reversed();
	}



	public static void main(String[] args) {
		String[] values = {" ab c   ", "abc", "ABC", "DEF", "def", " a bc ", " ab c ", "abcd", "ABCD"};
		System.out.println(Arrays.asList(values) + "元配列順");

		Arrays.sort(values, comparatorGenerator(true, true, true));
		System.out.println(Arrays.asList(values) + "自然な順序");

		Arrays.sort(values, comparatorGenerator(false, true, true));
		System.out.println(Arrays.asList(values) + "逆順");

		Arrays.sort(values, comparatorGenerator(true, false, true));
		System.out.println(Arrays.asList(values) + "大文字小文字無視");

		Arrays.sort(values, comparatorGenerator(true, true, false));
		System.out.println(Arrays.asList(values) + "空白無視");

		Arrays.sort(values, comparatorGenerator(false, false, false));
		System.out.println(Arrays.asList(values) + "組み合わせ(逆順、大文字小文字無視、空白無視)");

	}

}
