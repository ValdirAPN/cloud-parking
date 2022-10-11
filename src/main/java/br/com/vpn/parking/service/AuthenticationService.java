package br.com.vpn.parking.service;

import br.com.vpn.parking.model.Account;
import br.com.vpn.parking.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AuthenticationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = accountRepository.findOneByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found with username: " + username);
        return accountToUser(user);
    }

    private User accountToUser(Account user) {
        return new User(user.getUsername(), user.getPassword(), createAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> createAuthorities(Account user) {
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}
