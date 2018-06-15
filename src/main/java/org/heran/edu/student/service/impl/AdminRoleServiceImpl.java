package org.heran.edu.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.heran.edu.student.dao.TbAdminRoleDao;
import org.heran.edu.student.domain.TbAdminRole;
import org.heran.edu.student.service.AdminRoleService;
import org.heran.edu.student.util.data.Result;
import org.heran.edu.student.util.data.ResultCode;
import org.heran.edu.student.vo.AdminRoleAddVo;
import org.heran.edu.student.vo.AdminRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YaphetS on 2018/6/12.
 */
@Service
@Slf4j
@Transactional
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private TbAdminRoleDao adminRoleDao;


    @Override
    public Result<Map<String, Object>> selectAdminRolePage(AdminRoleVo adminRoleVo) {
        Result<Map<String, Object>> result = new Result<Map<String, Object>>(ResultCode.ERROR_DATA, "查询失败", null);
        Map<String, Object> resultMap = new HashedMap();
        TbAdminRole adminRole = new TbAdminRole();
        BeanUtils.copyProperties(adminRoleVo, adminRole);
        adminRole.setIsDel("0");
        try {
            int i = this.adminRoleDao.selectCount(adminRole, "countDao");
            List<TbAdminRole> list = this.adminRoleDao.selectList(adminRole, "pageDao");
            resultMap = new HashMap<String, Object>();
            resultMap.put("rows", list);
            resultMap.put("totalSize", i);
            result.setCode(ResultCode.SUCCESS);
            result.setContent(resultMap);
            result.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result<Boolean> addAdminRole(AdminRoleAddVo adminRoleAddVo) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "保存失败", false);
        TbAdminRole adminRole = new TbAdminRole();
        BeanUtils.copyProperties(adminRoleAddVo, adminRole);
        try{
            adminRole.setCreatetime(new Date());
            adminRole.setIsDel("0");
            this.adminRoleDao.save(adminRole);
            resBean.setContent(true);
            resBean.setMsg("保存成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> updateAdminRole(AdminRoleAddVo adminRoleAddVo) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "修改失败", false);
        TbAdminRole adminRole = new TbAdminRole();
        BeanUtils.copyProperties(adminRoleAddVo, adminRole);
        try{
            this.adminRoleDao.update(adminRole);
            resBean.setContent(true);
            resBean.setMsg("修改成功");
            resBean.setCode(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }

    @Override
    public Result<Boolean> deleteAdminRole(String ids) {
        Result<Boolean> resBean = new Result<Boolean>(ResultCode.ERROR_SERVICE, "删除失败", false);
        String[] split = ids.split(",");
        try{
            for(String id:split){
                TbAdminRole adminRole = new TbAdminRole();
                adminRole.setId(Long.valueOf(id));
                adminRole.setIsDel("1");
                this.adminRoleDao.update(adminRole);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resBean;
    }
}
