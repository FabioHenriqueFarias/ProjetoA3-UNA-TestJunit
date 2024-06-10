package com.quiz.A3project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Resposta{" +
                "id=" + id +
                ", textoDaResposta='" + textoDaResposta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resposta resposta = (Resposta) o;
        return id == resposta.id &&
                Objects.equals(textoDaResposta, resposta.textoDaResposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textoDaResposta);
    }
}
