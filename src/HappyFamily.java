import java.util.*;

public class HappyFamily {
    public static void main(String[] args)  {
        Family family = InitialFamily();
        InteractWithPets(family,InitializePets());
    }
    public static  Family InitialFamily(){
        Man someMan = new Man("Олександр","Прокопчук",1997);
        Woman someWoman = new Woman("Ольга","Іванчук",1997);
        System.out.println(someMan);
        System.out.println(someWoman);
        System.out.println("Сім'я 1");
        LinkedHashMap<String,String> fatherSchedule = new LinkedHashMap<>(
                Map.of("Понеділок", "Полагодити машину","Вівторок", "Піти в спортзал",
                "Середа", "Робота з дому","Четвер", "Зустріч з друзями",
                "П'ятниця", "Грати у футбол","Субота", "Сімейний пікнік",
                "Неділя", "Відпочинок"));
        LinkedHashMap<String,String> motherSchedule = new LinkedHashMap<>(
                Map.of("Понеділок", "Візит до салону","Вівторок", "Йога",
                "Середа", "Закупи продуктів","Четвер", "Робота над проєктом",
                "П'ятниця", "Зустріч з подругами за кавою","Субота", "Сімейна прогулянка",
                "Неділя", "Відпочинок і читання книги"));
        Man father = new Man("Іван","Ковальчук",1998,87,null,null,null,fatherSchedule);
        System.out.println(father);
        father.repairCar();
        Woman mother = new Woman("Христина","Возненко",1999,90,null,null,null,motherSchedule);
        System.out.println(mother);
        mother.makeup();
        Family family = new Family(mother,father);
        System.out.println(family);
        System.out.println("Народжується дитина");
        Human child=mother.bornChild();
        System.out.println(child);
        System.out.println(family);
        return family;
    }
    public  static  void InteractWithPets(Family family,LinkedHashSet<Pet> pets){
        int count=0;
        System.out.println("Привітання через окремий метод для кожного окремо");
        for (Pet pet : pets) {
            family.setPets(pets);
            System.out.println(pet);
            pet.eat();
            pet.respond();
            family.getFather().greetPet(pet);
            family.getMother().greetPet(pet);
            if(pet instanceof Foulable){
                ((Foulable) pet).foul();
            }
            count++;
            if (count==pets.size()){
                System.out.println("Привітання через окремий унікальний метод класу для всіх одразу");
                family.getFather().greetPets();
                family.getMother().greetPets();
            }
        }
    }
    public  static LinkedHashSet<Pet> InitializePets(){

        LinkedHashSet<Pet> pets = new LinkedHashSet<>();
        Fish fish = new Fish(false,"Голд");
        pets.add(fish);
        Fish fish1 = new Fish(true,"Немо", 2, 50, null);
        pets.add(fish1);
        Dog dog = new Dog(false,"Бровко");
        pets.add(dog);
        Dog dog1 = new Dog(true,"Рекс", 4, 75, new LinkedHashSet<>(Arrays.asList("спати", "їсти", "грати","спати","їсти", "грати","спати")));
        pets.add(dog1);
        DomesticCat cat = new DomesticCat(false,"Мурчик");
        pets.add(cat);
        DomesticCat cat1 = new DomesticCat(true,"Васька", 3, 60, new LinkedHashSet<>(Arrays.asList("ловити мишей", "дряпати меблі","дряпати меблі")));
        pets.add(cat1);
        RoboCat roboCat = new RoboCat(false,"Кіт-Троник");
        pets.add(roboCat);
        RoboCat roboCat1 = new RoboCat(true,"Меха", 5, 85, new LinkedHashSet<>(Arrays.asList("сканувати простір", "лазити по шафах","лазити по шафах")));
        pets.add(roboCat1);
        Snake snake = new Snake(false,"Каа");
        pets.add(snake);
        Snake snake1 = new Snake(true,"Змійка", 2, 40, new LinkedHashSet<>(Arrays.asList("повзати швидко", "шипіти при небезпеці","шипіти при небезпеці")));
        pets.add(snake1);
        Turtle turtle = new Turtle(false,"Лео");
        pets.add(turtle);
        Turtle turtle1 = new Turtle(true,"Дон", 10, 30, new LinkedHashSet<>(Arrays.asList("плавати", "ховатися в панцирі","ховатися в панцирі")));
        pets.add(turtle1);
        Bird bird = new Bird(false,"Ківі");
        pets.add(bird);
        Bird bird1 = new Bird(true,"Піпа", 1, 55, new LinkedHashSet<>(Arrays.asList("щебетати", "літати навколо клітки","щебетати", "літати навколо клітки")));
        pets.add(bird1);
        return pets;
    }
}
