package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.ExamManagerDao;
import org.heran.edu.student.dao.GradeDao;
import org.heran.edu.student.dao.StuInfoDao;
import org.heran.edu.student.domain.ExamManager;
import org.heran.edu.student.domain.Grade;
import org.heran.edu.student.domain.StuInfo;
import org.heran.edu.student.service.ExamManagerService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.CodeUtil;
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
class ExamManagerServiceImpl implements ExamManagerService {

    @Autowired
    private ExamManagerDao examManagerDao;
    @Autowired
    private StuInfoDao stuInfoDao;
    @Autowired
    private GradeDao gradeDao;


    @Override
    public Result<Boolean> add(ExamMangerSaveInVO examMangerSaveInVO) {
        log.debug("Enter addExam examMangerSaveInVO={}", examMangerSaveInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        ExamManager examManager=new ExamManager();
        BeanUtils.copyProperties(examMangerSaveInVO, examManager);
        try {
            if(CodeUtil.isNotNull(examMangerSaveInVO.getIdCard())){
                StuInfo stuInfo=new StuInfo();
                List<StuInfo> stuInfos = stuInfoDao.selectList(stuInfo, "getStuInObject");
                if(stuInfos.size()>0){
                    examManager.setStuId(stuInfos.get(0).getId());
                }
            }
            //生成uuid
            String uuid = UUIDUtil.creatUUID();
            examManager.setId(uuid);
            examManager.setCreateTime(new Date());
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            examManager.setTestNumber(sdf.format(d)+examManager.getStuRecord());
            //保存操作
            this.examManagerDao.save(examManager);
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
    public Result<Boolean> update(ExamMangerUpdateInVO examMangerUpdateInVO) {
        log.debug("Enter updateExam examMangerUpdateInVO={}", examMangerUpdateInVO);
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        ExamManager examManager=new ExamManager();
        BeanUtils.copyProperties(examMangerUpdateInVO, examManager);
        try {
            examManager.setUpdateTime(new Date());
            //修改操作
            this.examManagerDao.update(examManager);
            //修改成绩管理中的学生信息
            Grade grade=new Grade();
            grade.setExamManagerId(examManager.getId());
            grade.setName(examManager.getName());
            grade.setIdCard(examManager.getIdCard());
            grade.setUpdateTime(new Date());
            //修改操作
            this.gradeDao.update(grade,"updateByEid");
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
    public Result<Map<String, Object>> ifIdCard(String idCard) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"该学生是老生",null);
        Map<String, Object> resultMap = new HashedMap();
        ExamManager examManager=new ExamManager();
        examManager.setIdCard(idCard);
        try {
            List<ExamManager> infoByIdCard = this.examManagerDao.selectList(examManager, "infoByIdCard");
            if(CodeUtil.isNull(infoByIdCard)){
                res.setCode(ResultCode.SUCCESS);
                res.setContent(null);
                res.setMsg("该学生是新生");
            }
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        return res;
    }

    @Override
    public Result<Map<String, Object>> examInfoList(ExamMangerInfoInVO examMangerInfoInVO) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        log.debug("Enter examInfoList examMangerInfoInVO={}",examMangerInfoInVO);
        List<ExamManager> examManagerList = new ArrayList<>();
        Map<String, Object> resultMap = new HashedMap();
        ExamManager examManager= new ExamManager();
        BeanUtils.copyProperties(examMangerInfoInVO, examManager);
        int totalSize=0;
        try {
            examManagerList = this.examManagerDao.selectList(examManager, "pageExamInfo");
            totalSize = this.examManagerDao.selectCount(examManager,"pageExamInfoCount");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",examManagerList);
            resultMap.put("totalSize",totalSize);
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        res.setCode(ResultCode.SUCCESS);
        res.setContent(resultMap);
        res.setMsg("查询成功");
        log.debug("Get examManagerList size={}",examManagerList!=null?examManagerList.size() : "");
        return res;
    }

    @Override
    public Result<Map<String, Object>> detail(String id) {
        Result<Map<String, Object>> res = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        Map<String, Object> resultMap = new HashedMap();
        ExamManager examManager=new ExamManager();
        examManager.setId(id);
        try {
            ExamManager examManager1 = this.examManagerDao.selectOne(examManager);
            ExamMangerDetailOutVO examMangerDetailOutVO=new ExamMangerDetailOutVO();
            BeanUtils.copyProperties(examManager1,examMangerDetailOutVO);
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",examMangerDetailOutVO);
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
        log.debug("Enter del knowid={}",ids);
        boolean res = false;
        try {
            for(int i=0;i<ids.length;i++){
                ExamManager examManager=new ExamManager();
                examManager.setId(ids[i]);
                this.examManagerDao.delete(examManager);
            }
            res=true;
        }catch (Exception e){
            log.error("Find Exception", e);
        }
        log.debug("Leave del res={}",res);
        return res;
    }



}
