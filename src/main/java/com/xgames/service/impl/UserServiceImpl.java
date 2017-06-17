package com.xgames.service.impl;

import com.xgames.model.Role;
import com.xgames.model.User;
import com.xgames.repository.RoleRepository;
import com.xgames.repository.UserRepository;
import com.xgames.security.SystemUser;
import com.xgames.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role defaultRole = roleRepository.findByRole("TABLE_GAMES");
        user.setRoles(new HashSet<>());
        user.getRoles().add(defaultRole);
        userRepository.save(user);
        logger.info("User with email '{}' registered successfully!", user.getEmail());
    }

    @Override
    public boolean userAlreadyExists(String email) {
        User user = userRepository.findByEmail(email);
        return Optional.ofNullable(user).isPresent();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmailAndActiveTrue(userName);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SystemUser(user, getUserAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(Set<Role> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        return authorities;
    }
}
