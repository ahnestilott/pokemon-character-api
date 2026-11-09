<#import "layout.ftlh" as layout>

<@layout.page title="Home">

    <header>
        <h1>Pokédex Character Gallery</h1>
        <p>Explore some of my favorite Pokémon from the Pokémon universe!</p>
    </header>

    <main>

        <form action="/" method="get" class="search-container">

        <input
            type="text"
            name="name"
            placeholder="Search Pokémon by name..."
            value="${name!''}"
            aria-label="Search Pokémon by name"
        >

        <select name="region" aria-label="Filter Pokémon by region">
            <option value="">All Regions</option>

            <option value="Kanto" <#if region == "Kanto">selected</#if>>
            Kanto
            </option>

            <option value="Johto" <#if region == "Johto">selected</#if>>
            Johto
            </option>

            <option value="Hoenn" <#if region == "Hoenn">selected</#if>>
            Hoenn
            </option>

            <option value="Sinnoh" <#if region == "Sinnoh">selected</#if>>
            Sinnoh
            </option>

            <option value="Unova" <#if region == "Unova">selected</#if>>
            Unova
            </option>

            <option value="Kalos" <#if region == "Kalos">selected</#if>>
            Kalos
            </option>

            <option value="Alola" <#if region == "Alola">selected</#if>>
            Alola
            </option>

            <option value="Galar" <#if region == "Galar">selected</#if>>
            Galar
            </option>

            <option value="Paldea" <#if region == "Paldea">selected</#if>>
            Paldea
            </option>
        </select>

        <button type="submit" class="button">Search</button>

        <a href="/" class="button">Clear</a>

    </form>

        <section class="character-grid">

            <#list characters as pokemon>

                <div class="character-card">

                    <#if pokemon.imageUrl?? && pokemon.imageUrl?has_content>
                        <img
                            src="${pokemon.imageUrl}"
                            alt="${pokemon.name}"
                            onerror="this.onerror=null;this.src='/images/placeholder.svg';">
                    <#else>
                        <img src="/images/placeholder.svg"
                             alt="No image available">
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