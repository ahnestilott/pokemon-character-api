# Pokédex Gallery — Interactive Pokémon Web Application

## Assignment Information

**Course:** CSC 340 — Software Engineering  
**Project:** Pokédex Gallery  
**Assignments:** Assignment 2 — Static Website, Assignment 3 — Character REST API, Assignment 4 — FreeMarker MVC

## Project Description

Pokédex Gallery is an interactive Pokémon web application built using Java 25, Spring Boot, FreeMarker, and PostgreSQL.

The project originally began as a static HTML and CSS website for Assignment 2, featuring five of my favorite Pokémon: Oshawott, Clefairy, Riolu, Chespin, and Minccino.

For Assignment 3, I developed a RESTful CRUD API that allows Pokémon information to be stored and managed in a PostgreSQL database hosted on Neon.

For Assignment 4, I combined the original website design with the Spring Boot backend to create a fully functional web application.

Users can browse Pokémon, search by name, filter by region, view character details, add new Pokémon, edit existing characters, and delete characters.

All changes are saved to the PostgreSQL database.

The application is deployed on Render using Docker.

## Live Application

**Interactive Pokédex Gallery:**  
https://pokemon-character-api-3g6i.onrender.com/

**REST API:**  
https://pokemon-character-api-3g6i.onrender.com/api/characters

The website and REST API are hosted within the same Spring Boot application.

## Technologies Used

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Boot FreeMarker
- Jakarta Bean Validation
- HTML5
- CSS3
- PostgreSQL
- Neon PostgreSQL
- Docker
- Render
- Apache Maven
- Git and GitHub
- Postman

## Application Architecture

The application follows a layered architecture:

**Controller → Service → Repository → PostgreSQL**

### Entity Layer

`PokemonCharacter.java`

Defines the Pokémon attributes and maps the entity to the PostgreSQL `characters` table using JPA annotations.

### Repository Layer

`PokemonRepository.java`

Extends `JpaRepository` and provides database operations such as saving, retrieving, updating, and deleting Pokémon.

It also supports searching Pokémon by name and filtering by region.

### Service Layer

`PokemonService.java`

Contains the application logic for CRUD operations, searching, filtering, and handling missing characters.

Both the website controller and REST controller use this service.

### REST Controller

`PokemonController.java`

Provides RESTful API endpoints under `/api/characters`.

These endpoints allow external API clients to interact with the Pokémon database using HTTP requests and JSON.

### MVC Controller

`PokemonUiController.java`

Handles requests for the interactive website.

The controller retrieves information through `PokemonService`, adds the data to the Spring MVC Model, and returns FreeMarker templates.

The MVC controller communicates directly with the service layer rather than making HTTP requests to the REST API.

### View Layer

FreeMarker templates generate the HTML pages displayed in the browser.

The application uses a shared `layout.ftlh` template for the navigation bar, footer, and success messages.

The original Assignment 2 CSS is reused to preserve the website's design.

## Pokémon Entity

The `PokemonCharacter` entity is stored in the PostgreSQL table named `characters`.

| Attribute | Java Type | Description | Rules |
|---|---|---|---|
| characterId | Long | Unique Pokémon identifier | Auto-generated primary key |
| name | String | Pokémon name | Required, cannot be blank |
| region | String | Region where the Pokémon originated | Required, cannot be blank |
| type | String | Pokémon elemental type | Required, cannot be blank |
| description | String | Pokémon description | Required, cannot be blank |
| imageUrl | String | Pokémon image URL used by the website | Optional |
| image | String | Original Assignment 3 image field | Optional, retained for compatibility |

The application supports missing image URLs by displaying a placeholder image.

## Website Pages and Routes

The interactive website uses Spring Boot MVC controllers and FreeMarker templates.

