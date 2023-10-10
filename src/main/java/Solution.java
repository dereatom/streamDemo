import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Solution {
    public static void main(String[] args) {
        System.out.println(malesOnly(Person.persons()));

        names(Person.persons()).forEach(System.out::println);

        sortedByIncomeDesc().forEach(System.out::println);
        System.out.println("Distinct genders" +distinctGenders());
        sortedByIncomeDesc().forEach(System.out::println);
        System.out.println("List of people on the the list skipped First two people");
        skippedPeople().forEach(System.out::println);
        System.out.println("Person income > 8000" +anyPersonWithHighINcom());

        System.out.println("Person with Highest income" +personWithHighestIncome());

        if (personWithHighestIncome().isPresent()){
            Person p= personWithHighestIncome().get();
            System.out.println("Person with highest Income" +p);
        }

    }
     static List<String> malesOnly(List<Person> people) {
//        people= Person.persons();
        List<String> males = people.stream()
                .filter(Person::isMale)
                .map(Person::getName)
                .toList();
        return males;


     }
     static  List<String> names(List<Person> people){
        List<String> nemes = people.stream()
                .map(Person::getName)
                .toList();
        return nemes;
     }
     static List<Person> sortedByIncomeDesc(){
        List<Person> sortedList = Person.persons()
                .stream()
                .sorted(Comparator.comparing(Person::getIncome).reversed())
//                .sorted(Comparator.comparing(Person::getIncome))
                .toList();
        return sortedList;
     }
     static List<Person.Gender>  distinctGenders(){
        List<Person.Gender> genders = Person.persons()
                .stream()
                .map(Person::getGender)
                .distinct()
                .toList();
        return genders;
    }

    static List<Person> firstThreePeople(){
        List<Person> top3 = Person.persons()
                .stream()
                .limit(3)
                .toList();
        return top3;
    }
//    Skip (Intermediate Operation):**
//            - Skip the first 2 persons in the list
    static  List<Person> skippedPeople(){
        List<Person> skkiped = Person.persons()
                .stream()
                .skip(2)
                .toList();
        return skkiped;
    }

    static  void displayNames(){
        Person.persons()
                .stream()
                .peek(person -> System.out.println("Person name" +person))
                .forEach(System.out::println);

    }
    static boolean anyPersonWithHighINcom(){
     return    Person.persons()
                .stream()
                .anyMatch(p -> p.getIncome() >8000 );


    }
    static boolean isAllPeopleAreMale(){
       return Person.persons()
                .stream()
                .allMatch(Person::isMale);
    }
    static boolean noneHaveZeroIncome(){
      return  Person.persons()
                .stream()
                .noneMatch(p -> p.getIncome() ==0);
    }
    static long countFemale(){
      return   Person.persons()
                .stream()
                .filter(Person::isFemale)
                .count();
    }
   static  Optional <Person> personWithHighestIncome(){
     return    Person.persons()
                .stream()
                .max(Comparator.comparingDouble(Person::getIncome));
    }


}
