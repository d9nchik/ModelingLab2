public class Create extends Element {
    public Create(double delay) {
        super(delay);
        super.setName("CREATOR");
    }

    @Override
    public void outAct() {
        super.outAct();
        super.inAct(0);
        super.setTnext(super.getTcurr() + super.getDelay());
        double random = Math.random();
        if (random < 0.5) {
            super.getNextElement().inAct(1);
        } else if (random < 0.6) {
            super.getNextElement().inAct(2);
        } else {
            super.getNextElement().inAct(3);
        }
    }
}
