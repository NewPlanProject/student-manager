package org.heran.edu.student.util.mybatis.gener.template;


import org.heran.edu.student.util.dispose.StringUtil;
import org.heran.edu.student.util.gener.GeneratorContext;

public class Mapper {

	private String table;

	public Mapper(String table) {
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public String tableDomainName() {
		return StringUtil.firstLetterLower(mapperName().replaceAll("Mapper", ""));
	}

	public String mapperName() {
		StringBuffer mapperName = new StringBuffer();

		for (String str : StringUtil.splitToList(getTable(), "_")) {
			mapperName.append(StringUtil.firstLetterUpper(str.toLowerCase()));
		}

		return mapperName.append("Mapper").toString();
	}

	public String compareValue(String javaType) {
		return GeneratorContext.getDefaultValue(javaType);
	}

}
