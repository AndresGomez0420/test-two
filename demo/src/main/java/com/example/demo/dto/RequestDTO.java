package com.example.demo.dto;
import lombok.Data; // Anotación de Lombok para getters/setters/constructor

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestDTO {
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String origin;
    private String location;
    private String image;
    private List<String> episode = new ArrayList<>();

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public List<String> getEpisode() { return episode; }
    public void setEpisode(List<String> episode) {
        this.episode = episode != null ? episode : new ArrayList<>();
    }
}

