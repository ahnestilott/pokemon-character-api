package com.ahnesti.pokemon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "characters")
public class PokemonCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Region is required")
    @Column(nullable = false)
    private String region;

    @NotBlank(message = "Type is required")
    @Column(nullable = false)
    private String type;

    private String image;

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 1000)
    private String description;

    // Default constructor
    public PokemonCharacter() {
    }

    // Constructor
    public PokemonCharacter(String name, String region, String type,
                            String image, String description) {
        this.name = name;
        this.region = region;
        this.type = type;
        this.image = image;
        this.description = description;
    }

    // Getters and Setters

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}