import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;


public class TimeZoneOffset {

	public static void main(String[] args) {
		Stream<String> zoneIds = ZoneId.getAvailableZoneIds().stream();
		zoneIds.forEach(i -> {
			ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(i));
			System.out.println(zdt.getOffset() + " : " + zdt.getZone());
		});




	}

}
