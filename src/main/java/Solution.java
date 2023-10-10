import java.util.List;

public class Solution {
    public static void main(String[] args) {
        System.out.println(malesOnly(Person.persons()));
    }
     static List<String> malesOnly(List<Person> people) {
//        people= Person.persons();
        List<String> males = people.stream()
                .filter(Person::isMale)
                .map(Person::getName)
                .toList();
        return males;
     }
}
