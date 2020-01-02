package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, String> {
    District findDistinctById(String id);

    List<District> findAll();
}
