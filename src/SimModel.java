import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(1);
        Process p1 = new Process(3.0);
        Process p2 = new Process(3.0);

        MultipleProcesses mp = new MultipleProcesses();
        mp.addProcess(p1);
        mp.addProcess(p2);
        mp.setMaxqueue(15);

        c.setNextElement(mp);

        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(mp);

        Model model = new Model(list);
        model.simulate(1000);
    }
}
