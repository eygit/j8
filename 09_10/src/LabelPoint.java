import java.util.Objects;


public class LabelPoint {
	private String label;
	private int x;
	private int y;
	public LabelPoint(String ilabel, int ix, int iy) {
		label = ilabel;
		x = ix;
		y = iy;
	}
	@Override
	public int hashCode() {
		// Java7風の解法例.素晴らしく簡単.
		return Objects.hash(label, x, y);
		
		// 旧来のコード例
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((label == null) ? 0 : label.hashCode());
//		result = prime * result + x;
//		result = prime * result + y;
//		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabelPoint other = (LabelPoint) obj;
		
		// Java7風の解法例
		return Objects.equals(label, other.label) &&
				Objects.equals(x, other.x) &&
				Objects.equals(y, other.y);

		// 旧来のコード例
//		if (label == null) {
//			if (other.label != null)
//				return false;
//		} else if (!label.equals(other.label))
//			return false;
//		if (x != other.x)
//			return false;
//		if (y != other.y)
//			return false;
//		return true;
	}
	
	public int compareTo(LabelPoint other) {
		int diff = label.compareTo(other.label);
		if (diff != 0) return diff;
		diff = Integer.compare(x, other.x);
		if (diff != 0) return diff;
		return Integer.compare(y, other.y);
	}
	
	public static void main(String[] args) {
		LabelPoint lp1 = new LabelPoint("lp1", 10, 19);
		LabelPoint lp2 = new LabelPoint("lp2", 10, 19);
		LabelPoint lp3 = new LabelPoint("lp2", 10, 30);
		LabelPoint lp4 = null;
		
		System.out.println(lp1.compareTo(lp1));
		System.out.println(lp1.compareTo(lp2));
		System.out.println(lp1.compareTo(lp3));
		

	}
}
