package com.example.carental.security;

import com.example.carental.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                //documentation:
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**", "/webjars/**")
                .permitAll()

                //login controller
                .antMatchers("/api/register", "/api/login").permitAll()

                //methods GET from UserController:
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("USER", "SHARING_USER")
                .antMatchers(HttpMethod.GET, "/api/users/{userId}").hasAnyRole("USER", "SHARING_USER")
                .antMatchers(HttpMethod.GET, "/api/users/{userId}/cars").hasAnyRole("USER", "SHARING_USER")
                //methods GET from CarController:
                .antMatchers(HttpMethod.GET, "/api/cars").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/cars/{carId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/cars//types/{carTypeId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods POST from MessageController:
                .antMatchers(HttpMethod.POST, "/api/messages").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods GET from MessageController:
                .antMatchers(HttpMethod.GET, "/api/messages/{senderId}/{recipientId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods DELETE from MessageController:
                .antMatchers(HttpMethod.DELETE, "/api/messages/{messageId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/cars/users/{userId}/booking").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/cars/bookings/{bookingId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods GET from RentController:
                .antMatchers(HttpMethod.GET, "/api/users/{userId}/rents").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/rents/{rentId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods GET from PaymentController:
                .antMatchers(HttpMethod.GET, "/api/{userId}/payments").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/payments/{paymentId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/payments/{paymentId}/complete").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                .antMatchers(HttpMethod.GET, "/api/payments/{userId}").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods GET  from RatingController
                .antMatchers(HttpMethod.GET, "/api/cars/{carId}/ratings").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")

                //methods POST from CarController:
                .antMatchers(HttpMethod.POST, "/api/cars").hasAnyRole("SHARING_USER", "CAR_HIRE")
                //methods POST from BookingController:
                .antMatchers(HttpMethod.POST, "/api/cars/bookings").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods POST from RentController:
                .antMatchers(HttpMethod.POST, "/api/bookings/{bookingId}/rents").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods POST from PaymentController:
                .antMatchers(HttpMethod.POST, "/api/bookings/{bookingId}/payments").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")
                //methods POST from RatingController:
                .antMatchers(HttpMethod.POST, "/api/cars/{carId}/ratings").hasAnyRole("USER", "SHARING_USER", "CAR_HIRE")


                //methods PUT from UserController:
                .antMatchers(HttpMethod.PUT, "/api/users/{userId}").hasRole("ADMIN")
                //methods PUT from CarController:
                .antMatchers(HttpMethod.PUT, "/api/cars/{carId}").hasAnyRole("SHARING_USER", "CAR_HIRE", "ADMIN")
                //methods PUT from Booking Controller
                .antMatchers(HttpMethod.PUT, "/api/cars/bookings/{bookingId}").hasAnyRole("SHARING_USER", "CAR_HIRE", "ADMIN")
                //method PUT from RentController:
                .antMatchers(HttpMethod.PUT, "/api/rents/{rentId}").hasAnyRole("SHARING_USER", "CAR_HIRE", "ADMIN")


                //methods DELETE from UserController:
                .antMatchers(HttpMethod.DELETE, "/api/users/{userId}").hasRole("ADMIN")
                //method DELETE from CarController:
                .antMatchers(HttpMethod.DELETE, "/api/cars/{carId}").hasRole("ADMIN")
                //method DELETE from BookingController
                .antMatchers(HttpMethod.DELETE, "/api/cars/bookings/{bookingId}").hasRole("ADMIN")
                //method DELETE from RentController:
                .antMatchers(HttpMethod.DELETE, "/api/rents/{rentId}").hasRole("ADMIN")


                // AdminUserController methods:
                .antMatchers(HttpMethod.GET, "/api/admin/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/user/{userId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/user/{userId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/user/{userId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/user/{userId}/cars").hasRole("ADMIN")


                // AdminRentController methods:
                .antMatchers(HttpMethod.GET, "/api/admin/users/{userId}/rents").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/rents/{rentId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/rents/{rentId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/rents/{rentId}").hasRole("ADMIN")


                // AdminManufacturerController methods:
                .antMatchers(HttpMethod.POST, "/api/admin/manufacturer").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/manufacturers").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/manufacturer/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/manufacturer/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/manufacturer/{id}").hasRole("ADMIN")


                // AdminInsuranceController methods:
                .antMatchers(HttpMethod.POST, "/api/admin/insurance").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/insurances").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/insurance/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/insurance/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/insurance/{id}").hasRole("ADMIN")


                // AdminImageController methods:
                .antMatchers(HttpMethod.GET, "/api/admin/images").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/image/{id}").hasRole("ADMIN")


                // AdminCarTypeController methods:
                .antMatchers(HttpMethod.POST, "/api/admin/cartype").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/cartype").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/cartype/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/cartype/{id}").hasRole("ADMIN")


                // AdminCarStatusController methods:
                .antMatchers(HttpMethod.POST, "/api/admin/carstatus").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/carstatus").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/carstatus/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/carstatus/{id}").hasRole("ADMIN")


                // AdminCarController methods:
                .antMatchers(HttpMethod.GET, "/api/admin/car").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/car/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/car/{id}").hasRole("ADMIN")


                // AdminBookingController methods:
                .antMatchers(HttpMethod.GET, "/api/admin/booking/user/{userId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/booking/{bookingId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/admin/booking/{bookingId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/booking/{bookingId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/booking").hasRole("ADMIN")


                .anyRequest().hasRole("ADMIN")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
