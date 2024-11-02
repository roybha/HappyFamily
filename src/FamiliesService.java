import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FamiliesService {
    public static CollectionFamilyDAO collectionFamilyDAO;
    public FamiliesService(CollectionFamilyDAO collectionFamilyDAO) {
        this.collectionFamilyDAO = collectionFamilyDAO;
    }
    public static void displayAllFamilies(){
        IntStream.range(0,collectionFamilyDAO.getAllFamilies().size()).forEach(i->System.out.printf("%d - %s%n",i+1, collectionFamilyDAO.getAllFamilies().get(i).prettyFormat()));
    }
    public static void displayAllFamilies(List<Family> families) {
        IntStream.range(0,families.size()).forEach(i->System.out.printf("%d - %s%n",i+1, families.get(i).prettyFormat()));
       // families.stream().forEach(family -> System.out.printf("%s%n", family.prettyFormat()));
    }
    public static List<Family> getAllFamilies() {
        return collectionFamilyDAO.getAllFamilies();
    }
    public static List<Family>getFamiliesBiggerOrLessThan(int count,boolean option){
        return (option)?collectionFamilyDAO.getAllFamilies()
                .stream()
                .filter(family -> family.CountFamily() > count)
                .collect(Collectors.toList()):
                collectionFamilyDAO.getAllFamilies()
                        .stream()
                        .filter(family -> family.CountFamily() < count)
                        .collect(Collectors.toList());
    }
    public static int countFamiliesWithMemberNumber(int number){
        return collectionFamilyDAO.getAllFamilies()
                .stream()
                .filter(family -> family.CountFamily()==number)
                .collect(Collectors.toList())
                .size();
    }
    public static void createNewFamily(Woman mother,Man father){
        Family newFamily = new Family(mother,father);
        collectionFamilyDAO.saveFamily(newFamily);
    }
    public static void deleteFamilyByIndex(int index){
        collectionFamilyDAO.deleteFamily(index);
    }
    public static Family bornChild(Family family){
         family.getMother().bornChild();
         collectionFamilyDAO.saveFamily(family);
         return family;
    }
    public static Family adoptChild(Family family,Human child){
        family.AddChild(child);
        collectionFamilyDAO.saveFamily(family);
        return family;
    }
    public static void deleteAllChildrenOlderThan(int age){
        LocalDate edgeDate =LocalDate.of(LocalDate.now().getYear()-age,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
        collectionFamilyDAO.getAllFamilies().forEach(family ->
                family.getChildren().removeIf(child ->
                        Instant.ofEpochMilli(child.getBirthDate())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .isBefore(edgeDate) // Перевірка, чи дата народження старша за граничну
                )
        );
    }
    public static int count(){
        return collectionFamilyDAO.getAllFamilies().size();
    }
    public static Family getFamilyById(int index){
        return collectionFamilyDAO.getAllFamilies().get(index);
    }
    public static LinkedHashSet<Pet> getPets(int index){
        return collectionFamilyDAO.getFamilyByIndex(index).getPets();
    }
    public static void addPet(int index,Pet pet){
        getPets(index).add(pet);
    }
}
