
# 🎬 ScreenMatch

[![Java](https://img.shields.io/badge/Java-17+-blue?logo=java)](https://www.java.com)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql)](https://www.postgresql.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![API: OMDb](https://img.shields.io/badge/API-OMDb-orange?logo=omdb)](https://www.omdbapi.com/)

Aplicação Java que permite buscar informações de séries e episódios usando a API pública OMDb. Além disso, salva os dados no banco PostgreSQL para consultas futuras, rankings e filtros inteligentes.

## 🚀 Funcionalidades

✅ Buscar séries na API OMDb  
✅ Buscar episódios de uma série  
✅ Listar séries cadastradas  
✅ Buscar série por título ou ator  
✅ Filtrar por categoria, avaliação, quantidade de temporadas  
✅ Top 5 melhores séries e episódios  
✅ Buscar episódios lançados após determinada data  
✅ Persistência no banco de dados PostgreSQL  

## 📷 Demonstração

### 🎥 Menu Principal
```text
                    ******** Welcome to ScreenMatch! ********
                            1 - Buscar séries
                            2 - Buscar episódios
                            3 - Listar séries buscadas
                            4 - Buscar serie por Titulo
                            5 - Buscar series por Ator
                            6 - Top 5 Melhores Series
                            7 - Buscar series por categoria
                            8 - Filtrar series por temporadas e avaliações
                            9 - Buscar por trecho do episodio
                            10 - Top 5 Melhores Episodios de uma Series
                            11 - Buscar episódios a partir de uma data
                            
                            0 - Sair
```


### 🔍 Busca de Série
```text
🎭  GÊNERO: ACAO
🎬  Título: Lupin
⭐  Avaliação: 7.5
📝  Sinopse: Inspirado nas aventuras de Arsène Lupin, o ladrão gentil Assane Diop 
parte em busca de vingança pela injustiça que sua família rica causou a seu pai.
📚  Temporadas: 3
```

## 🏗️ Tecnologias Utilizadas

- ☕ Java 17+
- 🐘 PostgreSQL
- 🌐 API OMDb
- 📦 Maven
- 🏛️ Spring Data JPA + Hibernate
- 🔑 dotenv-java
- 🧪 JUnit 5

## 🔧 Configuração

### 🔑 Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com:

```env
API_URL_OMDB=https://www.omdbapi.com/?t=
API_KEY_OMDB= SUA_API_KEY
```

##### Obtenha sua chave no [Site do OMDB](https://www.omdbapi.com/apikey.asphttps://www.omdbapi.com/apikey.aspx)

### 🗄️ Configuração do Banco

```properties
spring.datasource.url=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME}
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 💻 Executando o projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/screenmatch.git
cd screenmatch
```

### 2️⃣ Compile e execute

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.screenmatch.main.Main"
```

## 🗂️ Estrutura do Projeto

```
screenmatch/
 ├── src/
 │   └── main/
 │       ├── java/
 │       │   └── br/com/screenmatch/
 │       │       ├── model/
 │       │       ├── repository/
 │       │       ├── service/
 │       │       ├── util/
 │       │       └── main/
 │       └── resources/
 │           ├── application.properties
 │           └── .env
 ├── docs/
 │   ├── menu-principal.png
 │   └── busca-serie.png
 ├── .gitignore
 ├── LICENSE
 └── README.md
```

## 🚀 Funcionalidades Futuras

- 🔑 Autenticação e cadastro de usuários  
- 🌐 Interface web (Spring Boot ou React)  
- 📊 Dashboard de estatísticas  
- 🐳 Dockerização do projeto  
- ☁️ Deploy na nuvem  

## 🤝 Contribuindo

Contribuições são muito bem-vindas! Abra uma issue, envie um pull request ou dê sugestões.  

## 📜 Licença

Distribuído sob a licença MIT. Veja [`LICENSE`](LICENSE) para mais informações.

## 🙌 Agradecimentos

- API pública [OMDb](https://www.omdbapi.com/)
- [Alura](https://www.alura.com.br/) pela base do projeto
