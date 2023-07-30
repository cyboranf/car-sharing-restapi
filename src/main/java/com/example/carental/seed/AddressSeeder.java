package com.example.carental.seed;

import com.example.carental.model.Address;
import com.example.carental.repository.AddressRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
@Profile("seed")
public class AddressSeeder implements DatabaseSeeder {
    private final AddressRepository addressRepository;

    public AddressSeeder(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Address:
     * "1": {"street": "Main St", "city": "New York", "state": "New York", "country": "United States of America"},
     * "2": {"street": "High St", "city": "Los Angeles", "state": "California", "country": "United States of America"},
     * "3": {"street": "Elm St", "city": "Chicago", "state": "Illinois", "country": "United States of America"},
     * "4": {"street": "Maple St", "city": "Houston", "state": "Texas", "country": "United States of America"}
     */
    @Override
    public void run(String... args) throws Exception {
        if (addressRepository.count() == 0) {
            List<Address> addresses = Arrays.asList(
                    newAddress("Main St", "New York", "New York", "United States of America"),
                    newAddress("High St", "Los Angeles", "California", "United States of America"),
                    newAddress("Elm St", "Chicago", "Illinois", "United States of America"),
                    newAddress("Maple St", "Houston", "Texas", "United States of America")
            );

            addressRepository.saveAll(addresses);
        }
    }

    protected Address newAddress(String street, String city, String state, String country) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        return address;
    }
}
