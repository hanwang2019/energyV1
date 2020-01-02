package cn.edu.shu.energy.Controller;


import cn.edu.shu.energy.Model.District;
import cn.edu.shu.energy.Model.Line;
import cn.edu.shu.energy.Model.Log;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Repository.DistrictRepository;
import cn.edu.shu.energy.Repository.LineRepository;
import cn.edu.shu.energy.Repository.LogRepository;
import cn.edu.shu.energy.Util.NetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LineController {
    @Autowired
    LineRepository lineRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    NetResult netResult;

    /**
     * 添加或更新产线
     *
     * @param id
     * @param name
     * @param description
     * @param districtId
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/line/edit", method = RequestMethod.POST)
    public NetResult editLine(@RequestParam(required = false, defaultValue = "") String id, @RequestParam String name, @RequestParam String description, @RequestParam String districtId, HttpSession session) {
        Line dbLine = lineRepository.findLineById(id);
        District dbDistrict = districtRepository.findDistinctById(districtId);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbDistrict == null) {
            netResult.status = 1;
            netResult.result = "无效的产区号";
        } else {
            if (dbLine == null) {
                Line newLine = new Line(name, description);
                newLine.setDistrict(dbDistrict);
                logRepository.save(new Log("创建产线:" + newLine.getName(), currentUser));
                lineRepository.save(newLine);
                netResult.status = 0;
                netResult.result = "创建成功";
            } else {
                dbLine.setName(name);
                dbLine.setDescription(description);
                dbLine.setDistrict(dbDistrict);
                logRepository.save(new Log("更新产线:" + dbLine.getName(), currentUser));
                lineRepository.save(dbLine);
                netResult.status = 0;
                netResult.result = "更新成功";
            }
        }
        return netResult;
    }

    /**
     * 查询所有产线
     *
     * @return
     */
    @RequestMapping(value = "/api/line/findAll", method = RequestMethod.POST)
    public NetResult findAllLine() {
        netResult.result = lineRepository.findAll();
        netResult.status = 0;
        return netResult;
    }

    /**
     * 根据ID删除产线
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/line/delete", method = RequestMethod.POST)
    public NetResult delete(@RequestParam String id, HttpSession session) {
        Line dbLine = lineRepository.findLineById(id);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbLine == null) {
            netResult.status = 1;
            netResult.result = "无效的产线号";
        } else {
            logRepository.save(new Log("删除产线:" + dbLine.getName(), currentUser));
            lineRepository.delete(dbLine);
            netResult.status = 0;
            netResult.result = "删除成功";
        }
        return netResult;
    }
}
