package org.fungover.webapi;

import org.fungover.webapi.entities.User;
import org.fungover.webapi.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userEntityRepository;

    public MyUserDetailsService(UserRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userEntityRepository.findByName(username);
        if( userEntity == null)
            throw new UsernameNotFoundException("Username not found: " + username);
        return new MyUserDetails(userEntity.getName(),userEntity.getPassword(),List.of(userEntity.getRoles().split(",")));
    }
}












































