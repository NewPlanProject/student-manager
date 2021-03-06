package org.heran.edu.student.util.gener.metadata;

import org.heran.edu.student.util.gener.GeneratorContext;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;


public class MetadataFactory {

	private MetadataFactory() {

	}

	public static Metadata newInstance() {
		try {
			String url = GeneratorContext.getJDBCUrl();
			String username = GeneratorContext.getJDBCUser();
			String password = GeneratorContext.getJDBCPassword();
			String driver = GeneratorContext.getJDBCDriver();

			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url, username, password);

			String metaClass = GeneratorContext.getMetaClass();
			Class<?> clazz = Class.forName(metaClass);
			Class<?>[] parameterTypes = { Connection.class };
			Constructor<?> constructor = clazz.getConstructor(parameterTypes);
			Object[] parameters = { connection };
			return (Metadata) constructor.newInstance(parameters);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
