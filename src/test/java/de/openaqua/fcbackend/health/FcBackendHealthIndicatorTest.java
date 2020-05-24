package de.openaqua.fcbackend.health;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import de.openaqua.fcbackend.FcBackendApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FcBackendApplication.class)
@AutoConfigureMockMvc
class FcBackendHealthIndicatorTest {

  @LocalServerPort
  int randomServerPort;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  public void testEnvironment() throws Exception {
    assertTrue(randomServerPort > 0);
  }

  @Test
  public void testService() throws Exception {
    final String ENDPOINT_PATH = "/actuator/health";
    mockMvc.perform(get(ENDPOINT_PATH).accept(MediaType.APPLICATION_JSON_VALUE))
        //
        .andExpect(status().isOk())
        //
        .andExpect(content().contentType("application/json"))
        //
        .andExpect(jsonPath("$.status", is("UP")))
        //
        .andExpect(jsonPath("$.components.*.status", hasItems("UP", "UP", "UP", "UP", "UP")))
        //
        .andExpect(jsonPath("$.components.*.status", hasItems(is("UP"))))
        ;
   
  }
}
