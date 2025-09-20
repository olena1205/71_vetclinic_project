package client;

import app.controller.DoctorController;
import app.controller.PetController;

import java.util.Scanner;

public class Client {

    private static DoctorController doctorController;
    private static PetController petController;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            doctorController = new DoctorController();
            petController = new PetController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Выберите желаемую операцию");
            System.out.println("1 - операции с докторами");
            System.out.println("2 - операции с питомцами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    doctorOperations();
                    break;
                case "2":
                    petOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }
    }

    public static void doctorOperations() {
        while (true) {
            try {
                System.out.println("Выберите операцию с докторами:");
                System.out.println("1 - сохранить доктора");
                System.out.println("2 - получить всех докторов");
                System.out.println("3 - получить доктора по идентификатору");
                System.out.println("4 - изменить доктора");
                System.out.println("5 - удалить доктора по идентификатору");
                System.out.println("6 - удалить доктора по имени");
                System.out.println("7 - восстановить доктора по идентификатору");
                System.out.println("8 - получить количество докторов");
                System.out.println("9 - добавить питомца доктору");
                System.out.println("10 - удалить питомца у доктора");
                System.out.println("11 - очистить список питомцев у доктора");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя доктора");
                        String name = scanner.nextLine();
                        System.out.println(doctorController.save(name));
                        break;
                    case "2":
                        doctorController.getAllActiveDoctors().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(doctorController.getActiveDoctorById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое имя доктора");
                        name = scanner.nextLine();
                        doctorController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        doctorController.deleteDoctorById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя доктора");
                        name = scanner.nextLine();
                        doctorController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        doctorController.restoreDoctorById(id);
                        break;
                    case "8":
                        System.out.println("Количество докторов: " +
                                doctorController.getActiveDoctorCount());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор доктора");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор питомца");
                        int petId = Integer.parseInt(scanner.nextLine());
                        doctorController.addPetToDoctorList(doctorId, petId);
                        break;
                    case "10":
                        System.out.println("Введите идентификатор доктора");
                        doctorId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор питомца");
                        petId = Integer.parseInt(scanner.nextLine());
                        doctorController.removePetFromDoctorList(doctorId, petId);
                        break;
                    case "11":
                        System.out.println("Введите идентификатор доктора");
                        doctorId = Integer.parseInt(scanner.nextLine());
                        doctorController.clearDoctorList(doctorId);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Это некорректный ввод!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public static void petOperations() {
        while (true) {
            try {
                System.out.println("Выберите операцию с питомцами:");
                System.out.println("1 - сохранить питомца");
                System.out.println("2 - получить всех питомцев");
                System.out.println("3 - получить питомца по идентификатору");
                System.out.println("4 - изменить питомца");
                System.out.println("5 - удалить питомца по идентификатору");
                System.out.println("6 - удалить питомца по имени");
                System.out.println("7 - восстановить питомца по идентификатору");
                System.out.println("8 - получить количество питомцев");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя питомца");
                        String name = scanner.nextLine();
                        System.out.println("Введите вид питомца");
                        String species = scanner.nextLine();
                        System.out.println("Введите имя владельца");
                        String owner = scanner.nextLine();
                        System.out.println(petController.save(name, species, owner));
                        break;
                    case "2":
                        petController.getAllActivePets().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(petController.getActivePetById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое имя");
                        name = scanner.nextLine();
                        System.out.println("Введите новый вид");
                        species = scanner.nextLine();
                        System.out.println("Введите нового владельца");
                        owner = scanner.nextLine();
                        petController.update(id, name, species, owner);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя питомца");
                        name = scanner.nextLine();
                        petController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество питомцев: " +
                                petController.getActivePetsCount());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Это некорректный ввод!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
