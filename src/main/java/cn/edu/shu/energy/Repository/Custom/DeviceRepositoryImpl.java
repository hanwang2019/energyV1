package cn.edu.shu.energy.Repository.Custom;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DeviceRepositoryImpl implements DeviceRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

}
