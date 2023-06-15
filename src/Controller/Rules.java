package Controller;

import Model.Carte;
import Model.Jeu;
import Model.Joueur;
import Model.Serie;

public class Rules {
    private Carte[] paquet;
    private Carte[] copiePaquet;
    private Joueur[] Tjoueur;
    private Serie[] LSerie;
    private int nbtetedeboeufmax;

    public Rules() {
        this.paquet = CreerPaquet();
        this.copiePaquet = this.paquet;
        this.Tjoueur = CreerJoueur();
        this.LSerie = CreerSerie();
        this.nbtetedeboeufmax = 0;
    }
    private Carte[] CreerPaquet(){
        int rep = Jeu.getRep();
        if (rep == 2){
            paquet = new Carte[(Jeu.getNbJoueur()*10)+4];
            for (int i = 0; i < (Jeu.getNbJoueur()*10)+4; i++){
                paquet[i] = new Carte(i);
            }
            paquet = Rules.MelangeTableau(paquet);
        }
        else{
            paquet = new Carte[104];
            for (int i = 0; i < 104; i++){
                paquet[i] = new Carte(i);
            }
            if (rep != 3){
                paquet = Rules.MelangeTableau(paquet);
            }
        }
        return (paquet);
    }
    private Joueur[] CreerJoueur(){
        Tjoueur = new Joueur[Jeu.getNbJoueur()];
        for (int i = 0; i < Jeu.getNbJoueur(); i++){
            Tjoueur[i] = new Joueur(this);
        }
        if (Jeu.getRep() == 3){
            int j = 0;
            while(j < 10){
                for (int i = 0; i < Jeu.getNbJoueur(); i++){
                    Tjoueur[i].CreationMainVar2(this,j);
                }
                j++;
            }
            paquet = Rules.MelangeTableau(paquet);
        }
        for (int i = 0; i < Jeu.getNbJoueur(); i++){
            Tjoueur[i].TrierMain();
        }
        return(Tjoueur);
    }

    private Serie[] CreerSerie(){
        LSerie = new Serie[4];
        for (int i = 0; i < 4; i++){
            LSerie[i] = new Serie((Jeu) this, i);
        }
        return(LSerie);
    }

    public static Joueur[] TrierTjoueur(Joueur[] Tjoueur){
        int posMin;
        for(int i = 0; i < Tjoueur.length - 1; i++){
            posMin = i;
            for(int j = i + 1; j < Tjoueur.length; j++){
                if (Tjoueur[j].getCarteselec().getNum() < Tjoueur[posMin].getCarteselec().getNum()){
                    posMin = j;
                }
            }
            Joueur temp = Tjoueur[posMin];
            Tjoueur[posMin] = Tjoueur[i];
            Tjoueur[i] = temp;
        }
        return(Tjoueur);
    }
    private void Tour(){
        for (int i = 0; i < this.Tjoueur.length; i++){
            System.out.println("Voici les lignes");
            for (int j = 0; j < 4; j++){
                System.out.print(this.LSerie[j] + " ");
                for (int k = 0; k < this.LSerie[j].getLSerieCarte().length; k++){
                    System.out.print(" " + this.LSerie[j].getLSerieCarte()[k] + " ");
                }
                System.out.println();
            }
            this.Tjoueur[i].ChoisirCarte();
        }

        this.Tjoueur = Jeu.TrierTjoueur(Tjoueur);

        for (int i = 0; i < this.Tjoueur.length; i++){
            if (Tjoueur[i].getCarteselec().CompareCarte(this.LSerie)){
                this.LSerie = Tjoueur[i].DepotCarte(this.LSerie,Tjoueur[i].getCarteselec().Difference(this.LSerie));
            }
            else{
                System.out.println("La carte sélectionnée " + Tjoueur[i] + " n'est pas valide.");
                System.out.println("Choisissez une série de carte à récuperer");
                System.out.println("Les lignes de séries sont: ");
                for (int j = 0; j < 4; j++){
                    System.out.print(this.LSerie[j] + " ");
                    for (int k = 0; k < this.LSerie[j].getLSerieCarte().length; k++){
                        System.out.print(" " + this.LSerie[j].getLSerieCarte()[k] + " ");
                    }
                    System.out.println();
                }
                this.LSerie = Tjoueur[i].DepotCarte(this.LSerie,Tjoueur[i].ChoixSerie());
            }
        }
    }
    public void Manche(){
        do {
            System.out.println("Nouvelle manche");
            for (int i = 0; i < 10; i++){
                Tour();
            }
            NewManche();
            ChangerNbTeteMax();
            for (int i = 0; i < this.getTjoueur().length; i++){
                System.out.println(this.getTjoueur()[i] + " a " + this.getTjoueur()[i].getNbTeteDeBoeuf() + " ttes de boeuf");
            }
        }while (this.nbtetedeboeufmax <= 66);
    }

