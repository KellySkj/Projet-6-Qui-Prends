package View;

import Controller.Rules;
import Model.Jeu;

public class MyGame {
    public static void main(String[] args) {

        Jeu.Menu();
        Rules rules = new Jeu();
        rules.Jouer();
    }
}
