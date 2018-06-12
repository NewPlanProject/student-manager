package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.ExamManagerDao;
import org.heran.edu.student.dao.FinanceDao;
import org.heran.edu.student.dao.GradeDao;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.Finance;
import org.heran.edu.student.domain.Grade;
import org.heran.edu.student.service.FinanceService;
import org.heran.edu.student.service.GradeService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.UUIDUtil;
import org.heran.edu.student.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Service
@Slf4j
@Transactional
class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;

    @Override
    public Result<Map<String, Object>> detail(String id) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        Map<String, Object> resultMap = new HashedMap();
        Finance finance= new Finance();
        finance.setId(id);
        try {
            Finance finance1 = this.financeDao.selectOne(finance);
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",finance1);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        return res;
    }

    @Override
    public Boolean del(String[] ids) {
        log.debug("Enter del ids={}",ids);
        boolean res = false;
        try {
            for(int i=0;i<ids.length;i++){
                Finance finance= new Finance();
                finance.setId(ids[i]);
                this.financeDao.delete(finance);
            }
            res=true;
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        log.debug("Leave del res={}",res);
        return res;
    }

    @Override
    public Result<Boolean> addFinance(FinanceSaveInVO financeSaveInVO) {
        log.debug("Enter addFinance financeSaveInVO={}", financeSaveInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        try {
            Finance finance= new Finance();
            //生成uuid
            String uuid = UUIDUtil.creatUUID();
            finance.setId(uuid);
            finance.setCreateTime(new Date());
            BeanUtils.copyProperties(financeSaveInVO, finance);
            //保存操作
            this.financeDao.save(finance);
            //给返回值赋值
            resBean.setContent(true);
            resBean.setMsg("保存成功");
            resBean.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        return resBean;
    }

    @Override
    public Result<Boolean> updateFinance(FinanceUpdateInVO financeUpdateInVO) {
        log.debug("Enter updateFinance financeUpdateInVO={}", financeUpdateInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        Finance finance= new Finance();
        BeanUtils.copyProperties(financeUpdateInVO, finance);
        try {
            finance.setUpdateTime(new Date());
            //修改操作
            this.financeDao.update(finance);
            //给返回值赋值
            resBean.setContent(true);
            resBean.setMsg("修改成功");
            resBean.setCode(ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("Find Exception", e);
        }
        return resBean;
    }

    @Override
    public Result<Map<String, Object>> financeInfoList(FinanceInfoInVO financeInfoInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.debug("Enter financeInfoList financeInfoInVO={}",financeInfoInVO);
        List<Finance> grades = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        Finance finance= new Finance();
        BeanUtils.copyProperties(financeInfoInVO, finance);
        int totalSize=0;
        try {
            grades = this.financeDao.selectList(finance, "pageFinanceInfo");
            totalSize = this.financeDao.selectCount(finance,"pageFinanceCount");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",grades);
            resultMap.put("totalSize",totalSize);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get financeInfoList size={}",grades!=null?grades.size() : "");
        return res;
    }


}
