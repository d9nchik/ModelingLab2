import java.util.LinkedList;
import java.util.Queue;

public class Process extends Element {
    protected Queue<Client> queue = new LinkedList<>();
    private int maxqueue, failure;
    private double meanQueue;
    private double averageLoadSum;
    private Client currentClient;

    public Process(double delay) {
        super(delay);
        super.setName("PROCESSOR");
        super.setTnext(Double.POSITIVE_INFINITY);
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    @Override
    public void inAct(Client client) {
        super.inAct(client);
        if (super.getState() == 0) {
            super.setState(1);
            currentClient = client;
            super.setTnext(getTcurr() + getDelay());
        } else {
            if (queue.size() < getMaxqueue()) {
                queue.add(client);
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
        if (!queue.isEmpty()) {
            currentClient = queue.poll();
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        }
    }

    private void callNextElement() {
        Element nextElement = super.getNextElement();
        if (nextElement != null) {
            nextElement.inAct(currentClient);
        }
    }

    public int getFailure() {
        return failure;
    }

    public int getMaxqueue() {
        return maxqueue;
    }

    public int getCurrentClientStatus() {
        return currentClient.getClientStatus();
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
        meanQueue += queue.size() * delta;

        averageLoadSum += getState() * delta;
    }

    public double getMeanQueue(double allTime) {
        return meanQueue / allTime;
    }

    public double averageLoad(double allTime) {
        return averageLoadSum / allTime;
    }
}
