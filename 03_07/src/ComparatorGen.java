import java.util.Arrays;
import java.util.Comparator;


public class ComparatorGen {

//	public static <T,U>  Comparator<T> comparatorGenerator(Function<? super T,? extends U> keyExtractor,Comparator<? super U> keyComparator) {
	public static <T extends Comparable<? super T>> Comparator<T> comparatorGenerator() {
		// TODO: 返却するオブジェクトはラムダ式で実現する必要があるため、これでは不適切。

		return Comparator.naturalOrder();
	}

	public static void main(String[] args) {
		String[] values = {"abc", "ABC", "def", "DEF", "a b c"};

		// コンパレータを引数でどのように指定するべきか？ enum?
		Arrays.sort(values, comparatorGenerator());

		System.out.println(Arrays.asList(values));

	}

}
