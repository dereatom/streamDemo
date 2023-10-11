import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        System.out.println(malesOnly(Person.persons()));

        names(Person.persons()).forEach(System.out::println);


        sortedByIncomeDesc().forEach(System.out::println);
        System.out.println("Distinct genders" +distinctGenders());
        sortedByIncomeDesc().forEach(System.out::println);
        System.out.println("List of people on the the list skipped First two people");
        skippedPeople().forEach(System.out::println);
        System.out.println("Person income > 8000" +anyPersonWithHighINcome());

        System.out.println("First person is"+firstPerson());
        System.out.println("Any person "+ anyPerson());

        System.out.println("Person with Highest income" +personWithHighestIncome());
        if (personWithHighestIncome().isPresent()){
            Person p= personWithHighestIncome().get();
            System.out.println("Person with highest Income" +p);
        }
        System.out.println("Person with lowest income" +personWithLowestIncome());

        System.out.println("Group persons by Gender" +personByGender);
        System.out.println("Partitioned to males and Females" +malesAndFemales);
        System.out.println("Summarizing statics " +incomeStatics);
        System.out.println("All name of person:" +allNames);

        System.out.println("Total income of all persons:" + totalIncome);
        System.out.println("person with the highest income using CollectingAndThen" +personWithHighestIncome());
        System.out.println("persons where the key is the ID and the value is the person" +idToPersonMap);
        System.out.println("list of persons to a set:" +personSet);
        System.out.println(" Stream of 10 integers starting from 1:" +numbersStream );
        System.out.println("Unordered stream of the list of persons:" +unorderedPersons );
        System.out.println("Limit and skip to process only the second and third persons:" +secondAndThirdPersons);
        System.out.println("custom collector to concatenate the names of all persons:" +concatenatedNames );

    }
//    1. Filter the list of persons to include only males.
     static List<String> malesOnly(List<Person> people) {
//        people= Person.persons();
        List<String> males = people.stream()
                .filter(Person::isMale)
                .map(Person::getName)
                .toList();
        return males;


     }
//     2. - Map the list of persons to their names.
     static  List<String> names(List<Person> people){
        List<String> nemes = people.stream()
                .map(Person::getName)
                .toList();
        return nemes;
     }
//     3.  Sort the list of persons by their income in descending order.
     static List<Person> sortedByIncomeDesc(){
        List<Person> sortedList = Person.persons()
                .stream()
                .sorted(Comparator.comparing(Person::getIncome).reversed())
//                .sorted(Comparator.comparing(Person::getIncome))
                .toList();
        return sortedList;
     }
//     4. - Find the distinct genders in the list of persons.
     static List<Person.Gender>  distinctGenders(){
        List<Person.Gender> genders = Person.persons()
                .stream()
                .map(Person::getGender)
                .distinct()
                .toList();
        return genders;
    }
//5. - Limit the list of persons to the first 3.
    static List<Person> firstThreePeople(){
        List<Person> top3 = Person.persons()
                .stream()
                .limit(3)
                .toList();
        return top3;
    }
//  6.  Skip (Intermediate Operation):**
//            - Skip the first 2 persons in the list
    static  List<Person> skippedPeople(){
        List<Person> skkiped = Person.persons()
                .stream()
                .skip(2)
                .toList();
        return skkiped;
    }
// 7. Peek (Intermediate Operation):
//      Use peek to print the names of all persons in the list.
    static  void displayNames(){
        Person.persons()
                .stream()
                .peek(person -> System.out.println("Person name" +person))
                .forEach(System.out::println);
    }
// 8. FlatMap (Intermediate Operation):
//    - Map each person to a stream of characters representing their names.
List<Character> allCharsInNames = Person.persons().stream()
        .flatMap(person -> person.getName().chars().mapToObj(c -> (char) c))
        .collect(Collectors.toList());

//    9. Concatenating Streams (Intermediate Operation):
//   Create a new stream by concatenating two lists of persons.
    List<Person> list1 =Person.persons();
    List<Person> list2 =Person.persons();

List<Person> combinedPersons = Stream.concat(list1.stream(), list2.stream())
        .collect(Collectors.toList());

    //  10  AnyMatch (Terminal Operation):
//    - Check if any person's income is greater than 8000.0.
    static boolean anyPersonWithHighINcome(){
     return    Person.persons()
                .stream()
                .anyMatch(p -> p.getIncome() >8000 );


    }
