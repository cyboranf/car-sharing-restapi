package com.example.carental.seed;

import com.example.carental.model.Address;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.AddressRepository;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserSeederTest {

    @InjectMocks
    private UserSeeder userSeeder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenUserRepositoryIsEmpty() throws Exception {
        when(userRepository.count()).thenReturn(0L);
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(new Address()));
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role()));

        userSeeder.run();

        verify(userRepository, times(1)).saveAll(anyListOf(User.class));
    }

    @Test
    public void testRunWhenUserRepositoryIsNotEmpty() throws Exception {
        when(userRepository.count()).thenReturn(4L);

        userSeeder.run();

        verify(userRepository, never()).saveAll(anyListOf(User.class));
    }
}
