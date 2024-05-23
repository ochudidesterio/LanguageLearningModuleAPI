package org.zeraki.task.learninglanguagemoduleapi.models.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zeraki.task.learninglanguagemoduleapi.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        List<GrantedAuthority> authorities=userDetails.getAuthorities().stream().collect(Collectors.toList());
        return userDetails;
    }
}
