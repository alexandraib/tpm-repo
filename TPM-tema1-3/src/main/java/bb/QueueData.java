package bb;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueueData implements Comparable<QueueData> {
    private long startTime;
    private int pid;

    @Override
    public int compareTo(QueueData o) {
        return Long.compare(startTime, o.getStartTime());
    }
}
