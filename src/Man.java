import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

final class Man extends Human{
    public Man(String name, String surname,int age){
        super(name,surname,age);
    }
    public Man(String name, String surname, int year, Woman mother, Man father){
        super(name,surname,year,mother,father);
    }
    public Man(String name, String surname, int year, int iq, LinkedHashSet<Pet> pets, Woman mother, Man father, LinkedHashMap<String,String> schedule){
        super(name,surname,year,iq,pets,mother,father,schedule);
    }
    @Override
    public void greetPets(){
        if (this.getFamily().getPets() != null && !this.getFamily().getPets().isEmpty()) {
            for (Pet pet : this.getFamily().getPets()) { // Проходимося по кожному улюбленцю
                String species = pet.getSpecies();
                switch (species) {
                    case "Dog":
                        System.out.printf("%s: Здоров собако", this.getClass().getSimpleName());
                        break;
                    case "Domestic Cat":
                        System.out.printf("%s: Здоров котяро", this.getClass().getSimpleName());
                        break;
                    case "Robot Cat":
                        System.out.printf("%s: Здоров роботе", this.getClass().getSimpleName());
                        break;
                    case "Fish":
                        System.out.printf("%s: Здоров рибище", this.getClass().getSimpleName());
                        break;
                    case "Snake":
                        System.out.printf("%s: Здоров зміюче", this.getClass().getSimpleName());
                        break;
                    case "Turtle":
                        System.out.printf("%s: Здоров черепаха", this.getClass().getSimpleName());
                        break;
                    case "Bird":
                        System.out.printf("%s: Здоров пташко", this.getClass().getSimpleName());
                        break;
                    case "Unknown":
                        System.out.printf("%s Здоров, хто б ти не був!", this.getClass().getSimpleName());
                        break;
                    default:
                        System.out.printf("%s: Привіт, невідомий!%n", this.getClass().getSimpleName());
                        break;
                }
                System.out.println("");
            }
        }
    }
    // Унікальний метод для чоловіків
    public void repairCar() {
        System.out.println("Ремонтую автомобіль.Перевірка двигуна...Замінюю мастило...Автомобіль відремонтовано!");
    }
}
