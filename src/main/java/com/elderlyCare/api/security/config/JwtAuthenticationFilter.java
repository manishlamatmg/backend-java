package com.elderlyCare.api.security.config;

import com.elderlyCare.api.role.entity.Role;
import com.elderlyCare.api.security.dto.UserDTO;
import com.elderlyCare.api.security.service.JwtService;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import com.elderlyCare.api.role.repository.RoleRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserDetailsService userDetailsService,
                                   RoleRepository roleRepository,
                                   ApplicationUserRepository applicationUserRepository){
        this.jwtService  = jwtService;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.applicationUserRepository = applicationUserRepository;
    }



    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken;
        String userEmail;

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
           filterChain.doFilter(request, response);
           return;
        }

        jwtToken = authorizationHeader.substring(7);

        userEmail =  jwtService.extractUserName(jwtToken);

        if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDTO userDTO = (UserDTO) userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwtToken, userDTO)){
                ApplicationUser applicationUser = applicationUserRepository.findApplicationUsersByEmail(userEmail);
                Set<Role> roleByApplicationUserId = roleRepository.findRoleByApplicationUserId(applicationUser.getId());
                Set<SimpleGrantedAuthority> authorities = roleByApplicationUserId.stream().map(per -> new SimpleGrantedAuthority("ROLE_" + per.getRoleType())).collect(Collectors.toSet());

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDTO,
                                null,
                                authorities
                        );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);
    }
}
