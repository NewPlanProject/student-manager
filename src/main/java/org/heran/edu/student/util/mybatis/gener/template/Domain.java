package org.heran.edu.student.util.mybatis.gener.template;


import org.heran.edu.student.util.dispose.StringUtil;

public class Domain {

	private String table;

	public Domain(String table) {
		this.table = table;
	}

	public String getTable() {
		return this.table;
	}

	public String className() {
		StringBuffer className = new StringBuffer();

		for (String str : StringUtil.splitToList(getTable(), "_")) {
			className.append(StringUtil.firstLetterUpper(str.toLowerCase()));
		}

		return className.toString();
	}

}
