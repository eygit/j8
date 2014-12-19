import java.time.LocalDate;


public class LeapYearAddar {

	public static void main(String[] args) {
		LocalDate leap = LocalDate.of(2000, 2, 29);

		//１年追加は、適切に閏日（閏日がない平年は前の日）が計算される。
		System.out.println(leap.plusYears(1));

		//４年追加は、適切に閏日が計算される。
		System.out.println(leap.plusYears(4));

		//途中で平日(2/28)に日付が変換されるので、4年後はうるう日とならない。
		System.out.println(leap.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
