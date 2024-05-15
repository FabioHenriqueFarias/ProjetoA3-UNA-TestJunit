package com.quiz.A3project.serviceTest;

import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PerguntaServiceTest {


    @InjectMocks
    private PerguntaService perguntaService;

    @Mock
    private PerguntaRepository perguntaRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePergunta() {
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("Qual é a capital do Uruguai?");

        when(perguntaRepository.save(pergunta)).thenReturn(pergunta);

        Pergunta createdPergunta = perguntaService.createPergunta(pergunta);
        assertEquals(pergunta.getTextoDaPergunta(), createdPergunta.getTextoDaPergunta());
    }


}
