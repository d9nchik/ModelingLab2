public class Create extends Element {
    public Create(double delay) {
        super(delay);
        super.setName("CREATOR");
    }

    @Override
    public void outAct() {
        super.outAct();
        super.inAct(new Client(0, 0));
        super.setTnext(super.getTcurr() + super.getDelay());
        double random = Math.random();
        Client client;
        if (random < 0.5) {
            client = new Client(1, getTcurr());
        } else if (random < 0.6) {
            client = new Client(2, getTcurr());
        } else {
            client = new Client(3, getTcurr());
        }
        super.getNextElement().inAct(client);
    }
}
