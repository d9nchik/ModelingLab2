public class Client {
    private final int clientStatus;
    private final double enterTime;

    public Client(int clientStatus, double enterTime) {
        this.clientStatus = clientStatus;
        this.enterTime = enterTime;
    }

    public int getClientStatus() {
        return clientStatus;
    }

    public double getEnterTime() {
        return enterTime;
    }
}
