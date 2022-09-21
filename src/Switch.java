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
}
