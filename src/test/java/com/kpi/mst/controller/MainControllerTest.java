package com.kpi.mst.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MainControllerTest {

    private MockMvc restUserMockMvc;

    @BeforeEach
    void init() {
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup()
                .setControllerAdvice()
                .build();
    }

    @Test
    void testFileIsNull() {
        try {
            restUserMockMvc.perform(
                    post("api/upload/22")
                            .contentType(MediaType.MULTIPART_FORM_DATA)
            .content(String.valueOf(Byte.parseByte(null))))
                    .andExpect(status().isCreated());
        }catch( RuntimeException re) {
            assert true;
        }catch (Exception e) {
            assert false;
        }
    }

    @Test
    void testLIsNull() {
        try {
            restUserMockMvc.perform(
                    post("api/upload/")
                            .contentType(MediaType.MULTIPART_FORM_DATA)
            .content(String.valueOf(Byte.parseByte("1"))))
                    .andExpect(status().isCreated());
        }catch( RuntimeException re) {
            assert true;
        }catch (Exception e) {
            assert false;
        }
    }
}
