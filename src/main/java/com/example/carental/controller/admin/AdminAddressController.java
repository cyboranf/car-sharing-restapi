package com.example.carental.controller.admin;

import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.dto.address.AddressResponseDTO;
import com.example.carental.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/address")
public class AdminAddressController {
    private final AddressService addressService;

    public AdminAddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * @param addressRequestDTO
     * @return DTO of new address of car-sharing
     */
    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO address = addressService.createAddress(addressRequestDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return DTO of address with id = @param
     */
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddress(@PathVariable Long id) {
        AddressResponseDTO address = addressService.getAddress(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    /**
     * @return All addresses of car-sharing
     */
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {
        List<AddressResponseDTO> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    /**
     * @param id
     * @param addressRequestDTO
     * @return Dto of updated Address with id = @param
     */
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO address = addressService.updateAddress(id, addressRequestDTO);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted Address with id = @param
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> deleteAddress(@PathVariable Long id) {
        AddressResponseDTO address = addressService.deleteAddress(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
