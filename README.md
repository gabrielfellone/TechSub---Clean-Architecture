
# TECH SUB FASE 03

**Pós 2ADJT**

Aluno: Gabriel Fellone  
RM: 350771

---

## Objetivo 

Foi desenvolvido um sistema de agendamento e gerenciamento de serviços de beleza e bem-estar.
Com ênfase no Clean Architecture e testes unitários. 


## Clean Architecture

Camadas da Arquitetura:

### Adapters:
- Controllers e Gateways:  Assim como na aula, foi usada para criar a camada que lida com a conversão de dados entre o mundo externo (interface com usuário, banco de dados) e o sistema.

### Core:
 - Entidades: Modelos de objetos de negócios fundamentais, como Estabelecimeneto, Cliente, Serviço, Agendamento, etc.
 - Use Cases: Regras de negócio, como formatarDataParaAgendamento, filtrarEstabelecimento, etc.

### Infraestructure:
  - Repository: É responsável por lidar com todas as interações externas ao sistema, como o acesso a bancos de dados, comunicação com APIs externas, manipulação de arquivos, entre outro.

![image](https://github.com/user-attachments/assets/2a098a0c-61ad-451d-aca7-59c92484a5b2)


## Testes Unitários

- Cobertura de Casos de Uso: Teste os principais casos de uso do sistema, como a criação de um novo agendamento, a validação de disponibilidade de horário, entre outros.

- Mocking de Dependências: Para evitar a dependência de banco de dados ou serviços externos durante os testes, use frameworks de mocking (como Mockito ou Moq) para simular comportamentos de dependências.

- Testes de Validação de Dados: Dados recebidos das interfaces de entrada (ex.: dados de agendamento) estão sendo validados corretamente antes de passar para a lógica de negócios.

#### Coverage do projeto

**Para verificar o Coverage do projeto**, basta acessar o arquivo: **`\target\site\index.html`** 

![image](https://github.com/user-attachments/assets/9f69422f-34db-4e2e-9622-8a7610076136)


- Foram criados os testes unitários para melhor cobertura de teste segundo JaCoCo foram cobertos 98 of 1.56493% do projeto em testes unitários.

- Criado também na esteira o step de Continuous Integration (CI) onde todo push nas branchs develop, homol e master será executado os testes.

  https://github.com/gabrielfellone/TechSub---Clean-Architecture/actions
  

  ![image](https://github.com/user-attachments/assets/f734ff7b-44ca-42ee-8e7f-eb1d782085f3)



---

## Desenho e Arquitetura do Projeto

- [**Desenho da solução** ](https://miro.com/app/board/uXjVLY1xlkY=/?share_link_id=477520251858)

- [**Desenho da Arquitetura** ](https://miro.com/app/board/uXjVLY1xlkY=/?share_link_id=477520251858)



---

## Configurar e testar localmente


O arquivo **init.sql** é a inicialização das tabelas, já com dados criados. Será criado assim que subir o container.
Este arquivo **`init.sql`** esta na raiz do projeto e deve estar no mesmo diretório do arquivo docker-compose.yml


### Subir o container, usar o comando na pasta do **docker-compose.yml** que esta na raiz do projeto:

**`docker-compose up`**


- Swagger:

http://localhost:8080/swagger-ui/index.html#/

OBS.:

Caso apresente o seguinte erro " ERROR: duplicate key value violates unique constraint" ao salvar na base de dados, 
é porque com o init.sql ele já cria valores no banco para realizar os testes e não precisar criar massa. Basta salvar algumas vezes (via endpoint mesmo) que ele não gera mais este erro, ou subir sem o init.sql.

---

## Deploy da aplicação em Nuvem

### Foi deployado o projeto no Render (aplicação e banco de dados) assim como apresentado na aula


Url da aplicação:
https://techsub-clean-architecture.onrender.com

Url do banco de dados:
postgresql://postgres_xgz1_user:6Ty8sV268IquTheB2DTYowIKbLIfDZoR@dpg-cssg3ji4esus739mgkb0-a/postgres_xgz1

![image](https://github.com/user-attachments/assets/7be137b3-3df8-4dfd-a9ca-e65d1502be2e)

![image](https://github.com/user-attachments/assets/93ed1158-1008-4306-8a4c-1bb30b0a7533)

---
