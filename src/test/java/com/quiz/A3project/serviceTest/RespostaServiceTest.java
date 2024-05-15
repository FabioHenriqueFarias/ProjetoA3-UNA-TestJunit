package com.quiz.A3project.serviceTest;

import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.repository.RespostaRepository;
import com.quiz.A3project.service.PerguntaService;
import com.quiz.A3project.service.RespostaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RespostaServiceTest {


    @InjectMocks
    private RespostaService respostaService;

    @Mock
    private RespostaRepository respostaRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateResposta() {
        Resposta resposta = new Resposta();
        resposta.setTextoDaResposta("Assunção");
        when(respostaRepository.save(resposta)).thenReturn(resposta);

        Resposta returnedResposta = respostaService.createResposta(resposta);

        assertEquals(resposta.getTextoDaResposta(), returnedResposta.getTextoDaResposta());
    }

    @Test
    public void testFindAllRespostas() {
        Resposta resposta1 = new Resposta();
        resposta1.setTextoDaResposta("Assunção");

        Resposta resposta2 = new Resposta();
        resposta2.setTextoDaResposta("Buenos Aires");

        List<Resposta> respostas = Arrays.asList(resposta1, resposta2);

        when(respostaRepository.findAll()).thenReturn(respostas);

        List<Resposta> returnedRespostas = respostaService.findAllRespostas();

        assertEquals(2, returnedRespostas.size());
        assertEquals(resposta1.getTextoDaResposta(), returnedRespostas.get(0).getTextoDaResposta());
        assertEquals(resposta2.getTextoDaResposta(), returnedRespostas.get(1).getTextoDaResposta());
    }

    @Test
    public void testFindRespostaById() {
        Resposta resposta = new Resposta();
        resposta.setTextoDaResposta("Assunção");
        when(respostaRepository.findById(1L)).thenReturn(Optional.of(resposta));

        Optional<Resposta> returnedResposta = respostaService.findRespostaById(1L);

        assertEquals(resposta.getTextoDaResposta(), returnedResposta.get().getTextoDaResposta());
    }

    @Test
    public void testDeleteResposta() {
        Resposta resposta = new Resposta();
        resposta.setTextoDaResposta("Assunção");
        when(respostaRepository.findById(1L)).thenReturn(Optional.of(resposta));

        Resposta returnedResposta = respostaService.deleteResposta(1L);

        assertEquals(resposta.getTextoDaResposta(), returnedResposta.getTextoDaResposta());
        verify(respostaRepository, times(1)).delete(resposta);
    }

    @Test
    public void testDeleteRespostaNotFound() {
        when(respostaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> respostaService.deleteResposta(1L));
    }
}
