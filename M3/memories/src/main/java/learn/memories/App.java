package learn.memories;

import learn.memories.data.MemoryFileRepository;
import learn.memories.domain.MemoryService;
import learn.memories.ui.ConsoleIO;
import learn.memories.ui.Controller;
import learn.memories.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}
