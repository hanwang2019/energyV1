package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Model.Log;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Repository.LogRepository;
import cn.edu.shu.energy.Repository.UserRepository;
import cn.edu.shu.energy.Util.NetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    NetResult netResult;

    /**
     * 添加或编辑用户
     *
     * @param username
     * @param password
     * @param name
     * @param department
     * @return
     */
    @RequestMapping(value = "/api/user/edit", method = RequestMethod.POST)
    public NetResult addUser(@RequestParam(required = false, defaultValue = "") String id, @RequestParam String username, @RequestParam(required = false, defaultValue = "") String password, @RequestParam String name, @RequestParam(required = false, defaultValue = "") String department, HttpSession session) {
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        User dbUser = userRepository.findUserById(id);
        if (dbUser == null) {
            dbUser = userRepository.findByUsername(username);
            if (dbUser == null) {
                User newUser = new User(username, password, name, department);
                userRepository.save(newUser);
                logRepository.save(new Log("创建用户:" + newUser.getName(), currentUser));
                netResult.status = 0;
                netResult.result = "创建成功";
            } else {
                netResult.status = 1;
                netResult.result = "用户名已存在";
            }
        } else {
            if (!password.equals("")) {
                dbUser.setPassword(User.encryptPassword(password));
            }
            dbUser.setName(name);
            dbUser.setUsername(username);
            dbUser.setDepartment(department);
            userRepository.save(dbUser);
            if (dbUser == currentUser) {
                session.setAttribute(User.CURRENT_USER, dbUser);
            }
            logRepository.save(new Log("更新用户:" + dbUser.getName(), currentUser));
            netResult.status = 0;
            netResult.result = "更新成功";
        }
        return netResult;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/api/user/findAll", method = RequestMethod.POST)
    public NetResult findAllUser() {
        netResult.result = userRepository.findAll();
        netResult.status = 0;
        return netResult;
    }

    /**
     * 通过ID删除用户
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/api/user/delete", method = RequestMethod.POST)
    public NetResult deleteUser(@RequestParam String uid, HttpSession session) {
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        User dbUser = userRepository.findUserById(uid);
        if (dbUser == null) {
            netResult.status = 1;
            netResult.result = "无效的用户ID";
        } else {
            if (dbUser == currentUser) {
                netResult.status = 1;
                netResult.result = "禁止删除当前用户";
            } else {
                logRepository.save(new Log("删除用户:" + dbUser.getName(), currentUser));
                userRepository.delete(dbUser);
                netResult.status = 0;
                netResult.result = "删除成功";
            }
        }
        return netResult;
    }

    /**
     * 用户登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public NetResult login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User dbUser = userRepository.findByUsername(username);
        if (dbUser == null) {
            netResult.status = 1;
            netResult.result = "用户不存在或密码错误";
        } else {
            if (dbUser.isPasswordCorrect(password)) {
                session.setAttribute(User.CURRENT_USER, dbUser);
                logRepository.save(new Log("登录系统", dbUser));
                dbUser.setLastLogin(new Date());
                userRepository.save(dbUser);
                netResult.status = 0;
                netResult.result = "";
            } else {
                netResult.status = 1;
                netResult.result = "用户不存在或密码错误";
            }
        }
        return netResult;
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public NetResult logout(HttpSession session) {
        session.removeAttribute(User.CURRENT_USER);
        netResult.status = 0;
        netResult.result = "";
        return netResult;
    }
}
