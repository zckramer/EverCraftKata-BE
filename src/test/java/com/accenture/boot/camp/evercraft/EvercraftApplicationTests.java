package com.accenture.boot.camp.evercraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.containsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class EvercraftApplicationTests {

    @Autowired
    private MockMvc mvc;

	@Test
	void person_endpoint_will_default_name_to_person_if_a_name_is_not_provided_as_a_request_parameter() throws Exception
    {
        //given the user does not provide a name in the request parameters

        //when we call the endpoint
        MvcResult result = mvc.perform(get("/person")).andReturn();

        //then the endpoint greets us as "Person"
        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertEquals("Hello, Person!", result.getResponse().getContentAsString());
	}
}
