package com.quiz.A3project.controller;


import com.quiz.A3project.model.Resposta;
import com.quiz.A3project.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    @Autowired
    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping
    public ResponseEntity<?> findAllRespostas(){
        List<Resposta> respostas = respostaService.findAllRespostas();
        return ResponseEntity.ok(respostas);
    }

    @PostMapping
    public ResponseEntity<Resposta> createResposta(@RequestBody Resposta resposta){
        Resposta novaResposta = respostaService.createResposta(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaResposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> findRespostaById(@PathVariable Long id){
        Optional<Resposta> resposta = respostaService.findRespostaById(id);
        return resposta.map(ResponseEntity::ok)
                .orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resposta> deleteResposta(@PathVariable Long id){
        Resposta resposta = respostaService.deleteResposta(id);
        return ResponseEntity.ok(resposta);
    }
}
