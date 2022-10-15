import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        ArrayList<Element> list = getSMO(10);
        Model model = new Model(list);
        model.setInfoPrinted(false);
        model.simulate(1000);
    }

    private static ArrayList<Element> getSMO(int N) {
        ArrayList<Element> list = new ArrayList<>();

        Create c = new Create(5);
        list.add(c);

        for (int i = 0; i < N; i++) {
            Process p = new Process(5.0);
            p.setDistribution("exp");
            list.get(list.size() - 1).setNextElement(p);
            list.add(p);
        }

        return list;
    }
}
