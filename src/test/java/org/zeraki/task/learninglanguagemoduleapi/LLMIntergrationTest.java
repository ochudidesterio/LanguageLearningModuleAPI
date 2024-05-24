package org.zeraki.task.learninglanguagemoduleapi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LLMIntergrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterUser() throws Exception {
        String requestBody = "{\"username\": \"user\", \"password\": \"testpass\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllLessons() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lesson/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
