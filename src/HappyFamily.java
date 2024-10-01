import java.util.Arrays;

public class HappyFamily {
    public static void main(String[] args)  {
        // Використання конструктора Pet з двома параметрами
        Pet pet1 = new Pet("Dog", "Buddy");

        // Використання конструктора Pet з усіма параметрами
        String[] habits = {"eat", "sleep", "play"};
        Pet pet2 = new Pet("Cat", "Whiskers", 3, 70, habits);

        // Використання геттерів та сеттерів для Pet
        pet1.setAge(5);
        pet1.setTrickLevel(60);
        pet1.setHabits(new String[]{"bark", "run"});

        //Метод toString для Pet
        System.out.println(pet1.toString());
        System.out.println(pet2.toString());

        // Виклик методів Pet
        pet1.eat();
        pet1.respond();
        pet1.foul();

        // Використання конструкторів Human
        Human testHuman= new Human();
        Human mother = new Human("Anna", "Doe", 1975);
        Human father = new Human("John", "Doe", 1970);

        Human child1 = new Human("Tom", "Doe", 2000, mother, father);
        String[][] schedule = {
                {"Monday", "Go to school"},
                {"Tuesday", "Play football"},
                {"Wednesday", "Do homework"}
        };
        Human child2 = new Human("Sarah", "Doe", 2003, 120, pet1, mother, father, schedule);

        // Використання геттерів та сеттерів для Human
        child1.setIq(100);
        child1.setPet(pet2);
        child1.setSchedule(new String[][]{{"Thursday", "Visit grandparents"}});


        // Виклик методів Human
        child1.greetPet();
        child1.describePet();

        // Демонстрація методу toString()
        System.out.println(child1.toString());
        System.out.println(child2.toString());

        // Перевірка equals та hashCode
        System.out.println("child1 equals child2: " + child1.equals(child2));
        System.out.println("child1 hashCode: " + child1.hashCode());
        System.out.println("child2 hashCode: " + child2.hashCode());

        System.out.println("pet1 equals pet2: " + pet1.equals(pet2));
        System.out.println("pet1 hashCode: " + pet1.hashCode());
        System.out.println("pet2 hashCode: " + pet2.hashCode());
    }
}
