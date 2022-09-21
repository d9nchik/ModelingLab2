import java.util.ArrayList;

public class MultipleProcesses extends Element {
    private final ArrayList<Process> processes = new ArrayList<>();
    private int failure;
    private int queue, maxqueue;
    private double meanQueue;

    public MultipleProcesses() {
        super(0);
        super.setName("MULTI_PROCESSOR");
        super.setTnext(Double.POSITIVE_INFINITY);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    public void addProcess(Process process) {
        processes.add(process);
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }


    @Override
    public void inAct() {
        super.inAct();
        if (super.getState() == 0) {
            super.setState(1);
        }

        for (Process process : processes) {
            if (process.getState() == 0) {
                process.inAct();
                return;
            }
        }

        if (getQueue() < getMaxqueue()) {
            setQueue(getQueue() + 1);
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

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
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

                if (queue > 0) {
                    queue--;
                    process.inAct();
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

        meanQueue += queue * delta;
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
