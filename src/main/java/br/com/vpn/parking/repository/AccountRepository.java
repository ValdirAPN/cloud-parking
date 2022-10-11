package br.com.vpn.parking.repository;

import br.com.vpn.parking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findOneByUsername(String username);
}
