package org.example.restservice.auth;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restservice.auth.payload.RefreshJwtRequest;
import org.example.restservice.security.UserDetailsServiceImpl;
import org.example.restservice.auth.payload.JwtRequest;
import org.example.restservice.auth.payload.JwtResponse;
import org.example.restservice.user.repository.UserRepo;
import org.example.restservice.user.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final Map<String, String> refreshTokenStorage = new HashMap<>();

    public JwtResponse authenticate(@NonNull JwtRequest jwtRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw  new UsernameNotFoundException("auth failed");
        }
        return generateTokens(jwtRequest.getEmail());
    }

    public JwtResponse refreshAccessToken(@NonNull RefreshJwtRequest refreshToken){
        if(!jwtUtil.validateRefreshToken(refreshToken.getRefreshToken())){
            throw new BadCredentialsException("");
        }
        Claims claims = jwtUtil.getRefreshClaims(refreshToken.getRefreshToken());
        log.info("token from request: {}", refreshToken.getRefreshToken());
        log.info("token from storage: {}", refreshTokenStorage.get(claims.getSubject()));
        if(refreshToken.getRefreshToken().equals(refreshTokenStorage.get(claims.getSubject()))){
            try{
                var user = userRepo.findUserEntityByEmail(claims.getSubject());
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            } catch (BadCredentialsException e){
                throw  new UsernameNotFoundException("auth failed");
            }
            return generateTokens(claims.getSubject());
        }else{
            throw new BadCredentialsException("refresh failed :( ");
        }
    }

    private JwtResponse generateTokens(String email){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        refreshTokenStorage.put(userDetails.getUsername(), refreshToken);
        return new JwtResponse(refreshToken, jwtUtil.generateAccessToken(userDetails));
    }

}
