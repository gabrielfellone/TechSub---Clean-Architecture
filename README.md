
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

## Testes Unitários

- Cobertura de Casos de Uso: Teste os principais casos de uso do sistema, como a criação de um novo agendamento, a validação de disponibilidade de horário, entre outros.

- Mocking de Dependências: Para evitar a dependência de banco de dados ou serviços externos durante os testes, use frameworks de mocking (como Mockito ou Moq) para simular comportamentos de dependências.

- Testes de Validação de Dados: Dados recebidos das interfaces de entrada (ex.: dados de agendamento) estão sendo validados corretamente antes de passar para a lógica de negócios.

#### Coverage do projeto

**Para verificar o Coverage do projeto**, basta acessar o arquivo: **`\target\site\index.html`** 

- Foram criados os testes unitários para melhor cobertura de teste segundo JaCoCo foram cobertos 98 of 1.56493% do projeto em testes unitários.

- Criado também na esteira o step de Continuous Integration (CI) onde todo push nas branchs develop, homol e master será executado os testes.

  https://github.com/gabrielfellone/TechSub---Clean-Architecture/actions

---

## Desenho e Arquitetura do Projeto

- [**Desenho da solução** ](https://miro.com/app/board/uXjVLY1xlkY=/?share_link_id=477520251858)

- [**Desenho da Arquitetura** ](https://miro.com/app/board/uXjVLY1xlkY=/?share_link_id=477520251858)



---

## Configurar e testar


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



  
