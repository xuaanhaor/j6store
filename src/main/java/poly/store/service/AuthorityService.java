package poly.store.service;

import org.springframework.stereotype.Service;
import poly.store.entity.Authority;

import java.util.List;

@Service

public interface AuthorityService {
    List<Authority> findAuthoritiesOfAdministrators();

    List<Authority> findAll();

    public void delete(Integer id);

    public Authority create(Authority auth);
}
