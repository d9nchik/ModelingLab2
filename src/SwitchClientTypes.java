import java.util.ArrayList;

public class SwitchClientTypes extends Element {
    private final ArrayList<Element> elements;

    public SwitchClientTypes(ArrayList<Element> elements) {
        super(0);
        this.elements = elements;
    }


    @Override
    public void inAct(int clientStatus) {
        super.inAct(clientStatus);

        elements.get(clientStatus - 1).inAct(clientStatus);
    }
}
