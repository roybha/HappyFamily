import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FamilyController {
    FamiliesService familyService = new FamiliesService(new CollectionFamilyDAO(new ArrayList<>()));
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            Optional<String> choice = getString("Оберіть опцію ");

            choice.ifPresentOrElse(opt -> {
                switch (opt.toLowerCase()) {
                    case "1":
                        loadEarlierData();
                        break;
                    case "2":
                        displayFamilies();
                        break;
                    case "3":
                        displayFamiliesMoreThan();
                        break;
                    case "4":
                        displayFamiliesLessThan();
                        break;
                    case "5":
                        countFamiliesEqualTo();
                        break;
                    case "6":
                        createFamily();
                        break;
                    case "7":
                        deleteFamily();
                        break;
                    case "8":
                        editFamily();
                        break;
                    case "9":
                        deleteChildrenOlderThan();
                        break;
                    case "10":
                        displayFamilyPets();
                        break;
                    case "11":
                        addSomePet();
                        break;
                    case "12":
                        saveData();
                        break;
                    case "13":
                        loadData();
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Невірний вибір, спробуйте ще раз.");
                        break;
                }
            }, () -> System.out.println("Не введено вибір, спробуйте ще раз."));
        }
    }

    private void printMenu() {
        System.out.println("Доступні команди:");
        System.out.println("1 -> Завантажити дані,збережені раніше");
        System.out.println("2 -> Відобразити весь список сімей");
        System.out.println("3 -> Відобразити список сімей, де кількість людей більша за задану");
        System.out.println("4 -> Відобразити список сімей, де кількість людей менша за задану");
        System.out.println("5 -> Підрахувати кількість сімей, де кількість членів дорівнює");
        System.out.println("6 -> Створити нову родину");
        System.out.println("7 -> Видалити сім'ю за індексом сім'ї у загальному списку");
        System.out.println("8 -> Редагувати сім'ю за індексом сім'ї у загальному списку");
        System.out.println("9 -> Видалити всіх дітей старше віку");
        System.out.println("10 -> Відобразити всіх домашніх улюбленців певної сім'ї");
        System.out.println("11 -> Додати домашнього улюбленця до певної сім'ї");
        System.out.println("12 -> Зберегти(записати) поточний список сімей у файл");
        System.out.println("13 -> Завантажити(прочитати) список сімей з файлу");
        System.out.println("exit -> Вихід");
    }
    private void loadEarlierData() {

        if(FamiliesService.getAllFamilies().isEmpty()) {
            familyService.loadData("families.dat");
            System.out.println("Заповнено раніше збереженими даними");
        }
        else{
            System.out.println("Сім'ї вже наявні в контролері");
        }

    }
    private void displayFamilies() {
        System.out.println("Всі сім'ї наявні в контролері");
        familyService.displayAllFamilies();
    }
    private void displayFamiliesMoreThan() {
        String message ="Введіть кількість осіб,БІЛЬШЕ за котру будуть відображені сім'ї ";
        Optional<Integer> count = getInt(message);
        count.ifPresentOrElse(value -> familyService.displayAllFamilies(familyService.getAllFamilies().stream().filter(family -> family.CountFamily()>value).collect(Collectors.toList())),
                () -> System.out.println("Кількість не була введена."));
    }
    private void displayFamiliesLessThan() {
        String message ="Введіть кількість осіб,МЕНШЕ за котру будуть відображені сім'ї ";
        Optional<Integer> count = getInt(message);
        count.ifPresentOrElse(value -> familyService.displayAllFamilies(familyService.collectionFamilyDAO.getAllFamilies().stream().filter(family -> family.CountFamily()<value).collect(Collectors.toList())),
                () -> System.out.println("Кількість не була введена."));
    }
    private void countFamiliesEqualTo() {
        String message = "Введіть кількість осіб в родині ";
        Optional<Integer> count = getInt(message);
        count.ifPresentOrElse(value -> {
            long familyCount = familyService.getAllFamilies().stream()
                    .filter(family -> family.CountFamily() == value)
                    .count();
            System.out.println("Кількість сімей з " + value + " особами: " + familyCount);
        }, () -> System.out.println("Кількість не була введена."));

    }
    private void createFamily() {
        Random random = new Random();
        System.out.println("Створення нової родини.");


        System.out.print("Введіть ім'я матері: ");
        String motherName = scanner.nextLine();

        System.out.print("Введіть прізвище матері: ");
        String motherSurname = scanner.nextLine();

        String motherBirthDateStr = getBirthDateStringFormat("Введіть дату народження матері (dd/MM/yyyy): ");
        int motherIq = getInt("Введіть IQ матері: ").orElse(random.nextInt(50, 100));
        scanner.nextLine();

        Woman mother;
        try {
            mother = new Woman(motherName, motherSurname, motherBirthDateStr, motherIq);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        System.out.print("Введіть ім'я батька: ");
        String fatherName = scanner.nextLine();

        System.out.print("Введіть прізвище батька: ");
        String fatherSurname = scanner.nextLine();

        String fatherBirthDateStr = getBirthDateStringFormat("Введіть дату народження батька (dd/MM/yyyy): ");
        int fatherIq = getInt("Введіть IQ батька: ").orElse(random.nextInt(50, 100));
        scanner.nextLine();
        Man father;
        try {
             father = new Man(fatherName, fatherSurname, fatherBirthDateStr, fatherIq);
        }catch (ParseException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        familyService.createNewFamily(mother, father);
        System.out.println("Нова родина була додана до списку сімей. ");
    }
    private void deleteFamily() {
        Optional<Integer> index = getInt("Введіть індекс родини, яку треба видалити ");
        index.ifPresentOrElse(value -> {
            if (value >= 0 && value < familyService.count()) {
                familyService.deleteFamilyByIndex(familyService.getAllFamilies().indexOf(familyService.getAllFamilies().get(value)));
                System.out.println("Сім'я з індексом " + value + " була видалена.");
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
                familyService.collectionFamilyDAO.logger.error("Сім'я за індексом "+value+" відсутня в списку сімей.Видалення не відбулося");
            }
        }, () -> System.out.println("Введено некоректний індекс"));
    }
    private void editFamily() {
        Optional<Integer> index = getInt("Введіть індекс родини, яку треба редагувати ");
        index.ifPresentOrElse(value -> {
            if (value >= 0 && value < familyService.count()) {
                Family familyToEdit = familyService.getFamilyById(value);
                try {
                    if(familyToEdit.CountFamily()>=10)
                    {
                        familyService.collectionFamilyDAO.logger.error("Неможливо додати нову дитину -> Переповнення сім'ї");
                        throw new FamilyOverflowException("Неможливо додати нову дитину -> Переповнення сім'ї");
                    }
                }catch (FamilyOverflowException exception) {
                    System.out.println(exception.getMessage());
                    familyService.collectionFamilyDAO.logger.error(exception.getMessage());
                    return;
                }
                Optional<Character> option =getChar("1-Народити дитину/2-Всиновити дитину ");
                option.ifPresentOrElse(childOption->{
                    switch (childOption) {
                        case '1':{
                            familyToEdit.getMother().bornChild();
                            familyService.getAllFamilies().set(value,familyToEdit);
                            System.out.println("Нова дитина народжена");
                            break;
                        }
                        case '2':{
                            familyService.adoptChild(familyToEdit,
                                    familyService.collectionFamilyDAO.generateSomeChild(familyToEdit));
                            familyService.getAllFamilies().set(value,familyToEdit);
                            System.out.println("Нова дитина всиновлена");
                            break;
                        }
                        case '3':return;
                        default: System.out.println("Введено неправильну опцію");break;
                    }
                },()-> System.out.println("Не введено опцію"));
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
                familyService.collectionFamilyDAO.logger.error("Індекс "+ value+" виходить за межі списку сімей.Оновлення не можливе");
            }
        }, () -> System.out.println("Не введено індекс"));
    }
    private void deleteChildrenOlderThan() {
        Optional<Integer> age = getInt("Введіть вік, діти вік яких БІЛЬШЕ даного - видаляться ");
        age.ifPresentOrElse(value -> {
            familyService.getAllFamilies().forEach(family -> {
                List<Human> childrenToRemove = family.getChildren().stream()
                        .filter(child -> {
                            long birthDateMillis = child.getBirthDate();
                            LocalDate birthDate = LocalDate.ofInstant(Instant.ofEpochMilli(birthDateMillis), ZoneId.systemDefault());
                            int childAge = LocalDate.now().getYear() - birthDate.getYear();
                            return childAge > value;
                        })
                        .collect(Collectors.toList());


                childrenToRemove.forEach(family::DeleteChild);
            });
            System.out.println("Діти старші за " + value + " років видалено з родин");
            familyService.collectionFamilyDAO.logger.error("Діти старші за " + value + " років видалено з родин");
        }, () -> System.out.println("Не введено коректний вік"));
    }

    private Optional<Integer> getInt(String message) {
        while (true) {

            System.out.print(message);
            scanner = new Scanner(System.in);


            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();

                if (input >= 0) {
                    return Optional.of(input);
                } else {
                    familyService.collectionFamilyDAO.logger.error("Число повинно бути більшим або дорівнювати 0.");
                    System.out.println("Число повинно бути більшим або дорівнювати 0.");
                }
            } else {

                System.out.println("Будь ласка, введіть коректне ціле число.");
                familyService.collectionFamilyDAO.logger.error("Введено число не типу int");
                scanner.next();
            }
        }
    }
    private Optional<Character> getChar(String message){
        System.out.print(message);
        scanner = new Scanner(System.in);
        if(scanner.hasNext()){
            char input = scanner.next().charAt(0);
            return Optional.of(input);
        }
        else {
            scanner.next();
            return Optional.empty();
        }
    }
    private Optional<String> getString(String message){
         scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.nextLine();
        return Optional.ofNullable(input.trim());
    }

    private String getBirthDateStringFormat(String message) {
        while (true) {
            System.out.print(message);
            String dateInput = scanner.nextLine();
            if (isValidDate(dateInput)) {
                return dateInput;
            } else {
                System.out.println("Невірний формат дати. Спробуйте ще раз (dd/MM/yyyy).");
                familyService.collectionFamilyDAO.logger.error("Невірний формат дати.");
            }
        }
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Family createSingleFamily(String motherName, String motherSurname, LocalDate motherBirthDate,
                              String fatherName, String fatherSurname, LocalDate fatherBirthDate) {
        Woman mother = new Woman(motherName, motherSurname,
                motherBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        Man father = new Man(fatherName, fatherSurname,
                fatherBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        Family family = new Family(mother,father);
        //family=familyService.bornChild(family);


        LinkedHashSet<Pet> pets = new LinkedHashSet<>(List.of(new Dog(true, "Рекс"),
                new Dog(false, "Багіра"), new Dog(true, "Пальма")));
        family.setPets(pets);

        return family;
    }
    private void displayFamilyPets(){
        Optional<Integer> index = getInt("Введіть індекс сім'ї,домашніх улюбленців якої ви хочете вивести на екран");
        index.ifPresentOrElse(value -> {
            if (value >= 0 && value < familyService.count()) {
                System.out.println("Тварини сім'ї");
                familyService.getPets(value)
                        .stream().forEach(pet -> System.out.println(pet.toString()));
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
            }
        }, () -> System.out.println("Не введено індекс"));
    }
    private void addSomePet() {
        Optional<Integer> familyIndex = getInt("Введіть індекс сім'ї, до якої ви хочете додати домашнього улюбленця");

        familyIndex.ifPresentOrElse(famIndex -> {
            if (famIndex >= 0 && famIndex < familyService.count()) {
                // Запит на введення деталей домашнього улюбленця
                Optional<String> petNameOpt = getString("Введіть ім'я домашнього улюбленця");

                if (petNameOpt.isPresent()) {
                    // Створення нового улюбленця
                    Pet newPet = new Dog(true, petNameOpt.get()); // Припустимо, у вас є конструктор у класі Pet

                    // Додавання нового улюбленця до сім'ї
                    familyService.addPet(famIndex, newPet);
                    System.out.println("Домашній улюбленець додано успішно.");
                } else {
                    System.out.println("Не було введено ім'я улюбленця.");
                    familyService.collectionFamilyDAO.logger.error("Не було введено ім'я улюбленця.");
                }
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
                familyService.collectionFamilyDAO.logger.error("Індекс виходить за межі списку сімей.");
            }
        }, () -> System.out.println("Не введено індекс сім'ї."));
    }
    private void saveData() {
        String filePath = getString("Введіть шлях до файлу для збереження даних: ").orElse("families.dat");
        familyService.saveData(filePath);
    }

    private void loadData() {
        String filePath = getString("Введіть шлях до файлу для завантаження даних: ").orElse("families.dat");
        familyService.loadData(filePath);
    }
}
