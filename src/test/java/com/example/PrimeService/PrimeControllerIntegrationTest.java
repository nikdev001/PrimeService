package com.example.PrimeService;

import com.example.PrimeService.services.AlgoTypes;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PrimeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Initialization or setup if necessary
    }

    @Test
    public void testGetPrimesJsonFormat() throws Exception {
        mockMvc.perform(get("/primes/10")
                        .param("algorithm", AlgoTypes.SIEVEOFERATOSTHENES)
                        .param("format", "json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial").value(10))
                .andExpect(jsonPath("$.primes[0]").value(2))
                .andExpect(jsonPath("$.primes[1]").value(3));
        // Add more assertions to verify the response content
    }

    @Test
    public void testGetPrimesXmlFormat() throws Exception {
        mockMvc.perform(get("/primes/10")
                        .param("algorithm", AlgoTypes.SIEVEOFERATOSTHENES)
                        .param("format", "xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(xpath("/response/initial").string("10"))
                .andExpect(xpath("/response/primes/integer[1]").string("2"))
                .andExpect(xpath("/response/primes/integer[2]").string("3"));

        // Add more assertions to verify the XML response content
    }

    @Test
    public void testGetPrimesTextFormat() throws Exception {
        mockMvc.perform(get("/primes/10")
                        .param("algorithm", AlgoTypes.SIEVEOFERATOSTHENES)
                        .param("format", "text"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(CoreMatchers.containsString("Initial: 10")))
                .andExpect(content().string(CoreMatchers.containsString("Primes: [2, 3, 5, 7]")));

        // Add more assertions to verify the plain text response content
    }
}
