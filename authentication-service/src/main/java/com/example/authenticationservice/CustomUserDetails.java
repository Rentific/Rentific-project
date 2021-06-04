package com.example.authenticationservice;
import com.example.authenticationservice.Model.MyUserPrincipal;
import com.example.authenticationservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) {
        RestTemplate restTemp = new RestTemplate();
        User user = restTemp.getForObject("http://userservice:8082/user/?email=" + username, User.class);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
         user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new MyUserPrincipal(user);

    }

}