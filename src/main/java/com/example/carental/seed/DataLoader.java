package com.example.carental.seed;

import com.example.carental.model.Role;
import com.example.carental.repository.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}
