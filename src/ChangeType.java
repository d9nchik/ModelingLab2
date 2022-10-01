public class ChangeType extends Element {
    private final int newType;

    public ChangeType(int newType) {
        super(0);
        setName("CHANGE_TYPE");
        this.newType = newType;
    }

    @Override
    public void inAct(int clientStatus) {
        super.inAct(clientStatus);
        Element nextElement = getNextElement();
        if (nextElement != null) {
            nextElement.inAct(newType);
        }
    }
}
