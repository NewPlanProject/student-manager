package org.heran.edu.student.dao;

import org.heran.edu.student.util.mybatis.MapperDaoTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.heran.edu.student.domain.StuDictionary;

@Repository
public class StuDictionaryDao extends MapperDaoTemplate<StuDictionary> {

    @Autowired
    public StuDictionaryDao(SqlSessionTemplate sqlSessionTemplate){
        super(sqlSessionTemplate);
    }

}
