import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        double simulationTime = 1000;

        for (int i = 100; i < 1100; i += 100) {
            ArrayList<Element> list = getSMO(i - 1);

            long startTime = System.currentTimeMillis();

            Model model = new Model(list);
            model.setInfoPrinted(false);
            model.simulate(simulationTime);

            long endTime = System.currentTimeMillis();

            long duration = (endTime - startTime);

            double v = 0;
            for (Element e : list) {
                if (e instanceof Process p) {
                    v += p.averageLoad(simulationTime) / p.getDelay();
                } else if (e instanceof Create c) {
                    v += 1 / c.getDelay();
                }
            }
            System.out.printf("%5d\t%5f\t%d\n", i, v, duration);

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
