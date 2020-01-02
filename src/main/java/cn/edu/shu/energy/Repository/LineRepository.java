package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, String> {
    Line findLineById(String id);

    List<Line> findAll();
}
