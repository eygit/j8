import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Stream;


public class TimeZoneLessThan1 {

	public static void main(String[] args) {
		ZoneOffset zo1 = ZoneOffset.ofHours(1);
		Stream<String> zoneIds = ZoneId.getAvailableZoneIds().stream();
		zoneIds.forEach(i -> {
			ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(i));
			if (zo1.compareTo(zdt.getOffset()) < 0) {
				System.out.println(zdt.getOffset() + " : " + zdt.getZone());
			}
		});




	}

}
