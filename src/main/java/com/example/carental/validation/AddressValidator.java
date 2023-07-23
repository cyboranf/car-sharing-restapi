package com.example.carental.validation;

import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.exception.address.*;
import com.example.carental.model.Address;
import com.example.carental.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class AddressValidator {
    private final AddressRepository addressRepository;

    public AddressValidator(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private static final List<String> VALID_CITIES = Arrays.asList(
            "Tokyo", "Delhi", "Shanghai", "Sao Paulo", "Mumbai", "Beijing", "Cairo", "Dhaka", "Mexico City", "Osaka",
            "Karachi", "Chongqing", "Istanbul", "Buenos Aires", "Kolkata", "Lagos", "Rio de Janeiro", "Tianjin", "Kinshasa", "Guangzhou",
            "Los Angeles", "Moscow", "Shenzhen", "Lahore", "Bangalore", "Jakarta", "Chennai", "Lima", "New York", "Bangkok",
            "Ho Chi Minh City", "Hyderabad", "Hangzhou", "Hong Kong", "Bogota", "Pune", "Riyadh", "Santiago", "Ahmedabad", "Singapore",
            "Johannesburg", "Beirut", "Cape Town", "Hanoi", "Sydney", "Houston", "Toronto", "Chengdu", "Kuala Lumpur", "Quito",
            "Baghdad", "Nanjing", "Addis Ababa", "Nairobi", "Guadalajara", "Salvador", "Berlin", "Algiers", "Rome", "Pyongyang",
            "Medellin", "Kabul", "Accra", "Cali", "Casablanca", "Athens", "Nagoya", "Porto Alegre", "Fortaleza", "Detroit",
            "Monterrey", "Abidjan", "Caracas", "Peshawar", "Montevideo", "Mecca", "Medina", "Dubai", "Aleppo", "Durban",
            "Kano", "Curitiba", "Minsk", "Port-au-Prince", "Belgrade", "Brisbane", "Ankara", "Recife", "Tel Aviv", "Lusaka",
            "Guayaquil", "Amman", "Nagpur", "San Francisco", "Melbourne", "Cordoba", "Dar es Salaam", "Kampala", "Manaus", "Maputo",
            "Algiers", "Budapest", "Warsaw", "Lahore", "Vienna", "Kharkiv", "Pretoria", "Bucharest", "Minsk", "Accra",
            "Kyiv", "Surabaya", "Quezon City", "Perth", "Auckland", "Montreal", "Caracas", "Damascus", "Karaj", "Doha",
            "Harare", "Pune", "Conakry", "Sana'a", "Santa Cruz", "Addis Ababa", "San Antonio", "Port-au-Prince", "Yaounde", "San Diego",
            "Brisbane", "Philadelphia", "Manila", "Kiev", "Incheon", "Sapporo", "Birmingham", "Jakarta", "Seattle", "Munich",
            "Milan", "Prague", "Copenhagen", "Adelaide", "Austin", "Lima", "Abuja", "Calgary", "Oslo", "Dublin",
            "Havana", "Riyadh", "Zurich", "Kuwait City", "Santo Domingo", "Guatemala City", "Vancouver", "Beirut", "Cali", "Kampala",
            "Asuncion", "Edmonton", "Auckland", "Helsinki", "Porto", "Antwerp", "Islamabad", "Barranquilla", "Vaduz", "Malabo",
            "Douala", "Yaounde", "Bamako", "Karachi", "Lagos", "Mogadishu", "Nairobi", "Addis Ababa", "Cape Town", "Kinshasa",
            "Luanda", "Abidjan", "Dakar", "Khartoum", "Algiers", "Casablanca", "Accra", "Dar es Salaam", "Alexandria", "Johannesburg"
    );
    private static final List<String> VALID_STREETS = Arrays.asList(
            "Main St", "High St", "Elm St", "Maple St", "Oak St", "Pine St", "Cedar St", "Walnut St", "Willow St", "Spring St",
            "Park St", "Church St", "Chestnut St", "School St", "River St", "North St", "South St", "East St", "West St", "Central St",
            "Second St", "Third St", "Fourth St", "Fifth St", "Sixth St", "Seventh St", "Eighth St", "Ninth St", "Tenth St", "Eleventh St",
            "Twelfth St", "Thirteenth St", "Fourteenth St", "Fifteenth St", "Sixteenth St", "Seventeenth St", "Eighteenth St", "Nineteenth St", "Twentieth St", "Twenty-first St",
            "Twenty-second St", "Twenty-third St", "Twenty-fourth St", "Twenty-fifth St", "Twenty-sixth St", "Twenty-seventh St", "Twenty-eighth St", "Twenty-ninth St", "Thirtieth St", "Thirty-first St"
    );
    private static final List<String> VALID_STATES = Arrays.asList(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    );

    private static final List<String> VALID_COUNTRIES = Arrays.asList(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
            "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Côte d'Ivoire", "Cabo Verde",
            "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)",
            "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia (Czech Republic)", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini (fmr. Swaziland)", "Ethiopia", "Fiji", "Finland",
            "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
            "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran",
            "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
            "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
            "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
            "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia",
            "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia (formerly Macedonia)", "Norway",
            "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland",
            "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
            "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland",
            "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
            "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu",
            "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
    );

    /**
     * @param addressRequestDTO
     */
    public void validate(AddressRequestDTO addressRequestDTO) {
        if (addressRequestDTO.getStreet() == null || addressRequestDTO.getCity() == null || addressRequestDTO.getState() == null || addressRequestDTO.getCountry() == null) {
            throw new InvalidAddressException("Street, city, state, and country must not be null");
        }

        if (!VALID_STREETS.contains(addressRequestDTO.getStreet())) {
            throw new InvalidStreetException("Invalid street: " + addressRequestDTO.getStreet());
        }

        if (!VALID_CITIES.contains(addressRequestDTO.getCity())) {
            throw new InvalidCityException("Invalid city: " + addressRequestDTO.getCity());
        }

        if (!VALID_STATES.contains(addressRequestDTO.getState())) {
            throw new InvalidStateException("Invalid state: " + addressRequestDTO.getState());
        }

        if (!VALID_COUNTRIES.contains(addressRequestDTO.getCountry())) {
            throw new IllegalArgumentException("Invalid country: " + addressRequestDTO.getCountry());
        }
    }

    /**
     * @param addressId
     * @return Address or Exception
     */
    public Address getByIdValidation(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Can not found Address with id = " + addressId));
    }

}
