package com.littlehui.fantuan.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: littlehui Date: 14-10-29 Time: 上午10:49
 */
public class ListUtils {

	/**
	 * 获取list里面单个对象的单个属性重新组装成一个list
	 *
	 * @param list
	 * @param columnName
	 * @param columnClass
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> getListItemsSingleColumnList(List list, String columnName, Class<T> columnClass) {
		List<T> returnList = new ArrayList<T>();
		if (!ListUtils.isEmpty(list)) {
			for (Object object : list) {
				Object columnObject = getPropValueByName(object, columnName);
				if (columnObject != null) {
					returnList.add((T) columnObject);
				}
			}
		}
		return returnList;
	}

	public static Object getPropValueByNameSimple(Object object, String propName) {
		try {
			// 优先从方法获取，get+属性(属性第一个字母为转换成大写)
			Object result = getPropValueByMethod(object, propName);
			if (result == null) {
				Field field = object.getClass().getDeclaredField(propName);
				if (field == null) {
					return null;
				}
				// 获取原来的访问控制权限
				boolean accessFlag = field.isAccessible();
				// 修改访问控制权限
				field.setAccessible(true);
				result = field.get(object);
				field.setAccessible(accessFlag);
			}
			return result;
		} catch (Exception e) {
			// 异常返回空
			return getPropValueByMethod(object, propName);
		}
	}

	/**
	 * 根据属性名称获取属性值.
	 *
	 * @param object
	 * @param propName
	 * @return
	 */
	public static Object getPropValueByName(Object object, String propName) {
		try {
			String props[] = propName.split("\\.");
			Object o = object;
			for (String prop : props) {
				o = getPropValueByNameSimple(o, prop);
				if (o == null) {
					break;
				}
			}
			return o;
		} catch (Exception e) {
			// 异常返回空
			return null;
		}
	}

	/**
	 * 根据属性名称获取属性值.
	 *
	 * @param object
	 * @param propName
	 * @return
	 */
	public static Object getPropValueByMethod(Object object, String propName, Object... params) {
		try {
			StringBuffer sb = new StringBuffer(propName);
			sb.setCharAt(0, Character.toUpperCase(propName.charAt(0)));

			Method method = object.getClass().getDeclaredMethod("get" + sb.toString());
			if (method == null) {
				return null;
			}
			// 获取原来的访问控制权限
			boolean accessFlag = method.isAccessible();
			// 修改访问控制权限
			method.setAccessible(true);
			Object result = method.invoke(object);
			method.setAccessible(accessFlag);
			return result;
		} catch (Exception e) {
			// 异常返回空
			return null;
		}
	}

	public static boolean isEmpty(Collection c) {
		if (c == null || c.size() < 1) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Collection c) {
		return !isEmpty(c);
	}

	/**
	 * 拼装list成String.
	 * 
	 * @param list
	 * @param separator
	 *            分隔符
	 * @return
	 * @author QiSF
	 * @date 2014年12月19日
	 */
	public static String list2String(List list, String separator) {
		StringBuffer returnStr = new StringBuffer("");
		if (isNotEmpty(list)) {
			int i = 0;
			for (Object o : list) {
				returnStr.append(o);
				i++;
				if (i < list.size()) {
					returnStr.append(separator);
				}
			}
		}
		return returnStr.toString();
	}

	public static List<Object> toObjectList(String[] classifyCodes) {
		List<Object> objects = new ArrayList<>();
		for (String code : classifyCodes) {
			objects.add((Object)code);
		}
		return objects;
	}

	public static List<Object> toObjectList(List<String> classifyCodes) {
		List<Object> objects = new ArrayList<>();
		for (String code : classifyCodes) {
			objects.add((Object)code);
		}
		return objects;
	}
}
