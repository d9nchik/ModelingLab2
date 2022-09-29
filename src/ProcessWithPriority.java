import java.util.PriorityQueue;

public class ProcessWithPriority extends Process {

    public ProcessWithPriority() {
        super(0);
        queue = new PriorityQueue<>((a, b) -> {
            if (a == 1) {
                return -1;
            }
            if (b == 1) {
                return 1;
            }
            return 0;
        });
    }

    @Override
    public double getDelay() {
        switch (getCurrentClientStatus()) {
            case 1 -> setDelayMean(15);
            case 2 -> setDelayMean(40);
            case 3 -> setDelayMean(30);
            default -> throw new IllegalArgumentException("processWithPriority illegal");
        }
        return super.getDelay();
    }
}
