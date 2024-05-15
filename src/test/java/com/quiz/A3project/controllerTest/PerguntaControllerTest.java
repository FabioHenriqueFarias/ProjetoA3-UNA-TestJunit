package com.quiz.A3project.controllerTest;


import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.service.PerguntaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PerguntaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerguntaService perguntaService;

    @Test
    public void testFindAllPerguntas() throws Exception {
        when(perguntaService.findAllPerguntas()).thenReturn(Arrays.asList(new Pergunta(), new Pergunta()));

        mockMvc.perform(get("/perguntas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
