package poly.store.service;

import org.springframework.stereotype.Service;
import poly.store.entity.Account;

import java.util.List;

@Service
public interface AccountService {
    Account findById(String username);

    List<Account> getAdministrators();

    List<Account> findAll();
}
