package com.quiz.A3project.service;

import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {

    private final PerguntaRepository perguntaRepository;
    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaService(RespostaRepository respostaRepository, PerguntaRepository perguntaRepository){
        this.respostaRepository = respostaRepository;
        this.perguntaRepository = perguntaRepository;
    }

    public Resposta createResposta(Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public List<Resposta> findAllRespostas(){
        return respostaRepository.findAll();
    }

    public Optional<Resposta> findRespostaById(Long id){
        return respostaRepository.findById(id);
    }

    public Resposta deleteResposta(Long id){
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));
        respostaRepository.delete(resposta);
        return resposta;
    }

}
