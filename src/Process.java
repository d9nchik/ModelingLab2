public class Process extends Element {
    private int queue, maxqueue, failure;
    private double meanQueue;
    private double averageLoadSum;

    public Process(double delay) {
        super(delay);
        super.setName("PROCESSOR");
        super.setTnext(Double.POSITIVE_INFINITY);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    @Override
    public void inAct() {
        if (super.getState() == 0) {
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        } else {
            if (getQueue() < getMaxqueue()) {
                setQueue(getQueue() + 1);
            } else {
                failure++;
            }
        }
    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(Double.POSITIVE_INFINITY);
        super.setState(0);
        callNextElement();
        if (getQueue() > 0) {
            setQueue(getQueue() - 1);
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        }
    }

    private void callNextElement() {
        Element nextElement = super.getNextElement();
        if (nextElement != null) {
            nextElement.inAct();
        }
    }

    public int getFailure() {
        return failure;
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
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + this.getFailure());
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue += queue * delta;

        averageLoadSum += getState() * delta;
    }

    public double getMeanQueue(double allTime) {
        return meanQueue / allTime;
    }

    public double averageLoad(double allTime) {
        return averageLoadSum / allTime;
    }
}
