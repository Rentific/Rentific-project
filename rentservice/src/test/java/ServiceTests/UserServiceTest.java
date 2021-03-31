package ServiceTests;

import com.example.rentservice.rentservice.Models.User;
import com.example.rentservice.rentservice.Repositories.UserRepository;
import com.example.rentservice.rentservice.Services.UserService;
import com.example.rentservice.rentservice.Services.ValidationService;
import com.flextrade.jfixture.JFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    private ValidationService validationService;
    private UserService userService;
    private JFixture fixture;

    UserServiceTest() {
        userRepository = mock(UserRepository.class);
        validationService = new ValidationService();
        userService = new UserService(userRepository, validationService);
        fixture = new JFixture();
    }

    @Test
    public void findAllUsers_ShouldReturnOkWithResults() {
        List<User> expected = Arrays.asList(
                new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null),
                new User("TestFirstName2", "TestLastName2", "email2@email.com", new Date(), "654321", null)
        );


        given(userRepository.findAll()).willReturn(expected);

        ResponseEntity<List<User>> actual = userService.findAllUsers();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(2, actual.getBody().size());
    }

    @Test
    public void findUserById_ShouldReturnOkWithResult() {
        User expected = new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null);

        //mock
        given(userRepository.findById(anyInt())).willReturn(Optional.of(expected));

        ResponseEntity<User> actual = null;
        try {
            actual = userService.findUserById(5);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void findUserByEmail_ShouldReturnOkWithResult() {
        User expected = new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null);

        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(expected));

        ResponseEntity<User> actual = null;
        try {
            actual = userService.findUserByEmail("email@email.com");
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void saveUser_ShouldReturnOkWithResult() {
        User expected = new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null);

        given(userRepository.save(expected)).willReturn(expected);

        ResponseEntity<User> actual = null;
        try {
            actual = userService.saveUser(expected);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void updateExistingUser_ShouldReturnOkWithResult() {
        User expected = new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null);

        given(userRepository.save(expected)).willReturn(expected);
        given(userRepository.findById(anyInt())).willReturn(Optional.of(expected));

        ResponseEntity<User> actual = null;
        try {
            actual = userService.updateExistingUser(5, expected);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void deleteUser_ShouldReturnOkWithResult() {
        User expected = new User("TestFirstName", "TestLastName", "email@email.com", new Date(), "123456", null);
        given(userRepository.findById(anyInt())).willReturn(Optional.of(expected));
        doNothing().when(userRepository).deleteById(anyInt());

        ResponseEntity<String> actual = null;
        try {
            actual = userService.deleteUser(5);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("User successfully deleted.", actual.getBody());
    }
}