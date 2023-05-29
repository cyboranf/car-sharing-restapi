package com.example.carental.googlecalendarconfig;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

@Configuration
public class GoogleCalendarConfig {

    @Value("${google.client.client-id}")
    private String clientId;

    @Value("${google.client.client-secret}")
    private String clientSecret;

    @Value("${google.client.refresh-token}")
    private String refreshToken;

    @Value("${google.client.access-token}")
    private String accessToken;

    @Bean
    public Calendar calendarService() throws IOException {
        Credential credential = new GoogleCredential.Builder()
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setTransport(new NetHttpTransport())
                .setClientSecrets(clientId, clientSecret)
                .build()
                .setRefreshToken(refreshToken)
                .setAccessToken(accessToken);

        return new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("CarRental")
                .build();
    }
}

