import java.time.LocalDate;


public class LeapYearAddar {

	public static void main(String[] args) {
		LocalDate leap = LocalDate.of(2000, 2, 29);

		//適切に閏日（閏日がない平年は前の日）が計算される。。
		System.out.println(leap.plusYears(1));

		//適切に閏日が計算される。
		System.out.println(leap.plusYears(4));

		//一度閏日から平日(2/28)変換されるので、4年後はうるう日とならない。
		System.out.println(leap.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
