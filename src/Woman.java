import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.time.LocalDate;

final class Woman extends Human implements HumanCreator{
    public Woman(String name,String surname,int year){
        super(name,surname,year);
    }
    public Woman (String name,String surname,int year,Woman mother,Man father){
        super(name,surname,year,mother,father);
    }
    public Woman(String name, String surname, int year, int iq, LinkedHashSet<Pet> pets, Woman mother, Man father, LinkedHashMap<String,String> schedule){
        super(name,surname,year,iq,pets,mother,father,schedule);
    }
    @Override
    public void greetPets() {
        if (this.getFamily().getPets() != null && !this.getFamily().getPets().isEmpty()) {
            for (Pet pet : this.getFamily().getPets()) { // Проходимося по кожному улюбленцю
                String species = pet.getSpecies(); // Отримуємо вид тварини
                switch (species) {
                    case "Dog":
                        System.out.printf("%s: Привіт цуцику", this.getClass().getSimpleName());
                        break;
                    case "Domestic Cat":
                        System.out.printf("%s: Привіт котику", this.getClass().getSimpleName());
                        break;
                    case "Robo Cat":
                        System.out.printf("%s: Привіт роботику", this.getClass().getSimpleName());
                        break;
                    case "Fish":
                        System.out.printf("%s: Привіт рибонько", this.getClass().getSimpleName());
                        break;
                    case "Snake":
                        System.out.printf("%s: Привіт змієнько", this.getClass().getSimpleName());
                        break;
                    case "Turtle":
                        System.out.printf("%s: Привіт черепашко", this.getClass().getSimpleName());
                        break;
                    case "Bird":
                        System.out.printf("%s: Привіт пташечка", this.getClass().getSimpleName());
                        break;
                    case "Unknown":
                        System.out.printf("%s: Привіт, таємниче створіннячко", this.getClass().getSimpleName());
                        break;
                    default:
                        System.out.printf("%s: Привіт, невідомий улюбленець!", this.getClass().getSimpleName());
                        break;
                }
                System.out.println("");
            }
        }
    }
    public void makeup() {
        System.out.println("Швиденько підфарбовуюсь.Нанесення основи...Нанесення румянцю...Макіяж нанесено!");
    }

    @Override
    public Human bornChild() {
        String[] manNames = {"Антон","Олег","Богдан","Ігор","Андрій","Дмитро","Василь"};
        String[] womanNames={"Юля","Олена","Аня","Лера","Марічка","Настя","Іра"};
        Random rand = new Random();
        boolean childSex=rand.nextBoolean();
        Human child=(childSex) ? new Woman(womanNames[rand.nextInt(womanNames.length)],
                this.getFamily().getFather().getSurname(),LocalDate.now().getYear(),this,(Man)this.getFamily().getFather()) :
                new Man(manNames[rand.nextInt(manNames.length)],
                this.getFamily().getFather().getSurname(),LocalDate.now().getYear(),this,(Man)this.getFamily().getFather());
        child.setIq((this.getIq()+this.getFamily().getFather().getIq())/2);
        return child;
    }
}
