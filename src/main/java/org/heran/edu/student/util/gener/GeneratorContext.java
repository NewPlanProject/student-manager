package org.heran.edu.student.util.gener;

import org.apache.velocity.VelocityContext;

import java.util.Set;

public class GeneratorContext {

	private static VelocityContext velocityContext = new VelocityContext();

	private static final String[][] defaultValues = { { "short", "0" }, { "int", "0" },
			{ "long", "0" }, { "float", "0.0" }, { "double", "0.0" } };

	private GeneratorContext() {
	}

	public static VelocityContext getContext() {
		return velocityContext;
	}

	public static void setParameter(Parameter parameter) {
		Set<String> keys = parameter.getParameter().keySet();
		for (String key : keys) {
			velocityContext.put(key, parameter.getParameter().get(key));
		}
	}

	public static void setParameter(String key, Object value) {
		velocityContext.put(key, value);
	}

	private static String getParameter(String key) {
		return (String) velocityContext.get(key);
	}

	public static String getMetaClass() {
		return getParameter(Parameter.MetaClass);
	}

	public static String getJDBCUrl() {
		return getParameter(Parameter.JDBCUrl);
	}

	public static String getJDBCUser() {
		return getParameter(Parameter.JDBCUser);
	}

	public static String getJDBCPassword() {
		return getParameter(Parameter.JDBCPassword);
	}

	public static String getJDBCDriver() {
		return getParameter(Parameter.JDBCDriver);
	}

	public static String getTableList() {
		return getParameter(Parameter.TableList);
	}

	public static String getTablePrefix() {
		return getParameter(Parameter.TablePrefix);
	}

	public static String getDomainPath() {
		return getParameter(Parameter.DomainPath);
	}

	public static String getDomainPackage() {
		return getParameter(Parameter.DomainPackage);
	}

	public static String getBuildPath() {
		return getParameter(Parameter.BuildPath);
	}

	public static String getMapperPath() {
		return getParameter(Parameter.MapperPath);
	}

	public static String getDaoPath() {
		return getParameter(Parameter.DaoPath);
	}

	public static String getDaoPackage() {
		return getParameter(Parameter.DaoPackage);
	}

	public static String getVmPath() {
		return "org/heran/edu/student/util/gener/template/vm/";
	}

	public static String getDefaultValue(String javaType) {
		for (int i = 0; i < defaultValues.length; i++) {
			if (defaultValues[i][0].equals(javaType)) {
				return defaultValues[i][1];
			}
		}

		return "null";
	}

}
