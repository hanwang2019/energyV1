package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Repository.LogRepository;
import cn.edu.shu.energy.Util.NetResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    NetResult netResult;

    @Autowired
    LogRepository logRepository;

    @RequestMapping(value = "/api/log/getLastTwenty")
    public NetResult getLastTwentyLog() {
        netResult.result = logRepository.findLastTwentyLog();
        netResult.status = 0;
        return netResult;
    }

    @RequestMapping(value = "/api/log/getByPage")
    public NetResult getByPage(@RequestParam int pageNumber) {
        JSONObject object = new JSONObject();
        object.put("logs", logRepository.findByPageId((pageNumber - 1) * 20, pageNumber * 20));
        object.put("counts", logRepository.countLogById());
        netResult.result = object;
        netResult.status = 0;
        return netResult;
    }
}
