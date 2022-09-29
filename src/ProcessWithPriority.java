import java.util.PriorityQueue;

public class ProcessWithPriority extends Process {

    public ProcessWithPriority(double delay) {
        super(delay);
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
}
