package com.doku.restapi;

import com.doku.restapi.controller.UserController;
import com.doku.restapi.services.TransactionServices;
import com.doku.restapi.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.swing.undo.AbstractUndoableEdit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Slf4j
public class errorTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServices userServices;

    @MockBean
    private TransactionServices transactionServices;

    @Test
    public void createUser() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/users/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "\t\"userId\": 1,\n" +
                            "\t\"fullName\": \"o\",\n" +
                            "\t\"userAddress\": \"KOTA KATANYA\",\n" +
                            "\t\"stockRequest\": \"1000\",\n" +
                            "\t\"currentMoney\": \"10000000\"\n" +
                            "}"))
                    .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    public void getUser() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/users/getById/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

}
