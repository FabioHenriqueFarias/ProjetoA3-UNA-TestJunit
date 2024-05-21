package com.quiz.A3project.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String textoDaPergunta;

    @JsonManagedReference
    @OneToOne // One pergunta to one resposta
    @JoinColumn(name = "resposta_correta_id")
    private Resposta respostaCorreta;

    @JsonManagedReference
    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas;
}
