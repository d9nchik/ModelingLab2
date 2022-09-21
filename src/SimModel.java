import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(5);
        Process p1 = new Process(3.0);
        Process p2 = new Process(5.0);
        Process p3 = new Process(10.0);

        Switch s = new Switch();


        MultipleProcesses mp = new MultipleProcesses();
        mp.addProcess(p1);
        mp.addProcess(p2);
        mp.addProcess(p3);
        mp.setNextElement(s);

        c.setNextElement(mp);
        s.addElement(mp, 0.95);

        p1.setMaxqueue(5);
        p2.setMaxqueue(5);
        p3.setMaxqueue(5);

        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(mp);

        Model model = new Model(list);
        model.simulate(1000);
    }
}
