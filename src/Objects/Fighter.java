/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.Random;

/**
 *
 * @author Victor Daniel
 */
public class Fighter {

    // Characters will also work as nodes on queues, so they will have a next
    // attribute
    private Fighter next;
    // Unique ID
    private String id;

    // Cicle counter
    private int counter;

    // Character stats they will always stay between 0 and 100
    private int skillPoints;
    private int healthPoints;
    private int strengthPoints;
    private int agilityPoints;
    // Character quality Booleans depending on the level of each stat, it will
    // qualify as "quality" or not, the requirements for the stat to be quality
    // varies for each stat
    private int overallQuality; //

    // Path of the character image
    private String[] imageCollection;

    private String name;
    
    public Fighter(String id, int skillPoints, int healthPoints, int strengthPoints, int agilityPoints,
            int overallQuality, String imageCollection) {
        this.id = id;
        this.next = null;
        this.counter = 0;
        this.skillPoints = skillPoints;
        this.healthPoints = healthPoints;
        this.strengthPoints = strengthPoints;
        this.agilityPoints = agilityPoints;

        this.overallQuality = overallQuality;

        this.imageCollection = randomCharacter(imageCollection);

        this.name = id + " " + this.imageCollection[1];
    }

    public Fighter getNext() {
        return next;
    }

    public void setNext(Fighter next) {
        this.next = next;
    }

    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public int getAgilityPoints() {
        return agilityPoints;
    }

    public int getOverallQuality() {
        return overallQuality;
    }

    public void setOverallQuality(int overallQuality) {
        this.overallQuality = overallQuality;
    }

    public String[] getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(String[] imageCollection) {
        this.imageCollection = imageCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void printStatitics() {
        System.out.println("----------------------------");
        System.out.println("Id: " + getId());
        System.out.println("SkillPoints: " + getSkillPoints());
        System.out.println("HealthPoints: " + getHealthPoints());
        System.out.println("StrengthPoints: " + getStrengthPoints());
        System.out.println("AgilityPoints: " + getAgilityPoints());
        System.out.println("Overall Quality: " + getOverallQuality());
        System.out.println("----------------------------");
    }

    private String[] randomCharacter(String images) {
        String[][] array = {};
        if ("A".equals(images)) {
            array = avatarCharacters;
        } else if ("RS".equals(images)) {
            array = regularShowCharacters;
        }

        // Picking a random element from the array
        Random random = new Random();
        String[] randomElement = array[random.nextInt(array.length)];

        return randomElement;
    }

    // What is below is for testing
    String[][] avatarCharacters = {
        {"src/images/Avatar_Aang1.png", "Aang"},
        {"src/images/Katara1.png", "Katara"},
        {"src/images/Sokka1.png", "Sokka"},
        {"src/images/toph1.png", "Toph"},
    };

    String[][] regularShowCharacters = {
        {"src/images/Benson1.png", "Benson"},
        {"src/images/Mordecai.png", "Mordecai"},
        {"src/images/Musculoso1.png", "Musculoso"},
        {"src/images/Papaleta1.png", "Papaleta"},
        {"src/images/Skips1.png", "Skips"},
        {"src/images/rigby1.png", "Rigby"}
    };
}
