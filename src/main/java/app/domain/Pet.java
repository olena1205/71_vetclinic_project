package app.domain;

import java.util.Objects;

public class Pet {
    private int id;
    private String name;
    private String species;
    private boolean active;
    private String ownerName;

    public Pet() {
    }

    public Pet(int id, String name, String species, boolean active, String ownerName) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.active = active;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;
        return id == pet.id && active == pet.active && Objects.equals(name, pet.name) && Objects.equals(species, pet.species) && Objects.equals(ownerName, pet.ownerName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(species);
        result = 31 * result + Boolean.hashCode(active);
        result = 31 * result + Objects.hashCode(ownerName);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Pet id : %d ,имя питомца: %s,вид животного: %s ,active: %b,ownerName: %s",id,name,species
                ,active,ownerName);
    }
}
