import java.lang.reflect.Field;
import java.util.Comparator;


public class FieldComparatorGenerator {

	private static Object getFieldNameObject(Object o, String name) {
		Class<?> clazz = o.getClass();
		Field field = null;
		while (clazz != null) {
			try {
				field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		Object object = null;
		try {
			if (field == null) {
				return null;
			}
			object = field.get(o);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}

	public static Comparator<Object> lexicographicComparator(String... fieldNames) {
		return (o1, o2) -> {
			for (String fieldName : fieldNames) {
				Object o1field = getFieldNameObject(o1, fieldName);
				Object o2field = getFieldNameObject(o2, fieldName);
				if ( o1field == null ||  ! o1field.equals(o2field) ) {
					return -1;// フィールドが異なる場合に「差を返す」について、どのように「数値」を返すのが適切か？
				}
			}

			return 0; // 全てのフィールドがマッチした場合
		};
	}
	public static void main(String[] args) {
		Comparator<Object> c = lexicographicComparator("lastname", "firstname");
		Comparator<Object> cE = lexicographicComparator("lastname", "worktime", "firstname");

		Object yamada = new Person("taro", "yamada");
		Object suzuki = new Person("ken", "suzuki");
		Object yamadaE = new Employee("taro", "yamada", 8);

		System.out.println(c.compare(yamada, yamada) + "\t ; 同一オブジェクトの比較");
		System.out.println(c.compare(yamada, suzuki) + "\t ; 同一クラス異なるオブジェクト値のインスタンス比較");

		System.out.println(c.compare(yamada, yamadaE) + "\t ; 異なるクラスの同一フィールド比較");
		System.out.println(cE.compare(yamada, yamadaE) + "\t ; 異なるクラスで拡張クラスのみにあるフィールド比較");

	}

}
