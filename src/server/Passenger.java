package server;

/**
Entity class that holds passenger info
***/
public class Passenger {

    private String time;
    int floor;
    String floorButton;
    private int CarButton;
/***
 * Default constructor to create a passenger
 * @param time string holdign the time that the passenger arrived
 * @param floor int for the floor number the passsenger is on
 * @param floorButton String for the direction the person is choosing
 * @param CarButton int for the destination floor
 */

    public Passenger(String time, int floor, String floorButton, int CarButton){
        this.time=time;
        this.floor=floor;
        this.floorButton=floorButton;
        this.CarButton=CarButton;

    }

}
