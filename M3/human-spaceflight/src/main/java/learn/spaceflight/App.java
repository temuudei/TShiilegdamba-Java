package learn.spaceflight;

import learn.spaceflight.personnel.Astronaut;
import learn.spaceflight.spacecraft.InterstellarTransport;
import learn.spaceflight.spacecraft.MoonHopper;
import learn.spaceflight.spacecraft.Probe;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        // Spring code here...
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-configuration.xml");
        InterstellarTransport interstellarTransport = container.getBean(InterstellarTransport.class);
        Probe one = container.getBean(Probe.class);
        Probe two = container.getBean(Probe.class);
        System.out.println(one);
        System.out.println(two);
        two.addDistance(25000);
        System.out.println(one);
        System.out.println(two);
    }
}
