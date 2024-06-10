package com.quiz.A3project.daoTest;

import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.repository.RespostaRepository;
import com.quiz.A3project.service.RespostaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RespostaServiceTest {
    @Mock
    private RespostaRepository respostaRepository;

    @Mock
    private PerguntaRepository perguntaRepository;

    @InjectMocks
    private RespostaService respostaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateResposta() {
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setTextoDaResposta("Texto da resposta");

        when(respostaRepository.save(resposta)).thenReturn(resposta);

        Resposta createdResposta = respostaService.createResposta(resposta);

        assertEquals(resposta, createdResposta);
        verify(respostaRepository, times(1)).save(resposta);
    }

    @Test
    public void testFindAllRespostas() {
        Resposta resposta1 = new Resposta();
        resposta1.setId(1L);
        resposta1.setTextoDaResposta("Resposta 1");

        Resposta resposta2 = new Resposta();
        resposta2.setId(2L);
        resposta2.setTextoDaResposta("Resposta 2");

        List<Resposta> respostas = Arrays.asList(resposta1, resposta2);

        when(respostaRepository.findAll()).thenReturn(respostas);

        List<Resposta> foundRespostas = respostaService.findAllRespostas();

        assertEquals(2, foundRespostas.size());
        assertEquals("Resposta 1", foundRespostas.get(0).getTextoDaResposta());
        assertEquals("Resposta 2", foundRespostas.get(1).getTextoDaResposta());
        verify(respostaRepository, times(1)).findAll();
    }

    @Test
    public void testFindRespostaById() {
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setTextoDaResposta("Texto da resposta");

        when(respostaRepository.findById(1L)).thenReturn(Optional.of(resposta));

        Optional<Resposta> foundResposta = respostaService.findRespostaById(1L);

        assertTrue(foundResposta.isPresent());
        assertEquals("Texto da resposta", foundResposta.get().getTextoDaResposta());
        verify(respostaRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteResposta() {
        Resposta resposta = new Resposta();
        resposta.setId(1L);
        resposta.setTextoDaResposta("Texto da resposta");

        when(respostaRepository.findById(1L)).thenReturn(Optional.of(resposta));

        Resposta deletedResposta = respostaService.deleteResposta(1L);

        assertEquals(resposta, deletedResposta);
        verify(respostaRepository, times(1)).findById(1L);
        verify(respostaRepository, times(1)).delete(resposta);
    }
}
