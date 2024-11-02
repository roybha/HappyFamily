import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CollectionFamilyDAO  implements FamilyDAO {
    private static final List<String> maleNames = Arrays.asList("Андрій","Богдан","Євген","Олександр","Дмитро","Роман","Oлексій","Ілля");
    private static final List<String> femaleNames = Arrays.asList("Оля","Ганна","Тая","Вероніка","Олена","Юля","Олеся","Віка");
    private  List<Family> families;
    public CollectionFamilyDAO(List<Family> families) {
        this.families = families;
    }

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        if (!families.stream().anyMatch(f->f==family)) {
            families.add(family);
        } else {
            int index = families.indexOf(family);
            families.set(index, family);
        }
    }
    public static Human generateSomeChild(Family family){
        Random random = new Random();
        int sex = random.nextInt(0,2);
        String name = (sex==0)?maleNames.get(random.nextInt(maleNames.size())):femaleNames.get(random.nextInt(femaleNames.size()));

        int year = random.nextInt(2000, 2025);


        int month = random.nextInt(1, 13);


        int day;
        switch (month) {
            case 2: // Лютий
                // Перевіряємо, чи є рік високосним
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    day = random.nextInt(1, 30); // 29 днів у високосному році
                } else {
                    day = random.nextInt(1, 29); // 28 днів у невисокосному році
                }
                break;
            case 4: case 6: case 9: case 11: // Квітень, Червень, Вересень, Листопад
                day = random.nextInt(1, 31); // 30 днів
                break;
            default: // Січень, Березень, Травень, Липень, Серпень, Жовтень, Грудень
                day = random.nextInt(1, 32); // 31 день
                break;
        }


        LocalDate date = LocalDate.of(year, month, day);

        long dateInMillis = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Human child = (sex==0)?
                new Man(name,family.getFather().getSurname(),dateInMillis):
                new Woman(name,family.getFather().getSurname(),dateInMillis);
        return child;
    }
}
