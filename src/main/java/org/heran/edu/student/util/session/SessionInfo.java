package org.heran.edu.student.util.session;

/**
 * Created by mazh3 on 2017/9/29.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 所有的微服务中的session对象信息
 */
public interface SessionInfo {

    /**
     * 从session对象中获取登录用户的相关信息
     * @param request
     * @return
     */
    default User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User)session.getAttribute(CommonConstants.SESSION_USER_KEY);
        }
        return null;
    }

}
