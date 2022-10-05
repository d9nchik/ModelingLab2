public class TimeInSystemCalculator extends Element {

    private double totalSpentTime = 0;
    private int clientServed = 0;

    public TimeInSystemCalculator() {
        super(0);
        setTnext(Double.POSITIVE_INFINITY);
        setName("TIME_IN_SYSTEM_CALCULATOR");
    }

    @Override
    public void inAct(Client client) {
        super.inAct(client);

        totalSpentTime += getTcurr() - client.getEnterTime();
        clientServed++;
    }

    public double getClientInterval() {
        return totalSpentTime / clientServed;
    }

    @Override
    public void printInfo() {
        System.out.println(getName() + " client interval = " + getClientInterval());
    }
}
