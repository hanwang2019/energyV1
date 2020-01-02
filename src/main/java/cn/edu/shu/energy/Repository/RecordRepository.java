package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, String> {

}
