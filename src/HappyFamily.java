import java.util.Arrays;

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
        String[][] fatherSchedule = {
                {"Понеділок", "Полагодити машину"},
                {"Вівторок", "Піти в спортзал"},
                {"Середа", "Робота з дому"},
                {"Четвер", "Зустріч з друзями"},
                {"П'ятниця", "Грати у футбол"},
                {"Субота", "Сімейний пікнік"},
                {"Неділя", "Відпочинок"}
        };
        String[][] motherSchedule = {
                {"Понеділок", "Візит до салону"},
                {"Вівторок", "Йога"},
                {"Середа", "Закупи продуктів"},
                {"Четвер", "Робота над проєктом"},
                {"П'ятниця", "Зустріч з подругами за кавою"},
                {"Субота", "Сімейна прогулянка"},
                {"Неділя", "Відпочинок і читання книги"}
        };
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
    public  static  void InteractWithPets(Family family,Pet[] pets){
        for (Pet pet : pets) {
            family.setPet(pet);
            System.out.println(pet);
            pet.eat();
            pet.respond();
            family.getFather().greetPet();
            family.getMother().greetPet();
            if(pet instanceof Foulable){
                ((Foulable) pet).foul();
            }
        }
    }
    public  static Pet[] InitializePets(){

        Pet[] pets = new Pet[14];
        Fish fish = new Fish(false,"Голд");
        pets[0] = fish;
        Fish fish1 = new Fish(true,"Немо", 2, 50, null);
        pets[1] = fish1;
        Dog dog = new Dog(false,"Бровко");
        pets[2] = dog;
        Dog dog1 = new Dog(true,"Рекс", 4, 75, new String[]{"бігати за м'ячем", "охороняти будинок"});
        pets[3] = dog1;
        DomesticCat cat = new DomesticCat(false,"Мурчик");
        pets[4] = cat;
        DomesticCat cat1 = new DomesticCat(true,"Васька", 3, 60, new String[]{"ловити мишей", "дряпати меблі"});
        pets[5] = cat1;
        RoboCat roboCat = new RoboCat(false,"Кіт-Троник");
        pets[6] = roboCat;
        RoboCat roboCat1 = new RoboCat(true,"Меха", 5, 85, new String[]{"сканувати простір", "лазити по шафах"});
        pets[7] = roboCat1;
        Snake snake = new Snake(false,"Каа");
        pets[8] = snake;
        Snake snake1 = new Snake(true,"Змійка", 2, 40, new String[]{"повзати швидко", "шипіти при небезпеці"});
        pets[9] = snake1;
        Turtle turtle = new Turtle(false,"Лео");
        pets[10] = turtle;
        Turtle turtle1 = new Turtle(true,"Дон", 10, 30, new String[]{"плавати", "ховатися в панцирі"});
        pets[11] = turtle1;
        Bird bird = new Bird(false,"Ківі");
        pets[12] = bird;
        Bird bird1 = new Bird(true,"Піпа", 1, 55, new String[]{"щебетати", "літати навколо клітки"});
        pets[13] = bird1;
        return pets;
    }
}
