package app.controller;

import app.domain.Pet;
import app.exceptions.PetNotFoundException;
import app.exceptions.PetSaveException;
import app.exceptions.PetUpdateException;
import app.service.PetService;

import java.io.IOException;
import java.util.List;

public class PetController {

    private final PetService service;

    public PetController() throws IOException {
        service = new PetService();
    }

    public Pet save(String name, String species, String ownerName) throws IOException, PetSaveException {
        Pet pet = new Pet(name, species, ownerName);
        return service.save(pet);
    }

    public List<Pet> getAllActivePets() throws IOException {
        return service.getAllActivePets();
    }

    public Pet getActivePetById(int id) throws IOException, PetNotFoundException {
        return service.getActivePetById(id);
    }

    public void update(int id, String name, String species, String ownerName) throws IOException, PetUpdateException {
        Pet pet = new Pet(id, name, species, ownerName);
        service.update(pet);
    }

    public void deleteById(int id) throws IOException, PetNotFoundException {
        service.deleteById(id);
    }

    public void deleteByName(String name) throws IOException, PetNotFoundException {
        service.deleteByName(name);
    }

    public void restoreById(int id) throws IOException, PetNotFoundException {
        service.restoreById(id);
    }

    public int getActivePetsCount() throws IOException {
        return service.getActivePetsCount();
    }

    public int getActivePetsTotalCount() throws IOException {
        return service.getActivePetsTotalCount();
    }
}
