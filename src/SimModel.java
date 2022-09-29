import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(15);
        ProcessWithPriority p1 = new ProcessWithPriority();
        ProcessWithPriority p2 = new ProcessWithPriority();

        ArrayList<Process> doctors = new ArrayList<>();
        doctors.add(p1);
        doctors.add(p2);

        MultipleProcesses mp = new MultipleProcesses(doctors);

        c.setNextElement(mp);

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(mp);

        Model model = new Model(list);
        model.simulate(100);
    }
}
