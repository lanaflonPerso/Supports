package JavaTestUtils;

import java.lang.reflect.Field;

public class TestUtils {
	public static Field getFieldByName( Class class_, String name )
	{
		Field fields[] = class_.getDeclaredFields();
		for(Field field : fields)
		{
			if( field.getName() == name ) return field;
		}
		return null;
	}
}
