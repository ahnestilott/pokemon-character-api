<#macro page title="Pokédex Gallery">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} | Pokédex Gallery</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>

    <nav>
        <div class="logo">Pokédex Gallery</div>

        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/characters/new">Add a Pokémon</a></li>
        </ul>
    </nav>

    <#if successMessage?? && successMessage?has_content>
        <div class="success-message">
            ${successMessage}
        </div>
    </#if>

    <#nested>

    <footer>
        <p>Pokédex Gallery | CSC 340</p>
    </footer>

</body>
</html>
</#macro>