package org.heran.edu.student.util.gener.metadata.mysql;


import org.heran.edu.student.util.gener.metadata.Column;

public class MysqlColumn extends Column {

	@Override
	protected int jdbcTypeWrapper(int jdbcType) {
		return jdbcType;
	}

}
