import java.util.ArrayList;

public class Model {
    private final ArrayList<Element> list;
    double tnext, tcurr;
    Element event;

    public Model(ArrayList<Element> elements) {
        list = elements;
        tnext = 0.0;
        tcurr = tnext;
    }

    public void simulate(double time) {
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e;
                }
            }
            System.out.println("\nIt's time for event in " +
                    event.getName() +
                    ", time = " + tnext);
            for (Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Element e : list) {
                e.setTcurr(tcurr);
            }
            event.outAct();
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }
            printInfo();
        }
        printResult();
    }

    public void printInfo() {
        for (Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Element e : list) {
            e.printResult();
            if (e instanceof Process p) {
                System.out.println("mean length of queue = " +
                        p.getMeanQueue(tcurr)
                        + "\nfailure probability = " +
                        p.getFailure() / (double) p.getQuantity());
                System.out.println("Average load = " + p.averageLoad(tcurr));
            } else if (e instanceof MultipleProcesses mp) {
                for (Process p : mp.getProcesses()) {
                    System.out.println("Average load = " + p.averageLoad(tcurr));
                }
                System.out.println("failure probability = " + mp.getFailure() / (double) mp.getQuantity());

                System.out.println("mean length of queue = " +
                        mp.getMeanQueue(tcurr));
            }
        }
    }
}
