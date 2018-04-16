package org.heran.edu.student.util.dispose;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * 对象工具类
 * 
 * @author John
 *
 */
public class ObjectUtil {

	/**
	 * 判断对象是否为Null
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		if (null == object) {
			return true;
		}

		return false;
	}

	/**
	 * Map转对象
	 * @param map
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, String> map, Class<T> T) throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException{
		BeanInfo beanInfo = Introspector.getBeanInfo(T);
		Object obj = T.newInstance();

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor descriptor : propertyDescriptors){
			String propertyName = descriptor.getName();
			Class propertyType = descriptor.getPropertyType();
			if(map.containsKey(propertyName)){
				try {
					Object[] args = new Object[1];
					Object value = map.get(propertyName);

					if(value!=null && !"".equals(value)){
						if(propertyType == String.class){
							value = value.toString();
						}else if(propertyType == int.class || propertyType == Integer.class){
							value = Integer.parseInt(value.toString());
						}else if(propertyType == boolean.class || propertyType == Boolean.class){
							value = value.equals("true") ? true : false;
						}else if(propertyType == Timestamp.class){
							value = Timestamp.valueOf(value.toString().substring(0,19));
						}else if(propertyType == Date.class){
							value = DateUtil.parseDateByFormat(value.toString(),"yyyy-MM-dd HH:mm:ss");
						}else if(propertyType == double.class || propertyType == Double.class) {
							value = Double.parseDouble(value.toString());
						}else{
							System.out.println("no propety--------------" + propertyName + "---->" + propertyType);
							value = null;
						}
					}else{
						value = null;
					}

					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (T)obj;
	}

}
