## 🔎 Para visualizar o Frontend em JavaScript vá para esse [repositório](https://github.com/Iuryppedrosa/A3-quiz-Js-Puro)

https://github.com/Iuryppedrosa/ProjetoA3-UNA-TestJunit/assets/89420889/9765e116-289b-4585-825b-927877b57e30

# 📚 Projeto Quiz

**Quiz**, é uma aplicação baseada em Java e Spring Boot que permite a criação e gerenciamento de perguntas e respostas para um jogo de quiz.

## Entidades Principais

A aplicação possui duas entidades principais: **Pergunta** e **Resposta**. Cada pergunta pode ter várias respostas associadas a ela, mas apenas uma é a correta. Isso é gerenciado através de um relacionamento One-to-One entre a Pergunta e a Resposta correta, e um relacionamento One-to-Many entre a Pergunta e todas as Respostas.

## Funcionalidades

As funcionalidades da aplicação são expostas através de dois controladores REST: `PerguntaController` e `RespostaController`.

### PerguntaController

O `PerguntaController` permite:

- **Listar todas as perguntas existentes.**
- **Criar uma nova pergunta.**
- **Buscar uma pergunta específica pelo seu ID.**
- **Definir a resposta correta para uma pergunta.**
- **Deletar uma pergunta pelo seu ID.**

### RespostaController

O `RespostaController` permite:

- **Listar todas as respostas existentes.**
- **Criar uma nova resposta.**
- **Buscar uma resposta específica pelo seu ID.**
- **Deletar uma resposta pelo seu ID.**

## Persistência de Dados

Os dados são persistidos em um banco de dados MySQL, conforme configurado no arquivo `application.properties`. A aplicação também parece ter suporte para ser executada em um ambiente de nuvem, como o Google Cloud, já que existem configurações comentadas para isso.

## Testes

Os testes para os controladores estão sendo feitos usando o framework de testes Spring Boot Test com MockMvc e Mockito para simular o comportamento dos serviços.

## Resumo

Em resumo, o projeto **Quiz** é uma aplicação robusta para gerenciamento de perguntas e respostas de um jogo de quiz, com uma arquitetura bem definida e testes para garantir a qualidade do código.
