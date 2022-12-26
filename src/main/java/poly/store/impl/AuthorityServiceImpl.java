package poly.store.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.store.dao.AccountDAO;
import poly.store.dao.AuthorityDAO;
import poly.store.service.AuthorityService;
import poly.store.entity.Authority;
import poly.store.entity.Account;
import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO dao;
    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Authority> findAuthoritiesOfAdministrators() {
        List<Account> accounts = accountDAO.getAdministrators();
        return dao.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public Authority create(Authority auth) {
        return dao.save(auth);
    }
}
