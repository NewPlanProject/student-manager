package org.heran.edu.student.dao;

import org.heran.edu.student.util.mybatis.MapperDaoTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.heran.edu.student.domain.TbStudent;

@Repository
public class TbStudentDao extends MapperDaoTemplate<TbStudent> {

    @Autowired
    public TbStudentDao(SqlSessionTemplate sqlSessionTemplate){
        super(sqlSessionTemplate);
    }

}
