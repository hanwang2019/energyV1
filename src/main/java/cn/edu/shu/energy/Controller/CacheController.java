package cn.edu.shu.energy.Controller;

import cn.edu.shu.energy.Model.Cache;
import cn.edu.shu.energy.Repository.CacheRepository;
import cn.edu.shu.energy.Repository.DeviceRepository;
import cn.edu.shu.energy.Util.NetResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CacheController {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    NetResult netResult;

    /**
     * 将网关传送的数据写入缓存表   测试
     *
     * @param object
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public NetResult receiveData(@RequestBody JSONObject object) {
        System.out.println(object.toJSONString());
        try {
            Map<String, Cache> map = decodeString(object);
            for (Cache cache : map.values()) {
                cacheRepository.save(cache);
//                System.out.println(cache);
            }
            netResult.result = "";
            netResult.status = 0;
        } catch (Exception e) {
            netResult.result = "无效的设备识别符";
            netResult.status = 1;
        }
        return netResult;
    }

    @RequestMapping(value = "/api/cache/getLastTen")
    public NetResult getLastTenCache(@RequestParam String deviceId) {
        List<Map> maps = cacheRepository.findLastTenCacheByDeviceId(deviceId);
        netResult.result = maps;
        netResult.status = 0;
        return netResult;
    }

    /**
     * 获取某台设备最新的数据
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/api/cache/getLastCacheByDeviceId")
    public NetResult getLastCacheByDeviceId(@RequestParam String deviceId) {
        List<Map> maps = cacheRepository.findLastCacheByDeviceId(deviceId);
        if (maps.size()==0) {
            netResult.result = "无效的设备号";
            netResult.status = 1;
            System.out.println("wuxiao");
        } else {
            netResult.result = maps;
            netResult.status = 0;
            System.out.println("有效");
        }
        return netResult;
    }


    public NetResult getAverage() {
        netResult.result= "";
        netResult.status = 0;
        return netResult;
    }


    /**
     * 通过智能网关上传的JSON对象解析得到Cache对象Map集合
     *
     * @param object
     * @return
     */
    private Map<String, Cache> decodeString(JSONObject object) {
        Map<String, Cache> map = new HashMap<String, Cache>();
        //JSONObject提取JSONArray
        JSONArray values = JSON.parseObject(object.toJSONString()).getJSONArray("rts");
        //遍历JSONArray
        for (int i = 0; i < values.size(); i++) {
            //JSONArray提取JSONObject
            JSONObject jsonObject = JSON.parseObject(values.get(i).toString());
            //通过tnm获取设备字符串
            String cacheString = jsonObject.getString("tnm");
            //通过val获取记录值
            String cacheValue = jsonObject.getString("val");
            //通过tss获取记录时间
            String cacheTime = jsonObject.getString("tss");

            //获取通道名称
            String channel = cacheString.split("_")[0];
            //获取设备名称
            String deviceName = cacheString.split("_")[1];
            //获取记录值的名称
            String valueName = cacheString.split("_")[2];

            //生成Map键名
            String mapKey = channel + "_" + deviceName;
            //判断Map中是否存在该键名
            if (map.containsKey(mapKey)) {
                //获取该键名对应缓存对象
                Cache cache = map.get(mapKey);
                //向缓存中添加值
                cache.addValue(valueName, cacheValue);
                //将缓存对象存回Map中
                map.put(mapKey, cache);
            } else {
                //新建缓存对象
                Cache cache = new Cache(new Date(Long.parseLong(cacheTime) * 1000));
                //设定缓存所属设备
                cache.setDevice(deviceRepository.findDeviceByChannelAndDeviceName(channel, deviceName));
                //向缓存中添加值
                cache.addValue(valueName, cacheValue);
                //将缓存对象存入Map中
                map.put(mapKey, cache);
            }
        }
        return map;
    }
}
