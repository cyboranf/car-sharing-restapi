package com.example.carental.validation;

import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.exception.user.InvalidDataException;
import com.example.carental.exception.user.InvalidEmailException;
import com.example.carental.exception.user.InvalidPasswordException;
import com.example.carental.exception.user.UserNotFoundException;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.User;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Password pattern explained:
     * ^ - start of the line
     * (?=.*[0-9]) - at least one digit
     * (?=.*[a-z]) - at least one lowercase letter
     * (?=.*[A-Z]) - at least one uppercase letter
     * .{8,} - at least 8 characters
     * $ - end of the line
     */
    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    /**
     * @param userRegistrationRequest
     */
    public void registerValidation(UserRegistrationRequest userRegistrationRequest) {
        String name = userRegistrationRequest.getFirstName();
        String surname = userRegistrationRequest.getLastName();
        String email = userRegistrationRequest.getEmail();
        if (userRepository.existsByFirstName(name) && userRepository.existsByLastName(surname)) {
            throw new InvalidDataException("User with the same name and surname is exists.");
        }
        if (name == null || name.length() < 3 || name.length() > 30) {
            throw new InvalidDataException("Can not create an account with first name = " + name + ", because name can not be empty and must have between 3 and 30 characters length.");
        }
        if (surname == null || surname.length() > 60) {
            throw new InvalidDataException("Can not create an account with last name = " + surname + ", because last name can not be null and can not have more than 60 characters length.");
        }
        if (email == null || email.length() > 60) {
            throw new InvalidEmailException("Can not create an account with email = " + email + ", because email can not be null and can not have more than 60 characters length.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new InvalidEmailException("Can not create an account with email = " + email + ", because account with this email is already exists.");
        }
        if (!PASSWORD_PATTERN.matcher(userRegistrationRequest.getPassword()).matches()) {
            throw new InvalidPasswordException("Password must be at least 8 characters long, contain at least one digit, " + "one lowercase letter, and one uppercase letter.\"");
        }
    }

    /**
     * @param id
     */
    public User validateUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + id));
    }

    /**
     * @param id
     * @param userRequestDTO
     */
    public void updateUserValidation(Long id, UserRequestDTO userRequestDTO) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can not found user with id =" + id));

        String name = userRequestDTO.getFirstName();
        String surname = userRequestDTO.getLastName();
        String email = userRequestDTO.getEmail();

        if (userRepository.existsByFirstName(name) && userRepository.existsByLastName(surname)) {
            throw new InvalidDataException("User with the same name and surname is exists.");
        }
        if (name == null || name.length() < 3 || name.length() > 30) {
            throw new InvalidDataException("Can not create an account with first name = " + name + ", because name can not be empty and must have between 3 and 30 characters length.");
        }
        if (surname == null || surname.length() > 60) {
            throw new InvalidDataException("Can not create an account with last name = " + surname + ", because last name can not be null and can not have more than 60 characters length.");
        }
        if (email == null || email.length() > 60) {
            throw new InvalidEmailException("Can not create an account with email = " + email + ", because email can not be null and can not have more than 60 characters length.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new InvalidEmailException("Can not create an account with email = " + email + ", because account with this email is already exists.");
        }
        if (!PASSWORD_PATTERN.matcher(userRequestDTO.getPassword()).matches()) {
            throw new InvalidPasswordException("Password must be at least 8 characters long, contain at least one digit, " + "one lowercase letter, and one uppercase letter.\"");
        }
    }
}
