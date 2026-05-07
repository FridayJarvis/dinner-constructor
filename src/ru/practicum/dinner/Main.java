package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    final static DinnerConstructor dinnerConstructor = new DinnerConstructor();
    final static Scanner scanner = new Scanner(System.in);


    static void main() {
        boolean shouldContinue = true;
        String cmd;
        while (shouldContinue) {
            printMenu();
            cmd = scanner.nextLine();

            shouldContinue = handleCommand(cmd);
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static boolean handleCommand(final String command) {
        switch (command) {
            case "1":
                addNewDish();
                break;
            case "2":
                generateDishCombo();
                break;
            case "3":
                System.out.println("Вы вышли из программы!");
                return false;
        }

        return true;
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dinnerConstructor.addNewDish(dishType, dishName);

        System.out.println("Блюдо добавлено!");
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int comboNumber;
        while (true) {
            comboNumber = scanner.nextInt();

            if (comboNumber > 0) break;

            System.out.println("Вы ввели отрицательное число, введите число, большее нуля");
        }
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        //input dish types. To exit, press clear input (press just "Enter")
        ArrayList<String> selectedTypes = new ArrayList<>();
        while (!nextItem.isEmpty()) {
            if (dinnerConstructor.hasType(nextItem))
                selectedTypes.add(nextItem);
            else
                System.out.println("Такой тип блюд мы еще не умеем готовить. Попробуйте что-нибудь другое!");

            nextItem = scanner.nextLine();
        }

        // generation dish combos and output
        var generatedCombosList = dinnerConstructor.generateCombos(comboNumber, selectedTypes);
        for (int i = 0; i < comboNumber; i++) {
            System.out.println("Комбинация " + (i + 1) + '\n' + generatedCombosList.get(i));
        }
    }
}
