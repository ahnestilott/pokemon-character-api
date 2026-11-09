<#import "layout.ftlh" as layout>

<@layout.page title="Pokemon Details">

    <header>
        <h1>Pokémon Details</h1>
        <p>Learn more about this Pokémon!</p>
    </header>

    <main>

        <section class="pokemon-details">

            <div class="details-image">

                <#if pokemon.imageUrl?? && pokemon.imageUrl?has_content>
                    <img src="${pokemon.imageUrl}" alt="${pokemon.name}">
                <#else>
                    <img src="/images/placeholder.svg"
                         alt="No image available">
                </#if>

            </div>

            <div class="details-info">

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
                    <strong>Description:</strong>
                    ${pokemon.description}
                </p>

                <div class="details-actions">

                    <a href="/" class="button">Back to Gallery</a>

                    <a href="/characters/${pokemon.characterId?c}/edit"
                       class="button">
                        Edit Pokémon
                    </a>

                    <form
                        action="/characters/${pokemon.characterId?c}/delete"
                        method="post"
                        onsubmit="return confirm('Are you sure you want to delete this Pokémon?');">

                        <button type="submit" class="button delete-button">
                            Delete Pokémon
                        </button>

                    </form>

                </div>

            </div>

        </section>

    </main>

</@layout.page>