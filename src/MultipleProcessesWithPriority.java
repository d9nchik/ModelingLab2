import java.util.ArrayList;
import java.util.PriorityQueue;

public class MultipleProcessesWithPriority extends MultipleProcesses {

    public MultipleProcessesWithPriority(ArrayList<Process> processes) {
        super(processes);
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
