import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class FamilyController {
    private List<Family> families = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final List<String> maleNames = Arrays.asList("Андрій","Богдан","Євген","Олександр","Дмитро","Роман","Oлексій","Ілля");
    private static final List<String> femaleNames = Arrays.asList("Оля","Ганна","Тая","Вероніка","Олена","Юля","Олеся","Віка");
    private static final Random random = new Random();

    public void start() {
        while (true) {
            printMenu();
            Optional<String> choice = getString("Оберіть опцію ");

            choice.ifPresentOrElse(opt -> {
                switch (opt.toLowerCase()) {
                    case "1":
                        fillTestData();
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
        System.out.println("1 -> Заповнити тестовими даними");
        System.out.println("2 -> Відобразити весь список сімей");
        System.out.println("3 -> Відобразити список сімей, де кількість людей більша за задану");
        System.out.println("4 -> Відобразити список сімей, де кількість людей менша за задану");
        System.out.println("5 -> Підрахувати кількість сімей, де кількість членів дорівнює");
        System.out.println("6 -> Створити нову родину");
        System.out.println("7 -> Видалити сім'ю за індексом сім'ї у загальному списку");
        System.out.println("8 -> Редагувати сім'ю за індексом сім'ї у загальному списку");
        System.out.println("9 -> Видалити всіх дітей старше віку");
        System.out.println("exit -> Вихід");
    }
    private char getUserInput() {
        while (true) {
            try {
                return scanner.nextLine().charAt(0);
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть правильний номер команди.");
            }
        }
    }
    private void fillTestData() {

        if(families.isEmpty()) {
            families.add(createSingleFamily("Анні", "Іванова", LocalDate.of(1989, Month.MARCH, 1),
                    "Іван", "Іванов", LocalDate.of(1989, Month.APRIL, 1)));
            families.add(createSingleFamily("Марія", "Петриченко", LocalDate.of(1985, Month.JANUARY, 15),
                    "Олег", "Петриченко", LocalDate.of(1985, Month.FEBRUARY, 20)));
            families.add(createSingleFamily("Ірина", "Соколенко", LocalDate.of(1990, Month.MAY, 10),
                    "Василь", "Соколенко", LocalDate.of(1988, Month.JUNE, 30)));
            families.add(createSingleFamily("Олена", "Коваль", LocalDate.of(1992, Month.AUGUST, 5),
                    "Сергій", "Коваль", LocalDate.of(1987, Month.SEPTEMBER, 25)));
            families.add(createSingleFamily("Наталія", "Таран", LocalDate.of(1995, Month.NOVEMBER, 1),
                    "Андрій", "Таран", LocalDate.of(1990, Month.OCTOBER, 12)));
            families.add(createSingleFamily("Тетяна", "Шевченко", LocalDate.of(1988, Month.DECEMBER, 20),
                    "Павло", "Шевченко", LocalDate.of(1986, Month.JULY, 15)));
            families.add(createSingleFamily("Катерина", "Гриценко", LocalDate.of(1993, Month.APRIL, 10),
                    "Микола", "Гриценко", LocalDate.of(1984, Month.AUGUST, 2)));
            System.out.println("Заповнено тестовими даними");
        }
        else{
            System.out.println("Сім'ї вже наявні в контролері");
        }

    }
    private void displayFamilies() {
        System.out.println("Всі сім'ї наявні в контролері");
        FamiliesService.displayAllFamilies(families);
    }
    private void displayFamiliesMoreThan() {
        String message ="Введіть кількість осіб,БІЛЬШЕ за котру будуть відображені сім'ї ";
        Optional<Integer> count = getInt(message);
        List<Family> familyList = new ArrayList<>();
        count.ifPresentOrElse(value -> FamiliesService.displayAllFamilies(families.stream().filter(family -> family.CountFamily()>value).collect(Collectors.toList())),
                () -> System.out.println("Кількість не була введена."));
    }
    private void displayFamiliesLessThan() {
        String message ="Введіть кількість осіб,МЕНШЕ за котру будуть відображені сім'ї ";
        Optional<Integer> count = getInt(message);
        count.ifPresentOrElse(value -> FamiliesService.displayAllFamilies(families.stream().filter(family -> family.CountFamily()<value).collect(Collectors.toList())),
                () -> System.out.println("Кількість не була введена."));
    }
    private void countFamiliesEqualTo() {
        String message = "Введіть кількість осіб в родині ";
        Optional<Integer> count = getInt(message);
        count.ifPresentOrElse(value -> {
            long familyCount = families.stream()
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


        Family newFamily = new Family(mother,father);

        families.add(newFamily);
        System.out.println("Нова родина була додана до списку сімей. ");
    }
    private void deleteFamily() {
        Optional<Integer> index = getInt("Введіть індекс родини, яку треба видалити ");
        index.ifPresentOrElse(value -> {
            if (value >= 0 && value < families.size()) {
                families.remove((int) value);
                System.out.println("Сім'я з індексом " + value + " була видалена.");
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
            }
        }, () -> System.out.println("Введено некоректний індекс"));
    }
    private void editFamily() {
        Optional<Integer> index = getInt("Введіть індекс родини, яку треба редагувати ");
        index.ifPresentOrElse(value -> {
            if (value >= 0 && value < families.size()) {
                Family familyToEdit = families.get(value);
                try {
                    if(familyToEdit.CountFamily()>=10)
                        throw new FamilyOverflowException("Неомжливо додати нову дитину -> Переповнення сім'ї");
                }catch (FamilyOverflowException exception) {
                    System.out.println(exception.getMessage());
                    return;
                }
                Optional<Character> option =getChar("1-Народити дитину/2-Всиновити дитину ");
                option.ifPresentOrElse(childOption->{
                    switch (childOption) {
                        case '1':{
                            familyToEdit.getMother().bornChild();
                            families.set(value,familyToEdit);
                            System.out.println("Нова дитина народжена");
                            break;
                        }
                        case '2':{
                            familyToEdit.AddChild(generateSomeChild(familyToEdit));
                            families.set(value,familyToEdit);
                            System.out.println("Нова дитина всиновлена");
                            break;
                        }
                        case '3':return;
                        default: System.out.println("Введено неправильну опцію");break;
                    }
                },()-> System.out.println("Не введено опцію"));
            } else {
                System.out.println("Індекс виходить за межі списку сімей.");
            }
        }, () -> System.out.println("Не введено індекс"));
    }
    private void deleteChildrenOlderThan() {
        Optional<Integer> age = getInt("Введіть вік, діти вік яких БІЛЬШЕ даного - видаляться ");
        age.ifPresentOrElse(value -> {
            families.forEach(family -> {
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
                    System.out.println("Число повинно бути більшим або дорівнювати 0.");
                }
            } else {

                System.out.println("Будь ласка, введіть коректне ціле число.");
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
    private Human generateSomeChild(Family familyToEdit){
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
                new Man(name,familyToEdit.getFather().getSurname(),dateInMillis):
                new Woman(name,familyToEdit.getFather().getSurname(),dateInMillis);
        return child;
    }

    private Family createSingleFamily(String motherName, String motherSurname, LocalDate motherBirthDate,
                              String fatherName, String fatherSurname, LocalDate fatherBirthDate) {
        Human mother = new Woman(motherName, motherSurname,
                motherBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        Human father = new Man(fatherName, fatherSurname,
                fatherBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());

        Family family = new Family((Woman) mother, (Man) father);

        Human child = generateSomeChild(family);

        family.AddChild(child);

        LinkedHashSet<Pet> pets = new LinkedHashSet<>(List.of(new Dog(true, "Рекс"),
                new Dog(false, "Багіра"), new Dog(true, "Пальма")));
        family.setPets(pets);

        return family;
    }
}
