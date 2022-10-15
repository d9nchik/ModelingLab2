import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {

        for (int i = 10000; i < 100000; i += 10000) {
            ArrayList<Element> list = getSMO(i - 1);

            long startTime = System.currentTimeMillis();

            Model model = new Model(list);
            model.setInfoPrinted(false);
            model.simulate(1000);

            long endTime = System.currentTimeMillis();

            long duration = (endTime - startTime);
            System.out.printf("%5d\t%d\n", i, duration);
        }
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
