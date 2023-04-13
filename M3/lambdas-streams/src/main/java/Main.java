import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //1
        List<Integer> even = new ArrayList<>();
        even.add(1);
        even.add(2);
        even.add(3);
        even.add(4);
        even.add(5);
        int sumOfEven = even.stream().filter(e->e % 2 == 0).reduce(0, (a,b) -> a + b);
        System.out.println(sumOfEven);

        //2
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("Hi");
        listOfStrings.add("JohnWhick");
        listOfStrings.add("There");

        listOfStrings.stream().filter(e -> e.length() > 5).collect(Collectors.toList()).forEach(System.out::println);

        //3
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        int maxValue = numbers.stream().max(Integer::compare).get();
        System.out.println(maxValue);

        //4
        listOfStrings.stream().forEach(e -> System.out.println(e.toUpperCase()));

        //5
        int product = numbers.stream().reduce(1, (a,b) -> a * b);
        System.out.println(product);

        //6
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person("John", 25, 1000));
        listPerson.add(new Person("Tim", 17, 3000));
        listPerson.add(new Person("David", 16, 4000));
        listPerson.stream().filter(e -> e.getAge() < 18).forEach(e -> System.out.println(e.getAge()));

        //7
        double average = numbers.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(average);

        //8
        listOfStrings.stream().sorted((a,b) -> a.compareTo(b)).forEach(System.out::println);

        //9
        Optional<Person> salary = listPerson.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).findFirst();
        System.out.println(salary.get().getSalary());
    }
}
