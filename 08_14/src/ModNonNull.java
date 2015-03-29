import java.util.Objects;


public class ModNonNull {

	public static void main(String[] args) {
		String hoge = null;
		Objects.requireNonNull(hoge, "hoge is null...");

	}

}
