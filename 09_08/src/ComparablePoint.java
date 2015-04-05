import java.awt.Point;


public class ComparablePoint extends Point {
	public ComparablePoint(int x, int y) {
		super(x, y);
	}
	
	public int compareTo(Point other) {
		// アップキャストによる解法例
		long diff = (long)x - (long)other.x;
		if (diff != 0) return (int)diff;
		return (int)((long)y - (long)other.y);
		
		// 書籍のオリジナル記述
//		int diff = Integer.compare(x,  other.x);
//		if (diff != 0)  return diff;
//		return Integer.compare(y, other.y);
	}

	public static void main(String[] args) {
		System.out.println(new ComparablePoint(4, 3).compareTo(new ComparablePoint(7, 0)));
		System.out.println(new ComparablePoint(Integer.MAX_VALUE, 3).compareTo(new ComparablePoint(7, 0)));

	}

}
