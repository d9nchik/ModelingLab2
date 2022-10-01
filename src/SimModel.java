import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(15);
        ProcessWithPriority p1 = new ProcessWithPriority();
        ProcessWithPriority p2 = new ProcessWithPriority();

        ArrayList<Process> doctors = new ArrayList<>();
        doctors.add(p1);
        doctors.add(p2);

        MultipleProcessesWithPriority mpWithP = new MultipleProcessesWithPriority(doctors);
        mpWithP.setMaxqueue(Integer.MAX_VALUE);

        c.setNextElement(mpWithP);

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(mpWithP);

        Model model = new Model(list);
        model.simulate(100);
    }
}
