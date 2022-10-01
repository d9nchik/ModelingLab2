import java.util.ArrayList;

public class SimModel {
    public static void main(String[] args) {
        Create c = new Create(15);

        MultipleProcesses welcomeRoom = getWelcomeNurses();
        welcomeRoom.setName("WELCOME_ROOM");
        MultipleProcesses chamberRoom = getChamberNurses();
        chamberRoom.setName("CHAMBER_ROOM");
        MultipleProcesses corridorToRegistry = getCorridor();
        corridorToRegistry.setName("CORRIDOR_TO_REGISTRY");
        corridorToRegistry.setInfoPrinted(false);

        SwitchClientTypes sct = new SwitchClientTypes(chamberRoom, corridorToRegistry, corridorToRegistry);
        welcomeRoom.setNextElement(sct);


        c.setNextElement(welcomeRoom);

        ArrayList<Element> list = new ArrayList<>();

        list.add(c);
        list.add(welcomeRoom);
        list.add(chamberRoom);
        list.add(corridorToRegistry);

        Model model = new Model(list);
        model.simulate(100);
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
        return mpChamberNurses;
    }

    private static MultipleProcesses getCorridor() {
        ArrayList<Process> chamberNurses = new ArrayList<>();
        double start = 2.0;
        double end = 5.0;
        double deviation = (end - start) / 2;
        for (int i = 0; i < 10; i++) {
            Process chamberNurse = new Process(end - deviation);
            chamberNurse.setDistribution("unif");
            chamberNurse.setDelayDeviation(deviation);
            chamberNurse.setName("CORRIDOR");
            chamberNurses.add(chamberNurse);
        }

        return new MultipleProcesses(chamberNurses);
    }
}
