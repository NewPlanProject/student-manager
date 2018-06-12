package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.ExamManagerDao;
import org.heran.edu.student.dao.GradeDao;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.Grade;
import org.heran.edu.student.service.GradeService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.UUIDUtil;
import org.heran.edu.student.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Mrs.Jia
 * Date: 2018/4/26
 * Time: 11:40
 */
@Service
@Slf4j
@Transactional
class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private ExamManagerDao examManagerDao;
    @Autowired
    private StuInfoDao stuInfoDao;


    @Override
    public Result<Boolean> add(List<GradeSaveInVO> gradeSaveInVOs) {
        log.debug("Enter addGrade gradeSaveInVOs={}", gradeSaveInVOs);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        try {
            for(int i=0;i<gradeSaveInVOs.size();i++){
                Grade grade=new Grade();
                //生成uuid
                String uuid = UUIDUtil.creatUUID();
                grade.setId(uuid);
                grade.setCreateTime(new Date());
                BeanUtils.copyProperties(gradeSaveInVOs.get(i), grade);
                //保存操作
                this.gradeDao.save(grade);
            }
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
    public Result<Boolean> update(GradeUpdateInVO gradeUpdateInVO) {
        log.debug("Enter updateGrade gradeUpdateInVO={}", gradeUpdateInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        Grade grade=new Grade();
        BeanUtils.copyProperties(gradeUpdateInVO, grade);
        try {
            grade.setUpdateTime(new Date());
            //修改操作
            this.gradeDao.update(grade);
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
    public Result<Map<String, Object>> gradeInfoList(GradeInfoInVO gradeInfoInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.debug("Enter gradeInfoList gradeInfoInVO={}",gradeInfoInVO);
        List<Grade> grades = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        Grade grade= new Grade();
        BeanUtils.copyProperties(gradeInfoInVO, grade);
        int totalSize=0;
        try {
            grades = this.gradeDao.selectList(grade, "pageGradeInfo");
            totalSize = this.gradeDao.selectCount(grade,"pageGradeCount");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",grades);
            resultMap.put("totalSize",totalSize);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get gradeInfoList size={}",grades!=null?grades.size() : "");
        return res;
    }


    @Override
    public Result<Map<String, Object>> detail(String id) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        Map<String, Object> resultMap = new HashedMap();
        Grade grade= new Grade();
        grade.setId(id);
        try {
            Grade grade1 = this.gradeDao.selectOne(grade);
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",grade1);
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
                Grade grade= new Grade();
                grade.setId(ids[i]);
                this.gradeDao.delete(grade);
            }
            res=true;
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        log.debug("Leave del res={}",res);
        return res;
    }



}
