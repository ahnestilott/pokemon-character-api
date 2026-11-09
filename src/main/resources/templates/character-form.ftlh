<#import "layout.ftlh" as layout>

<@layout.page title=(isEdit!false)?then("Edit Pokémon", "Add Pokémon")>

    <header>
        <h1>
            <#if isEdit!false>
                Edit Pokémon
            <#else>
                Add a Pokémon
            </#if>
        </h1>

        <p>
            <#if isEdit!false>
                Update this Pokémon's information.
            <#else>
                Enter information for a new character in the gallery.
            </#if>
        </p>
    </header>

    <main>

        <#if errorMessage??>
            <p class="form-error">${errorMessage}</p>
        </#if>

        <form class="character-form"
              action="${actionUrl}"
              method="post">

            <label for="name">Pokémon Name</label>
            <input type="text"
                   id="name"
                   name="name"
                   value="${pokemon.name!''}"
                   placeholder="Example: Jigglypuff"
                   required>

            <label for="region">Region</label>
            <input type="text"
                   id="region"
                   name="region"
                   value="${pokemon.region!''}"
                   placeholder="Example: Kanto"
                   required>

            <label for="type">Type</label>
            <input type="text"
                   id="type"
                   name="type"
                   value="${pokemon.type!''}"
                   placeholder="Example: Fairy / Normal"
                   required>

            <label for="imageUrl">Image URL</label>
            <input type="url"
                   id="imageUrl"
                   name="imageUrl"
                   value="${pokemon.imageUrl!''}"
                   placeholder="https://example.com/image.png">

            <label for="description">Short Description</label>
            <textarea id="description"
                      name="description"
                      rows="5"
                      required>${pokemon.description!''}</textarea>

            <button type="submit">
                <#if isEdit!false>
                    Save Changes
                <#else>
                    Add Pokémon
                </#if>
            </button>

            <#if isEdit!false>
                <a href="/characters/${pokemon.characterId?c}"
                   class="button">
                    Cancel
                </a>
            <#else>
                <a href="/" class="button">Cancel</a>
            </#if>

        </form>

    </main>

</@layout.page>