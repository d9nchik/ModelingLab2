public class ProcessWithPriority extends Process {

    public ProcessWithPriority() {
        super(0);
    }

    @Override
    public double getDelay() {
        switch (getCurrentClientStatus()) {
            case 1 -> setDelayMean(15);
            case 2 -> setDelayMean(40);
            case 3 -> setDelayMean(30);
            default -> throw new IllegalArgumentException("processWithPriority illegal");
        }
        setDistribution("exp");

        return super.getDelay();
    }
}
