import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.function.UnaryOperator;

/**
 *
 * 回答作成中
 * ”カスタマイズされた引数”の指定方法が不適切かもしれない。
 * 課題の意図から、UnaryOperatorを利用して、連鎖的にComparatorを適用する作りにすべきと思うが・・・
 */


public class ComparatorGen {

	public enum SComparator {NATURAL, REVERSE, CASE_SENSITIVE, CASE_INSENSITIVE, SPACE_INCLUDE, SPACE_EXCLUDE};

	public static Comparator<String> comparatorGenerator(EnumSet<SComparator> sc) {

		return String.CASE_INSENSITIVE_ORDER;

	}

	public static Comparator<String> comparatorGenerator(UnaryOperator<Comparator<String>>... carray) {
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER ;
		for (UnaryOperator<Comparator<String>> c : carray) {
			c.apply(comparator);
		}
		return comparator;

//		return Comparator.naturalOrder(); // 普通の順序
//		return Comparator.reverseOrder(); // 逆順
//		return String.CASE_INSENSITIVE_ORDER; // 大文字小文字を区別しない

	}

	public static UnaryOperator<Comparator<String>> natural() {
		return c -> c.thenComparing( Comparator.naturalOrder() ); // [ABC, DEF, a b c, abc, def]
	}

	public static UnaryOperator<Comparator<String>> reverse() {
		return c -> c.thenComparing( Comparator.reverseOrder() ); // [def, abc, a b c, DEF, ABC]
	}

	public static UnaryOperator<Comparator<String>> caseInsensitive() {
		return c -> c.thenComparing( String.CASE_INSENSITIVE_ORDER ); //[a b c, abc, ABC, DEF, def]
	}



	public static void main(String[] args) {
		String[] values = {" ab c   ", "abc", "ABC", "DEF", "def", " a bc ", " ab c ", "abcd", "ABCD"};
		//System.out.println(Arrays.asList(values));

		Comparator<String> natural = (s1, s2) -> s1.compareTo(s2); // 自然な順序
		Comparator<String> reverse = natural.reversed(); // 逆順
		Comparator<String> caseInsensitive = (s1, s2) -> s1.compareToIgnoreCase(s2); // 大文字と小文字を無視

		Comparator<String> spaceInsensitive = (s1, s2) -> {
			String ns1 = s1.replace(" ", "");
			String ns2 = s2.replace(" ", "");
			return ns1.compareTo(ns2);
		}; // 空白を除外する(無視する)

		Arrays.sort(values, natural );
		System.out.println(Arrays.asList(values));

		Arrays.sort(values, reverse);
		System.out.println(Arrays.asList(values));

		Arrays.sort(values, caseInsensitive);
		System.out.println(Arrays.asList(values));

		Arrays.sort(values, spaceInsensitive);
		System.out.println(Arrays.asList(values));

		Comparator<String> c1 = reverse;
		Comparator<String> c2 = spaceInsensitive;
		Arrays.sort(values, c1.thenComparing(c2));
		System.out.println(Arrays.asList(values));



	}

}
