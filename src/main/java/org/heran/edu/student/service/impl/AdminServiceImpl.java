package org.heran.edu.student.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.TbAdminDao;
import org.heran.edu.student.domain.TbAdmin;
import org.heran.edu.student.service.AdminService;
import org.heran.edu.student.service.CacheService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.util.dispose.Md5Utils;
import org.heran.edu.student.vo.AdminAddVo;
import org.heran.edu.student.vo.AdminLoginVo;
import org.heran.edu.student.vo.AdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YaphetS on 2018/5/21.
 */
@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbAdminDao tbAdminDao;

    @Autowired
    private CacheService cacheService;

    public static final int CACHE_ADMIN_EXP_HOUR = 3600 * 24;

    @Override
    public Result<Map<String, Object>> selectAdminPage(AdminVo adminVo) {
        Result<Map<String, Object>> result = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"查询失败",null);
        Map<String, Object> resultMap = new HashedMap();
        TbAdmin admin = new TbAdmin();
        BeanUtils.copyProperties(adminVo, admin);
        try {
            int  i =this.tbAdminDao.selectCount(admin,"countDao");
            List<TbAdmin> list = this.tbAdminDao.selectList(admin,"pageDao");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows",list);
            resultMap.put("totalSize",i);
            result.setCode(ResultCode.SUCCESS);
            result.setContent(resultMap);
            result.setMsg("查询成功");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result<Boolean> addAdmin(AdminAddVo adminAddVo) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        TbAdmin admin = new TbAdmin();
        BeanUtils.copyProperties(adminAddVo, admin);
        try{
            admin.setCreateTime(new Date());
            admin.setUpdateTime(new Date());
            admin.setStatus("0");
            admin.setLoginPwd(Md5Utils.MD5Encode("888888",null));
            this.tbAdminDao.save(admin);
            resBean.setContent(true);
            resBean.setMsg("保存成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> updateAdmin(AdminAddVo adminAddVo) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        TbAdmin admin = new TbAdmin();
        BeanUtils.copyProperties(adminAddVo, admin);
        try{
            admin.setUpdateTime(new Date());
            admin.setStatus("0");
            this.tbAdminDao.update(admin,"updateDao");
            resBean.setContent(true);
            resBean.setMsg("修改成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> deleteAdmin(String ids) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "删除失败", false);
        String[] split = ids.split(",");
        try{
            for(String id:split){
                TbAdmin admin = new TbAdmin();
                admin.setAdminId(Long.valueOf(id));
                admin.setStatus("1");
                admin.setUpdateTime(new Date());
                this.tbAdminDao.update(admin,"updateDao");
            }
            resBean.setContent(true);
            resBean.setMsg("删除成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> resetPassWord(String ids) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "重置出错了", false);
        String[] split = ids.split(",");
        try{
            for(String id:split){
                TbAdmin admin = new TbAdmin();
                admin.setAdminId(Long.valueOf(id));
                admin.setUpdateTime(new Date());
                admin.setLoginPwd(Md5Utils.MD5Encode("888888", null));
                this.tbAdminDao.update(admin,"updateDao");
            }
            resBean.setContent(true);
            resBean.setMsg("密码重置成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Map<String, Object>> loginAdmin(AdminLoginVo adminLoginVo) {
        Result<Map<String, Object>> resBean = new Result<Map<String, Object>>(ResultCode.ERROR_DATA,"登录出错了",null);
        TbAdmin admin = new TbAdmin();
        BeanUtils.copyProperties(adminLoginVo, admin);
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            TbAdmin admins= this.tbAdminDao.selectOne(admin,"findAdminByEmail");
            if(admins == null){
                resBean.setMsg("用户不存在!");
                return resBean;
            }
            cacheService.remove("login_admin"+admins.getAdminId());
            String s = Md5Utils.MD5Encode(admin.getLoginPwd(), null);
            if(!s.equals(admins.getLoginPwd())){
                resBean.setMsg("密码不正确!");
                return resBean;
            }
            if(!admins.getStatus().equals("0")){
                resBean.setMsg("用户已被禁用或删除!");
                return resBean;
            }
            cacheService.set("login_admin"+admins.getAdminId(), JSON.toJSONString(admins),CACHE_ADMIN_EXP_HOUR);
            map.put("admin",admins);
            resBean.setContent(map);
            resBean.setCode(ResultCode.SUCCESS);
            resBean.setMsg("登录成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> loginOffAdmin(Long id) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "登出错误", false);
        try{
            cacheService.remove("login_admin"+id);
            resBean.setContent(true);
            resBean.setMsg("登出成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }


}
