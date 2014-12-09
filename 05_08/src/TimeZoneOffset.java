import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Stream;


public class TimeZoneOffset {

	public static void main(String[] args) {
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println(zdt.getOffset().getId()); // 今日の日付のオフセット(+9時間)取得の普通の方法。

		Stream<Set<String>> zoneIds = Stream.of(ZoneId.getAvailableZoneIds());
		zoneIds.forEach(System.out::println);
		//T.B.D.




	}

}
