package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Device;
import cn.edu.shu.energy.Repository.Custom.DeviceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, String>, DeviceRepositoryCustom {

    @Query(value = "SELECT * FROM device WHERE channel=?1 AND device_name=?2", nativeQuery = true)
    Device findDeviceByChannelAndDeviceName(String channel, String deviceName);

    Device findDeviceById(String id);

    List<Device> findAll();
}
