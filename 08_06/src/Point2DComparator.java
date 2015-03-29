import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;
import java.util.function.Function;


public class Point2DComparator  {

	public static Comparator<Point2D> genp2dc() {
		Function<Point2D, Double> pxfunc = p -> p.getX();
		Function<Point2D, Double> pyfunc = p -> p.getY();
		Comparator<Point2D> cx =  Comparator.comparing( pxfunc );
		Comparator<Point2D> cy =  Comparator.comparing( pyfunc );
		return  cx.thenComparing(cy);
	}

	public static Comparator<Rectangle2D> genr2dc() {
		Function<Rectangle2D, Double> rxfunc = p -> p.getX();
		Function<Rectangle2D, Double> ryfunc = p -> p.getY();
		Function<Rectangle2D, Double> rwfunc = p -> p.getWidth();
		Function<Rectangle2D, Double> rhfunc = p -> p.getHeight();
		Comparator<Rectangle2D> cx =  Comparator.comparing( rxfunc );
		Comparator<Rectangle2D> cy =  Comparator.comparing( ryfunc );
		Comparator<Rectangle2D> cw =  Comparator.comparing( rwfunc );
		Comparator<Rectangle2D> ch =  Comparator.comparing( rhfunc );
		return  cx.thenComparing(cy).thenComparing(cw).thenComparing(ch);
	}

	public static void main(String[] args) {
		Point2D p1 = new Point(2, 3);
		Point2D p1same = new Point(2, 3);
		Point2D p2 = new Point(3, 3);
		
		System.out.println(genp2dc().compare(p1,  p1));
		System.out.println(genp2dc().compare(p1,  p1same));
		System.out.println(genp2dc().compare(p1,  p2));
		
		Rectangle2D r1 = new Rectangle(2, 3, 4, 5);
		Rectangle2D r1same = new Rectangle(2, 3, 4, 5);
		Rectangle2D r2 = new Rectangle(3, 3, 3, 3);
		System.out.println(genr2dc().compare(r1,  r1));
		System.out.println(genr2dc().compare(r1,  r1same));
		System.out.println(genr2dc().compare(r1,  r2));

	}

}
