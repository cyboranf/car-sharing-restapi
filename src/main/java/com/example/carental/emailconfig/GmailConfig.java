package com.example.carental.emailconfig;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GmailConfig {

    // I changed the id, secret and refresh token because I received an email from GitGuardian
    private String clientId="424740626016-75uceg6d2a2h5a879hdrp4vcssa1qsnadsp.apps.googleusercontent.com ";

    private String clientSecret="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F8O3";


    private String refreshToken="1//04yygqogbCEGgCgYIARAAGAQSNgFL9IrTViVPLwsBHQhywu97TDUl033CNQhyCa_cbox5JroiYzmt9TO-WLJAG8xYID7uCTyxxsg";

    @Bean
    public Gmail getGmailService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setClientSecrets(clientId, clientSecret)
                .build()
                .setRefreshToken(refreshToken);

        return new Gmail.Builder(HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Car Rental App")
                .build();
    }
}
