package Model;

import Controller.Rules;

import java.util.Scanner;

public class Joueur {
    final private String nom;
    private int nbTeteDeBoeuf;
    private Carte[] nmain;
    private Carte carteselec;

    public Joueur(Rules rules) {
        System.out.println("Quel est votre nom ?");
        Scanner joueur = new Scanner(System.in);
        this.nom = joueur.nextLine();
        this.nbTeteDeBoeuf = 0;
        this.nmain = new Carte[10];
        if (Jeu.getRep() != 3){
            for(int i = 0; i < 10; i++){
                this.nmain[i] = Rules.RecupererCarte(rules.getPaquet(),0);
                rules.setPaquet(Rules.EnleverCarte(rules.getPaquet(),0));
            }
        }
    }

    public void CreationMainVar2(Rules rules, int x){
        System.out.println(this.nom + " Choisissez parmis les cartes restantes");
        for (int i = 0; i < rules.getPaquet().length; i++){
            System.out.println(rules.getPaquet()[i]);
        }
        Scanner joueur = new Scanner(System.in);
        boolean déjà = false;
        while(!déjà){
            int valchoisie = joueur.nextInt();
            for (int i = 0; i < rules.getPaquet().length; i++){
                if (rules.getPaquet()[i].getNum() == valchoisie){
                    this.nmain[x] = Rules.RecupererCarte(rules.getPaquet(), i);
                    rules.setPaquet(Rules.EnleverCarte(rules.getPaquet(), i));
                    déjà = true;
                }
            }
            if (!déjà){
                System.out.println("choisissez une autre carte");
            }
        }
    }
    public void ChoisirCarte(){
        Carte carteselec;
        carteselec = new Carte();
        System.out.println("Voici vos cartes " + this.nom + ": Choisissez celle que vous voulez poser (entrez la valeur de celle-ci).");
        for (int i = 0; i <this.nmain.length; i++) System.out.println(this.nmain[i]);
        Scanner joueur = new Scanner(System.in);
        boolean déjà = false;
        while(!déjà){
            int valchoisie = joueur.nextInt();
            for (int i = 0; i <this.nmain.length; i++){
                if (this.nmain[i].getNum() == valchoisie){
                    carteselec = Rules.RecupererCarte(this.nmain, i);
                    this.nmain = Rules.EnleverCarte(this.nmain, i);
                    déjà = true;
                }
            }
            if (!déjà){
                System.out.println("La carte choisie n'est pas dans votre main, choisissez en une autre");
            }
        }
        setCarteselec(carteselec);
    }
    public void TrierMain(){
        int posMin;
        for (int i = 0; i < this.nmain.length - 1; i++){
            posMin = i;
            for (int j = i +1; j < this.nmain.length; j++){
                if (this.nmain[j].getNum() < this.nmain[posMin].getNum()){
                    posMin = j;
                }
            }
            Carte temp = this.nmain[posMin];
            this.nmain[posMin] = this.nmain[i];
            this.nmain[i] = temp;
        }

    }

    public Serie[] DepotCarte(Serie[] tabSerie, int numSerie){
        Serie[] newLSerie = new Serie[tabSerie.length];
        for (int i = 0; i < tabSerie.length; i++){
            if (i == numSerie){
                if ((tabSerie[i].getNbCarte() + 1) <= tabSerie[i].getLongueurMax() && this.carteselec.getNum() > tabSerie[i].getLSerieCarte()[tabSerie[i].getLSerieCarte().length-1].getNum()){
                    newLSerie[i] = new Serie(i, tabSerie[i].getNbCarte()+1,tabSerie[i].getLSerieCarte(),this.carteselec);
                }
                else{
                    this.nbTeteDeBoeuf = this.nbTeteDeBoeuf + Rules.RecupererTeteDeBoeuf(tabSerie[i]);
                    newLSerie[i] = new Serie(i, this.carteselec);
                }
            }
            else{
                newLSerie[i] = new Serie();
                newLSerie[i] = tabSerie[i];
            }
        }
        return(newLSerie);
    }
    public int ChoixSerie(){
        Scanner joueur = new Scanner(System.in);
        int numSerie = joueur.nextInt();
        numSerie = numSerie - 1;
        return(numSerie);
    }
    public String toString() {
        return (nom);
    }

    public int getNbTeteDeBoeuf() {
        return nbTeteDeBoeuf;
    }
    public void setnmain(Carte[] nmain) {
        this.nmain = nmain;
    }
    public Carte getCarteselec() {
        return carteselec;
    }
    public void setCarteselec(Carte carteselec) {
        this.carteselec = carteselec;
    }
}
