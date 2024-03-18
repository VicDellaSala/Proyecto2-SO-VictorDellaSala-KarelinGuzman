/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cdmar
 */
public class AI extends Thread {

    private Admin admin;
    private Simulation simulation;
    private String status;

    public AI(Simulation simulation) {
        this.admin = simulation.getAdmin();
        this.simulation = simulation;

    }
    int avatarVictory = 0;
    int regularShowVictory = 0;

    public void performBattle(Fighter character1, Fighter character2) {

        Random random = new Random();
        int randomNumber = random.nextInt(101);
        int desition = randomNumber; 
        try {
            sleep(simulation.getBattleDuration()); 
        } catch (InterruptedException ex) {
            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (0 <= desition && desition <= 40) { 
            
            System.out.println("----THE FIGHT BETWEEN " + character1.getId() + " AND " + character2.getId() + " BEGINS----");
            if (character1.getAgilityPoints() > character2.getAgilityPoints()) { 

                while (true) { 
                    character2.setHealthPoints(character2.getHealthPoints() - character1.getStrengthPoints());
                    System.out.println("The character " + character1.getId() + " hits " + character2.getId() + " for " + character1.getStrengthPoints() + " damage");
                    System.out.println(character2.getId() + "has " + character2.getHealthPoints() + "hp remaining!");
                    if (character2.getHealthPoints() <= 0) { 

                        System.out.println("The character " + character2.getId() + "DIED, " + character1.getId() + " wins");
                        admin.winnerEscenario(character1, character2);
                        regularShowVictory++;
                        Main.battleInterface.getRegularShowVictories().setText(String.valueOf(regularShowVictory));
                        Main.battleInterface.getResult().setText("El personaje " + character2.getId() + " murió de hambre, " + character1.getId() + " gana");
                        break;
                    }
                    character1.setHealthPoints(character1.getHealthPoints() - character2.getStrengthPoints());
                    System.out.println("The character " + character2.getId() + " hits " + character1.getId() + " for " + character2.getStrengthPoints() + " damage");
                    System.out.println(character1.getId() + "has " + character1.getHealthPoints() + "hp remaining!");
                    if (character1.getHealthPoints() <= 0) { 

                        System.out.println("The character " + character1.getId() + "DIED, " + character2.getId() + " wins");
                        admin.winnerEscenario(character2, character1); 
                        avatarVictory++;
                        Main.battleInterface.getAvatarVictories().setText(String.valueOf(avatarVictory));
                        Main.battleInterface.getResult().setText("El peleador " + character1.getId() + " murió de un susto, " + character2.getId() + " gana");
                        break;
                    }

                }
            } else { 
                while (true) { 
                  
                    character1.setHealthPoints(character1.getHealthPoints() - character2.getStrengthPoints());
                    System.out.println("The character " + character2.getId() + " hits " + character1.getId() + " for " + character2.getStrengthPoints() + " damage");
                    System.out.println(character1.getId() + "has " + character1.getHealthPoints() + "hp remaining!");

                    if (character1.getHealthPoints() <= 0) { 

                        System.out.println("The character " + character1.getId() + "DIED, " + character2.getId() + " wins");
                        admin.winnerEscenario(character2, character1); 
                        avatarVictory++;
                        Main.battleInterface.getAvatarVictories().setText(String.valueOf(avatarVictory));
                        Main.battleInterface.getResult().setText("El peleador " + character1.getId() + " estornudo y su caja toraxica implosiono, " + character2.getId() + " gana");
                        break;
                    }

                    character2.setHealthPoints(character2.getHealthPoints() - character1.getStrengthPoints());
                    System.out.println("The character " + character1.getId() + " hits " + character2.getId() + " for " + character1.getStrengthPoints() + " damage");
                    System.out.println(character2.getId() + "has " + character2.getHealthPoints() + "hp remaining!");
                    if (character2.getHealthPoints() <= 0) { 

                        System.out.println("The character " + character2.getId() + "DIED, " + character1.getId() + " wins");
                        admin.winnerEscenario(character1, character2); 
                        regularShowVictory++;
                        Main.battleInterface.getRegularShowVictories().setText(String.valueOf(regularShowVictory));
                        Main.battleInterface.getResult().setText("El peleador " + character2.getId() + " escuchó Harry Styles y murió, " + character1.getId() + " gana");
                        break;
                    }

                }
            }
        }

        if (40 < desition && desition <= 67) { 
            System.out.println("----THE FIGHT BETWEEN " + character1.getId() + " AND " + character2.getId() + " ENDED IN A TIE----");
            Main.battleInterface.getResult().setText("----THE FIGHT BETWEEN " + character1.getId() + " AND " + character2.getId() + " ENDED IN A TIE----");
            admin.tieEscenario(character1, character2);
        }
        if (67 < desition && desition <= 100) {
            System.out.println("----THE FIGHT BETWEEN " + character1.getId() + " AND " + character2.getId() + " WAS CANCELLED, THEY WERE NOT READY FOR BATTLE----");
            Main.battleInterface.getResult().setText("----THE FIGHT BETWEEN " + character1.getId() + " AND " + character2.getId() + " WAS CANCELLED, THEY WERE NOT READY FOR BATTLE----");
            admin.canceledCombatEscenario(character1, character2);
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
