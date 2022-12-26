package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.store.entity.Account;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, String> {
    @Query("select distinct ar.account from Authority  ar where  ar.role.id in ('DIRE','STAF')")
    List<Account> getAdministrators();
}