    private void NewManche(){
        if (Jeu.getRep() != 3){
            this.paquet = MelangeTableau(this.copiePaquet);
            for (int i = 0; i < this.Tjoueur.length; i++){
                Carte[] newTcarte = new Carte[10];
                for(int j = 0; j < 10; j++){
                    newTcarte[j] = RecupererCarte(this.paquet,0);
                    this.paquet = EnleverCarte(this.paquet,0);
                    this.Tjoueur[i].setnmain(newTcarte);
                }
                this.Tjoueur[i].TrierMain();
            }
        }
        else{
            this.paquet = this.copiePaquet;
            for (int i = 0; i < this.Tjoueur.length; i++){
                this.Tjoueur[i].setnmain(new Carte[10]);
            }
            int j = 0;
            while(j < 10){
                for (int i = 0; i < this.Tjoueur.length; i++){
                    Tjoueur[i].CreationMainVar2(this,j);
                }
                j++;
            }
            for (int i = 0; i < Jeu.getNbJoueur(); i++){
                Tjoueur[i].TrierMain();
            }
            paquet = MelangeTableau(paquet);
        }
        for (int i = 0; i < this.LSerie.length; i++){
            this.LSerie[i] = new Serie((Jeu) this, i);
        }
    }
    private void ChangerNbTeteMax(){
        int tetedeboeufmax = Tjoueur[0].getNbTeteDeBoeuf();
        for (int i = 1; i < this.Tjoueur.length; i++){
            if (tetedeboeufmax < Tjoueur[i].getNbTeteDeBoeuf()){
                tetedeboeufmax = Tjoueur[i].getNbTeteDeBoeuf();
            }
        }
        this.nbtetedeboeufmax = tetedeboeufmax;
    }
    public void Jouer(){
        Manche();
        System.out.println("Le vainqueur est " + Vainqueur(this.Tjoueur));
    }

    public static Carte[] MelangeTableau(Carte[] tab){
        for (int k = 0; k < 555; k++){
            int i = 0 + (int)(Math.random() * (((tab.length-1) - 0) + 1));
            int j = 0 + (int)(Math.random() * (((tab.length-1) - 0) + 1));
            Carte temp;
            temp = tab[i];
            tab[i] = tab[j];
            tab[j] = temp;
        }
        return (tab);
    }
    public static Carte RecupererCarte(Carte[] tab, int x){
        Carte enleveCarte = new Carte();
        for (int i = 0; i < tab.length; i++){
            if (i == x){
                enleveCarte = tab[i];
            }
        }
        return(enleveCarte);
    }
    public static Carte[] EnleverCarte(Carte[] tab, int x){
        Carte[] newTab = new Carte[tab.length-1];
        for (int i = 0; i < tab.length-1; i++){
            if (i < x){
                newTab[i] = new Carte();
                newTab[i] = tab[i];
            }
            else{
                newTab[i] = new Carte();
                newTab[i] = tab[i+1];
            }
        }
        return(newTab);
    }
    public static int RecupererTeteDeBoeuf(Serie l){
        int teteSum = 0;
        for (int i = 0; i < l.getLSerieCarte().length; i++){
            teteSum= teteSum + l.getLSerieCarte()[i].getTeteDeBoeuf();
        }
        return  teteSum;
    }

    public static Joueur Vainqueur(Joueur[] Tjoueur){
        Joueur Vainqueur = Tjoueur[0];
        int teteMin = Tjoueur[0].getNbTeteDeBoeuf();
        for (int i = 1; i < Tjoueur.length; i++){
            if (teteMin > Tjoueur[i].getNbTeteDeBoeuf()){
                Vainqueur = Tjoueur[i];
                teteMin = Tjoueur[i].getNbTeteDeBoeuf();
            }
        }
        return(Vainqueur);
    }

    public Carte[] getPaquet() {
        return paquet;
    }

    public void setPaquet(Carte[] paquet) {
        this.paquet = paquet;
    }

    public Carte[] getCopiePaquet() {
        return copiePaquet;
    }

    public void setCopiePaquet(Carte[] copiePaquet) {
        this.copiePaquet = copiePaquet;
    }

    public Joueur[] getTjoueur() {
        return Tjoueur;
    }

    public void setTjoueur(Joueur[] Tjoueur) {
        this.Tjoueur = Tjoueur;
    }

    public Serie[] getLSerie() {
        return LSerie;
    }

    public void setLSerie(Serie[] LSerie) {
        this.LSerie = LSerie;
    }

}
