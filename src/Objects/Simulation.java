/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import proyecto2.Main;
import static proyecto2.Main.battleInterface;
import static proyecto2.Main.cartoonNetwork;
import static proyecto2.Main.nickelodeon;

/**
 *
 * @author Victor Daniel
 */
public class Simulation extends Thread {

    private Corporation studio1;
    private Corporation studio2;
    private Admin admin;
    private Queue winnersQueue;
    private int battleDuration;
    private AI AI;

    public Simulation(String studio1Name, String studio2Name, int battleDuration) {
        this.studio1 = new Corporation(studio1Name);
        this.studio2 = new Corporation(studio2Name);
        this.admin = new Admin(studio1, studio2, this);
        this.winnersQueue = new Queue();
        this.battleDuration = battleDuration;
        this.AI = new AI(this);
    }

    public void operate() {

        while (true) {
            Fighter[] chosenCharacters = getAdmin().chooseCharactersBattle();
            //Each cycle, the admin will start by choosing both characters that are going to battle
            if (chosenCharacters == null) {
                System.out.println("-------Game ended--------"); //If no characters were found in one studio, then the game will end
                break;
            } else {
                battleInterface.getLevel1RS().setText(cartoonNetwork.getPrior1Queue().printQueue());
                battleInterface.getLevel2RS().setText(cartoonNetwork.getPrior2Queue().printQueue());
                battleInterface.getLevel3RS().setText(cartoonNetwork.getPrior3Queue().printQueue());
                battleInterface.getReinforceRS().setText(cartoonNetwork.getReinfQueue().printQueue());

                battleInterface.getLevel1A().setText(nickelodeon.getPrior1Queue().printQueue());
                battleInterface.getLevel2A().setText(nickelodeon.getPrior2Queue().printQueue());
                battleInterface.getLevel3A().setText(nickelodeon.getPrior3Queue().printQueue());
                battleInterface.getReinforceA().setText(nickelodeon.getReinfQueue().printQueue());

                int tenth = (int) (getBattleDuration() * 0.10);

                AI.setStatus("waiting");
                try {
                    Main.battleInterface.getAiStatus().setText("Esperando");
                    sleep(1000);
                    Main.battleInterface.getResult().setText("");

                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                Fighter character1 = chosenCharacters[0]; //Studio 1 Character
                Fighter character2 = chosenCharacters[1]; //Studio 2 character

                try {
                    battleInterface.getRSCharacter().setIcon(new ImageIcon(ImageIO.read(new File(character1.getImageCollection()[0]))));
                    battleInterface.getRSName().setText(String.valueOf(character1.getName()));

                    battleInterface.getACharacter().setIcon(new ImageIcon(ImageIO.read(new File(character2.getImageCollection()[0]))));
                    battleInterface.getAName().setText(String.valueOf(character2.getName()));

                } catch (IOException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                AI.setStatus("Processing results");
                Main.battleInterface.getAiStatus().setText("Procesando");

                AI.performBattle(character1, character2);
                inanitionAvoider();
                AI.setStatus("Announcing results");
                try {
                    Main.battleInterface.getAiStatus().setText("Anunciando resultados");

                    sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 21; i++) { //When the simulation is started, it generates the first 20 characters
            Fighter character1 = studio1.generateCharacter();
            Fighter character2 = studio2.generateCharacter();
            //Then the admin adds them to their respective queues
            getAdmin().EnqueueCharacters(character1, character2);

        }
        operate(); //After the 20 initial characters have been created and enqueued, the operate cycle starts
    }

    public Queue getWinnersQueue() {
        return winnersQueue;
    }

    public int getBattleDuration() {
        return battleDuration;
    }

    public void setBattleDuration(int battleDuration) {
        this.battleDuration = battleDuration;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void inanitionAvoider() {
        studio1.queueInanitionUpdate();
        studio2.queueInanitionUpdate();
    }

    public Corporation getStudio1() {
        return studio1;
    }

    public void setStudio1(Corporation studio1) {
        this.studio1 = studio1;
    }

    public Corporation getStudio2() {
        return studio2;
    }

    public void setStudio2(Corporation studio2) {
        this.studio2 = studio2;
    }

}
