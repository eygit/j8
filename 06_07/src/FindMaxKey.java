import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap<String, Long>内で、最大値を持つキーを見つけなさい
 * (同じ最大値を持つキーがあれば、どちらのキーでもかまいません)。
 * ヒント：reduceEntries
 *
 */

public class FindMaxKey {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 10000; i++) {
			map.put("key " + i, (long) i%365);
		}

		Map.Entry<String, Long> ans = map.reduceEntries(4, (t, u) -> {
			return (t.getValue() > u.getValue()) ? t : u;
		});

		System.out.println(ans);

	}

}
