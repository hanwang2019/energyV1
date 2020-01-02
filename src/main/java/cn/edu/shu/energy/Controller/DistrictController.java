package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Model.District;
import cn.edu.shu.energy.Model.Log;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Model.Workshop;
import cn.edu.shu.energy.Repository.DistrictRepository;
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
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    NetResult netResult;

    /**
     * 添加或更新产区
     *
     * @param id
     * @param name
     * @param description
     * @param workshopId
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/district/edit", method = RequestMethod.POST)
    public NetResult editDistrict(@RequestParam(required = false, defaultValue = "") String id, @RequestParam String name, @RequestParam String description, @RequestParam String workshopId, HttpSession session) {
        Workshop dbWorkshop = workshopRepository.findWorkshopById(workshopId);
        District dbDistrict = districtRepository.findDistinctById(id);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbWorkshop == null) {
            netResult.status = 1;
            netResult.result = "无效的产区号";
        } else {
            if (dbDistrict == null) {
                District newDistrict = new District(name, description);
                newDistrict.setWorkshop(dbWorkshop);
                logRepository.save(new Log("创建产区:" + newDistrict.getName(), currentUser));
                districtRepository.save(newDistrict);
                netResult.status = 0;
                netResult.result = "创建成功";
            } else {
                dbDistrict.setName(name);
                dbDistrict.setDescription(description);
                dbDistrict.setWorkshop(dbWorkshop);
                logRepository.save(new Log("更新产区:" + dbDistrict.getName(), currentUser));
                districtRepository.save(dbDistrict);
                netResult.status = 0;
                netResult.result = "更新成功";
            }
        }
        return netResult;
    }

    /**
     * 查询所有产区
     *
     * @return
     */
    @RequestMapping(value = "/api/district/findAll", method = RequestMethod.POST)
    public NetResult findAllDistrict() {
        netResult.result = districtRepository.findAll();
        netResult.status = 0;
        return netResult;
    }

    /**
     * 通过ID删除产区
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/district/delete", method = RequestMethod.POST)
    public NetResult deleteDistrict(@RequestParam String id, HttpSession session) {
        District dbDistrict = districtRepository.findDistinctById(id);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbDistrict == null) {
            netResult.status = 1;
            netResult.result = "无效的产区号";
        } else {
            logRepository.save(new Log("删除产区:" + dbDistrict.getName(), currentUser));
            districtRepository.delete(dbDistrict);
            netResult.status = 0;
            netResult.result = "删除成功";
        }
        return netResult;
    }
}
