package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Cache;
import cn.edu.shu.energy.Repository.Custom.CacheRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CacheRepository extends JpaRepository<Cache, String>, CacheRepositoryCustom {
    @Transactional
    @Query(value = "UPDATE cache SET type = 1 WHERE type = 0", nativeQuery = true)
    @Modifying
    int updateType();
}
