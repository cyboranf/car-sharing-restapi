package com.example.carental.seed;

import com.example.carental.model.Address;
import com.example.carental.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressSeederTest {

    @InjectMocks
    private AddressSeeder addressSeeder;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenAddressRepositoryIsEmpty() throws Exception {
        when(addressRepository.count()).thenReturn(0L);

        addressSeeder.run();

        verify(addressRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    public void testRunWhenAddressRepositoryIsNotEmpty() throws Exception {
        when(addressRepository.count()).thenReturn(4L);

        addressSeeder.run();

        verify(addressRepository, never()).saveAll(any(List.class));
    }

    @Test
    public void testNewAddress() {
        String street = "Main St";
        String city = "New York";
        String state = "New York";
        String country = "United States of America";

        Address address = addressSeeder.newAddress(street, city, state, country);

        assertEquals(street, address.getStreet());
        assertEquals(city, address.getCity());
        assertEquals(state, address.getState());
        assertEquals(country, address.getCountry());
    }
}
