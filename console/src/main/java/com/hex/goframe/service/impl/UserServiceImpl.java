//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hex.goframe.service.impl;

import com.alibaba.fastjson.JSON;
import com.hex.bigdata.udsp.common.service.FtpUserManagerService;
import com.hex.goframe.dao.GFOrgMapper;
import com.hex.goframe.dao.GFRoleMapper;
import com.hex.goframe.dao.GFUserLogMapper;
import com.hex.goframe.dao.GFUserMapper;
import com.hex.goframe.dao.GFUserSessionMapper;
import com.hex.goframe.framework.LoginEvent;
import com.hex.goframe.model.GFLog;
import com.hex.goframe.model.GFLoginUser;
import com.hex.goframe.model.GFOrg;
import com.hex.goframe.model.GFRole;
import com.hex.goframe.model.GFTenant;
import com.hex.goframe.model.GFUser;
import com.hex.goframe.model.GFUserLog;
import com.hex.goframe.model.GFUserSession;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.OrgTreeNode;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.service.FuncService;
import com.hex.goframe.service.GFLogService;
import com.hex.goframe.service.GFTenantService;
import com.hex.goframe.service.UserService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import com.hex.goframe.vo.LogBodyInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

// ---------------------2018-09-13 by Junjie.M--------------------------
// 添加了FTP用户管理功能
// --------------------- END --------------------------
@Service
public class UserServiceImpl extends BaseService implements ApplicationContextAware, UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private GFUserMapper userMapper;
    @Autowired
    private GFRoleMapper roleMapper;
    @Autowired
    private GFOrgMapper orgMapper;
    @Autowired
    private GFLogService GFLogService;
    @Autowired
    private GFTenantService tenantService;
    @Autowired
    private FuncService funcService;
    @Autowired
    private GFUserSessionMapper userSessionMapper;
    private ApplicationContext applicationContext;
    @Autowired
    private GFUserLogMapper userLogMapper;
    // ---------------------2018-09-13 by Junjie.M--------------------------
    @Autowired
    private FtpUserManagerService ftpUserManagerService;
    // --------------------- END --------------------------

    @Value("${app.pwd.cannot.repeatTimes:-1}")
    private int pwdRepeatTimes;
    @Value("${app.pwd.complexity:0}")
    private String pwdComplexity;
    @Value("${app.pwd.complexityName:密码复杂度不满足要求}")
    private String pwdComplexityName;
    @Value("${app.pwd.maxErrorCount:-1}")
    private int maxErrorCount;
    private static String lw = ".*[a-z]{1,}.*";
    private static String uw = ".*[A-Z]{1,}.*";
    private static String nw = ".*\\d{1,}.*";
    private static String sw = ".*[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";

    public UserServiceImpl() {
    }

    @Override
    public List<GFUser> queryUsers(GFUser user, Page page, String authId) {
        return this.userMapper.queryUsers(user, page, authId);
    }

    @Override
    public List<GFUser> queryUsersInOrg(GFUser user, Page page, String orgId) {
        return this.userMapper.queryUsersInOrg(user, page, orgId);
    }

    @Override
    public boolean addUser(GFUser user) {

        // ---------------------2018-09-13 by Junjie.M--------------------------
        ftpUserManagerService.addConsumerFtpUser (user.getUserId (), user.getPassword ());
        // --------------------- END --------------------------

        int id = (int)this.getNextPrimaryId("GFUser");
        user.setId(String.valueOf(id));
        user.setEmpId("1");
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return this.userMapper.insert(user) == 1;
    }

    @Override
    public boolean addUserHasEmp(GFUser user) {

        // ---------------------2018-09-13 by Junjie.M--------------------------
        ftpUserManagerService.addConsumerFtpUser (user.getUserId (), user.getPassword ());
        // --------------------- END --------------------------

        int id = (int)this.getNextPrimaryId("GFUser");
        user.setId(String.valueOf(id));
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return this.userMapper.insert(user) == 1;
    }

    @Override
    public boolean updateUser(GFUser user) {
        return this.userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public boolean updateUserStatus(GFUser user, String status) {
        return this.userMapper.updateStatus(user.getId(), status) == 1;
    }

    @Override
    public GFUser getUserById(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GFUser getUserByUserId(String userId, String appId) {
        return this.userMapper.selectByUserId(userId, appId);
    }

    @Override
    public boolean checkUser(GFUser user) {
        return this.userMapper.existsUserId(user.getUserId(), user.getAppId());
    }

    @Override
    @Transactional
    public boolean deleteUsers(GFUser[] users) throws Exception {
        GFUser[] arr$ = users;
        int len$ = users.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            GFUser user = arr$[i$];

            // ---------------------2018-09-13 by Junjie.M--------------------------
            user = userMapper.selectByPrimaryKey (user.getId ());
            ftpUserManagerService.delConsumerFtpUser (user.getUserId ());
            // --------------------- END --------------------------

            this.userMapper.removeByPrimaryKey(user.getId(), "0");
        }

        return true;
    }

    @Transactional
    public boolean deleteUsersByEmpId(OrgTreeNode[] nodes) {
        OrgTreeNode[] arr$ = nodes;
        int len$ = nodes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            OrgTreeNode node = arr$[i$];
            if("emp".equals(node.getNodeType())) {

                // ---------------------2018-09-13 by Junjie.M--------------------------
                List<GFUser> list = userMapper.queryUsersByEmpId (node.getNodeId ());
                for (GFUser user : list) {
                    ftpUserManagerService.delConsumerFtpUser (user.getUserId ());
                }
                // --------------------- END --------------------------

                this.userMapper.deleteLoginUserByEmpId(node.getNodeId());
            }
        }

        return true;
    }

    @Override
    public GFLoginUser login(GFUser user, HttpServletRequest request) {
        GFLoginUser loginUser = this.login(user);
        if(null != loginUser && "1".equals(loginUser.getStatus())) {
            List orgList = this.getOrgTreeList(loginUser.getOrgid() + "");
            loginUser.setOrgList(orgList);
            loginUser.setOrgTree(this.toOrgTreeString(orgList));
            if(null != orgList && orgList.size() > 0) {
                Iterator roles = orgList.iterator();

                while(roles.hasNext()) {
                    GFOrg list = (GFOrg)roles.next();
                    if(list.getOrgid().equalsIgnoreCase(loginUser.getOrgid())) {
                        loginUser.setOrg(list);
                        break;
                    }
                }
            }

            if(!StringUtils.hasLength(loginUser.getBranchName()) && !StringUtils.hasLength(loginUser.getBranchNo())) {
                GFOrg roles1 = this.orgMapper.getBranchOrg(String.valueOf(loginUser.getOrgid()));
                if(roles1 != null) {
                    loginUser.setBranchName(roles1.getOrgname());
                    loginUser.setBranchNo(String.valueOf(roles1.getOrgid()));
                }
            }

            if(null != request.getSession()) {
                request.getSession().invalidate();
            }

            request.getSession(true).setAttribute("SESSION_USER", loginUser);
            List roles2 = this.roleMapper.getUserRoles(loginUser.getUserId());
            loginUser.setRoles(roles2);
            request.getSession().setAttribute("SESSION_USER_ROLES", this.getUserRoleIds(roles2));
            request.getSession().setAttribute("SESSION_ROLES", roles2);
            this.applicationContext.publishEvent(new LoginEvent(this.applicationContext, loginUser));
            List list1 = this.funcService.queryAuthFuncsByUserId(loginUser.getUserId());
            request.getSession().setAttribute("SESSION_AUTH_FUNCS", list1);
        }

        return loginUser;
    }

    private List<GFOrg> getOrgTreeList(String orgId) {
        return this.orgMapper.getOrgTree(orgId);
    }

    private String toOrgTreeString(List<GFOrg> orgs) {
        if(orgs != null && orgs.size() != 0) {
            StringBuffer orgTree = new StringBuffer();

            for(int i = orgs.size() - 1; i >= 0; --i) {
                orgTree.append(((GFOrg)orgs.get(i)).getOrgname());
                if(i != 0) {
                    orgTree.append("-");
                }
            }

            return orgTree.toString();
        } else {
            return null;
        }
    }

    private List<String> getUserRoleIds(List<GFRole> roles) {
        if(roles != null && roles.size() != 0) {
            ArrayList roleIds = new ArrayList();
            Iterator i$ = roles.iterator();

            while(i$.hasNext()) {
                GFRole role = (GFRole)i$.next();
                roleIds.add(role.getRoleid());
            }

            return roleIds;
        } else {
            return null;
        }
    }

    @Override
    public GFLoginUser login(GFUser user) {
        GFLoginUser guser = this.userMapper.selectFullUserByUserId(user.getUserId(), user.getAppId());
        if(guser == null) {
            return null;
        } else {
            if(!"1".equals(guser.getOrgType()) && !"3".equals(guser.getOrgType())) {
                guser.setMasterOrgId(this.userMapper.getMasterOrgId(user.getAppId(), user.getUserId()));
            } else {
                guser.setMasterOrgId(String.valueOf(guser.getOrgid()));
            }

            if(!"1".equals(guser.getStatus())) {
                return guser;
            } else {
                if(this.tenantService.isTenantEnabled()) {
                    GFTenant msg = this.tenantService.getTenantByUser(guser);
                    guser.setTenant(msg);
                }

                String msg1 = "登录";
                String passwd = user.getPassword();
                String errorCount;
                if(passwd.startsWith("md5:")) {
                    passwd = passwd.substring("md5:".length());
                } else if(passwd.startsWith("sso:")) {
                    errorCount = passwd.substring("sso:".length());
                    if(errorCount.equalsIgnoreCase(DigestUtils.md5Hex(user.getUserId()))) {
                        passwd = guser.getPassword();
                        msg1 = "SSO登录";
                    }
                } else {
                    passwd = DigestUtils.md5Hex(passwd);
                }

                if(StringUtils.hasText(guser.getPassword()) && guser.getPassword().equals(passwd)) {
                    this.insertLoginLog(guser, msg1);
                    errorCount = guser.getUserComment();
                    int num = StringUtils.isEmpty(errorCount)?1:Integer.parseInt(errorCount) + 1;
                    this.userMapper.setUserLoginNum(user.getUserId(), user.getAppId(), num + "");
                    guser.setLoginCount(num);
                    return guser;
                } else {
                    this.userMapper.setUserLoginErrNum(user.getUserId(), user.getAppId());
                    int errorCount1 = guser.getErrorCount() == null?0:guser.getErrorCount().intValue();
                    if(this.maxErrorCount > 0 && errorCount1 + 1 >= this.maxErrorCount) {
                        this.userMapper.updateStatus(user.getUserId(), "2");
                    }

                    return null;
                }
            }
        }
    }

    @Override
    public void logout(GFLoginUser user) {
        this.insertLoginLog(user, "注销");
    }

    private void insertLoginLog(GFLoginUser user, String actionType) {
        GFLog log = new GFLog();
        log.setAppId(user.getAppId());
        log.setCreateUserId(user.getUserId());
        log.setActionType(actionType);
        log.setCreateUsername(user.getUserName());
        LogBodyInfo logBodyInfo = new LogBodyInfo(Util.getClientIP(WebUtil.getRequest()), DateUtil.format(DateUtil.getCurrentTimestamp(), "yyyy-MM-dd HH:mm:ss"));
        log.setLogBody(JSON.toJSONString(logBodyInfo));
        this.GFLogService.insert(log);
    }

    @Transactional
    public boolean resetPassword(GFUser[] users) {
        GFUser[] arr$ = users;
        int len$ = users.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            GFUser user = arr$[i$];

            // ---------------------2018-09-13 by Junjie.M--------------------------
            user = userMapper.selectByPrimaryKey (user.getId ());
            user.setPassword ("000000");
            ftpUserManagerService.delConsumerFtpUser (user.getUserId ());
            ftpUserManagerService.addConsumerFtpUser (user.getUserId (), user.getPassword ());
            // --------------------- END --------------------------

            this.userMapper.resetPassword(user.getId());
        }

        return true;
    }

    @Override
    public boolean checkPassword(String id, String oldPassword) {
        return this.userMapper.checkPassword(id, oldPassword);
    }

    @Override
    @Transactional
    public boolean changePassword(String id, String newPassword) {

        // ---------------------2018-09-13 by Junjie.M--------------------------
        GFUser user = userMapper.selectByPrimaryKey (id);
        user.setPassword (newPassword);
        ftpUserManagerService.delConsumerFtpUser (user.getUserId ());
        ftpUserManagerService.addConsumerFtpUser (user.getUserId (), user.getPassword ());
        // --------------------- END --------------------------

        this.userLogMapper.saveUserLog(id, newPassword);
        this.userMapper.changePassword(id, newPassword);
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void deleteUserSessionByClientIP(String clientIP) {
        try {
            this.userSessionMapper.deleteByClientIP(clientIP);
        } catch (Exception var3) {
            logger.error("userSessionMapper deleteByClientIP exception[{}]", var3.getMessage());
        }

    }

    @Override
    public MessageResult deleteUserSessionByUserId(String userId) {
        MessageResult messageResult = new MessageResult();

        try {
            this.userSessionMapper.deleteByPrimaryKey(userId);
            messageResult.setStatus(true);
        } catch (Exception var4) {
            logger.error("userSessionMapper deleteByPrimaryKey exception", var4);
        }

        return messageResult;
    }

    @Override
    public void addUserSession(String clientIP, String userId) {
        GFUserSession userSession = new GFUserSession();
        userSession.setClientIp(clientIP);
        userSession.setUserId(userId);
        userSession.setLoginTime(DateUtil.getCurrentTimestamp());

        try {
            this.userSessionMapper.insert(userSession);
        } catch (Exception var5) {
            logger.error("userSessionMapper insert exception[{}]", var5.getMessage());
        }

    }

    @Override
    public GFUserSession getUserSession(String userId) {
        return this.userSessionMapper.selectByPrimaryKey(userId);
    }

    @Override
    public MessageResult validateUser(String loginUserId, String password) {
        MessageResult messageResult = new MessageResult();
        if(!StringUtils.isEmpty(loginUserId) && !StringUtils.isEmpty(password)) {
            GFUser user = this.userMapper.selectByUserId(loginUserId, "default");
            if(user == null) {
                return messageResult;
            } else {
                if(DigestUtils.md5Hex(password).equals(user.getPassword())) {
                    messageResult.setStatus(true);
                }

                return messageResult;
            }
        } else {
            return messageResult;
        }
    }

    @Override
    public List<GFUserLog> queryUserLogs(String logInId, String actionType) {
        List list = this.userLogMapper.selectByLoginId(logInId, actionType);
        return list;
    }

    @Override
    public boolean checkUserLog(String logInId, String actionType) {
        return this.userLogMapper.checkUserLog(logInId, actionType);
    }

    @Override
    public MessageResult checkNewPassword(String id, String newPassword) {
        MessageResult result = new MessageResult();
        result.setStatus(true);
        if(this.pwdRepeatTimes > 0) {
            Page complexityResult = new Page();
            complexityResult.setPageSize(this.pwdRepeatTimes);
            complexityResult.setPageIndex(0);
            List list = this.userLogMapper.selectByLoginId(id, "modify_pwd", complexityResult);
            if(list != null && list.size() > 0) {
                String passwordMd5 = DigestUtils.md5Hex(newPassword);

                for(int i = 0; i < list.size(); ++i) {
                    if(passwordMd5.equals(((GFUserLog)list.get(i)).getActionContent())) {
                        result.setStatus(false);
                        result.setMessage("修改后的密码不应与最近" + this.pwdRepeatTimes + "次使用的密码相同");
                        return result;
                    }
                }
            }
        }

        if(!StringUtils.isEmpty(this.pwdComplexity.trim()) && !"0".equals(this.pwdComplexity)) {
            int var8 = passwordMatch(newPassword, this.pwdComplexity.trim());
            if(var8 == 0) {
                result.setStatus(false);
                result.setMessage(this.pwdComplexityName);
                return result;
            }
        }

        return result;
    }

    public static int passwordMatch(String password, String rowStr) {
        String excuteRow = null;
        rowStr = rowStr.trim();
        if(rowStr.equals("lw")) {
            excuteRow = lw;
        } else if(rowStr.equals("uw")) {
            excuteRow = uw;
        } else if(rowStr.equals("nw")) {
            excuteRow = nw;
        } else if(rowStr.equals("sw")) {
            excuteRow = sw;
        }

        if(excuteRow != null) {
            return password.matches(excuteRow)?1:0;
        } else {
            int start = rowStr.indexOf("(");
            int end = rowStr.lastIndexOf(")");
            if(start >= 0 && end >= 0) {
                int lastComma = rowStr.lastIndexOf(",");
                if(lastComma < 0) {
                    return -1;
                } else {
                    int num = Integer.parseInt(rowStr.substring(lastComma + 1, end).trim());
                    rowStr = rowStr.substring(start + 1, lastComma);
                    List rowStrList = extractMessage(rowStr);
                    int count = 0;

                    int ret;
                    for(Iterator i$ = rowStrList.iterator(); i$.hasNext(); count += ret) {
                        String str = (String)i$.next();
                        ret = passwordMatch(password, str);
                        if(ret == -1) {
                            return -1;
                        }
                    }

                    if(count >= num) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            } else {
                return -1;
            }
        }
    }

    public static List<String> extractMessage(String msg) {
        ArrayList list = new ArrayList();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;

        for(int i = 0; i < msg.length(); ++i) {
            if(msg.charAt(i) == 91) {
                ++startFlag;
                if(startFlag == endFlag + 1) {
                    start = i;
                }
            } else if(msg.charAt(i) == 93) {
                ++endFlag;
                if(endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }

        return list;
    }

    @Override
    public MessageResult checkPasswordValidity(String id, int validityDays) {
        MessageResult result = new MessageResult();
        result.setStatus(true);
        Page page = new Page();
        page.setPageSize(10);
        page.setPageIndex(0);
        List list = this.userLogMapper.selectByLoginId(id, "modify_pwd", page);
        if(list != null && list.size() > 0) {
            GFUserLog userLog = (GFUserLog)list.get(0);
            Date lastModfiyPwdDate = userLog.getActionTime();
            String tody = DateUtil.getCurrentDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastModfiyPwdDate);
            calendar.add(5, validityDays);
            Date endDate = calendar.getTime();
            String endDateStr = DateUtil.format(endDate, "yyyyMMdd");
            if(endDateStr.compareTo(tody) <= 0) {
                result.setStatus(false);
                result.setMessage("密码已经到期");
            }
        }

        return result;
    }

    @Override
    public MessageResult unlockUser(String userId) {
        MessageResult messageResult = new MessageResult();

        try {
            this.userSessionMapper.deleteByPrimaryKey(userId);
            this.userMapper.updateStatus(userId, "1");
            messageResult.setStatus(true);
        } catch (Exception var4) {
            logger.error("userSessionMapper unlockUser exception:{}", var4);
        }

        return messageResult;
    }

    @Override
    public MessageResult stopUse(String userId) {
        MessageResult messageResult = new MessageResult();

        try {
            this.userSessionMapper.deleteByPrimaryKey(userId);
            this.userMapper.updateStatus(userId, "2");
            messageResult.setStatus(true);
        } catch (Exception var4) {
            logger.error("userSessionMapper stopUse exception:{}", var4);
        }

        return messageResult;
    }
}
