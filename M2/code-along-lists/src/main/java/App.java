import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("name1");
        stringList.add("name2");
        stringList.add("name3");

        List<Integer> intList = new ArrayList<>();
        
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }
}
