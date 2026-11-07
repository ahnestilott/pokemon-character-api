# Pokémon Character REST API

## Assignment Information

**Assignment:** Assignment 3 — Character REST API
**Course:** CSC 340 — Software Engineering
**Project:** Pokémon Character REST API

## Project Description

The Pokémon Character REST API is a Spring Boot application developed as an extension of my Assignment 2 Pokédex Gallery website. The original website featured five Pokémon: Oshawott, Clefairy, Riolu, Chespin, and Minccino. This project introduces a RESTful backend that allows users to create, retrieve, update, delete, filter, and search Pokémon characters stored in a PostgreSQL database. The API was developed using Java 25, Spring Boot, Spring Data JPA, and PostgreSQL. The database is hosted on Neon, and the application is deployed on Render using Docker.

## Technologies Used

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Bean Validation
- PostgreSQL
- Neon PostgreSQL
- Docker
- Render
- Maven
- Git and GitHub
- Postman

## Project Structure

The application follows a layered architecture:

**Controller → Service → Repository → Database**

- **PokemonCharacter.java:** Defines the Pokémon entity and its attributes.
- **PokemonRepository.java:** Handles database operations using Spring Data JPA.
- **PokemonService.java:** Contains the business logic for CRUD operations, filtering, and searching.
- **PokemonController.java:** Handles HTTP requests and returns appropriate responses.
- **application.properties:** Configures the database connection, Hibernate, and server port.
- **Dockerfile:** Builds and runs the application using a multi-stage Docker build.

## Pokémon Entity

The `PokemonCharacter` entity is stored in the PostgreSQL table named `characters`.

| Attribute | Data Type | Description | Rules |
|---|---|---|---|
| characterId | Long | Unique identifier for each Pokémon | Primary key, auto-generated |
| name | String | Name of the Pokémon | Required, cannot be blank |
| region | String | Region where the Pokémon originated | Required, cannot be blank |
| type | String | Pokémon elemental type | Required, cannot be blank |
| image | String | URL of the Pokémon's image | Optional |
| description | String | Short description of the Pokémon | Required, cannot be blank |

## API Endpoints

Base path: `/api/characters`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all Pokémon | 200 OK |
| GET | `/api/characters/{id}` | Retrieve a Pokémon by ID | 200 OK |
| POST | `/api/characters` | Add a new Pokémon | 201 Created |
| PUT | `/api/characters/{id}` | Update an existing Pokémon | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a Pokémon | 204 No Content |
| GET | `/api/characters?region=Unova` | Filter Pokémon by region | 200 OK |
| GET | `/api/characters?name=osh` | Search Pokémon by name | 200 OK |

Region filtering is case-insensitive.

Name searching supports partial, case-insensitive matches.

## Example API Requests

### GET All Pokémon

**Request:**

`GET /api/characters`

Returns:
{
    "name": "Clefairy",
    "region": "Kanto",
    "type": "Fairy",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/035.png",
    "description": "A friendly Fairy Pokémon known for its adorable appearance and love of moonlight.",
    "characterId": 2
  },
  {
    "name": "Riolu",
    "region": "Sinnoh",
    "type": "Fighting",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/447.png",
    "description": "A Fighting-type Pokémon that senses emotions and communicates through aura.",
    "characterId": 3
  },
  {
    "name": "Chespin",
    "region": "Kalos",
    "type": "Grass",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/650.png",
    "description": "A cheerful Grass-type Pokémon with a tough protective shell covering its head.",
    "characterId": 4
  },
  {
    "name": "Minccino",
    "region": "Unova",
    "type": "Normal",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/572.png",
    "description": "A tidy Normal-type Pokémon that uses its tail to clean its surroundings.",
    "characterId": 5
  },
  {
    "name": "Oshawott",
    "region": "Unova",
    "type": "Water",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/501.png",
    "description": "Oshawott is an adorable Water-type Pokémon from the Unova region. It uses the scalchop on its stomach to defend itself and attack opponents.",
    "characterId": 1
  }

### GET Pokémon by ID

**Request:**

`GET /api/characters/1`

Returns the Pokémon with ID 1.
{
    "name": "Oshawott",
    "region": "Unova",
    "type": "Water",
    "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/501.png",
    "description": "Oshawott is an adorable Water-type Pokémon from the Unova region. It uses the scalchop on its stomach to defend itself and attack opponents.",
    "characterId": 1
  }
If the ID does not exist, the API returns `404 Not Found`.

### POST — Add a Pokémon

**Request:**

`POST /api/characters`

**Request Body:**

```json
{
  "name": "Oshawott",
  "region": "Unova",
  "type": "Water",
  "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/501.png",
  "description": "A Sea Otter Pokémon that uses the scalchop on its stomach to battle."
}
```

**Success Status:** `201 Created`

The database automatically generates a unique character ID.

### PUT — Update a Pokémon

**Request:**

`PUT /api/characters/1`

**Request Body:**

```json
{
  "name": "Oshawott",
  "region": "Unova",
  "type": "Water",
  "image": "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/501.png",
  "description": "Oshawott is a Water-type Pokémon from the Unova region that uses its scalchop in battle."
}
```

**Success Status:** `200 OK`

If the specified Pokémon does not exist, the API returns `404 Not Found`.

### DELETE — Remove a Pokémon

**Request:**

`DELETE /api/characters/1`

**Success Status:** `204 No Content`

If the specified Pokémon does not exist, the API returns `404 Not Found`.

### GET — Filter by Region

**Request:**

`GET /api/characters?region=Unova`

Returns Pokémon from the Unova region.

For example, this filter includes Oshawott and Minccino.

### GET — Search by Name

**Request:**

`GET /api/characters?name=osh`

Returns Pokémon with names containing the specified text.

This search is case-insensitive, so `osh`, `OSH`, and `Osh` can all match Oshawott.

## Database Configuration

The application uses PostgreSQL hosted on Neon.
Database credentials are configured using environment variables rather than being hardcoded into the source code.
Required environment variable:

`SPRING_DATASOURCE_URL`

The Spring Boot configuration includes:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.jpa.hibernate.ddl-auto=update
server.port=${PORT:8080}
```

Hibernate automatically creates or updates the database table based on the entity definition.
No database passwords or connection strings are stored in the GitHub repository.

## Running the Application Locally

1. Clone the GitHub repository.
2. Open the project in VS Code.
3. Ensure Java 25 is installed.
4. Configure the `SPRING_DATASOURCE_URL` environment variable with the Neon JDBC connection string.
5. Run the application using:

```bash
./mvnw spring-boot:run
```

6. Access the API locally:

`http://localhost:8080/api/characters`

## Live Deployment

The Pokémon Character REST API is deployed on Render.

**Live API:**

https://pokemon-character-api-3g6i.onrender.com/api/characters

The deployed application connects to the Neon PostgreSQL database using the `SPRING_DATASOURCE_URL` environment variable configured in Render.

### Render Free-Tier Note

Render's free Web Services may spin down after a period of inactivity.
As a result, the first API request after inactivity may take approximately one minute to respond while the service starts again.
Subsequent requests should respond more quickly while the service remains active.

## Original Pokémon Characters

The application includes the five Pokémon featured in my Assignment 2 Pokédex Gallery:

| Pokémon | Region | Type |
|---|---|---|
| Oshawott | Unova | Water |
| Clefairy | Kanto | Fairy |
| Riolu | Sinnoh | Fighting |
| Chespin | Kalos | Grass |
| Minccino | Unova | Normal |