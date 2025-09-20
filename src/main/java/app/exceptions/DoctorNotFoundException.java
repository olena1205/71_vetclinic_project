package app.exceptions;

public class DoctorNotFoundException extends Exception{

    public DoctorNotFoundException(int id) {
        super(String.format("Доктор с идентификатором %d не найден", id));
    }


    public DoctorNotFoundException(String name) {
        super(String.format("Доктор с именем %s не найден", name));
    }
}
