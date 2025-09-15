package app.repository;

import app.domain.Doctor;
import app.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorRepository {

    // Файл, который является базой данных
    private final File database;

    // Маппер для чтения и записи объектов в файл
    private final ObjectMapper mapper;

    // Поле, которое хранит максимальный идентификатор, сохраненный в БД
    private int maxId;

    //    Конструктор
    public DoctorRepository() throws IOException {
        database = new File("database/doctor.txt");
        mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //    Выясняем, какой идентификатор в БД на данный момент максимальный
        List<Doctor> doctors = findAll();
        if (!doctors.isEmpty()) {
            Doctor lastDoctor = doctors.get(doctors.size() - 1);
            maxId = lastDoctor.getId();
        }

    }

    //    Чтение списка всех врачей
    public List<Doctor> findAll() throws IOException {
        Doctor[] doctors = mapper.readValue(database, Doctor[].class);
        return new ArrayList<>(Arrays.asList(doctors));
    }

    //    Чтение одного врача из БД
    public Doctor findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //    Сохранение нового врача в БД
    public Doctor save(Doctor doctor) throws IOException {
        doctor.setId(++maxId);
        List<Doctor> doctors = findAll();
        doctors.add(doctor);
        mapper.writeValue(database, doctors);
        return doctor;
    }

    //    Изменение данных врача
    public void update(Doctor doctor) throws IOException {
        int id = doctor.getId();
        String newName = doctor.getName();
        boolean active = doctor.isActive();
        List<Pet> pets = doctor.getPets();

        List<Doctor> doctors = findAll();
        doctors
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setName(newName);
                    x.setActive(active);
                    x.setPets(pets);
                });
        mapper.writeValue(database, doctors);
    }

    //    Удалить врача
    public void deleteById(int id) throws IOException {
        List<Doctor> doctors = findAll();
        doctors.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, doctors);
    }
}