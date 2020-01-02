package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, String> {
    @Query(value = "SELECT * FROM log ORDER BY operate_time DESC LIMIT 20", nativeQuery = true)
    List<Log> findLastTwentyLog();

    @Query(value = "SELECT * FROM log ORDER BY operate_time DESC LIMIT ?1,?2", nativeQuery = true)
    List<Log> findByPageId(int startPage, int stopPage);

    @Query(value = "SELECT COUNT(*) FROM log", nativeQuery = true)
    int countLogById();
}
