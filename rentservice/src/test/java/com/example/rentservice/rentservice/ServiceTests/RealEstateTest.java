package com.example.rentservice.rentservice.ServiceTests;

import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Repositories.RealEstateRepository;
import com.example.rentservice.rentservice.Services.RealEstateService;
import com.example.rentservice.rentservice.Services.ValidationService;
import com.flextrade.jfixture.JFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class RealEstateTest {
    @MockBean
    private RealEstateRepository _realEstateRepository;

    private ValidationService _validationService;
    private RealEstateService _realEstateService;
    private JFixture fixture;

    RealEstateTest() {
        _realEstateRepository = mock(RealEstateRepository.class);
        _validationService = new ValidationService();
        _realEstateService = new RealEstateService(_realEstateRepository, _validationService);
        fixture = new JFixture();
    }

    @Test
    public void findAllRealEstates_ShouldReturnOkWithResults() {
        List<RealEstate> expected = Arrays.asList(
                new RealEstate(1, null, false),
                new RealEstate(1, 1, true)
        );


        given(_realEstateRepository.findAll()).willReturn(expected);

        ResponseEntity<List<RealEstate>> actual = _realEstateService.findAllRealEstates();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(2, actual.getBody().size());
    }

    @Test
    public void findAllReservatedRealEstates_ShouldReturnOkWithResults() {
        List<RealEstate> all = Arrays.asList(
                new RealEstate(1, null, false),
                new RealEstate(1, 1, true)
        );
        List<RealEstate> expected = Arrays.asList(
                new RealEstate(1, 1, true)
        );


        given(_realEstateRepository.findByIsReservedTrue()).willReturn(expected);

        ResponseEntity<List<RealEstate>> actual = _realEstateService.findAllReservedRealEstates();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(1, actual.getBody().size());
    }

    @Test
    public void findRealEstateById_ShouldReturnOkWithResult() {
        RealEstate expected = new RealEstate(1, null, false);
        given(_realEstateRepository.findById(anyInt())).willReturn(Optional.of(expected));

        ResponseEntity<RealEstate> actual = null;
        try {
            actual = _realEstateService.findRealEstateById(5);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void saveRealEstate_ShouldReturnOkWithResult() {
        RealEstate expected = new RealEstate(1, 1, true);

        given(_realEstateRepository.save(expected)).willReturn(expected);

        ResponseEntity<RealEstate> actual = null;
        try {
            actual = _realEstateService.saveRealEstate(expected);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void reserveRealEstate_ShouldReturnOkWithResult() {
        RealEstate expected = new RealEstate(1, 1, true);

        given(_realEstateRepository.save(expected)).willReturn(expected);
        given(_realEstateRepository.findById(anyInt())).willReturn(Optional.of(expected));

        ResponseEntity<RealEstate> actual = null;
        try {
            actual = _realEstateService.reserveRealEstate(1);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void deleteRealEstate_ShouldReturnOkWithResult() {
        RealEstate expected = new RealEstate(1, 1, true);
        given(_realEstateRepository.findById(anyInt())).willReturn(Optional.of(expected));
        doNothing().when(_realEstateRepository).deleteById(anyInt());

        ResponseEntity<String> actual = null;
        try {
            actual = _realEstateService.deleteRealEstate(1);
        }
        catch (Exception e)
        {
            return;
        }

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Real estate successfully deleted.", actual.getBody());
    }
}
