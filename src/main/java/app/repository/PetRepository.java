package app.repository;

import app.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetRepository {

        // Файл, который является базой данных
        private final File database;

        // Маппер для чтения и записи объектов в файл
        private final ObjectMapper mapper;

        // Поле, которое хранит максимальный идентификатор, сохраненный в БД
        private int maxId;

        // Конструктор
        // В этом конструкторе мы инициализируем все поля репозитория
        public PetRepository() throws IOException {
            database = new File("database/pet.txt");
            mapper = new ObjectMapper();

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Выясняем, какой идентификатор БД на данный момент максимальный
            List<Pet> pets = findAll();

            if (!pets.isEmpty()) {
                Pet lastPet = pets.get(pets.size() - 1);

                maxId = lastPet.getId();

            }
        }

        public Pet save(Pet pet) throws IOException {
            pet.setId(++maxId);
            List<Pet> pets = findAll();
            pets.add(pet);
            mapper.writeValue(database, pets);
            return pet;
        }

        // Чтение всех животных из БД
        public List<Pet> findAll() throws IOException {
            try {
                Pet[] pets = mapper.readValue(database, Pet[].class);

                return new ArrayList<>(Arrays.asList(pets));
            } catch (MismatchedInputException e) {
                return new ArrayList<>();
            }
        }

        // Чтение одного животного по id
        public Pet findById(int id) throws IOException {
            return findAll().
                    stream()
                    .filter(x -> x.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        // Обновление существующего животного
        public void update(Pet pet) throws IOException {
            int id = pet.getId();

            boolean active = pet.isActive();
            String newName = pet.getName();
            String newSpecies = pet.getSpecies();
            String newOwnerName = pet.getOwnerName();

            List<Pet> pets  = findAll();
            pets
                    .stream()
                    .filter(x -> x.getId() == id)
                    .forEach(x -> {
                        x.setActive(active);
                        x.setName(newName);
                        x.setSpecies(newSpecies);
                        x.setOwnerName(newOwnerName);
                            }
                    );

            mapper.writeValue(database, pets);
        }

        // Удаление животного
        public void deleteById(int id) throws IOException {
            List<Pet> pets = findAll();
            pets.removeIf(x -> x.getId() == id);
            mapper.writeValue(database,pets);
        }
    }