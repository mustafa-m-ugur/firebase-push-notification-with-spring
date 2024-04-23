package com.firebasePushNotification.firebasePushNotification.provider.firebase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.firebasePushNotification.firebasePushNotification.dto.FirebaseDTO;
import com.firebasePushNotification.firebasePushNotification.dto.FirebaseNotificationDtoConverter;
import com.firebasePushNotification.firebasePushNotification.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Firebase {
    private static final String URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "";
    private static final String SENDER_ID = "";

    @Autowired
    private FirebaseNotificationDtoConverter firebaseNotificationDtoConverter;

    public Object sendNotification(NotificationDTO notification, List<String> deviceTokens) throws IOException, InterruptedException {
        FirebaseDTO firebaseDTO = new FirebaseDTO();
        firebaseDTO.setRegistrationIds(deviceTokens);
        firebaseDTO.setNotification(firebaseNotificationDtoConverter.convert(notification));

        String requestData = mapper().writeValueAsString(firebaseDTO);
        String credential = "Bearer " + SERVER_KEY;

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(URL)).headers("Content-Type", "application/json", "Authorization", credential).POST(HttpRequest.BodyPublishers.ofString(requestData)).build();
        HttpResponse<String> response = execute(httpRequest);
        return response.body();
    }

    private HttpResponse<String> execute(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    private ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
