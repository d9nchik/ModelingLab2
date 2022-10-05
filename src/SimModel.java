import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(15);

        MultipleProcesses welcomeRoom = getWelcomeNurses();
        MultipleProcesses chamberRoom = getChamberNurses();

        MultipleProcesses corridorToRegistry = getCorridor();
        corridorToRegistry.setName("CORRIDOR_TO_REGISTRY");

        Process registry = getRegistry();
        corridorToRegistry.setNextElement(registry);

        MultipleProcesses laboratory = getLaboratory();
        registry.setNextElement(laboratory);

        MultipleProcesses corridorToWelcomeRoom = getCorridor();
        corridorToRegistry.setName("CORRIDOR_TO_WELCOME_ROOM");

        SwitchClientTypes switchChamberRegistry = new SwitchClientTypes(chamberRoom, corridorToRegistry, corridorToRegistry);
        welcomeRoom.setNextElement(switchChamberRegistry);

        TimeInSystemCalculator tc = new TimeInSystemCalculator();
        chamberRoom.setNextElement(tc);

        SwitchClientTypes switchLaboratory = new SwitchClientTypes(null, corridorToWelcomeRoom, tc);
        laboratory.setNextElement(switchLaboratory);

        ChangeType changeType = new ChangeType(1);
        changeType.setNextElement(welcomeRoom);
        corridorToWelcomeRoom.setNextElement(changeType);


        c.setNextElement(welcomeRoom);

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(welcomeRoom);
        list.add(chamberRoom);
        list.add(corridorToRegistry);
        list.add(registry);
        list.add(laboratory);
        list.add(corridorToWelcomeRoom);
        list.add(tc);

        Model model = new Model(list);
        model.simulate(10000);
    }

    private static MultipleProcesses getWelcomeNurses() {
        ArrayList<Process> doctors = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            ProcessWithPriority doctor = new ProcessWithPriority();
            doctor.setName("DOCTOR");
            doctors.add(doctor);
        }

        MultipleProcessesWithPriority mpWithP = new MultipleProcessesWithPriority(doctors);
        mpWithP.setMaxqueue(Integer.MAX_VALUE);
        mpWithP.setName("WELCOME_ROOM");

        return mpWithP;
    }

    private static MultipleProcesses getChamberNurses() {
        ArrayList<Process> chamberNurses = new ArrayList<>();
        double start = 3.0;
        double end = 8.0;
        double deviation = (end - start) / 2;
        for (int i = 0; i < 3; i++) {
            Process chamberNurse = new Process(end - deviation);
            chamberNurse.setDistribution("unif");
            chamberNurse.setDelayDeviation(deviation);
            chamberNurse.setName("CHAMBER_NURSE");
            chamberNurses.add(chamberNurse);
        }

        MultipleProcesses mpChamberNurses = new MultipleProcesses(chamberNurses);
        mpChamberNurses.setMaxqueue(Integer.MAX_VALUE);
        mpChamberNurses.setName("CHAMBER_ROOM");

        return mpChamberNurses;
    }

    private static MultipleProcesses getCorridor() {
        ArrayList<Process> corridors = new ArrayList<>();
        double start = 2.0;
        double end = 5.0;
        double deviation = (end - start) / 2;
        for (int i = 0; i < 10; i++) {
            Process corridor = new Process(end - deviation);
            corridor.setDistribution("unif");
            corridor.setDelayDeviation(deviation);
            corridor.setName("CORRIDOR");
            corridors.add(corridor);
        }

        MultipleProcesses corridor = new MultipleProcesses(corridors);
        corridor.setInfoPrinted(false);

        return corridor;
    }

    private static Process getRegistry() {
        Process registry = new Process(4.5);
        registry.setDelayDeviation(3);
        registry.setDistribution("erlang");
        registry.setMaxqueue(Integer.MAX_VALUE);
        registry.setName("REGISTRY");
        return registry;
    }

    private static MultipleProcesses getLaboratory() {
        ArrayList<Process> laboratory = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Process laborant = new Process(4.5);
            laborant.setDelayDeviation(3);
            laborant.setDistribution("erlang");
            laborant.setMaxqueue(Integer.MAX_VALUE);
            laborant.setName("LABORANT");
            laboratory.add(laborant);
        }

        MultipleProcesses laborratoryMP = new MultipleProcesses(laboratory);
        laborratoryMP.setMaxqueue(Integer.MAX_VALUE);
        laborratoryMP.setName("LABORATORY");
        return laborratoryMP;
    }
}
