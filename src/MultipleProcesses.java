import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultipleProcesses extends Element {
    private final ArrayList<Process> processes;
    private int failure;

    private final Queue<Integer> queue = new LinkedList<>();
    private int maxqueue;
    private double meanQueue;

    public MultipleProcesses(ArrayList<Process> processes) {
        super(0);
        this.processes = processes;
        super.setName("MULTI_PROCESSOR");
        super.setTnext(Double.POSITIVE_INFINITY);
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }


    @Override
    public void inAct(int clientStatus) {
        super.inAct(clientStatus);
        if (super.getState() == 0) {
            super.setState(1);
        }

        for (Process process : processes) {
            if (process.getState() == 0) {
                process.inAct(clientStatus);
                return;
            }
        }

        if (queue.size() < getMaxqueue()) {
            queue.add(clientStatus);
        } else {
            failure++;
        }
    }

    public double getTnext() {
        double tnext = Double.POSITIVE_INFINITY;

        for (Process process : processes) {
            if (process.getTnext() < tnext) {
                tnext = process.getTnext();
            }
        }

        setTnext(tnext);
        if (Double.isInfinite(tnext)) {
            setState(0);
        } else {
            setState(1);
        }

        return tnext;
    }

    public int getMaxqueue() {
        return maxqueue;
    }

    public void setMaxqueue(int maxqueue) {
        this.maxqueue = maxqueue;
    }

    @Override
    public void outAct() {
        super.outAct();

        double tnext = getTnext();

        for (Process process : processes) {
            if (process.getTnext() == tnext) {
                process.outAct();

                if (!queue.isEmpty()) {
                    process.inAct(queue.poll());
                }
            }
        }

    }

    @Override
    public void setNextElement(Element nextElement) {
        for (Process process : processes) {
            process.setNextElement(nextElement);
        }
    }

    public int getFailure() {
        return failure;
    }


    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + this.getFailure());
        for (Process process : processes) {
            process.printInfo();
        }
    }

    @Override
    public void doStatistics(double delta) {
        for (Process process : processes) {
            process.doStatistics(delta);
        }

        meanQueue += queue.size() * delta;
    }

    public double getMeanQueue(double allTime) {
        return meanQueue / allTime;
    }

    @Override
    public void setTcurr(double tcurr) {
        super.setTcurr(tcurr);

        for (Process process : processes) {
            process.setTcurr(tcurr);
        }
    }
}
