<#import "layout.ftlh" as layout>

<@layout.page title="Add a Pokémon">

    <header>
        <h1>Add a Pokémon</h1>
        <p>Enter information for a new character in the gallery.</p>
    </header>

    <main>

        <#if errorMessage??>
            <p class="form-error">${errorMessage}</p>
        </#if>

        <form class="character-form"
              action="${actionUrl}"
              method="post">

            <label for="name">Pokémon Name</label>
            <input
                type="text"
                id="name"
                name="name"
                value="${pokemon.name!''}"
                placeholder="Example: Jigglypuff"
                required
            >

            <label for="region">Region</label>
            <input
                type="text"
                id="region"
                name="region"
                value="${pokemon.region!''}"
                placeholder="Example: Kanto"
                required
            >

            <label for="type">Type</label>
            <input
                type="text"
                id="type"
                name="type"
                value="${pokemon.type!''}"
                placeholder="Example: Fairy / Normal"
                required
            >

            <label for="imageUrl">Image URL</label>
            <input
                type="url"
                id="imageUrl"
                name="imageUrl"
                value="${pokemon.imageUrl!''}"
                placeholder="https://example.com/image.png"
            >

            <label for="description">Short Description</label>
            <textarea
                id="description"
                name="description"
                rows="5"
                placeholder="Enter a short description of the Pokémon..."
                required
            >${pokemon.description!''}</textarea>

            <button type="submit">Add Pokémon</button>

        </form>

    </main>

</@layout.page>