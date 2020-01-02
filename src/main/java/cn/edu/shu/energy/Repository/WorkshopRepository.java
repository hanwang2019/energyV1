package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Workshop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop,String> {

    Workshop findWorkshopById(String id);

    List<Workshop> findAll();
}
