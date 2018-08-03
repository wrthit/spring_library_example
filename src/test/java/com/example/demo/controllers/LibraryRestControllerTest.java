package com.example.demo.controllers;

import com.example.demo.repositories.LibraryRepository;
import com.example.demo.repositories.LocationRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryRestController.class)
public class LibraryRestControllerTest{

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @MockBean
    private LibraryRepository libraryRepo;

    @MockBean
    private LocationRepository locationRepo;

    @Before
    public void setup(){
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void getLibrariesTest() throws Exception{
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.get("/libraries")
                .accept(MediaType.APPLICATION_JSON_UTF8);
        
        mockMvc.perform(builder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print());     
    }
    
}