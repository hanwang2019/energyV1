package cn.edu.shu.energy.Repository.Custom;

import java.util.List;
import java.util.Map;

public interface CacheRepositoryCustom {
    List<Map> findLastTenCacheByDeviceId(String deviceId);

    List<Map> findLastCacheByDeviceId(String deviceId);

    List<Map> getAverageValueFromUnsavedCache();
}
