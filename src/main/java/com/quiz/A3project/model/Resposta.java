package com.quiz.A3project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String textoDaResposta;

    @JsonBackReference
    @ManyToOne // Many respostas to one pergunta
    @JoinColumn(name = "pergunta_id", nullable = false)
    private Pergunta pergunta;
}
