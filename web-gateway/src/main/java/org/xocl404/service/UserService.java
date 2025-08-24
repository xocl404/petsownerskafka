package org.xocl404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xocl404.Role;
import org.xocl404.UserDto;
import org.xocl404.entity.User;
import org.xocl404.mapper.UserMapper;
import org.xocl404.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    @Transactional
    public UserDto registerUser(String username, String password, Long ownerId) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userRepository.save(User.builder()
                .username(username)
                .password(encoder.encode(password))
                .role(Role.USER)
                .ownerId(ownerId)
                .build());
        return UserDto.builder().username(user.getUsername())
                .role(user.getRole())
                .ownerId(user.getOwnerId())
                .build();
    }

    @Transactional(readOnly = true)
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void checkUser(Long ownerId) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new AccessDeniedException("User not found"));
        boolean isOwner = ownerId.equals(user.getOwnerId());
        boolean isAdmin = user.getRole().name().equals("ADMIN");
        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("Access denied: not owner or admin");
        }
    }

    public void checkAdmin() throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new AccessDeniedException("User not found"));
        boolean isAdmin = user.getRole().name().equals("ADMIN");
        if (!isAdmin) {
            throw new AccessDeniedException("Access denied: not admin");
        }
    }
}
