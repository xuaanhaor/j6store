package poly.store.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.store.dao.RoleDAO;
import poly.store.entity.Role;
import poly.store.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO rdao;
    @Override
    public List<Role> findAll() {
        return rdao.findAll();
    }
}
