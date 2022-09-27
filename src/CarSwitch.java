public class CarSwitch extends Element {
    private final Process cashier1;
    private final Process cashier2;
    private double meanInBank = 0;

    private double lastLeave = 0;
    private double totalTimeLeaveElapsed = 0;
    private int leaveCount = 0;

    private int changeCashier = 0;

    public CarSwitch(Process cashier1, Process cashier2) {
        super(0);
        super.setName("CAR_SWITCH");
        this.cashier1 = cashier1;
        this.cashier2 = cashier2;
        recalculateTnext();
    }


    @Override
    public void inAct() {
        super.inAct();

        if (cashier2.getQueue() < cashier1.getQueue()) {
            cashier2.inAct();
            return;
        }


        cashier1.inAct();


        recalculateTnext();
    }

    private void recalculateTnext() {
        double tnext = Double.POSITIVE_INFINITY;

        tnext = Double.min(tnext, cashier1.getTnext());
        tnext = Double.min(tnext, cashier2.getTnext());

        setTnext(tnext);
        if (Double.isInfinite(tnext)) {
            setState(0);
        } else {
            setState(1);
        }

    }


    @Override
    public void outAct() {
        super.outAct();

        Process bigQueue = this.cashier1;
        Process smallQueue = this.cashier2;
        if (bigQueue.getQueue() < smallQueue.getQueue()) {
            Process temp = bigQueue;
            bigQueue = smallQueue;
            smallQueue = temp;
        }

        if (smallQueue.getQueue() + 2 <= bigQueue.getQueue()) {
            bigQueue.setQueue(bigQueue.getQueue() - 1);
            smallQueue.inAct();
            changeCashier++;
        }

        if (lastLeave != 0) {
            leaveCount++;
            totalTimeLeaveElapsed += getTcurr() - lastLeave;
        }
        lastLeave = getTcurr();

        recalculateTnext();
    }

    @Override
    public void doStatistics(double delta) {
        super.doStatistics(delta);

        meanInBank += delta * (cashier1.getQueue() + cashier1.getState() + cashier2.getQueue() + cashier2.getState());
    }

    public double getMeanInBank(double allTime) {
        return meanInBank / allTime;
    }

    public double getLeaveInterval() {
        return totalTimeLeaveElapsed / leaveCount;
    }

    public int getFailure() {
        return cashier1.getFailure();
    }

    public int getChangeCashier() {
        return changeCashier;
    }
}
