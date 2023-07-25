package com.example.carental.seed;

import com.example.carental.exception.address.AddressNotFoundException;
import com.example.carental.exception.role.RoleNotFoundException;
import com.example.carental.model.Address;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.AddressRepository;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(3)
@Profile("seed")
public class UserSeeder implements DatabaseSeeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * User:
     * "1": {"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "password": "Password1", "address": 1, "role": "ROLE_USER"},
     * "2": {"firstName": "Jane", "lastName": "Doe", "email": "jane.doe@example.com", "password": "Password1", "address": 2, "role": "ROLE_SHARING_USER"},
     * "3": {"firstName": "Alice", "lastName": "Smith", "email": "alice.smith@example.com", "password": "Password1", "address": 3, "role": "ROLE_CAR_HIRE"},
     * "4": {"firstName": "Bob", "lastName": "Johnson", "email": "bob.johnson@example.com", "password": "Password1", "address": 4, "role": "ROLE_ADMIN"}
     */
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            List<User> users = Arrays.asList(
                    newUser("John", "Doe", "john.doe@example.com", "Password1", 1L, "ROLE_USER"),
                    newUser("Jane", "Doe", "jane.doe@example.com", "Password1", 2L, "ROLE_SHARING_USER"),
                    newUser("Alice", "Smith", "alice.smith@example.com", "Password1", 3L, "ROLE_CAR_HIRE"),
                    newUser("Bob", "Johnson", "bob.johnson@example.com", "Password1", 4L, "ROLE_ADMIN")
            );

            userRepository.saveAll(users);
        }
    }

    private User newUser(String firstName, String lastName, String email, String password, Long addressId, String roleName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address with id " + addressId + " not found"));
        user.setAddress(address);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role " + roleName + " not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return user;
    }
}
