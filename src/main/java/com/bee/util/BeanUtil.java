/**
 * Package: com.qingwei.common.util
 * Description: 
 */
package com.bee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Description: Bean工具类，提供对象属性复制功能（根据java提供的内省机制实现）
 * 以及对象转换功能
 * Date: 2017年6月25日 下午3:03:06
 * @author wufenyun 
 */
public class BeanUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
	
	/** 
	 * Description: 对象转换功能，通过源对象得到指定类型的实例，如果存在相同属性会复制属性 
	 * @param source 源对象
	 * @param clazz 目标类型
	 * @return 目标类型的对象
	 */
	public static <T> T convert(Object source,Class<T> clazz) {
		AssertUtil.notNull(source, "source  object is null");
		AssertUtil.notNull(clazz, "target calss is null");
		
		T target = null;
		try {
			target = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("clazz get instance error",e);
		}
		
		copyProperties(source,target);
		return target;
	}
	
	/** 
	 * Description: 复制source对象属性值给target对象;
	 * @param source 源对象
	 * @param target 目标对象
	 */
	public static void copyProperties(Object source,Object target) {
		copyProperties(source,target, null,false);
	}
	
	/** 
	 * Description: 复制source对象属性值给target对象;
	 * 如果参数ignoreNullProperties为true，则所有的null属性不会复制
	 * @param source 源对象
	 * @param target 目标对象
	 */
	public static void copyProperties(Object source,Object target,boolean ignoreNullProperties) {
		copyProperties(source,target, null,ignoreNullProperties);
	}
	
	/** 
	 * Description: 复制source对象属性值给target对象;
	 * 如果ignoreProperties列表不为空，则忽略复制列表中的属性复制操作;
	 * 如果参数ignoreNullProperties为true，则所有的null属性不会复制
	 * @param source 源对象
	 * @param target 目标对象
	 * @param ignoreProperties 忽略复制操作的属性列表
	 * @param ignoreNullProperties 时候需要复制属性值为null的属性
	 */
	public static void copyProperties(Object source, Object target, String[] ignoreProperties,
			boolean ignoreNullProperties) {
		AssertUtil.notNull(source, "source objcet is null");
		AssertUtil.notNull(target, "target objcet is null");
		try {
			Class<?> editable = target.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(editable);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			//遍历复制属性
			List<String> ignorePropertyList = (ignoreProperties == null ? null : Arrays.asList(ignoreProperties));
			for (PropertyDescriptor pd : propertyDescriptors) {
				String propertyName = pd.getName();
				if (ignorePropertyList != null && ignorePropertyList.contains(propertyName)) {
					continue;
				}

				// 获取源对象属性的值
				Method targetWriteMethod = pd.getWriteMethod();
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), propertyName);
				if (targetWriteMethod == null || null == sourcePd) {
					continue;
				}
				
				//如果忽略复制null属性并且属性为null，则不执行赋值操作，否则赋值属性
				Method sourceReadMethod = sourcePd.getReadMethod();
				setAccessibleMethod(sourceReadMethod);
				Object value = sourceReadMethod.invoke(source);
				if(ignoreNullProperties && null==value) {
					continue;
				}
				setAccessibleMethod(targetWriteMethod);
				targetWriteMethod.invoke(target, value);
			}
		} catch (Exception e) {
			LOGGER.error("Bean copy failed!", e);
			throw new RuntimeException("Bean copy failed!", e);
		}
	}
	
	/** 
	 * Description:  根据PropertyDescriptor返回具有取消默认 Java 语言访问控制检查能力的方法
	 * @param method PropertyDescriptor
	 * @return 返回具有取消默认 Java 语言访问控制检查能力的方法
	 */
	public static void setAccessibleMethod(Method method) {
		AssertUtil.notNull(method);
		if(!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);
		}
	}

	/** 
	 * Description: 获取指定属性名在类型中的PropertyDescriptor
	 * @param clazz Class
	 * @param propertyName 属性名
	 * @return PropertyDescriptor
	 * @throws IntrospectionException 
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz,String propertyName) throws IntrospectionException {
		AssertUtil.notNull(clazz,"Object class is null");
		AssertUtil.hasLength(propertyName,"Property name is blank");
		
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd:pds) {
			if(propertyName.equals(pd.getName())) {
				return pd;
			}
		}
		return null;
	}
	
}
