import java.time.DayOfWeek;
import java.time.LocalDate;


public class Sat13OfAD20 {

	public static void main(String[] args) {
		// 20世紀の全ての13日の金曜日列挙
		LocalDate date = LocalDate.of(1901,1,13);

		int counter = 0;
		while(date.getYear() <= 2000) {
			if (DayOfWeek.FRIDAY.equals(date.getDayOfWeek())) {
				System.out.println(date + " : " +  date.getDayOfWeek());
				counter++;
			}
			date = date.plusMonths(1);
		}
		System.out.println( counter + "days");
	}

}
