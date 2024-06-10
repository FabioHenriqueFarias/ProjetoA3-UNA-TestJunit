package com.quiz.A3project.daoTest;

import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.repository.RespostaRepository;
import com.quiz.A3project.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PerguntaServiceTest {

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @BeforeEach
    public void setUp() {
        perguntaRepository.deleteAll();
        respostaRepository.deleteAll();
    }

    @Test
    public void testCreatePergunta() {
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("What is the capital of France?");
        Pergunta savedPergunta = perguntaService.createPergunta(pergunta);
        Optional<Pergunta> fetchedPergunta = perguntaService.findPerguntaById(savedPergunta.getId());
        assertTrue(fetchedPergunta.isPresent());
        assertEquals("What is the capital of France?", fetchedPergunta.get().getTextoDaPergunta());
    }

    @Test
    public void testSetRespostaCorreta() {
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("What is 2 + 2?");
        Pergunta savedPergunta = perguntaService.createPergunta(pergunta);

        Resposta resposta = new Resposta();
        resposta.setTextoDaResposta("4");
        resposta.setPergunta(savedPergunta);
        Resposta savedResposta = respostaRepository.save(resposta);

        Pergunta updatedPergunta = perguntaService.setRespostaCorreta(savedPergunta.getId(), savedResposta.getId());
        assertEquals(savedResposta, updatedPergunta.getRespostaCorreta());
    }

    @Test
    public void testDeletePergunta() {
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("What is the capital of Germany?");
        Pergunta savedPergunta = perguntaService.createPergunta(pergunta);
        Pergunta deletedPergunta = perguntaService.deletePergunta(savedPergunta.getId());
        assertEquals(savedPergunta.getId(), deletedPergunta.getId());
    }
}
