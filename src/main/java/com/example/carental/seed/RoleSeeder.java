package com.example.carental.seed;

import com.example.carental.model.Role;
import com.example.carental.repository.RoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Profile("seed")
public class RoleSeeder implements DatabaseSeeder {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Role:
     * "1": "ROLE_USER",
     * "2": "ROLE_SHARING_USER",
     * "3": "ROLE_CAR_HIRE"
     * "4": "ROLE_ADMIN"
     */
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            Role sharingUserRole = new Role();
            Role carHireRole = new Role();
            Role adminRole = new Role();

            userRole.setName("ROLE_USER");
            sharingUserRole.setName("ROLE_SHARING_USER");
            carHireRole.setName("ROLE_CAR_HIRE");
            adminRole.setName("ROLE_ADMIN");

            roleRepository.save(userRole);
            roleRepository.save(sharingUserRole);
            roleRepository.save(carHireRole);
            roleRepository.save(adminRole);
        }
    }
}
