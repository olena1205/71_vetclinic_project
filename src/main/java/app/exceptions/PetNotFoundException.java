package app.exceptions;


    public class PetNotFoundException extends Exception {

        public PetNotFoundException(int id) {
            super(String.format("Животное с идетификатором %d не найдено", id));
        }

        public PetNotFoundException(String name) {
            super(String.format("Животное с именем %s не найдено",name ));
        }

    }
