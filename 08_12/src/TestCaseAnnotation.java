import java.lang.annotation.Repeatable;


@Repeatable (TestCases.class)
@interface TestCase {
	String params();
	String expected();
}

@interface TestCases {
	TestCase[] value();
}

public class TestCaseAnnotation {
	@TestCase(params="4", expected="24")
	@TestCase(params="0", expected="1")
	public static int hoge(int in) {
		if (in == 4) return 24;
		if (in == 0) return 1;
		return -1;
	}

}
