package com.quiz.A3project.serviceTest;

import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

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


    @Test
    public void testFindPerguntaById(){
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("Qual é a capital do Paraguai?");

        when(perguntaRepository.findById(1L)).thenReturn(java.util.Optional.of(pergunta));

        Pergunta returnedPergunta = perguntaService.findPerguntaById(1L).get();
        assertEquals(pergunta.getTextoDaPergunta(), returnedPergunta.getTextoDaPergunta());
    }

    @Test
    public void testFindAllPerguntas() {
        Pergunta pergunta1 = new Pergunta();
        pergunta1.setTextoDaPergunta("Qual é a capital do Brasil?");

        Pergunta pergunta2 = new Pergunta();
        pergunta2.setTextoDaPergunta("Qual é a capital da Argentina?");

        List<Pergunta> perguntas = Arrays.asList(pergunta1, pergunta2);

        when(perguntaRepository.findAll()).thenReturn(perguntas);

        List<Pergunta> returnedPerguntas = perguntaService.findAllPerguntas();

        assertEquals(2, returnedPerguntas.size());
        assertEquals(pergunta1.getTextoDaPergunta(), returnedPerguntas.get(0).getTextoDaPergunta());
        assertEquals(pergunta2.getTextoDaPergunta(), returnedPerguntas.get(1).getTextoDaPergunta());
    }

    @Test
    public void testDeletePergunta(){
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("Qual é a capital do Paraguai?");

        when(perguntaRepository.findById(1L)).thenReturn(java.util.Optional.of(pergunta));
        Pergunta returnedPergunta = perguntaService.deletePergunta(1L);
        assertEquals(pergunta.getTextoDaPergunta(), returnedPergunta.getTextoDaPergunta());
    }

    @Test
    public void testSetRespostaCorreta(){
        Pergunta pergunta = new Pergunta();
        pergunta.setTextoDaPergunta("Qual é a capital do Paraguai?");
        when(perguntaRepository.findById(1L)).thenReturn(java.util.Optional.of(pergunta));

        Resposta resposta = new Resposta();
        resposta.setTextoDaResposta("Assunção");
        when(perguntaRepository.save(pergunta)).thenReturn(pergunta);

        pergunta.setRespostaCorreta(resposta);

        assertEquals(pergunta.getRespostaCorreta().getTextoDaResposta(), resposta.getTextoDaResposta());
    }
}
