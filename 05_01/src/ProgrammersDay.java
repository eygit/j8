import java.time.LocalDate;



public class ProgrammersDay {

	static LocalDate getProgrammersDay(int year) {
		LocalDate programmersDay = LocalDate.of(year, 9, 13);
		if(programmersDay.isLeapYear()) {
			programmersDay = LocalDate.of(year, programmersDay.getMonth(), 12);
		}
		return programmersDay;
	}
	public static void main(String[] args) {
		// 問5-1の回答
		for (int i = 2014; i < 2024; i++) {
			LocalDate programmersDay = getProgrammersDay(i);
			System.out.println(programmersDay);
		}
	}

}
