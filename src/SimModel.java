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

        Create c = new Create(1.0 / N);
        list.add(c);

        Switch s = new Switch();
        c.setNextElement(s);

        for (int i = 0; i < N; i++) {
            Process p = new Process(5.0);
            p.setDistribution("exp");
            s.addElement(p, 1.0 / (N + 1));
            list.add(p);
        }

        return list;
    }
}
