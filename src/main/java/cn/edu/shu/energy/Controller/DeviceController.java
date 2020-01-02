package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Model.Device;
import cn.edu.shu.energy.Model.Line;
import cn.edu.shu.energy.Model.Log;
import cn.edu.shu.energy.Model.User;
import cn.edu.shu.energy.Repository.DeviceRepository;
import cn.edu.shu.energy.Repository.LineRepository;
import cn.edu.shu.energy.Repository.LogRepository;
import cn.edu.shu.energy.Util.NetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.Net;

import javax.servlet.http.HttpSession;

@RestController
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    LineRepository lineRepository;

    @Autowired
    LogRepository logRepository;

    @Autowired
    NetResult netResult;

    /**
     * 添加或更新设备
     *
     * @param id
     * @param name
     * @param manufacture
     * @param lineId
     * @param channel
     * @param deviceName
     * @return
     */
    @RequestMapping(value = "/api/device/edit", method = RequestMethod.POST)
    public NetResult addDevice(@RequestParam(required = false, defaultValue = "") String id, @RequestParam String name, @RequestParam String manufacture, @RequestParam String lineId, @RequestParam String channel, @RequestParam String deviceName, HttpSession session) {
        Device dbDevice = deviceRepository.findDeviceById(id);
        Line dbLine = lineRepository.findLineById(lineId);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbLine == null) {
            netResult.status = 1;
            netResult.result = "无效的产线号";
        } else {
            if (dbDevice == null) {
                Device newDevice = new Device(name, manufacture, channel, deviceName);
                newDevice.setLine(dbLine);
                logRepository.save(new Log("创建设备:" + newDevice.getName(), currentUser));
                deviceRepository.save(newDevice);
                netResult.status = 0;
                netResult.result = "创建成功";
            } else {
                dbDevice.setName(name);
                dbDevice.setManufacture(manufacture);
                dbDevice.setLine(dbLine);
                dbDevice.setChannel(channel);
                dbDevice.setDeviceName(deviceName);
                logRepository.save(new Log("更新设备:" + dbDevice.getName(), currentUser));
                deviceRepository.save(dbDevice);
                netResult.status = 0;
                netResult.result = "更新成功";
            }
        }
        return netResult;
    }

    /**
     * 查询所有设备
     *
     * @return
     */
    @RequestMapping(value = "/api/device/findAll", method = RequestMethod.POST)
    public NetResult findAllDevice() {
        netResult.result = deviceRepository.findAll();
        netResult.status = 0;
        return netResult;
    }

    /**
     * 根据ID删除设备
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/api/device/delete", method = RequestMethod.POST)
    public NetResult delete(@RequestParam String id, HttpSession session) {
        Device dbDevice = deviceRepository.findDeviceById(id);
        User currentUser = (User) session.getAttribute(User.CURRENT_USER);
        if (dbDevice == null) {
            netResult.status = 1;
            netResult.result = "无效的设备号";
        } else {
            logRepository.save(new Log("删除设备:" + dbDevice.getName(), currentUser));
            deviceRepository.delete(dbDevice);
            netResult.status = 0;
            netResult.result = "删除成功";
        }
        return netResult;
    }


}
