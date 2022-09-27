import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(0.5);
        Process p1 = new Process(1);
        Process p2 = new Process(1);

        // Prepare
        p1.setDelayDeviation(0.3);
        p2.setDelayDeviation(0.3);
        p1.setDistribution("norm");
        p2.setDistribution("norm");
        p1.setMaxqueue(3);
        p2.setMaxqueue(3);
        for (int i = 0; i < 3; i++) {
            p1.inAct();
            p2.inAct();
        }
        c.setDistribution("exp");
        c.setTnext(0.1);

        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p1.setDelayMean(0.3);
        p2.setDelayMean(0.3);


        CarSwitch cs = new CarSwitch(p1, p2);
        c.setNextElement(cs);

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(p1);
        list.add(p2);
        list.add(cs);

        Model model = new Model(list);
        model.simulate(100);
    }
}
