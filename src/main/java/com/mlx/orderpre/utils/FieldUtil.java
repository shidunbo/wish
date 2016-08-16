package com.mlx.orderpre.utils;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

public class FieldUtil {

	/**
	 * 创建一个实体 [custom]前缀后跟自定义的属性值，[get]前缀后跟对象中的属性名 不支持从对象中获取时间 ，配置在kingdee.properties文件
	 * @param clazz 生成实体的类型
	 * @param prefix 前缀
	 * @param e 获取属性值的对象
	 * @return
	 */
	public static <T, E> T filedSet(Class<T> clazz, String prefix, E e) {
		try {
			T instance = clazz.newInstance();
			// 获取类的数组
			Field[] declaredFields = clazz.getDeclaredFields();
			Class<? extends Object> tarClass = e.getClass();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				// 根据属性名获取配置的映射值
				String fieldValue = ApplicationProperties.getMessage(prefix + field.getName());
				if (!StringUtils.isEmpty(fieldValue)) {
					// 直接读取配置文件中的[custom]后的属性值
					if (fieldValue.startsWith("[custom]")) {
						field.set(instance, fieldValue.substring("[custom]".length()));
					}
					// 根据读取配置文件中的[get]后的属性名从对象中获取
					if (fieldValue.startsWith("[get]")) {
						Field tarCField = tarClass.getDeclaredField(fieldValue.substring("[get]".length()));
						tarCField.setAccessible(true);
						field.set(instance, tarCField.get(e));
					}
				}
			}
			return instance;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
