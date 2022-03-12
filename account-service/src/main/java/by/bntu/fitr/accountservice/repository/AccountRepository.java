package by.bntu.fitr.accountservice.repository;

import by.bntu.fitr.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserName(String userName);

    Optional<Account> findByEmail(String email);
}
