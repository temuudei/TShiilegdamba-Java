public class Main {
    public static void main(String[] args) {
        Enclosure myEnclosure = new Enclosure();
        myEnclosure.setName("Turtle Exhibit");
        myEnclosure.setTemparature(31);

        Inhabitat myInhabitant = new Inhabitat();
        myInhabitant.setName("Box Turtle");
        myInhabitant.setFood("Lettuce");
        myInhabitant.setMin(28);
        myInhabitant.setMin(35);

        myEnclosure.setResident(myInhabitant);
        myEnclosure.getResident().setName("Steve");

        System.out.println("Our enclosure name is: " + myEnclosure.getName());
    }
}