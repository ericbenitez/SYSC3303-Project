package server;

public class Main {
    /**
     * Main Function that initializes the program by creating and starting the threads
     * @param args
     */
    public static void main(String[] args) {
        Scheduler scheduler=new Scheduler();
        FloorSubsystem floorSubsystem=new FloorSubsystem(scheduler);
        Thread floorSubsystemThread = new Thread(floorSubsystem);
        floorSubsystemThread.start();
    }
}
