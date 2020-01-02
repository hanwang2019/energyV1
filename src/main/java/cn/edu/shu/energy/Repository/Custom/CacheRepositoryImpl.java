package cn.edu.shu.energy.Repository.Custom;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class CacheRepositoryImpl implements CacheRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 查询最近十条记录
     *
     * @return
     */
    public List<Map> findLastTenCacheByDeviceId(String deviceId) {
        String sql = "SELECT r.id,r.voltage_a,r.voltage_b,r.voltage_c,r.current_a,r.current_b,r.current_c,r.power,r.energy,r.time FROM  `cache` c  " +
                "LEFT JOIN record r on c.id=r.id " +
                "WHERE c.type=0 AND r.device_id=:deviceId " +
                "ORDER BY time DESC " +
                "LIMIT 90";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("deviceId", deviceId);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public List<Map> findLastCacheByDeviceId(String deviceId) {
        String sql = "SELECT r.voltage_a,r.voltage_b,r.voltage_c,r.current_a,r.current_b,r.current_c,r.power,r.energy,r.time FROM  `cache` c  " +
                "LEFT JOIN record r on c.id=r.id " +
                "WHERE c.type=0 AND r.device_id=:deviceId " +
                "ORDER BY time DESC " +
                "LIMIT 1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("deviceId", deviceId);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public List<Map> getAverageValueFromUnsavedCache() {
        String sql = "SELECT r.device_id AS deviceId,avg(r.voltage_a) AS VoltageA,avg(r.voltage_b) AS VoltageB,avg(r.voltage_c) AS VoltageC,avg(r.current_a) AS CurrentA,avg(r.current_b) as CurrentB,avg(r.current_c) AS CurrentC,avg(r.power) AS Power,MAX(r.power_usage) AS PowerUsage " +
                "FROM  `cache` c LEFT JOIN record r on c.id=r.id WHERE c.type=0 GROUP BY r.device_id";
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
}
