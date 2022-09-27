public class CarSwitch extends Element {
    private final Process cashier1;
    private final Process cashier2;

    public CarSwitch(Process cashier1, Process cashier2) {
        super(0);
        this.cashier1 = cashier1;
        this.cashier2 = cashier2;
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
        }

        recalculateTnext();
    }
}
