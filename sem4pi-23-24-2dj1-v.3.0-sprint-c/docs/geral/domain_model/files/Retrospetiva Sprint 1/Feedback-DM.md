# Feedback Modelo Domínio - Sprint 1

## A Colocar
- **Number of Vacancies**: + todos os atributos de abertura de vaga

## Erros de Colocação de Termos
Casts de Listagem e Consultas: *Não* em modelo de Domínio:

Exemplo: Termo "results" e possivelmente "rank"

Caso de listagem:
- **Number of Applicants** —> derivado

Em falta: busca de significado **derivado**.

## Termos a Adicionar
- **Status** em "Job Opening" em caso de erro —>  Justificação
- Possivelmente "status" em entrevista

- PhaseName --> **Enum**

## Em Arquivo
Falta:
- **FilePath**
- *Algo mais*: Talvez "fileName" ou "FileId"
  (Diogo Nunes tem isso correto no modelo de domínio dele)

# Cardinalidade
Recruitment Process:
Cardinalidade: 0-1


## Nomenclatura
- Evitar nomes genéricos: "Status", "Name", "File"

**Nota:** A palavra "possivelmente" indica que certa parte estará correta, mas o raciocínio pode não estar completamente certo ou completo.
