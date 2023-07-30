package com.example.carental.seed;

import com.example.carental.model.Role;
import com.example.carental.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RoleSeederTest {

    @InjectMocks
    private RoleSeeder roleSeeder;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenRoleRepositoryIsEmpty() throws Exception {
        when(roleRepository.count()).thenReturn(0L);

        roleSeeder.run();

        verify(roleRepository, times(1)).save(any(Role.class));
        verify(roleRepository, times(4)).save(any(Role.class));
    }

    @Test
    public void testRunWhenRoleRepositoryIsNotEmpty() throws Exception {
        when(roleRepository.count()).thenReturn(4L);

        roleSeeder.run();

        verify(roleRepository, never()).save(any(Role.class));
    }
}
