package com.quiz.A3project.service;

import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.repository.PerguntaRepository;
import com.quiz.A3project.repository.RespostaRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {
    private static final Logger logger = LoggerFactory.getLogger(PerguntaService.class);


    private final PerguntaRepository perguntaRepository;

    private final RespostaRepository respostaRepository;

    @Autowired
    public PerguntaService(PerguntaRepository perguntaRepository, RespostaRepository respostaRepository) {
        this.perguntaRepository = perguntaRepository;
        this.respostaRepository = respostaRepository;
    }

    public Pergunta createPergunta(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public List<Pergunta> findAllPerguntas() {
        List<Pergunta> perguntas = perguntaRepository.findAll();
        for(Pergunta pergunta : perguntas){
            Hibernate.initialize(pergunta.getRespostas());
        }
        return perguntaRepository.findAll();
    }

    public Optional<Pergunta> findPerguntaById(Long id) {
        return perguntaRepository.findById(id);
    }

    public Pergunta setRespostaCorreta(Long perguntaId, Long respostaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new RuntimeException("Resposta não encontrada"));

        pergunta.setRespostaCorreta(resposta);


        return perguntaRepository.save(pergunta);
    }


    public Pergunta deletePergunta(Long id){
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
        perguntaRepository.delete(pergunta);
        return pergunta;
    }
}
