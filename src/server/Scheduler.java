package server;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	private boolean inProcess;  			// true means elevator is in process, initially is false until it gets request from floor system
	private Queue<Object> floorRequests; 	//requests from floor system

	public Scheduler () {
		inProcess=false;
		floorRequests= new LinkedList<>();

	}

	//function receives request from floor system

	public synchronized void makeFloorRequest(Object request) {

		//if inProcess is true means elevator is in process and floor has to wait
		while (inProcess) {
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
		inProcess=true;
		notifyAll();



	}

	// lamps [] booleans
	// iterate through array and find the destination floor
	// function return destination=-1 if there is no button pressed

	public synchronized int sendlamps(boolean lamps[]) {
		int destination=-1;

		while(!inProcess) {
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



}
