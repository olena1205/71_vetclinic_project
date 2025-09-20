package app.service;

import app.domain.Pet;
import app.exceptions.PetNotFoundException;
import app.exceptions.PetSaveException;
import app.exceptions.PetUpdateException;
import app.repository.PetRepository;

import java.io.IOException;
import java.util.List;

public class PetService {
    private final PetRepository repository;

    public PetService() throws IOException {
        repository = new PetRepository();
    }

    //   Сохранить животное в базе данных (при сохранении животное автоматически считается активным).
    public Pet save(Pet pet) throws PetSaveException, IOException {

        String name = pet.getName();
        String ownerName = pet.getOwnerName();
        String species = pet.getSpecies();

        if (pet == null) {
            throw new PetSaveException("Животное не может быть null");
        }


        if (name == null || name.trim().isEmpty()) {
            throw new PetSaveException("Имя животного не может быть пустым");
        }

        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new PetSaveException("Имя владельца не может быть пустым");
        }

        if (species == null || species.trim().isEmpty()) {
            throw new PetSaveException("Необходимо указать вид животного");
        }

        pet.setActive(true);
        return repository.save(pet);
    }

    //   Вернуть всех животных из базы данных (активные).
    public List<Pet> getAllActivePets() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Pet::isActive)
                .toList();
    }

    //   Вернуть одного животного из базы данных по его идентификатору (если он активен).
    public Pet getActivePetById(int id) throws IOException, PetNotFoundException {
        Pet pet = repository.findById(id);

        if (pet == null || !pet.isActive()) {
            throw new PetNotFoundException(id);
        }
        return pet;
    }

    //   Изменить одно животное в базе данных по его идентификатору.
    public void update(Pet pet) throws PetUpdateException, IOException {
        if (pet == null) {
            throw new PetUpdateException("Животное не может быть null");
        }

        if (pet.getName().trim().isEmpty()) {
            throw new PetUpdateException("Животное должно иметь имя");
        }

        if (pet.getSpecies().trim().isEmpty()) {
            throw new PetUpdateException("Необходимо указать вид животного");
        }

        pet.setActive(true);
        repository.update(pet);
    }

    //   Удалить животное из базы данных по его идентификатору.
    // По требованию должно происходить soft удаление - изменение статуса активности продукта
    public void deleteById(int id) throws IOException, PetNotFoundException {
        Pet pet = getActivePetById(id);
        pet.setActive(false);
        repository.update(pet);
    }

    //   Удалить животное из базы данных по его наименованию.
    public void deleteByName(String name) throws IOException, PetNotFoundException {
        Pet pet = getAllActivePets()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new PetNotFoundException(name)
                );
        repository.update(pet);
    }


    //   Восстановить удаленное животное в базе данных по его идентификатору.
    public void restoreById(int id) throws IOException, PetNotFoundException {
        Pet pet = repository.findById(id);

        if (pet != null) {
            pet.setActive(true);
            repository.update(pet);
        } else {
            throw new PetNotFoundException(id);
        }
    }

    //   Вернуть общее количество животных в базе данных (активных).
    public int getActivePetsCount() throws IOException {
        return getAllActivePets().size();
    }

    //   Вернуть общее количество животных в базе данных по id.
    public int getActivePetsTotalCount() throws IOException {
        return Math.toIntExact(getAllActivePets()
                .stream()
                .mapToInt(Pet::getId)
                .count());
    }

}
