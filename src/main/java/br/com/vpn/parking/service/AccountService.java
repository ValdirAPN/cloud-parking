package br.com.vpn.parking.service;

import br.com.vpn.parking.enums.AccountRole;
import br.com.vpn.parking.model.Account;
import br.com.vpn.parking.repository.AccountRepository;
import br.com.vpn.parking.util.IdUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initialize() {
        if (accountRepository.findOneByUsername("user") == null) {
            save(new Account(
                    IdUtil.getUUID(),
                    "user",
                    "userPass",
                    AccountRole.USER
            ));
        }
        if (accountRepository.findOneByUsername("admin") == null) {
            save(new Account(
                    IdUtil.getUUID(),
                    "admin",
                    "adminPass",
                    AccountRole.ADMIN
            ));
        }
    }

    @Transactional
    private Account save(Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }
}
