package org.heran.edu.student.service;

import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Transactional
public interface FinanceService {


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
     * @param financeSaveInVO
     * @return
     */
    Result<Boolean> addFinance(FinanceSaveInVO financeSaveInVO);

    /**
     *
     * @param financeUpdateInVO
     * @return
     */
    Result<Boolean> updateFinance(FinanceUpdateInVO financeUpdateInVO);

    /**
     *
     * @param financeInfoInVO
     * @return
     */
    Result<Map<String,Object>> financeInfoList(FinanceInfoInVO financeInfoInVO);
}
