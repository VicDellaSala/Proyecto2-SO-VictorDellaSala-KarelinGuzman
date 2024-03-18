/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import Objects.Corporation;
import Objects.Simulation;

/**
 *
 * @author VicDellaSala
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Simulation simulation = new Simulation("RS", "A", 3000);

    public static Corporation cartoonNetwork = simulation.getStudio1();
    public static Corporation nickelodeon = simulation.getStudio2();

    public static userInterface battleInterface = new userInterface();

    public static void main(String[] args) {
        simulation.start();
        battleInterface.getResult().setText("");
        
    }
}
