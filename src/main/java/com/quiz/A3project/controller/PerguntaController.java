package com.quiz.A3project.controller;


import com.quiz.A3project.model.Pergunta;
import com.quiz.A3project.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    private final PerguntaService perguntaService;

    @Autowired
    public PerguntaController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @GetMapping
    public ResponseEntity<?> findAllPerguntas(){
        List<Pergunta> perguntas = perguntaService.findAllPerguntas();
        return ResponseEntity.ok(perguntas);
    }

    @PostMapping
    public ResponseEntity<Pergunta> createPergunta(@RequestBody Pergunta pergunta){
        Pergunta novaPergunta = perguntaService.createPergunta(pergunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> findPerguntaById(@PathVariable Long id){
        Optional<Pergunta> pergunta = perguntaService.findPerguntaById(id);
        return pergunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{perguntaId}/resposta-correta/{respostaId}")
    public ResponseEntity<Pergunta> setRespostaCorreta(@PathVariable Long perguntaId, @PathVariable Long respostaId){
        Pergunta pergunta = perguntaService.setRespostaCorreta(perguntaId, respostaId);
        return ResponseEntity.ok(pergunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pergunta> deletePergunta(@PathVariable Long id){
        Pergunta pergunta = perguntaService.deletePergunta(id);
        return ResponseEntity.ok(pergunta);
    }
}
