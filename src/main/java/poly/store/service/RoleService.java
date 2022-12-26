package poly.store.service;

import org.springframework.stereotype.Service;
import poly.store.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();
}
