package cn.edu.shu.energy.Repository;

import cn.edu.shu.energy.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findUserById(String id);

    List<User> findAll();

}
