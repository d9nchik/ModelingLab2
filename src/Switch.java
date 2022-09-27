import java.util.ArrayList;

public class Switch extends Element {
    private final ArrayList<Element> elements = new ArrayList<>();
    private final ArrayList<Double> possibilities = new ArrayList<>();

    public Switch() {
        super(0);
    }

    public void addElement(Element element, double possibility) {
        elements.add(element);
        possibilities.add(possibility);

        this.check();
    }

    @Override
    public void inAct() {
        super.inAct();
        double rand = Math.random();

        for (int i = 0; i < elements.size(); i++) {
            double currentPossibility = possibilities.get(i);
            if (rand < currentPossibility) {
                elements.get(i).inAct();
                return;
            }

            rand -= currentPossibility;
        }
    }

    private void check() {
        if (this.elements.size() != this.possibilities.size()) {
            throw new IllegalStateException("size of possibilities and elements mismatch");
        }

        double totalPossibility = 0;
        for (Double possibility : possibilities) {
            if (possibility <= 0) {
                throw new IllegalArgumentException("possibility is equal or less than zero");
            }
            totalPossibility += possibility;
        }

        if (totalPossibility == 0 || totalPossibility > 1) {
            throw new IllegalArgumentException("total possibility is more than zero");
        }
    }
}
