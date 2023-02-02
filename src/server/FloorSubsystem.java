package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FloorSubsystem implements Runnable{
    private Scheduler scheduler;
    public FloorSubsystem(Scheduler scheduler){
        this.scheduler=scheduler;

    }

    @Override
    public void run() {
        ArrayList<Passanger> passengers=new ArrayList<>();
        passengers=readFile();
        
    }
    public ArrayList<Passanger> readFile() {
        String time;
        int floor;
        String floorButton;
        int carButton;
        String[] lines;
        ArrayList<Passanger> passengerList = new ArrayList<>();
        try {
            File myObj = new File("inputFile.txt");
            Scanner floorFile = new Scanner(myObj);

            while (floorFile.hasNextLine()) {
                lines = floorFile.nextLine().split(",");
                time = lines[0];
                floor = Integer.parseInt(lines[1]);
                floorButton = lines[2];
                carButton = Integer.parseInt(lines[3]);
                passengerList.add(new Passanger(time, floor, floorButton, carButton));

            }
            floorFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        return passengerList;
    }
}
