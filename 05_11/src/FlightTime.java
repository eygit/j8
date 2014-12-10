import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


public class FlightTime {
	public static void main(String[] args) {
		ZonedDateTime frankfurt = ZonedDateTime.of(2014, 12, 10, 14, 5, 0, 0, ZoneId.of("CET"));
		ZonedDateTime los = ZonedDateTime.of(2014, 12, 10, 16, 40, 0, 0, ZoneId.of("America/Los_Angeles"));
		long flightHours = frankfurt.until(los, ChronoUnit.HOURS);
		long flightMinutes = frankfurt.until(los, ChronoUnit.MINUTES);
		System.out.println("出発時刻（フランクフルト基準）" + frankfurt );
		System.out.println("到着時刻（ロス基準）" + los);
		System.out.println("フライト時間: " + flightHours + "時間 " + flightMinutes%60 + "分");

	}

}
