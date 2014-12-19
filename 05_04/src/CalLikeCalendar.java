import java.time.DayOfWeek;
import java.time.LocalDate;


public class CalLikeCalendar {

	static void printMonthCalendar(int year, int month) {
		System.out.printf("  === %d / %2d ===\n", year, month);

		LocalDate date = LocalDate.of(year, month, 1);
		int spaces = date.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue(); // 月曜日が1.

		// 月頭の空白印字。
		for (int i = 0; i < spaces; i++) {
			System.out.printf("   ");
		}
		//週単位に印字
		while(true) {
			System.out.printf(" %2d", date.getDayOfMonth());
			if (DayOfWeek.SUNDAY.equals(date.getDayOfWeek()) ) {
				System.out.println();
			}
			date = date.plusDays(1);
			if (date.getMonth().getValue() != month) {
				break;
			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		printMonthCalendar(2014, 12);
		printMonthCalendar(2015, 1);

	}

}
