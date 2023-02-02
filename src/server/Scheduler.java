package server;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	private boolean inProcess, isAvailable, onDestination;  			// true means elevator is in process, initially is false until it gets request from floor system
	private Queue<Object> floorRequests; 	//requests from floor system
	private int elevatorLocation, destination;
	
	public Scheduler () {
		inProcess=false;
		isAvailable=true;
		floorRequests= new LinkedList<>();
		elevatorLocation=0;
		destination=-1;
		onDestination=false;
		
	}
	
	//function receives request from floor system
	
	public synchronized void makeFloorRequest(Object request) {
		
		//if is not available is true means elevator is in process and floor has to wait
		while (!isAvailable) {
            try { 
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
		
		//after receiving request from floor it adds on queue
		// make inProcess true
		// and notify all threads
		floorRequests.add(request);
		isAvailable=false;
		inProcess=true;
		notifyAll();
		
		
		
	}
	
	// lamps [] booleans 
	// iterate through array and find the destination floor
	// function return destination=-1 if there is no button pressed
	
	public synchronized int sendlamps(boolean lamps[]) {
		
		
		while(inProcess) {
			try { 
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
		}
		
		for(int i=0; i<lamps.length;i++) {
			if(lamps[i]) {
				destination=i;
			}
		}		
		return destination;
	}
	
	public synchronized void sendElevatorUpdates(int level) {
		while(!inProcess) {
			try { 
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
		}
		elevatorLocation=level;
		 if(elevatorLocation==destination) {
			 onDestination=true;
			 inProcess=false;
			 isAvailable=true;
			 destination=-1;		 
		 }	
		 
		 notifyAll();
		
	}
	
	public int getElevatorUpdates() {
		return elevatorLocation;
	}
	
	public boolean isOnDestinatiom() {
		return onDestination;
	}
	
	
	
}
