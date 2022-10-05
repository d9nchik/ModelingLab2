import java.util.ArrayList;
import java.util.PriorityQueue;

public class MultipleProcessesWithPriority extends MultipleProcesses {

    public MultipleProcessesWithPriority(ArrayList<Process> processes) {
        super(processes);
        queue = new PriorityQueue<>((a, b) -> {
            if (a.getClientStatus() == 1) {
                return -1;
            }
            if (b.getClientStatus() == 1) {
                return 1;
            }
            return 0;
        });
    }
}
