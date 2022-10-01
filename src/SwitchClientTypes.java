import java.util.ArrayList;

public class SwitchClientTypes extends Element {
    private final ArrayList<Element> elements;

    public SwitchClientTypes(ArrayList<Element> elements) {
        super(0);
        this.elements = elements;
    }

    public SwitchClientTypes(Element firstType, Element secondType, Element thirdType) {
        super(0);
        elements = new ArrayList<>();
        elements.add(firstType);
        elements.add(secondType);
        elements.add(thirdType);
    }


    @Override
    public void inAct(int clientStatus) {
        super.inAct(clientStatus);
        Element element = elements.get(clientStatus - 1);
        if (element != null) {
            element.inAct(clientStatus);
        }
    }
}