| Method | Route | Template | Description |
|---|---|---|---|
| GET | `/` | `index.ftlh` | Display the Pokémon gallery |
| GET | `/?name=osh` | `index.ftlh` | Search Pokémon by name |
| GET | `/?region=Unova` | `index.ftlh` | Filter Pokémon by region |
| GET | `/?name=osh&region=Unova` | `index.ftlh` | Search using both filters |
| GET | `/characters/{id}` | `details.ftlh` | View a Pokémon's details |
| GET | `/about` | `about.ftlh` | View information about the project |
| GET | `/characters/new` | `character-form.ftlh` | Display the Add Pokémon form |
| POST | `/characters` | Redirect to gallery | Save a new Pokémon |
| GET | `/characters/{id}/edit` | `character-form.ftlh` | Display the pre-filled Edit form |
| POST | `/characters/{id}` | Redirect to details | Update an existing Pokémon |
| POST | `/characters/{id}/delete` | Redirect to gallery | Delete a Pokémon |
| GET | `/characters/{invalidId}` | `error.ftlh` | Display a friendly not-found page |

## Website Features

### Pokémon Gallery

The homepage displays a card for each Pokémon stored in the database.

Each card includes the Pokémon's image, name, region, type, and character information.

The cards are generated dynamically using FreeMarker instead of being hardcoded into HTML.

Each card links to the individual Pokémon's details page.

### Search and Region Filtering

Users can search Pokémon by name and filter them by region.

Searching supports partial, case-insensitive name matches.

Both filters can be applied simultaneously.

For example:

`/?name=osh&region=Unova`

This displays Pokémon whose names contain "osh" and whose region is Unova.

When no results match, the application displays a friendly message.

### Pokémon Details

Each Pokémon has an individual details page displaying its image, name, region, type, and full description.

Users can return to the gallery or choose to edit or delete the character.

### Add Pokémon

Users can add new Pokémon using the Add a Character form.

The form accepts:

- Name
- Region
- Type
- Image URL
- Description

Submitted information is validated and saved to Neon PostgreSQL through the service and repository layers.

After a successful submission, the user is redirected to the gallery and shown a one-time success message.

### Edit Pokémon

Users can edit existing Pokémon through a pre-filled form.

The Add and Edit features reuse the same FreeMarker template, `character-form.ftlh`.

The existing Pokémon is loaded by ID, updated, and saved to the database.

Editing preserves the original character ID rather than creating a duplicate.

After saving, the user is redirected to the details page.

### Delete Pokémon

Users can delete Pokémon from their details pages.

Before deletion, the website displays a confirmation prompt.

After successful deletion, the user is redirected to the gallery with a success message.

### Error Handling

When a user requests a Pokémon ID that does not exist, the application displays a friendly not-found page.

Form validation prevents required fields from being submitted with missing or blank values.

### Post/Redirect/Get

The application uses the Post/Redirect/Get pattern after successful form submissions.

This prevents refreshing the resulting page from automatically resubmitting the form and creating duplicate records.

### Shared Navigation

All pages use the shared `layout.ftlh` template.

The navigation includes:

- Home
- About
- Add a Character

This keeps the website layout consistent without duplicating navigation code across individual templates.

## REST API Endpoints

The original Assignment 3 REST API remains available alongside the interactive website.

Base path: `/api/characters`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all Pokémon | 200 OK |
| GET | `/api/characters/{id}` | Retrieve Pokémon by ID | 200 OK |
| POST | `/api/characters` | Add a Pokémon | 201 Created |
| PUT | `/api/characters/{id}` | Update a Pokémon | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a Pokémon | 204 No Content |
| GET | `/api/characters?region=Unova` | Filter by region | 200 OK |
| GET | `/api/characters?name=osh` | Search by name | 200 OK |

Region filtering is case-insensitive.

Name searching supports partial, case-insensitive matches.

## Example REST API Requests

### GET All Pokémon

**Request:**

`GET /api/characters`

**Success Status:** `200 OK`

Returns a JSON array containing the Pokémon stored in the database.

### GET Pokémon by ID

**Request:**

`GET /api/characters/1`

**Success Status:** `200 OK`

Returns the Pokémon with the specified ID.

### POST — Add a Pokémon

**Request:**

`POST /api/characters`

**Request Body:**

