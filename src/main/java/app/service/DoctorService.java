
package app.service;

import app.domain.Doctor;
import app.domain.Pet;
import app.exceptions.DoctorNotFoundException;
import app.exceptions.DoctorSaveException;
import app.exceptions.PetNotFoundException;
import app.repository.DoctorRepository;

import java.io.IOException;
import java.util.List;

public class DoctorService {

    private final DoctorRepository repository;
    private final PetService petService;

    // Конструктор
    public DoctorService() throws IOException {
        repository = new DoctorRepository();
        petService = new PetService();
    }

    //   Сохранить доктора в базе данных
    public Doctor save(Doctor doctor) throws IOException, DoctorSaveException {
        if (doctor == null) {
            throw new DoctorSaveException("Доктор не может быть null");
        }

        String name = doctor.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new DoctorSaveException("Имя доктора не может быть пустым");
        }

        doctor.setActive(true);
        return repository.save(doctor);
    }

    //   Вернуть всех докторов из базы данных (активных).
    public List<Doctor> getAllActiveDoctors() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Doctor::isActive)
                .toList();
    }

    //    Вернуть одного доктора из базы данных по его идентификатору (если он активен).
    public Doctor getActiveDoctorById(int id) throws IOException, DoctorNotFoundException {
        Doctor doctor = repository.findById(id);

        if (doctor == null || !doctor.isActive()) {
            throw new DoctorNotFoundException(id);
        }
        return doctor;
    }

    //    Изменить одного доктора в базе данных по его идентификатору.
    public void update(Doctor doctor) throws DoctorSaveException, IOException {
        if (doctor == null) {
            throw new DoctorSaveException("Доктор не может быть null");
        }

        String name = doctor.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new DoctorSaveException("Имя доктора не может быть пустым");
        }
        doctor.setActive(true);
        repository.update(doctor);
    }

    //    Удалить доктора из базы данных по его идентификатору.
    public void deleteDoctorById(int id) throws IOException, DoctorNotFoundException {
        Doctor doctor = getActiveDoctorById(id);
        doctor.setActive(false);
        repository.update(doctor);
    }

    //    Удалить доктора из базы данных по его имени.
    public void deleteByName(String name) throws IOException, DoctorNotFoundException {
        Doctor doctor = getAllActiveDoctors()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new DoctorNotFoundException(name)
                );
        repository.update(doctor);

    }

    //    Восстановить удалённого доктора в базе данных по его идентификатору.
    public void restoreDoctorById(int id) throws IOException, DoctorNotFoundException {
        Doctor doctor = repository.findById(id);

        if (doctor != null) {
            doctor.setActive(true);
            repository.update(doctor);
        } else {
            throw new DoctorNotFoundException(id);
        }
    }

    //    Вернуть общее количество докторов в базе данных (активных).
    public int getActiveDoctorCount() throws IOException {
        return getAllActiveDoctors().size();
    }

    //    Вернуть список животных доктора по его идентификатору (если он активен).
    public int getDoctorPets(int id) throws IOException, DoctorNotFoundException {
        return Math.toIntExact(getActiveDoctorById(id)
                .getPets()
                .stream()
                .filter(Pet::isActive)
                .count());
    }


    //    Добавить животное в список доктора по его идентификатору (если оба активны)
    public void addPetToDoctorList(int doctorId, int petId) throws IOException, DoctorNotFoundException, PetNotFoundException {
        Doctor doctor = getActiveDoctorById(doctorId);
        Pet pet = petService.getActivePetById(petId);
        doctor.getPets().add(pet);
        repository.update(doctor);
    }

    //    Удалить животное из списка доктора по идентификатору
    public void removePetFromDoctorList(int doctorId, int petId) throws IOException, PetNotFoundException, DoctorNotFoundException {
        Doctor doctor = getActiveDoctorById(doctorId);
        Pet pet = petService.getActivePetById(petId);
        doctor.getPets().remove(pet);
        repository.update(doctor);
    }

    //    Полностью очистить список животных доктора по его идентификатору (если он активен)
    public void clearDoctorList(int id) throws IOException, DoctorNotFoundException {
        Doctor doctor = getActiveDoctorById(id);
        doctor.getPets().clear();
        repository.update(doctor);
    }
}



