<#import "layout.ftlh" as layout>

<@layout.page title="About">

    <header>
        <h1>About My Pokédex Gallery</h1>
        <p>A little more about my Pokémon collection!</p>
    </header>

    <main>

        <section class="pokemon-details">

            <div class="details-image">
                <img
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQN2g3qrYjAM1opq_AUQdZJ8TmsasmvfmyQEeja7Yi60I_elEYdorJHhCfx&s=10"
                    alt="Mincinno">
            </div>

            <div class="details-info">

                <h2>Welcome to My Pokédex!</h2>

                <p>
                    This website started as a static Pokémon gallery
                    for my CSC 340 Software Engineering class.
                    I wanted to create a collection featuring some
                    of my favorite Pokémon, including Oshawott,
                    Clefairy, Riolu, Chespin, and Minccino.
                </p>

                <p>
                    The original website was built using HTML and CSS.
                    It displayed Pokémon information, but the search
                    bar and forms were not functional.
                </p>

                <p>
                    The gallery has now evolved into an interactive
                    web application where visitors can browse,
                    search, add, edit, and delete Pokémon.
                </p>

            </div>

        </section>

        <section class="about-architecture">

            <h2>How This Website Works</h2>

            <p>
                This application uses Java 25, Spring Boot,
                FreeMarker, and PostgreSQL.
            </p>

            <p>
                FreeMarker generates the HTML pages using information
                retrieved from the database.
            </p>

            <p>
                The application follows a layered architecture:
            </p>

            <p class="architecture-flow">
                Controller → Service → Repository → PostgreSQL
            </p>

            <p>
                The Controller receives requests from the website
                and communicates with the Service layer.
                The Service handles the application logic, while
                the Repository uses Spring Data JPA to communicate
                with the PostgreSQL database hosted on Neon.
            </p>

            <p>
                The application also provides REST API endpoints
                that allow external clients to interact with
                the Pokémon database.
            </p>

            <p>
                The website is deployed on Render using Docker,
                making the gallery accessible online.
            </p>

        </section>

    </main>

</@layout.page>