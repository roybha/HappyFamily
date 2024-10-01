import java.util.Arrays;

public class HappyFamily {
    public static void main(String[] args)  {
        System.out.println("Сім'я 1");
        // Створюємо об'єкти Human та Pet з використанням конструкторів
        Human mother = new Human("Olena", "Ivanova", 35);
        Human father = new Human("Ivan", "Ivanov", 40);
        Family family = new Family(mother, father);

        Human child = new Human("Oleh", "Ivanov", 10);
        family.AddChild(child);

        String[][] schedule = {
                {"Понеділок", "Ходити в спортзал"},
                {"Вівторок", "Читати книги"}
        };
        child.setSchedule(schedule);

        Pet pet = new Pet("Dog", "Rex", 5, 75, new String[]{"їсти", "спати", "гратися"});
        family.setPet(pet);

        // Використання всіх методів класу Human
        child.setFamily(family);
        child.greetPet(); // "Привіт Rex"
        child.describePet(); // Описує вихованця: "У мене є Dog, йому 5. Він дуже хитрий"
        System.out.println(child.toString());

        // Використання всіх геттерів та сеттерів Human
        child.setName("Oleh");
        child.setSurname("Ivanov");
        child.setYear(10);
        child.setIq(90);

        System.out.println("Дитина: " + child.getName() + " " + child.getSurname());
        System.out.println("Вік: " + child.getYear());
        System.out.println("IQ: " + child.getIq());
        System.out.println("Розклад: " + Arrays.deepToString(child.getSchedule()));

        // Використання equals та hashCode в Human
        Human anotherChild = new Human("Oleh", "Ivanov", 10, 90, pet, mother, father, schedule);
        System.out.println("Діти однакові? " + child.equals(anotherChild));
        System.out.println("Хеш-код дитини: " + child.hashCode());

        // Використання всіх методів класу Pet
        pet.eat(); // "Я їм."
        pet.respond(); // "Привіт хазяїне. Мене звати Rex Мені 5. Я скучив!"
        pet.foul(); // "Потрібно добре замести сліди, бо буде халепа"

        // Використання всіх геттерів та сеттерів Pet
        pet.setSpecies("Cat");
        pet.setNickname("Murchyk");
        pet.setAge(3);
        pet.setTrickLevel(40);
        pet.setHabits(new String[]{"спати", "мурчати", "лазити по деревах"});

        System.out.println("Домашня тварина: " + pet.getSpecies() + ", " + pet.getNickname());
        System.out.println("Вік тварини: " + pet.getAge());
        System.out.println("Рівень хитрості: " + pet.getTrickLevel());
        System.out.println("Звички: " + String.join(", ", pet.getHabits()));

        // Використання equals та hashCode в Pet
        Pet anotherPet = new Pet("Cat", "Murchyk", 3, 40, new String[]{"спати", "мурчати", "лазити по деревах"});
        System.out.println("Тварини однакові? " + pet.equals(anotherPet));
        System.out.println("Хеш-код тварини: " + pet.hashCode());

        // Перевірка методу toString() у класі Pet
        System.out.println("Опис тварини: " + pet.toString());

        System.out.println("Сім'я 2");
        // Створюємо об'єкти Human для батьків
        Human mother4 = new Human("Олена", "Іванова", 1980);
        Human father4 = new Human("Олександр", "Іванов", 1978);

        // Створюємо об'єкт Family
        Family family4 = new Family(mother4, father4);

        // Додаємо дітей
        Human child1 = new Human("Андрій", "Іванов", 2005);
        Human child2 = new Human("Марія", "Іванова", 2008);
        family4.AddChild(child1);
        family4.AddChild(child2);

        // Виводимо кількість членів родини
        System.out.println("Кількість членів родини до видалення дитини: " + family4.CountFamily());

        // Видаляємо дитину за індексом 1 (другу дитину)
        boolean isDeleted = family4.DeleteChild(1);
        if (isDeleted) {
            System.out.println("Дитина була успішно видалена.");
        } else {
            System.out.println("Видалення не вдалося.");
        }


        // Виводимо кількість членів родини після видалення
        System.out.println("Кількість членів родини після видалення дитини: " + family4.CountFamily());

        System.out.println("Сім'я 3");
        // Створюємо об'єкти Human з використанням конструктора з параметрами
        Human mother5 = new Human("Olena", "Ivanova", 35);
        Human father5 = new Human("Ivan", "Ivanov", 40);
        Family family5 = new Family(mother5, father5);

        // Створюємо дитину з посиланнями на батьків
        Human child5 = new Human("Oleh", "Ivanov", 10, mother, father);


        // Створюємо домашню тварину з використанням конструктора
        Pet pet5 = new Pet("Dog", "Rex");
        family.setPet(pet5); // Встановлюємо тварину в сім'ї

        // Використання всіх методів класу Human для дитини
        child.setFamily(family5);
        String[][] schedule5 = {
                {"Понеділок", "Ходити в спортзал"},
                {"Вівторок", "Читати книги"}
        };
        child5.setSchedule(schedule5);

        // Виклик методів дитини
        child5.greetPet(); // "Привіт Rex"
        child5.describePet(); // "У мене є Dog, йому 5. Він дуже хитрий"
        System.out.println(child5.toString());

        // Виклик методів вихованця
        pet5.eat(); // "Я їм."
        pet5.respond(); // "Привіт хазяїне. Мене звати Rex. Мені 5. Я скучив!"
        pet5.foul(); // "Потрібно добре замести сліди, бо буде халепа"

        // Додаткове виведення інформації
        System.out.println("Дитина: " + child5.getName() + " " + child5.getSurname());
        System.out.println("Вік: " + child5.getYear());
        System.out.println("Розклад: " + Arrays.deepToString(child5.getSchedule()));
        System.out.println("Домашня тварина: " + pet5.getSpecies() + ", " + pet5.getNickname());
    }
}
