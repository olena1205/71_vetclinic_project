package app.repository;

import app.domain.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class doctorRepository {

    // Файл, который является базой данных
    private final File database;

    // Маппер для чтения и записи объектов в файл
    private final ObjectMapper mapper;

    // Поле, которое хранит максимальный идентификатор, сохраненный в БД
    private int maxId;

    public doctorRepository() {
        database = new File("database/doctor.txt");
        mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

    }

//    Методы:
//
//    findAll() — список всех врачей
//
//    findById(Long id) — найти врача
//
//    create(Doctor d) — добавить нового
//
//    update(Doctor d) — изменить данные
//
//    delete(Long id) — удалить врача
}