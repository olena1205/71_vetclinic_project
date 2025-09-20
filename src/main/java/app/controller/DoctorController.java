package app.controller;

import app.domain.Doctor;
import app.exceptions.DoctorNotFoundException;
import app.exceptions.DoctorSaveException;
import app.exceptions.PetNotFoundException;
import app.service.DoctorService;

import java.io.IOException;
import java.util.List;

public class DoctorController {

    private final DoctorService service;

    public DoctorController() throws IOException {
        service = new DoctorService();
    }

    public Doctor save(String name) throws IOException, DoctorSaveException {
        Doctor doctor = new Doctor(name);
        return service.save(doctor);
    }

    public List<Doctor> getAllActiveDoctors() throws IOException {
        return service.getAllActiveDoctors();
    }

    public Doctor getActiveDoctorById(int id) throws IOException, DoctorNotFoundException {
        return service.getActiveDoctorById(id);
    }

    public void update(int id, String name) throws IOException, DoctorSaveException {
        Doctor doctor = new Doctor(id, name);
        service.update(doctor);
    }

    public void deleteDoctorById(int id) throws IOException, DoctorNotFoundException {
        service.deleteDoctorById(id);
    }

    public void deleteByName(String name) throws IOException, DoctorNotFoundException {
        service.deleteByName(name);
    }

    public void restoreDoctorById(int id) throws IOException, DoctorNotFoundException {
        service.restoreDoctorById(id);
    }

    public int getActiveDoctorCount() throws IOException {
        return service.getActiveDoctorCount();
    }

    public int getDoctorPets(int id) throws IOException, DoctorNotFoundException {
        return service.getDoctorPets(id);
    }

    public void addPetToDoctorList(int doctorId, int petId) throws IOException, DoctorNotFoundException, PetNotFoundException {
        service.addPetToDoctorList(doctorId, petId);
    }

    public void removePetFromDoctorList(int doctorId, int petId) throws IOException, DoctorNotFoundException, PetNotFoundException {
        service.removePetFromDoctorList(doctorId, petId);
    }

    public void clearDoctorList(int id) throws IOException, DoctorNotFoundException {
        service.clearDoctorList(id);
    }
}
