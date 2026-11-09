package com.ahnesti.pokemon.controller;

import com.ahnesti.pokemon.entity.PokemonCharacter;
import com.ahnesti.pokemon.service.PokemonService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PokemonUiController {

    private final PokemonService pokemonService;

    public PokemonUiController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Display all Pokemon
    @GetMapping("/")
    public String showGallery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String region,
            Model model) {

        model.addAttribute("characters", pokemonService.searchPokemon(name, region));

        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("region", region == null ? "" : region);

        return "index";
    }

    // Display one Pokemon
    @GetMapping("/characters/{id}")
    public String showDetails(@PathVariable Long id, Model model) {

        try {
            PokemonCharacter pokemon = pokemonService.getPokemonById(id);

            model.addAttribute("pokemon", pokemon);

            return "details";

        } catch (ResponseStatusException e) {

            if (e.getStatusCode().value() != 404) {
                throw e;
            }

            model.addAttribute("errorMessage", "Sorry! That Pokémon could not be found.");

            return "error";
        }
    }

    // Display the Add Pokemon form
    @GetMapping("/characters/new")
    public String showAddForm(Model model) {

        model.addAttribute("pokemon", new PokemonCharacter());
        model.addAttribute("actionUrl", "/characters");

        return "character-form";
    }

    // Save a new Pokemon
    @PostMapping("/characters")
    public String addPokemon(
            @Valid @ModelAttribute("pokemon") PokemonCharacter pokemon,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check for invalid form data
        if (bindingResult.hasErrors()) {
            model.addAttribute("actionUrl", "/characters");
            model.addAttribute("errorMessage",
                    "Please fill out all required fields correctly.");

            return "character-form";
        }

        // Save Pokemon to Neon
        PokemonCharacter savedPokemon = pokemonService.addPokemon(pokemon);

        // Display a success message after redirecting
        redirectAttributes.addFlashAttribute(
                "successMessage",
                savedPokemon.getName() + " was successfully added!"
        );

        // Return to the gallery
        return "redirect:/";
    }

    // Display the Edit Pokemon form
    @GetMapping("/characters/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        try {
            PokemonCharacter pokemon = pokemonService.getPokemonById(id);

            model.addAttribute("pokemon", pokemon);
            model.addAttribute("actionUrl", "/characters/" + id);
            model.addAttribute("isEdit", true);

            return "character-form";

        } catch (ResponseStatusException e) {

            if (e.getStatusCode().value() != 404) {
                throw e;
            }

            model.addAttribute("errorMessage",
                    "Sorry! That Pokémon could not be found.");

            return "error";
        }
    }

    // Save changes to an existing Pokemon
    @PostMapping("/characters/{id}")
    public String editPokemon(
            @PathVariable Long id,
            @Valid @ModelAttribute("pokemon") PokemonCharacter pokemon,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            // Verify that this Pokemon exists
            pokemonService.getPokemonById(id);

            if (bindingResult.hasErrors()) {
                pokemon.setCharacterId(id);

                model.addAttribute("actionUrl", "/characters/" + id);
                model.addAttribute("isEdit", true);
                model.addAttribute("errorMessage",
                        "Please fill out all required fields correctly.");

                return "character-form";
            }

            PokemonCharacter updatedPokemon =
                    pokemonService.updatePokemon(id, pokemon);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    updatedPokemon.getName() + " was successfully updated!"
            );

            return "redirect:/characters/" + id;

        } catch (ResponseStatusException e) {

            if (e.getStatusCode().value() != 404) {
                throw e;
            }

            model.addAttribute("errorMessage",
                    "Sorry! That Pokémon could not be found.");

            return "error";
        }
    }

}