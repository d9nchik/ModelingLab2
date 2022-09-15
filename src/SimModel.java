import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        double[] delayParameters = new double[]{1, 3, 5};
        for (double cParameter : delayParameters) {
            for (double p1Parameter : delayParameters) {
                for (double p2Parameter : delayParameters) {
                    for (double p3Parameter : delayParameters) {
                        Element.resetNexId();
                        System.out.printf("%d\t%d\t%d\t%d", (int) cParameter, (int) p1Parameter, (int) p2Parameter, (int) p3Parameter);

                        Create c = new Create(cParameter);
                        Process p1 = new Process(p1Parameter);
                        Process p2 = new Process(p2Parameter);
                        Process p3 = new Process(p3Parameter);

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
            }
        }
    }
}
