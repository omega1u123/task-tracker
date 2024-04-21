package org.example.restservice.security;

import lombok.RequiredArgsConstructor;
import org.example.restservice.user.model.UserEntity;
import org.example.restservice.user.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepo.findUserEntityByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserDetailsImpl(user);
    }
}
