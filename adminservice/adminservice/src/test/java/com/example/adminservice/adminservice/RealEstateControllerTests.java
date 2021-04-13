package com.example.adminservice.adminservice;

import com.example.adminservice.adminservice.Admin.Controllers.RealEstateController;
import com.example.adminservice.adminservice.Admin.ErrorHandling.GlobalErrorHandling;
import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RealEstateControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RealEstateService realEstateService;

    @Before("")
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(RealEstateController.class)
                .setControllerAdvice(new GlobalErrorHandling())
                .build();
    }

    @Test
    public void findRealEstateById_ShouldReturnOkWithResult() throws Exception {
        /*StaffUser staff = new StaffUser("TestIme", "TestPrezime", null);
        RealEstate expected = new RealEstate("TestBane", 250000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
                staff, null);
        expected.setRealEstateId(1);

        given(realEstateService.findRealEstateById(1)).willReturn(new ResponseEntity<>(expected, HttpStatus.OK));

        mvc.perform(get("/real-estate/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.realEstateId", is(expected.getRealEstateId())))
                .andExpect(jsonPath("$.name", is("TestBane")));*/
    }

    @Test
    public void findRealEstateById_ShouldReturn400_WhenIdIsInvalid() throws Exception {
        given(realEstateService.findRealEstateById(anyInt()))
                .willThrow(new InvalidRequestException("Received Id is not valid."));

        mvc.perform(get("/real-estate/0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addNewRealEstate_ShouldReturnOkWithResult() throws Exception {
        /*StaffUser staff = new StaffUser("TestIme", "TestPrezime", null);
        RealEstate expected = new RealEstate("TestBane", 250000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
                staff, null);
        expected.setRealEstateId(1);

        given(realEstateService.saveRealEstate(expected)).willReturn(new ResponseEntity<>(expected, HttpStatus.OK));

        mvc.perform(post("/real-estate/add")
                .content(new ObjectMapper().writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/
    }

    @Test
    public void addNewRealEstate_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        /*RealEstate expected = new RealEstate(null, 250000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
                null, null);
        expected.setRealEstateId(1);

        given(realEstateService.saveRealEstate(ArgumentMatchers.<RealEstate>any()))
                .willThrow(new InvalidRequestException("Properties that must be provided: Name, Staff."));

        mvc.perform(post("/real-estate/add")
                .content(new ObjectMapper().writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());*/
    }

   /* @Test
    public void findAllRealEstates_ShouldReturnRealEstatesSortedByPriceAsc_WhenSortIsDefault() throws Exception {
        StaffUser staff = new StaffUser("TestIme", "TestPrezime", null);
RealEstate e1 = new RealEstate("TestBane", 250000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
        staff, null);
        List<RealEstate> estateList = new ArrayList<RealEstate>();
        estateList.add(new RealEstate("TestBane", 250000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
                staff, null));
        estateList.add(new RealEstate("TestBane2", 750000.0, "Adresa 123", "BiH", "Sarajevo", "Namjestena kuca", false, false,
                staff, null));

        Page<RealEstate> page = new PageImpl<RealEstate>(estateList);
        Pageable paging = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "price"));

        given(realEstateService.findAllRealEstates(paging)).willReturn(new ResponseEntity<Page<RealEstate>>
                (page, HttpStatus.OK));

        mvc.perform(get("/real-estate/all/real-estates").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(250000.0)))
                .andExpect(jsonPath("$.name", is("TestBane")));
    }
*/
}
