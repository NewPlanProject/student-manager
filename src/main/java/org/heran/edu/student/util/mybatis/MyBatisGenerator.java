package org.heran.edu.student.util.mybatis;


import org.heran.edu.student.util.gener.Generator;
import org.heran.edu.student.util.gener.GeneratorContext;
import org.heran.edu.student.util.gener.Parameter;

/**
 * Created by zhaohl on 2017/5/31.
 */
public class MyBatisGenerator {

    public static void main(String args[]){
        String[] parameters = {"buildPath=build",
                "metaClass=org.heran.edu.student.util.gener.metadata.mysql.MysqlMetadata",
                "jdbcUrl=jdbc:mysql://111.198.15.79:65101/db_student",
                "jdbcUser=root",
                "jdbcPassword=123456",
                "jdbcDriver=com.mysql.jdbc.Driver",
                "sqlSessionTemplateName=sqlSessionTemplate",
                "domainPackage=org.heran.edu.student.domain",
                "domainPath=D:\\projects\\student-manager\\src\\main\\java\\org\\heran\\edu\\student\\domain",
                "mapperPath=D:\\projects\\student-manager\\src\\main\\resources\\mapper",
                "daoPackage=org.heran.edu.student.dao",
                "daoPath=D:\\projects\\student-manager\\src\\main\\java\\org\\heran\\edu\\student\\dao",
                "tableList=stu_inifo"};
        // 解析参数
        Parameter parameter = new Parameter(parameters);
        // 将参数存入上下文对象，供其他类引用
        GeneratorContext.setParameter(parameter);
        // 开始生成
        Generator generator = new Generator();
        generator.generate();
    }
}
