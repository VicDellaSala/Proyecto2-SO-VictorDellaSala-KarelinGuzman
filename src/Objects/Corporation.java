/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Victor Daniel
 */

public class Corporation extends Thread { //The studio class contains all the information of each studio in the simulation, incluiding priority queues, the admin, and a shared IDlist
    //Studio info section
    private String studioName;
    
    //Queue section
    private Queue prior1Queue;
    private Queue prior2Queue;
    private Queue prior3Queue;
    private Queue reinfQueue;
    
    //Counter for IDs
    private int idCounter;

    public Corporation(String name) {
        this.studioName = name;
        this.prior1Queue = new Queue();
        this.prior2Queue = new Queue();
        this.prior3Queue = new Queue();
        this.reinfQueue = new Queue();
        this.idCounter = 0;
    }
    
    public Fighter generateCharacter(){ 
        /*
        The process for generating a character:
        SECTION 1: generates a unique id by concatenating the studio name and the idCounter, then adds 1 to the counter
        SECTION 2:generates a random number between 0 and 100 for each statistic      
        SECTION 3: The overall Quality of a character will be the sum of the stats that are considered quality stats
        
        overallQuality = 0 or 1  ; The character is deficient
        overallQuality = 2  ; The character is average
        overallQuality = 3 or 4 ; The character is exceptional   
        at the end the overallQuality will be rounded to just 1, 2, or 3, so that it will be easier to read later
        SECTION 4: Returns the newly generated Character
        */
        
        //Section 1
        String id = getStudioName() + getIdCounter();  
        setIdCounter(getIdCounter() + 1);
        
        //Section 2
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        int skillPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int healthPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int strengthPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int agilityPoints = randomNumber;
        
        //Section 3
        int overallQuality = 0;
        if (skillPoints >40) { overallQuality++;}   // 60% Chance of being skilled
        if (healthPoints > 30) { overallQuality++;} // 70% Chance of being healthy
        if (strengthPoints > 50) {overallQuality++;}    // 50% Chance of being strong
        if (agilityPoints > 60) {overallQuality++;}     // 40% Chance of being agile 
        
        if (overallQuality  == 4) {overallQuality = 3;} //The character is Exceptional
        if (overallQuality  == 0) {overallQuality = 1;} // The character is deficient
        
        String imageCollection = getStudioName();
        //Section 4
        Fighter character = new Fighter(id, skillPoints, healthPoints, strengthPoints, agilityPoints, overallQuality, imageCollection);
        return character;
    }


    public String getStudioName() {
        return studioName;
    }

    public Queue getPrior1Queue() {
        return prior1Queue;
    }

    public Queue getPrior2Queue() {
        return prior2Queue;
    }

    public Queue getPrior3Queue() {
        return prior3Queue;
    }

    public Queue getReinfQueue() {
        return reinfQueue;
    }


    public int getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public void queueInanitionUpdate(){
        //Priority 3 queue
        Fighter pointer = getPrior3Queue().getHead();
        Fighter tempPointer = null;
        while (pointer != null) {
            pointer.setCounter(pointer.getCounter()+1);
            if (pointer.getCounter() == 8){
                tempPointer = pointer;
                getPrior3Queue().deleteFromQueue(pointer);
                getPrior2Queue().enqueue(tempPointer);
                tempPointer.setCounter(0);
            }
            pointer = (Fighter) pointer.getNext();
        }
        //Priority 2 queue
        pointer = getPrior2Queue().getHead();
        tempPointer = null;
        while (pointer != null) {
            pointer.setCounter(pointer.getCounter()+1);
            if (pointer.getCounter() == 8){
                tempPointer = pointer;
                getPrior2Queue().deleteFromQueue(pointer);
                getPrior1Queue().enqueue(tempPointer);
                tempPointer.setCounter(0);
            }
            pointer = (Fighter) pointer.getNext();
        }
        
    }
}


