package com.hex.goframe.controller;

import com.hex.goframe.model.*;
import com.hex.goframe.service.AuthRightService;
import com.hex.goframe.service.EmployeeService;
import com.hex.goframe.service.MenuService;
import com.hex.goframe.service.UserService;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ---------------------2018-09-13 by Junjie.M--------------------------
// 去除了同一个用户只能同时有一个登录的代码
// --------------------- END --------------------------
@Controller
@RequestMapping({"/goframe"})
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);
    private static final String IS_REMEMBER = "1";
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private AuthRightService authRightService;
    @Autowired
    private EmployeeService employeeService;
    @Value("${sso.cookie.server:}")
    private String ssoServer;
    @Value("${app.cookie.remember}")
    private String cookieRemember;

    public UserController() {
    }

    @RequestMapping({"/user/queryUsers"})
    @ResponseBody
    public Map queryUsers(GFUser user, Page page, String authId) {
        List userList = this.userService.queryUsers(user, page, authId);
        HashMap map = new HashMap();
        map.put("data", userList);
        map.put("total", Integer.valueOf(page.getTotalCount()));
        return map;
    }

    @RequestMapping({"/user/queryUsersInOrg"})
    @ResponseBody
    public Map queryUsersInOrg(GFUser user, Page page, String orgId) {
        List userList = this.userService.queryUsersInOrg(user, page, orgId);
        HashMap map = new HashMap();
        map.put("data", userList);
        map.put("total", Integer.valueOf(page.getTotalCount()));
        return map;
    }

    @RequestMapping(
        value = {"/user/login"},
        method = {RequestMethod.POST}
    )
    @ResponseBody
    public MessageResult login(@RequestBody GFUser user, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.hasText(user.getUserId()) && StringUtils.hasText(user.getPassword())) {
            GFUserSession userSession = this.userService.getUserSession(user.getUserId());
            if(userSession != null) {
                // ---------------------2018-09-13 by Junjie.M--------------------------
//                if(!DateUtil.isAfterThirtyMinutes(userSession.getLoginTime())) {
//                    String loginUser1 = "用户[" + user.getUserId() + "]已在" + userSession.getClientIp() + "登录";
//                    return new MessageResult(false, loginUser1);
//                }
//
//                this.userService.deleteUserSessionByUserId(userSession.getUserId());
                // --------------------- END --------------------------
            }

            if(!StringUtils.hasLength(user.getAppId())) {
                user.setAppId("default");
            }

            GFLoginUser loginUser = this.userService.login(user, request);
            if(loginUser != null) {
                this.userService.deleteUserSessionByUserId(user.getUserId());
                this.userService.addUserSession(Util.getClientIP(request), user.getUserId());
                HashMap map = new HashMap();
                map.put("sid", request.getSession().getId());
                map.put("userId", loginUser.getUserId());
                map.put("userName", loginUser.getUserName());
                if(StringUtils.hasText(this.ssoServer)) {
                    String authRights = String.format("uid=%s", new Object[]{loginUser.getUserId()});
                    String menuList = DigestUtils.md5Hex(loginUser.getUserId());
                    authRights = authRights + "|" + menuList;
                    Cookie buffer = new Cookie(this.ssoServer, authRights);
                    buffer.setPath("/");
                    response.addCookie(buffer);
                }

                List authRights1 = this.authRightService.queryAuths(user.getUserId(), (String)null, loginUser.getAppId());
                map.put("authRights", authRights1);
                List menuList1 = this.menuService.selectMenusByUserId(user.getUserId(), loginUser.getAppId());
                map.put("menuList", menuList1);
                StringBuffer buffer1 = new StringBuffer();
                if("1".equals(user.getIsRemember())) {
                    buffer1.append(user.getUserId());
                    buffer1.append("|");
                    buffer1.append(user.getPassword());
                } else {
                    buffer1.append("");
                }

                Cookie cookie = new Cookie(this.cookieRemember, buffer1.toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return new MessageResult(true, map);
            } else {
                return new MessageResult(false, "用户名或密码不正确!");
            }
        } else {
            return new MessageResult(false, "用户名或密码不正确!");
        }
    }

    @RequestMapping({"/user/logout"})
    @ResponseBody
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        GFLoginUser loginUser = WebUtil.getCurrentUser();
        if(null == loginUser) {
            return false;
        } else {
            this.userService.deleteUserSessionByUserId(WebUtil.getLoginUserId());
            this.userService.logout(loginUser);
            request.getSession().invalidate();
            if(StringUtils.hasText(this.ssoServer)) {
                Cookie[] arr$ = request.getCookies();
                int len$ = arr$.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    if(cookie.getName().equalsIgnoreCase(this.ssoServer)) {
                        cookie.setValue((String)null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }

            return true;
        }
    }

    @RequestMapping({"/user/addUser"})
    @ResponseBody
    public boolean addUser(@RequestBody GFUser user) {
        logger.debug("enter addEmployee");
        String empId = user.getEmpId();
        boolean success = false;
        if(!StringUtils.isEmpty(empId)) {
            success = this.addUser(user, true);
        } else {
            success = this.addUser(user, false);
        }

        return success;
    }

    public boolean addEmployee(GFEmployee employee) {
        try {
            return this.employeeService.addEmployee(employee);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public boolean addUser(GFUser user, boolean bool) {
        try {
            return bool?this.userService.addUserHasEmp(user):this.userService.addUser(user);
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    @RequestMapping({"/user/updateUser"})
    @ResponseBody
    public boolean updateUser(@RequestBody GFUser user) {
        logger.debug("enter addEmployee");

        try {
            boolean e = this.userService.updateUser(user);
            return e;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    @RequestMapping({"/user/checkUser"})
    @ResponseBody
    public boolean checkUser(@RequestBody GFUser user) {
        try {
            boolean e = this.userService.checkUser(user);
            return e;
        } catch (Exception var3) {
            return false;
        }
    }

    @RequestMapping({"/user/deleteUsers"})
    @ResponseBody
    public boolean deleteUsers(@RequestBody GFUser[] users) {
        try {
            boolean e = this.userService.deleteUsers(users);
            return e;
        } catch (Exception var3) {
            return false;
        }
    }

    @RequestMapping({"/user/deleteUsersByEmpId"})
    @ResponseBody
    public boolean deleteUsersByEmpId(@RequestBody OrgTreeNode[] nodes) {
        try {
            boolean e = this.userService.deleteUsersByEmpId(nodes);
            return e;
        } catch (Exception var3) {
            return false;
        }
    }

    @RequestMapping({"/user/getUser"})
    @ResponseBody
    public GFUser getUser(@RequestBody GFUser user, HttpServletRequest req) {
        if(user != null && StringUtils.hasText(user.getId())) {
            logger.debug("user.id" + user.getId());

            try {
                return this.userService.getUserById(user.getId());
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @RequestMapping({"/user/changePassword"})
    @ResponseBody
    public boolean changePassword(@RequestBody Map paramMap) throws Exception {
        Map user = (Map)paramMap.get("user");
        String id = (String)user.get("id");
        String newPassword = (String)user.get("password");
        return this.userService.changePassword(id, newPassword);
    }

    @RequestMapping({"/user/checkPassword"})
    @ResponseBody
    public boolean checkPassword(@RequestBody Map paramMap) {
        Map user = (Map)paramMap.get("user");
        String id = (String)user.get("id");
        String oldPassword = (String)paramMap.get("password");
        return this.userService.checkPassword(id, oldPassword);
    }

    @RequestMapping({"/user/resetPassword"})
    @ResponseBody
    public boolean resetPassword(@RequestBody GFUser[] users) {
        try {
            boolean e = this.userService.resetPassword(users);
            return e;
        } catch (Exception var3) {
            return false;
        }
    }

    @RequestMapping({"/user/unlock"})
    @ResponseBody
    public MessageResult unlock(String loginUserId) {
        return this.userService.deleteUserSessionByUserId(loginUserId);
    }

    @RequestMapping({"/user/validateUser"})
    @ResponseBody
    public MessageResult validateUser(String userId, String password) {
        return this.userService.validateUser(userId, password);
    }
}
