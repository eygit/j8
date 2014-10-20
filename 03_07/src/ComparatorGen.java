import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.function.UnaryOperator;

/**
 *
 * 回答作成中
 * 現時点で、コンパイルふのう。。。
 * 課題の意図から、UnaryOperatorを利用して、連鎖的にComparatorを適用する作りにすべきと思うが、
 * comparatorGenerator
 */


public class ComparatorGen {

	public enum SComparator {NATURAL, REVERSE, CASE_SENSITIVE, CASE_INSENSITIVE, SPACE_INCLUDE, SPACE_EXCLUDE};

	public static Comparator<String> comparatorGenerator(EnumSet<SComparator> sc) {


		return String.CASE_INSENSITIVE_ORDER;

	}

	public static Comparator<String> comparatorGenerator(UnaryOperator<Comparator<String>>... carray) {
		Comparator<String> comparator = ;
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
		String[] values = {"abc", "ABC", "DEF", "def", "a b c"};

		// コンパレータを引数でどのように指定するべきか？ enum?
		Arrays.sort(values, comparatorGenerator( natural() ));
		System.out.println(Arrays.asList(values));

		Arrays.sort(values, comparatorGenerator( reverse() ));
		System.out.println(Arrays.asList(values));

		Arrays.sort(values, comparatorGenerator( caseInsensitive() ));
		System.out.println(Arrays.asList(values));

//		Arrays.sort(values,
////				Comparator.naturalOrder() // [ABC, DEF, a b c, abc, def]
////				Comparator.reverseOrder() // [def, abc, a b c, DEF, ABC]
////				String.CASE_INSENSITIVE_ORDER.thenComparing(Comparator.reverseOrder()) //[a b c, abc, ABC, def, DEF]
////				String.CASE_INSENSITIVE_ORDER.thenComparing(Comparator.naturalOrder()) //[a b c, ABC, abc, DEF, def]
////				Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER) //[abc, ABC, DEF, def, a b c]
//				);


	}

}
