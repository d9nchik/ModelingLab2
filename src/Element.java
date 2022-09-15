public class Element {
    private static int nextId = 0;
    private String name;
    private double tnext;
    private final double delayMean;
    private final int id;
    private String distribution;
    private int quantity;
    private double tcurr;
    private int state;
    private Element nextElement;
    private double delayDeviation;


    public static void resetNexId() {
        nextId = 0;
    }

    public Element(double delay) {
        tnext = 0.0;
        delayMean = delay;
        distribution = "";
        tcurr = tnext;
        state = 0;
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public double getDelay() {
        return switch (distribution) {
            case "exp" -> FunRand.Exp(delayMean);
            case "norm" -> FunRand.Norm(delayMean - delayDeviation * 0.5, delayMean + delayDeviation * 0.5);
            case "unif" -> FunRand.Unif(delayMean, delayDeviation);
            default -> delayMean;
        };
    }


    public void setDistribution(String distribution) {
        this.distribution = distribution.toLowerCase();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTcurr() {
        return tcurr;
    }

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Element getNextElement() {
        return nextElement;
    }

    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }

    public void inAct() {

    }

    public void outAct() {
        quantity++;
    }

    public double getTnext() {
        return tnext;
    }

    public void setTnext(double tnext) {
        this.tnext = tnext;
    }

    public int getId() {
        return id;
    }

    public void printInfo() {
        System.out.println(getName() + " state= " + state + " quantity = " + quantity + " tnext= " + tnext);
    }

    public String getName() {
        return name + " " + getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doStatistics(double delta) {

    }
}

