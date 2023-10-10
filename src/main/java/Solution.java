import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(malesOnly(Person.persons()));

        names(Person.persons()).forEach(System.out::println);
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

}
