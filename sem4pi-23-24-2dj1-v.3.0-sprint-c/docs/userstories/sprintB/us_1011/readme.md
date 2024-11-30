## As Customer Manager, I want to select the interview model to use for the interviews of a job opening (for their evaluation/grading).

### 1. Requisitos

Q38 Pedro – Cada questão de um interview model aceita um x tipos de respostas(ex escolha múltipla) ou é a interview
model que aceita um x tipos de respostas em todas as suas questões? Assumimos que uma job opening só segue um interview
model?

A38 Sim, cada pergunta/resposta aceita um tipo de pergunta/resposta (um dos tipos que aparece no inicio da página 8). Na
US1011, o Customer manager seleciona o interview model a usar nas entrevistas para um job opening. Ou seja, existirá
apenas um interview model a usar nas entrevistas desse job opening.

### 2. Análise

Para responder ao problema o modelo de domínio foi suficiente, tendo em especial atenção
a exigência de agregados separados para a us.

### 3. Design

Em termos de design para esta us não existiram grandes decisões a tomar uma vez que apenas envolve
selecionar um plugin do repositorio e dar update a um objeto job opening. Não existiem problemas 
transacionais com o update ao objeto job opening uma vez que apenas um utilizador pode aceder ao mesmo:
Cada customer manager acede apenas às suas job openings.