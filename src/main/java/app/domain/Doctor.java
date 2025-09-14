package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {
    private int id;
    private String name;
    private boolean active;
    private List<Pet> pets = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(int id, String name, boolean active, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.pets = pets;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;
        return id == doctor.id && active == doctor.active && Objects.equals(name, doctor.name) && Objects.equals(pets, doctor.pets);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Boolean.hashCode(active);
        result = 31 * result + Objects.hashCode(pets);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Доктор: %d, имя :%s, доступен:%b,pets:%s",id,name,active,pets);
    }
}
