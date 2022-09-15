import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(1.0);
        Process p1 = new Process(3.0);
        Process p2 = new Process(5.0);
        Process p3 = new Process(10.0);

        c.setNextElement(p1);
        p1.setNextElement(p2);
        p2.setNextElement(p3);

        p1.setMaxqueue(5);
        p2.setMaxqueue(5);
        p3.setMaxqueue(5);

        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Model model = new Model(list);
        model.simulate(1000);
    }
}