// 11.AllMatch (Terminal Operation):
//- Check if all persons are male.
    static boolean isAllPeopleAreMale(){
       return Person.persons()
                .stream()
                .allMatch(Person::isMale);
    }
//   12.NoneMatch (Terminal Operation):
//      Check if none of the persons have zero income.
    static boolean noneHaveZeroIncome(){
      return  Person.persons()
                .stream()
                .noneMatch(p -> p.getIncome() ==0);
    }
// 13. Count (Terminal Operation):**
//          Count the number of persons.
    static long countFemale(){
      return   Person.persons()
                .stream()
                .filter(Person::isFemale)
                .count();
    }
//  14. FindFirst (Terminal Operation):
//            - Find the first person in the list.
    static Optional<Person> firstPerson() {
     return    Person.persons()
                .stream()
                .findFirst();
    }
//    15. FindAny (Terminal Operation)
//             Find any person in the list.
    static Optional <Person> anyPerson(){
        return Person.persons()
                .stream()
                .findAny();
    }
//  16. Max (Terminal Operation):
//      Find the person with the highest income.
   static  Optional <Person> personWithHighestIncome(){
     return    Person.persons()
                .stream()
                .max(Comparator.comparingDouble(Person::getIncome));
    }
//17 Min (Terminal Operation):
//        Find the person with the lowest income.
    static  Optional<Person> personWithLowestIncome(){
        return  Person.persons()
                .stream()
                .min(Comparator.comparingDouble(Person::getIncome));
    }
// 18. GroupingBy (Collector):**
//       Group the persons by gender.
    static Map<Person.Gender, List<Person>> personByGender= Person.persons()
        .stream()
        .collect(Collectors.groupingBy(Person::getGender));


//  19. PartitioningBy (Collector):
//      Partition the persons into male and female.
     static Map<Boolean, List<Person>> malesAndFemales = Person.persons()
        .stream()
        .collect(Collectors.partitioningBy(p -> p.getGender() == Person.Gender.MALE));

//  20. SummarizingDouble (Collector):
//      Calculate the sum, average, max, and min of incomes.
    static DoubleSummaryStatistics  incomeStatics =Person.persons()
        .stream()
        .collect(Collectors.summarizingDouble(Person::getIncome));

// 21. Joining (Collector):
//      Join the names of all persons into a single string.
    static String allNames = Person.persons()
        .stream()
        .map(Person::getName)
        .collect(Collectors.joining(","));
// 22. Reducing (Collector):
//     Calculate the total income of all persons.
    static Double totalIncome = Person.persons()
        .stream().map(Person::getIncome).reduce(0.0, Double::sum);
//    .collect(Collectors.reducing(0.0, Person::getIncome,Double::sum ));

// 23. CollectingAndThen (Collector):**
//         Find the person with the highest income using CollectingAndThen.
      static  Optional<Person> personWithHighestIncome = Optional.ofNullable(Person.persons()
        .stream()
        .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Person::getIncome)),
                Optional::get)));
// 24.ToMap (Collector):
//   Create a map of persons where the key is the ID and the value is the person.
        static Map<Long, Person> idToPersonMap = Person.persons()
        .stream()
        .collect(Collectors.toMap(Person::getId, person -> person));

//25. ToSet (Collector):
//     Convert the list of persons to a set.
    static Set<Person> personSet = Person.persons()
        .stream()
        .collect(Collectors.toSet());

//26.Parallel Stream (Intermediate Operation):
//    Use parallel stream to process the list of persons.
//static List<Person> personList = Person.persons()
//        .parallelStream()
//        .forEach(person-> System.out.println("Processing: " + personList));

//27. Iterating (Intermediate Operation):
// Use iterate to create a stream of 10 integers starting from 1.

    static Stream<Integer> numbersStream = Stream.iterate(1, n -> n + 1)
            .limit(10);
//28. **Unordered (Intermediate Operation):**
//    Use unordered to create an unordered stream of the list of persons.
static List<Person> unorderedPersons = Person.persons()
        .stream()
        .unordered()
        .collect(Collectors.toList());

//29. Limiting and Skipping (Intermediate Operation):
//     Use limit and skip to process only the second and third persons.

    static List<Person> secondAndThirdPersons = Person.persons()
            .stream()
            .skip(1)
            .limit(2)
            .collect(Collectors.toList());

    // 30. Custom Collector (Terminal Operation):
//     Create a custom collector to concatenate the names of all persons.
    static String concatenatedNames = Person.persons().stream()
        .map(Person::getName)
        .collect(CustomCollectors.concatenating());



}
