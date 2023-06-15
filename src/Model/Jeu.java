package Model;

import Controller.Rules;

import java.util.Scanner;

public class Jeu extends Rules {
    static private int nbJoueur;
    static private int rep;

    private static void DemandeNbJoueur(){
        do{
            System.out.println("Quel nombre il y'a t'il de joueurs ?");
            Scanner menu = new Scanner(System.in);
            nbJoueur = menu.nextInt();
            if(nbJoueur < 2 || nbJoueur > 10){
                System.out.println("Nombre de joueur incorrect, rentrez un autre nombre de joueurs");
            }
        }while(nbJoueur < 2 || nbJoueur > 10);
    }

    public static void Menu(){
        String espace = System.getProperty("line.separator");
        System.out.println("                    Vous allez commencer le jeu 6 QUI PREND                    "
                + espace +         "                                                                      "
                + espace +         "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                + espace +         "                                                                      "
                + espace +         "   Pour commencer appuyez sur n'importe quelle touche  "
                + espace +         "                                                                      ");
        Scanner menu = new Scanner(System.in);
        rep = menu.nextInt();
        DemandeNbJoueur();
    }



    public static int getNbJoueur() {
        return nbJoueur;
    }

    public static int getRep() {
        return rep;
    }


}
