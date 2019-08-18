package com.mirotic91.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
public class MockApiTest {

    protected MockMvc mvc;

    protected ObjectMapper objectMapper = buildObjectMapper();

    protected MockMvc buildMockMvc(WebApplicationContext context) {
        return MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    private ObjectMapper buildObjectMapper() {
        return new ObjectMapper();
    }

}