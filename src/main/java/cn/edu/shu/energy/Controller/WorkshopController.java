package cn.edu.shu.energy.Controller;


import cn.edu.shu.energy.Model.Log;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Model.Workshop;
import cn.edu.shu.energy.Repository.LogRepository;
import cn.edu.shu.energy.Repository.WorkshopRepository;
import cn.edu.shu.energy.Util.NetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class WorkshopController {

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    NetResult netResult;

    @Autowired
    LogRepository logRepository;

    /**
     * 添加或删除车间
     *
     * @param id
     * @param name
     * @param description
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/workshop/edit", method = RequestMethod.POST)
    public NetResult addWorkshop(@RequestParam(required = false, defaultValue = "") String id, @RequestParam String name, @RequestParam String description, HttpSession session) {
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        Workshop dbWorkshop = workshopRepository.findWorkshopById(id);
        if (dbWorkshop == null) {
            Workshop newWorkshop = new Workshop(name, description);
            logRepository.save(new Log("创建车间：" + newWorkshop.getName(), currentUser));
            workshopRepository.save(newWorkshop);
            netResult.status = 0;
            netResult.result = "创建成功";
        } else {
            dbWorkshop.setName(name);
            dbWorkshop.setDescription(description);
            logRepository.save(new Log("更新车间：" + dbWorkshop.getName(), currentUser));
            workshopRepository.save(dbWorkshop);
            netResult.status = 0;
            netResult.result = "更新成功";
        }
        return netResult;
    }

    /**
     * 查找所有车间
     *
     * @return
     */
    @RequestMapping(value = "/api/workshop/findAll", method = RequestMethod.POST)
    public NetResult findAllWorkshop() {
        netResult.result = workshopRepository.findAll();
        netResult.status = 0;
        return netResult;
    }

    /**
     * 通过车间ID删除车间
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/workshop/delete", method = RequestMethod.POST)
    public NetResult deleteWorkshop(@RequestParam String id, HttpSession session) {
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        Workshop dbWorkshop = workshopRepository.findWorkshopById(id);
        if (dbWorkshop == null) {
            netResult.status = 1;
            netResult.result = "无效的车间号";
        } else {
            logRepository.save(new Log("删除车间：" + dbWorkshop.getName(), currentUser));
            workshopRepository.delete(dbWorkshop);
            netResult.status = 0;
            netResult.result = "更新成功";
        }
        return netResult;
    }
}
