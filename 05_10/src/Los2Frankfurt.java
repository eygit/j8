import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Los2Frankfurt {

	public static void main(String[] args) {
		ZonedDateTime los = ZonedDateTime.of(2014, 12, 10, 3, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime flight = los.plusHours(10).plusMinutes(50);
		ZonedDateTime frankfurt = flight.withZoneSameInstant(ZoneId.of("CET"));
		System.out.println("出発時刻（ロス基準）" + los);
		System.out.println("到着時刻（ロス基準）" + flight);
		System.out.println("到着時刻（CET基準）" + frankfurt);

	}

}
