import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class ZipStream {
	
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		// 遅延で評価していないので、おそらく間違い。無限ストリームに非対応な解き方・・・
		Iterator<T> itef = first.iterator();
		Iterator<T> ites = second.iterator();
		List<T> l = new ArrayList<T>();
		while(true) {
			if (itef.hasNext()) {
				l.add(itef.next());
			} else {
				break;
			}
			if (ites.hasNext()) {
				l.add(ites.next());
			} else {
				break;
			}
		}
		return l.stream();
	}
	
	public static void main(String[] args) {
		Stream<String> as = Stream.of("a","a","a","a","a","a","a","a","a","a");
		Stream<String> bs = Stream.of("b","b","b","b","b","b","b","b","b","b","b");
		Stream<String> abs = zip(as, bs);
		abs.forEachOrdered(System.out::print);

	}

}
