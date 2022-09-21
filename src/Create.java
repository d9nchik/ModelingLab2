public class Create extends Element {
    public Create(double delay) {
        super(delay);
        super.setName("CREATOR");
    }

    @Override
    public void outAct() {
        super.outAct();
        super.inAct();
        super.setTnext(super.getTcurr() + super.getDelay());
        super.getNextElement().inAct();
    }
}