```json
{
  "name": "Jigglypuff",
  "region": "Kanto",
  "type": "Normal / Fairy",
  "imageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/39.png",
  "description": "A Balloon Pokémon known for singing lullabies that make its listeners fall asleep."
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
  "imageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/501.png",
  "description": "Oshawott is a Water-type Sea Otter Pokémon that uses its scalchop in battle."
}
```

**Success Status:** `200 OK`

The existing Pokémon is updated without changing its character ID.

### DELETE — Remove a Pokémon

**Request:**

`DELETE /api/characters/1`

**Success Status:** `204 No Content`

Removes the Pokémon associated with the specified ID.

### GET — Filter Pokémon by Region

**Request:**

`GET /api/characters?region=Unova`

**Success Status:** `200 OK`

Returns Pokémon from the Unova region.

### GET — Search Pokémon by Name

**Request:**

`GET /api/characters?name=osh`

**Success Status:** `200 OK`

Returns Pokémon whose names contain the specified text.

## API Validation and Error Handling

The API uses Jakarta Bean Validation to validate incoming requests.

| Condition | HTTP Status |
|---|---|
| Request for a nonexistent character ID | 404 Not Found |
| POST with a missing or blank name | 400 Bad Request |
| POST with a missing or blank description | 400 Bad Request |
| PUT with a missing or blank name | 400 Bad Request |
| PUT with a missing or blank description | 400 Bad Request |

The application uses `@NotBlank` for required string attributes.

The service layer uses `ResponseStatusException` to handle missing characters.

## Database Configuration

The application uses PostgreSQL hosted on Neon.

Database credentials are configured using environment variables instead of being hardcoded into the repository.

**Required environment variable:**

`SPRING_DATASOURCE_URL`

The Spring Boot configuration includes:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.jpa.hibernate.ddl-auto=update
server.port=${PORT:8080}
```

Hibernate automatically creates or updates database tables based on the JPA entity definitions.

Database passwords and connection strings are not committed to GitHub.

## Running the Application Locally

1. Clone the GitHub repository.
2. Open the project in VS Code.
3. Ensure Java 25 is installed.
4. Configure `SPRING_DATASOURCE_URL` using the Neon JDBC connection string.
5. Run:

```bash
./mvnw spring-boot:run
```

6. Open the interactive website:

http://localhost:8080/

7. Access the REST API:

http://localhost:8080/api/characters

## Docker and Render Deployment

The application uses a multi-stage Dockerfile.

The first stage uses Java 25 and Maven to compile the Spring Boot project into a JAR file.

The second stage runs the JAR using a smaller Java runtime image.

The application is deployed on Render as a Docker Web Service.

Both the website and REST API use the same deployed application and Neon database.

### Live Website

https://pokemon-character-api-3g6i.onrender.com/

### Live REST API

https://pokemon-character-api-3g6i.onrender.com/api/characters

### Render Free-Tier Cold Starts

Render's free Web Services may spin down after periods of inactivity.

The first request after inactivity may take approximately one minute while the service starts again.

Subsequent requests should respond more quickly while the service is active.

## Original Pokémon Collection

The original Assignment 2 website featured these five Pokémon:

| Pokémon | Region | Type |
|---|---|---|
| Oshawott | Unova | Water |
| Clefairy | Kanto | Fairy |
| Riolu | Sinnoh | Fighting |
| Chespin | Kalos | Grass |
| Minccino | Unova | Normal |

Additional Pokémon can be added, edited, and removed through the interactive website.

## Project Development

This project was developed incrementally across three assignments.

**Assignment 2:** Created the original static Pokédex Gallery using HTML and CSS.

**Assignment 3:** Implemented a Spring Boot REST API with PostgreSQL persistence, CRUD operations, searching, filtering, validation, and Docker deployment.

**Assignment 4:** Added FreeMarker MVC templates, shared navigation, database-driven gallery pages, working forms, combined search filters, editing, deletion, and user-friendly error handling.

The application continues to support its original REST API while providing an interactive frontend for users.