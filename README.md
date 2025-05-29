# Tasky - Lista de Tarefas
Aplicação web fullstack que permite o gerenciamento de tarefas simples.

## Funcionalidades
- **Criar tarefas**: Adicione novas tarefas com um nome.
- **Listar tarefas**: Visualize todas as tarefas cadastradas em uma tabela.
- **Editar tarefas**: Atualize o nome de uma tarefa existente.
- **Excluir tarefas**: Remova tarefas com confirmação via modal.

## Tecnologias Utilizadas

### Backend (Spring Boot)
- **Spring Boot**: para construção da API REST.
- **Spring Web**: para criação de endpoints RESTful.
- **Spring Data JPA**: interação com o banco de dados.
- **Hibernate**: mapeamento objeto-relacional (ORM).
- **PostgreSQL** (ou outro banco de dados relacional configurável).

### Frontend (Vue.js)
- **Vue 3**: para construção da interface.
- **PrimeVue**: biblioteca de componentes prontos.
- **Axios**: para chamadas HTTP ao backend.

## Pré-requisitos
- **Java 17+** (para o backend).
- **Maven** (para gerenciar dependências do backend).
- **Node.js 16+** e **npm** (para o frontend).
- **PostgreSQL** (ou outro banco de dados relacional).

## Instalação

### Backend
1. Clone o repositório:
   ```bash
   git clone https://github.com/juanfkr/tasky.git
   cd tasky/tasky.api
   ```

2. Configure o banco de dados no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.application.name=tasky.api
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Instale as dependências e inicie o backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   A API estará disponível em `http://localhost:8080/api/tasks`.

### Frontend
1. Navegue até a pasta do frontend:
   ```bash
   cd tasky/frontend
   ```

2. Instale as dependências:
   ```bash
   npm install
   ```

3. Inicie o servidor de desenvolvimento:
   ```bash
   npm run dev
   ```
   O frontend estará disponível em `http://localhost:5173`.

## Estrutura do Projeto

### Backend (`tasky.api`)
```
tasky.api/
├── src/main/java/com/juan/tasky/api/
│   ├── config/
│   │   └── CorsConfig.java           # Configuração de CORS
│   ├── controller/
│   │   └── TaskController.java       # Endpoints da API
│   ├── exceptions/
│   │   └── GlobalExceptionHandler.java # Tratamento de erros
│   ├── model/
│   │   └── Task.java                 # Entidade Task
│   ├── repository/
│   │   └── TaskRepository.java       # Repositório JPA
│   └── Application.java              # Classe principal
├── src/main/resources/
│   └── application.properties         # Configurações do banco
└── pom.xml                           # Dependências Maven
```

### Frontend (`tasky.client`)
```
frontend/
├── src/
│   ├── components/
│   │   ├── Form.vue                 # Formulário para criar tarefas
│   │   └── Table.vue                # Tabela para listar/editar/excluir tarefas
│   ├── assets/
│   │   └── styles.css               # Estilos globais
│   └── App.vue                      # Componente principal
├── public/
│   └── icons8-github.svg            # Ícone do GitHub
├── package.json                     # Dependências npm
└── main.js                          # Inicialização do Vue
```

## Endpoints da API

| Método  | Endpoint                        | Descrição                            |
|---------|---------------------------------|--------------------------------------|
| GET     | `/api/tasks`                   | Retorna a lista de todas as tarefas. |
| POST    | `/api/tasks/post`              | Cria uma nova tarefa.                |
| GET     | `/api/tasks/get/{id}`          | Busca uma tarefa por ID.             |
| PATCH   | `/api/tasks/update/{id}`       | Atualiza o nome de uma tarefa.       |
| DELETE  | `/api/tasks/delete/{id}`       | Exclui uma tarefa por ID.            |

## Tratamento de Erros

O backend inclui tratamento de exceções para:
- **TaskNotFound**: Quando uma tarefa não é encontrada (HTTP 404).
- **TaskAlreadyExists**: Quando uma tarefa com o mesmo nome já existe (HTTP 409).
- **InvalidTaskName**: Quando o nome da tarefa é inválido (HTTP 400).
- **Erros genéricos**: Outros erros retornam HTTP 500 com detalhes.
