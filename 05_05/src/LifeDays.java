import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class LifeDays {

	static long countDateDuration(LocalDate day, LocalDate birthday) {
		return birthday.until(day, ChronoUnit.DAYS);
	}

	public static void main(String[] args) {
		System.out.println(countDateDuration(LocalDate.of(2014,1,2), LocalDate.of(2014,1,1)));
		System.out.println(countDateDuration(LocalDate.of(2015,1,1), LocalDate.of(2014,1,1)));
		System.out.println(countDateDuration(LocalDate.of(2014,12,3), LocalDate.of(2000,1,1)));
	}
}
