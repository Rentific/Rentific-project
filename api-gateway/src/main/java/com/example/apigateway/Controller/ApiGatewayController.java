package com.example.apigateway.Controller;

import com.example.apigateway.Model.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApiGatewayController {
    @Value("${security.oauth2.resource.getToken}")
    private String authPath;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        RestTemplate restTemplate = new RestTemplate();
        Login login = new Login(email, password);

        // According OAuth documentation we need to send the client id and secret key in the header for authentication
        String credentials = "rentific:secretkey";
        String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("grant_type", "password");
        body.add("scope", "webclient");
        body.add("username", login.getEmail());
        body.add("password", login.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);
        HttpEntity<?> request = new HttpEntity<Object>(body, headers);

        String access_token_url = authPath;
        ResponseEntity<?> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        return response;
    }
}