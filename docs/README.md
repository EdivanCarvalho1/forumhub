# Projeto ForumHub

O FórumHub é uma plataforma interativa projetada para promover discussões e trocas de conhecimento em diversas áreas. Com um ambiente moderno e intuitivo, o FórumHub oferece aos usuários um espaço para criar, compartilhar e explorar tópicos de interesse, seja em tecnologia, ciência, cultura, educação ou qualquer outro tema relevante.

## Diagrama de Classes:

![Diagrama de Classe](/docs/diagrama.png)

## Histórias de Usuário:

### Como um usuário, quero criar uma conta no fórum para participar das discussões e acessar funcionalidades exclusivas.

### Critérios de Aceitação:
- O usuário deve informar nome, e-mail, e senha.
- Deve haver validação de e-mail duplicado.
- A senha deve atender critérios mínimos de segurança (ex.: 8 caracteres, com letras e números).
### Como um usuário autenticado, quero criar tópicos para iniciar discussões sobre assuntos de interesse.

Critérios de Aceitação:
- O tópico deve conter um título e uma descrição.
- O tópico deve ser associado ao criador.
### Como um usuário autenticado, quero responder a tópicos existentes para contribuir com as discussões.

### Critérios de Aceitação:
- A resposta deve conter texto e, opcionalmente, anexos.
- O sistema deve identificar e associar a resposta ao tópico e ao autor.

### Como um usuário, quero buscar tópicos e respostas no fórum para encontrar discussões relevantes rapidamente.

### Critérios de Aceitação:
- A busca deve ser possível por título do tópico, autor ou palavras-chave.
- Os resultados devem ser ordenados por relevância ou data de publicação.
Deve ser possível filtrar por categorias ou tags.
### Como um administrador, quero gerenciar categorias e tags para organizar os tópicos do fórum.

### Critérios de Aceitação:
- O administrador deve poder criar, editar e excluir categorias e tags.
- Os tópicos e respostas associados devem ser atualizados automaticamente ao alterar categorias.

### Como um usuário autenticado, quero editar ou excluir minhas próprias respostas e tópicos para corrigir erros ou remover conteúdos.

### Critérios de Aceitação:
Apenas o autor ou um administrador pode editar ou excluir.
O sistema deve registrar histórico de edição.
### Como um administrador, quero moderar tópicos e respostas para manter o fórum organizado e livre de conteúdos inadequados.

### Critérios de Aceitação:
- O administrador pode editar, excluir ou arquivar qualquer conteúdo.
- O sistema deve permitir reportar conteúdos inadequados para análise.

### Como um usuário, quero visualizar um perfil de outro usuário para conhecer mais sobre sua participação no fórum.

### Critérios de Aceitação:
- O perfil deve exibir informações públicas, como nome, foto, biografia.

### Como um usuário autenticado, quero dar likes em respostas e tópicos para destacar conteúdos relevantes.

### Critérios de Aceitação:
- Apenas usuários autenticados podem dar likes.
- Cada usuário pode dar apenas um like por conteúdo.
- O número total de likes deve ser exibido no conteúdo.

# DDL BANCO DE DADOS PSQL

```sql
CREATE TABLE person(
    id_person SERIAL PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    person_password TEXT NOT NULL,
    phone TEXT NOT NULL,
    signin_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    person_points BIGINT NOT NULL
);

CREATE TABLE users(
    id_user SERIAL PRIMARY KEY,
    id_person INT NOT NULL,

    FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE admins(
    id_admin SERIAL PRIMARY KEY,
    id_person INT NOT NULL,

    FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE person_roles(
    id_person_roles SERIAL PRIMARY KEY,
    id_person INT NOT NULL,
    id_role INT NOT NULL,
    
    FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE punishment(
    id_punishment SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    punishment_period INTERVAL NOT NULL
);

CREATE TABLE punishment_log(
    id_punishment_log SERIAL PRIMARY KEY,
    id_person INT NOT NULL,
    id_punishment INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    
    FOREIGN KEY (id_person) REFERENCES person(id_person),
    FOREIGN KEY (id_punishment) REFERENCES punishment(id_punishment)
);

CREATE TABLE category(
    id_category SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creation_date DATE NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE topic(
    id_topic SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    creation_date DATE NOT NULL,
    id_category INT NOT NULL,

    FOREIGN KEY (id_category) REFERENCES category(id_category)
);

CREATE TABLE post(
    id_post SERIAL PRIMARY KEY,
    likes BIGINT NOT NULL,
    dislikes BIGINT NOT NULL,
    post_content TEXT NOT NULL,
    creation_date DATE NOT NULL,
    id_person INT NOT NULL,
    id_topic INT NOT NULL,
    
    FOREIGN KEY (id_person) REFERENCES person(id_person),
    FOREIGN KEY (id_topic) REFERENCES topic(id_topic)
);

CREATE TABLE post_comments(
    id_comment SERIAL PRIMARY KEY,
    comment_content TEXT NOT NULL,
    likes BIGINT NOT NULL,
    dislikes BIGINT NOT NULL,
    creation_date DATE NOT NULL,
    edit_date DATE,
    id_person INT NOT NULL,
    id_post INT NOT NULL,

    FOREIGN KEY (id_person) REFERENCES person(id_person),
    FOREIGN KEY (id_post) REFERENCES post(id_post)
);

CREATE TABLE reply(
    id_reply SERIAL PRIMARY KEY,
    reply_content TEXT NOT NULL,
    likes BIGINT NOT NULL,
    dislikes BIGINT NOT NULL,
    creation_date DATE NOT NULL,
    edit_date DATE,
    id_person INT NOT NULL,
    id_comment INT NOT NULL,

    FOREIGN KEY (id_person) REFERENCES person(id_person),
    FOREIGN KEY (id_comment) REFERENCES post_comments(id_comment)
);
```
