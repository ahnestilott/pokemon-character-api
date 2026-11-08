<#import "layout.ftlh" as layout>

<@layout.page title="Home">

    <header>
        <h1>Pokédex Character Gallery</h1>
        <p>Explore some of my favorite Pokémon from the Pokémon universe!</p>
    </header>

    <main>

        <div class="search-container">
            <input
                type="text"
                placeholder="Search Pokémon by name..."
                aria-label="Search Pokemon"
            >
        </div>

        <section class="character-grid">

            <#list characters as pokemon>

                <div class="character-card">

                    <#if pokemon.image?? && pokemon.image?has_content>
                        <img
                            src="${pokemon.image}"
                            alt="${pokemon.name}"
                        >
                    <#else>
                        <img
                            src="/images/placeholder.svg"
                            alt="No image available"
                        >
                    </#if>

                    <h2>${pokemon.name}</h2>

                    <p>
                        <strong>Region:</strong>
                        ${pokemon.region}
                    </p>

                    <p>
                        <strong>Type:</strong>
                        ${pokemon.type}
                    </p>

                    <p>
                        ${pokemon.description}
                    </p>

                    <a
                        class="button"
                        href="/characters/${pokemon.characterId?c}"
                    >
                        View Details
                    </a>

                </div>

            <#else>

                <p>No Pokémon found!</p>

            </#list>

        </section>

    </main>

</@layout.page>