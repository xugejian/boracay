
package com.hex.goframe.controller;

import com.hex.goframe.dto.UserSession;
import com.hex.goframe.model.GFEmployee;
import com.hex.goframe.model.GFLoginUser;
import com.hex.goframe.model.GFUser;
import com.hex.goframe.model.GFUserSession;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.OrgTreeNode;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.AuthRightService;
import com.hex.goframe.service.EmployeeService;
import com.hex.goframe.service.FuncService;
import com.hex.goframe.service.MenuService;
import com.hex.goframe.service.UserService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.GFStringUtil;
import com.hex.goframe.util.ImgValidateCode;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import com.hex.goframe.vo.CheckLoginInfo;
import com.hex.goframe.vo.LoginInfo;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.map.HashedMap;
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
    @Autowired
    private FuncService funcService;
    @Value("${sso.cookie.server:}")
    private String ssoServer;
    @Value("${app.cookie.remember}")
    private String cookieRemember;
    private static Map<String, CheckLoginInfo> checkLogin = new HashedMap();
    @Value("${app.login.intervalTime}")
    private Long intervalTime;
    @Value("${app.login.number}")
    private Long number;
    @Value("${app.login.useImgValidateCode}")
    private String useImgValidateCode;

    public UserController() {
    }

    @RequestMapping({"/user/queryUsers"})
    @ResponseBody
    public Map queryUsers(GFUser user, Page page, String authId) {
        if(user == null) {
            user = new GFUser();
        }

        List userList = this.userService.queryUsers(user, page, authId);
        HashMap map = new HashMap();
        map.put("data", userList);
        map.put("total", Integer.valueOf(page.getTotalCount()));
        return map;
    }

    @RequestMapping({"/user/queryValidUsers"})
    @ResponseBody
    public Map queryValidUsers(GFUser user, Page page, String authId) {
        if(user == null) {
            user = new GFUser();
        }

        user.setStatus("1");
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

    private boolean isFrequentLogin(String clientIP) {
        logger.info("clientIP:{}", new Object[]{clientIP});
        Timestamp currentTime = DateUtil.getCurrentTimestamp();
        CheckLoginInfo checkLoginInfo = (CheckLoginInfo)checkLogin.get(clientIP);
        if(checkLoginInfo != null) {
            int num = checkLoginInfo.getNum() + 1;
            checkLoginInfo.setNum(num);
            if(currentTime.getTime() < checkLoginInfo.getDate().getTime() + this.intervalTime.longValue()) {
                if((long)num > this.number.longValue()) {
                    checkLoginInfo.setDate(currentTime);
                    return true;
                }

                return false;
            }
        } else {
            checkLoginInfo = new CheckLoginInfo();
        }

        checkLoginInfo.setDate(currentTime);
        checkLoginInfo.setNum(1);
        checkLogin.put(clientIP, checkLoginInfo);
        return false;
    }

    @RequestMapping(
            value = {"/user/login"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public MessageResult login(@RequestBody LoginInfo user, HttpServletRequest request, HttpServletResponse response) {
        String clientIP = Util.getClientIP(request);
        if(this.isFrequentLogin(clientIP)) {
            return new MessageResult(false, "登录太频繁，请稍后再试！");
        } else if(StringUtils.hasText(user.getUserId()) && StringUtils.hasText(user.getPassword())) {
            String imgValidateCode = (String)request.getSession().getAttribute("IMG_VALIDATE_CODE_KEY");
            if(!"1".equals(this.useImgValidateCode) || !StringUtils.isEmpty(user.getImgValidateCode()) && imgValidateCode.equalsIgnoreCase(user.getImgValidateCode())) {
                GFUserSession userSession = this.userService.getUserSession(user.getUserId());
                if(userSession != null) {
                    // ---------------------2018-09-13 by Junjie.M--------------------------
//                    if(!DateUtil.isAfterThirtyMinutes(userSession.getLoginTime())) {
//                        String loginUser1 = "用户[" + user.getUserId() + "]已在" + userSession.getClientIp() + "登录";
//                        return new MessageResult(false, loginUser1);
//                    }
//
//                    this.userService.deleteUserSessionByUserId(userSession.getUserId());
                    // --------------------- END --------------------------
                }

                if(!StringUtils.hasLength(user.getAppId())) {
                    user.setAppId("default");
                }

                GFLoginUser loginUser = this.userService.login(user, request);
                if(loginUser != null && "1".equals(loginUser.getStatus())) {
                    this.userService.deleteUserSessionByUserId(user.getUserId());
                    this.userService.addUserSession(clientIP, user.getUserId());
                    HashMap map = new HashMap();
                    map.put("sid", request.getSession().getId());
                    map.put("userId", loginUser.getUserId());
                    map.put("userName", loginUser.getUserName());
                    if(StringUtils.hasText(this.ssoServer)) {
                        String authRights = String.format("uid=%s", new Object[]{loginUser.getUserId()});
                        String menuList = DigestUtils.md5Hex(loginUser.getUserId());
                        authRights = authRights + "|" + menuList;
                        Cookie sessionUser = new Cookie(this.ssoServer, authRights);
                        sessionUser.setPath("/");
                        response.addCookie(sessionUser);
                    }

                    List authRights1 = this.authRightService.queryAuths(user.getUserId(), (String)null, loginUser.getAppId());
                    map.put("authRights", authRights1);
                    List menuList1 = this.menuService.selectMenusByUserId(user.getUserId(), loginUser.getAppId());
                    map.put("menuList", menuList1);
                    UserSession sessionUser1 = new UserSession(loginUser);
                    sessionUser1.setMenuList(menuList1);
                    request.getSession().setAttribute("GF_USER_SESSION", sessionUser1);
                    StringBuffer buffer = new StringBuffer();
                    if("1".equals(user.getIsRemember())) {
                        buffer.append(user.getUserId());
                        buffer.append("|");
                        buffer.append(user.getPassword());
                    } else {
                        buffer.append("");
                    }

                    Cookie cookie = new Cookie(this.cookieRemember, buffer.toString());
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return new MessageResult(true, map);
                } else {
                    return loginUser != null && "2".equals(loginUser.getStatus())?new MessageResult(false, "用户已锁定，请联系管理员!"):new MessageResult(false, "用户名或密码不正确!");
                }
            } else {
                return new MessageResult(false, "验证码不正确");
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
            logger.error("addEmployee exception:{}", var3);
            return false;
        }
    }

    public boolean addUser(GFUser user, boolean bool) {
        try {
            return bool?this.userService.addUserHasEmp(user):this.userService.addUser(user);
        } catch (Exception var4) {
            logger.error("addUser exception:{}", var4);
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
            logger.error("updateUser exception:{}", var3);
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
            logger.error("checkUser exception:{}", var3);
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
            logger.error("deleteUsers exception:{}", var3);
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
            logger.error("deleteUsersByEmpId exception:{}", var3);
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
                logger.error("getUser exception:{}", var4);
                return null;
            }
        } else {
            return null;
        }
    }

    @RequestMapping({"/user/changePassword"})
    @ResponseBody
    public MessageResult changePassword(@RequestBody Map paramMap) {
        MessageResult result = new MessageResult();
        result.setStatus(false);
        String oldPassword = (String)paramMap.get("password");
        Map user = (Map)paramMap.get("user");
        String userId = (String)user.get("userId");
        if(!WebUtil.getLoginUserId().equals(userId)) {
            result.setMessage("用户只能修改自己的密码");
            return result;
        } else {
            String id = (String)user.get("id");
            String newPassword = (String)user.get("password");
            newPassword = GFStringUtil.hexDecode(newPassword);
            boolean succed = this.userService.checkPassword(id, oldPassword);
            if(!succed) {
                result.setStatus(false);
                result.setMessage("原密码错误");
                return result;
            } else {
                MessageResult messageResult = this.userService.checkNewPassword(id, newPassword);
                if(!messageResult.isStatus()) {
                    return messageResult;
                } else {
                    try {
                        this.userService.changePassword(id, DigestUtils.md5Hex(newPassword));
                        result.setStatus(true);
                    } catch (Exception var11) {
                        logger.error("failed to change pwd!", var11);
                    }

                    return result;
                }
            }
        }
    }

    @RequestMapping({"/user/resetPassword"})
    @ResponseBody
    public boolean resetPassword(@RequestBody GFUser[] users) {
        try {
            boolean e = this.userService.resetPassword(users);
            return e;
        } catch (Exception var3) {
            logger.error("resetPassword exception:{}", var3);
            return false;
        }
    }

    @RequestMapping({"/user/unlock"})
    @ResponseBody
    public MessageResult unlock(String loginUserId) {
        return this.userService.unlockUser(loginUserId);
    }

    @RequestMapping({"/user/stopUse"})
    @ResponseBody
    public MessageResult stopUse(String loginUserId) {
        return this.userService.stopUse(loginUserId);
    }

    @RequestMapping({"/user/validateUser"})
    @ResponseBody
    public MessageResult validateUser(String userId, String password) {
        return this.userService.validateUser(userId, password);
    }

    @RequestMapping({"/user/replacePwd"})
    @ResponseBody
    public MessageResult replacePwd(@RequestBody GFLoginUser loginUser, HttpServletRequest request) {
        GFLoginUser user = (GFLoginUser)request.getSession().getAttribute("SESSION_USER");
        MessageResult messageResult = this.userService.checkNewPassword(user.getId(), loginUser.getPassword());
        if(!messageResult.isStatus()) {
            return messageResult;
        } else {
            try {
                boolean e = this.userService.changePassword(user.getId(), loginUser.getPassword());
                if(e) {
                    request.getSession().setAttribute("SESSION_USER", user);
                } else {
                    messageResult.setMessage("重置密码失败");
                }

                messageResult.setStatus(e);
            } catch (Exception var6) {
                logger.error("replacePwd exception:{}", var6);
                messageResult.setStatus(false);
                messageResult.setMessage("重置密码失败");
            }

            return messageResult;
        }
    }

    @RequestMapping({"/user/loginImgValidateCode"})
    public void imgValidateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0L);
        ImgValidateCode imgValidateCode = new ImgValidateCode();
        imgValidateCode.getRandcode(request, response);
    }
}
