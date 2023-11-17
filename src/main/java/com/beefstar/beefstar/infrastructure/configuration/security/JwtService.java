package com.beefstar.beefstar.infrastructure.configuration.security;


import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtRequest;
import com.beefstar.beefstar.infrastructure.configuration.security.model.JwtResponse;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService implements UserDetailsService {


    private final JwtHelper jwtHelper;


    private final UserInfoDao userInfoDao;


    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this);
        return provider;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            new UsernamePasswordAuthenticationToken(userName, userPassword);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.userName();
        String userPassword = jwtRequest.userPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtHelper.generateToken(userDetails);

        UserInfo userInfo = userInfoDao.findById(userName).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new JwtResponse(userInfo, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findById(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (userInfo != null) {
            return new org.springframework.security.core.userdetails.User(
                    userInfo.getUserName(),
                    userInfo.getUserPassword(),
                    getAuthority(userInfo)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


    private Set<SimpleGrantedAuthority> getAuthority(UserInfo newUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        newUser.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }


}
