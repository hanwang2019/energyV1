package cn.edu.shu.energy.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.edu.shu.energy.Model.Record;
import cn.edu.shu.energy.Repository.CacheRepository;
import cn.edu.shu.energy.Repository.DeviceRepository;
import cn.edu.shu.energy.Repository.RecordRepository;
import cn.edu.shu.energy.Util.NetResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class RecordController {

    @Autowired
    NetResult netResult;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CacheRepository cacheRepository;

    /**
     * 每5分钟计算缓存平均值写入记录表
     */
//    @Async
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void updateRecord() {
//        //从数据库获取所有未计算的缓存
//        List<Map> maps = cacheRepository.getAverageValueFromUnsavedCache();
//        //遍历所有缓存
//        for (Map map : maps) {
//            //排除无效的设备
//            if (map.get("deviceId") != null) {
//                //新建均值记录
//                Record record = new Record();
//                //通过deviceId判断所属设备
//                record.setDevice(deviceRepository.findDeviceById((String) map.get("deviceId")));
//                //获取所有记录值名
//                Set<String> set = map.keySet();
//                set.remove("deviceId");
//                //遍历所有记录值
//                for (String key : set) {
//                    //判断记录值是否为null
//                    if (map.get(key) != null) {
//                        //判断记录值类型
//                        if (map.get(key).getClass() == Double.class) {
//                            record.addValue(key, (double) map.get(key));
//                        } else {
//                            record.addValue(key, (String) map.get(key));
//                        }
//                    } else {
//                        record.addValue(key, 0d);
//                    }
//                }
//                recordRepository.save(record);
//            }
//        }
//        cacheRepository.updateType();
//    }
}
