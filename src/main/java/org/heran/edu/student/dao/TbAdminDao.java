package org.heran.edu.student.dao;

import org.heran.edu.student.util.mybatis.MapperDaoTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.heran.edu.student.domain.TbAdmin;

@Repository
public class TbAdminDao extends MapperDaoTemplate<TbAdmin> {

    @Autowired
    public TbAdminDao(SqlSessionTemplate sqlSessionTemplate){
        super(sqlSessionTemplate);
    }

}
