import java.time.DayOfWeek;
import java.time.LocalDate;


public class Sat13Counter {

	public static void main(String[] args) {
		// 20世紀の全ての13日の金曜日列挙
		LocalDate date = LocalDate.of(1901,1,13);

		while(date.getYear() <= 2000) {
			if (DayOfWeek.FRIDAY.equals(date.getDayOfWeek())) {
				System.out.println(date + " : " +  date.getDayOfWeek());
			}
			date = date.plusMonths(1);
		}



	}

}
