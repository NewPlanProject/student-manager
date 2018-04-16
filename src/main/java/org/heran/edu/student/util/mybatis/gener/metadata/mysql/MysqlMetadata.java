package org.heran.edu.student.util.mybatis.gener.metadata.mysql;


import org.heran.edu.student.util.gener.metadata.Column;
import org.heran.edu.student.util.gener.metadata.Metadata;

import java.sql.Connection;
public class MysqlMetadata extends Metadata {

	public MysqlMetadata(Connection connection) {
		super(connection);
	}

	@Override
	public String getDatabase() {
		return "mysql";
	}

	@Override
	public Column getColumn() {
		return new MysqlColumn();
	}

}
