package com.example.carental.seed;

import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadRoles() {
        if (!roleRepository.findByName("ROLE_USER").isPresent()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        if (!roleRepository.findByName("ROLE_SHARING_USER").isPresent()) {
            Role sharingUserRole = new Role();
            sharingUserRole.setName("ROLE_SHARING_USER");
            roleRepository.save(sharingUserRole);
        }

        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
    }

    private void loadUsers() {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Default user role not found."));
        Role sharingUserRole = roleRepository.findByName("ROLE_SHARING_USER")
                .orElseThrow(() -> new IllegalStateException("Default sharing user role not found."));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Default admin role not found."));

        if (!userRepository.existsByFirstName("UserTest")) {
            User user = new User();
            user.setFirstName("UserTest");
            user.setLastName("LastTest");
            user.setEmail("usertest@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.getRoles().add(userRole);

            userRepository.save(user);
        }

        if (!userRepository.existsByFirstName("SharingUserTest")) {
            User sharingUser = new User();
            sharingUser.setFirstName("SharingUserTest");
            sharingUser.setLastName("SharingLastTest");
            sharingUser.setEmail("sharingusertest@example.com");
            sharingUser.setPassword(passwordEncoder.encode("password"));
            sharingUser.getRoles().add(sharingUserRole);

            userRepository.save(sharingUser);
        }

        if (!userRepository.existsByFirstName("AdminTest")) {
            User admin = new User();
            admin.setFirstName("AdminTest");
            admin.setLastName("AdminLastTest");
            admin.setEmail("admintest@example.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.getRoles().add(adminRole);

            userRepository.save(admin);
        }
    }


}

