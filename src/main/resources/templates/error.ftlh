<#import "layout.ftlh" as layout>

<@layout.page title="Pokemon Not Found">

    <header>
        <h1>Pokémon Not Found!</h1>
        <p>Oops! We couldn't find the Pokémon you're looking for.</p>
    </header>

    <main class="error-page">

        <h2>404 - Pokémon Missing</h2>

        <p>${errorMessage!"The requested Pokémon does not exist."}</p>

        <a href="/" class="button">Return to Gallery</a>

    </main>

</@layout.page>