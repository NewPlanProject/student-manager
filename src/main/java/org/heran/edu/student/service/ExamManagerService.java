package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Transactional
public interface ExamManagerService {

    /**
     *
     * @param examMangerSaveInVO
     * @return
     */
    Result<Boolean> add(ExamMangerSaveInVO examMangerSaveInVO);

    /**
     *
     * @param idCard
     * @return
     */
    Result<Map<String,Object>> ifIdCard(String idCard);


    /**
     *
     * @param examMangerInfoInVO
     * @return
     */
    Result<Map<String,Object>> examInfoList(ExamMangerInfoInVO examMangerInfoInVO);


    /**
     *
     * @param id
     * @return
     */
    Result<Map<String,Object>> detail(String id);

    /**
     *
     * @param ids
     * @return
     */
    Boolean del(String[] ids);

    /**
     *
     * @param examMangerUpdateInVO
     * @return
     */
    Result<Boolean> update(ExamMangerUpdateInVO examMangerUpdateInVO);
}
