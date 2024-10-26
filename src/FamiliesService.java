import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class FamiliesService {
    public static void displayAllFamilies(List<Family> families){
        families.stream().forEach(family -> System.out.printf("%s%n", family.toString()));
    }
    public static List<Family>getFamiliesBiggerOrLessThan(List<Family> families,int count,boolean option){
        return (option)?families
                .stream()
                .filter(family -> family.CountFamily() > count)
                .collect(Collectors.toList()):
                families
                        .stream()
                        .filter(family -> family.CountFamily() < count)
                        .collect(Collectors.toList());
    }
    public static int countFamiliesWithMemberNumber(List<Family> families,int number){
        return families
                .stream()
                .filter(family -> family.CountFamily()==number)
                .collect(Collectors.toList())
                .size();
    }
    public static void deleteAllChildrenOlderThan(List<Family> families,int age){
        LocalDate edgeDate =LocalDate.of(LocalDate.now().getYear()-age,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
        families.forEach(family ->
                family.getChildren().removeIf(child ->
                        Instant.ofEpochMilli(child.getBirthDate())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .isBefore(edgeDate) // Перевірка, чи дата народження старша за граничну
                )
        );
    }
}
