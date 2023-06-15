package Model;

import Controller.Rules;

public class Serie {
    private int numSerie;
    private int nbCarte;
    private int longueurMax;
    private Carte[] LSerieCarte;

    public Serie() {
    }
    public Serie(int num, Carte newCarte) {
        this.numSerie = num + 1;
        this.nbCarte = 1;
        this.LSerieCarte = new Carte[this.nbCarte];
        this.LSerieCarte[0] = newCarte;
        this.longueurMax = 5;
    }

    public Serie(int num, int nbCarte, Carte[] tab, Carte newCarte) {
        this.numSerie = num + 1;
        this.nbCarte = nbCarte;
        this.LSerieCarte = new Carte[this.nbCarte];
        if (nbCarte - 1 >= 0) System.arraycopy(tab, 0, this.LSerieCarte, 0, nbCarte - 1);
        this.LSerieCarte[nbCarte-1] = newCarte;
        this.longueurMax = 5;
    }
    public Serie(Jeu rules, int num) {
        this.numSerie = num + 1;
        this.nbCarte = 1;
        this.LSerieCarte = new Carte[this.nbCarte];
        this.LSerieCarte[0] = Rules.RecupererCarte(rules.getPaquet(),0);
        rules.setPaquet(Rules.EnleverCarte(rules.getPaquet(),0));
        this.longueurMax = 5;
    }
    @Override
    public String toString() {
        return "Serie " + numSerie;
    }

    public int getNbCarte() {
        return nbCarte;
    }

    public Carte[] getLSerieCarte() {
        return LSerieCarte;
    }

    public int getLongueurMax() {
        return longueurMax;
    }

}
