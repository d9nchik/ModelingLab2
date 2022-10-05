public class ChangeType extends Element {
    private final int newType;

    public ChangeType(int newType) {
        super(0);
        setName("CHANGE_TYPE");
        this.newType = newType;
    }

    @Override
    public void inAct(Client client) {
        super.inAct(client);
        Element nextElement = getNextElement();
        if (nextElement != null) {
            nextElement.inAct(new Client(newType, client.getEnterTime()));
        }
    }
}
