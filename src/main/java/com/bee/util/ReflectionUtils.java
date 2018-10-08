package com.bee.util;

/**
 * Created by luopeng
 * on 2015/9/9.
 */
public class ReflectionUtils {

	public static <T> T initFromClass(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
