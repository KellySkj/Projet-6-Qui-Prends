package Model;

public class Carte {
    private int numero;
    private int teteDeBoeuf;

    public Carte() {
    }


    public Carte(int numero) {
        this.numero = numero + 1;
        this.teteDeBoeuf = Calculertetedeboeuf(numero);
    }

    private int Calculertetedeboeuf(int numero) {

        if (numero % 10 == 0) {
            return 3;
        } else if (numero % 10 == 5) {
            return 2;
        } else if (numero % 11 == 0) {
            return 5;
        } else {
            return 1;
        }

    }

    public boolean CompareCarte(Serie[] LSerie){
        boolean jouable = false;
        int i = 0;
        while (!jouable && i < LSerie.length){
            if (this.numero > LSerie[i].getLSerieCarte()[LSerie[i].getLSerieCarte().length - 1].getNum()){
                jouable = true;
            }
            i++;
        }
        return(jouable);
    }
    public int Difference(Serie[] LSerie){
        int Seriep = 0;
        int somme;
        if ((this.getNum() + LSerie[0].getLSerieCarte()[LSerie[0].getLSerieCarte().length - 1].getNum()) > (2 * this.getNum())){
            somme = 0;
        }
        else{
            somme = this.getNum() + LSerie[0].getLSerieCarte()[LSerie[0].getLSerieCarte().length - 1].getNum();
        }
        for (int i = 1; i < LSerie.length; i++){
            if ((somme < this.getNum() + LSerie[i].getLSerieCarte()[LSerie[i].getLSerieCarte().length - 1].getNum()) && ((this.getNum() + LSerie[i].getLSerieCarte()[LSerie[i].getLSerieCarte().length - 1].getNum()) < (2 * this.getNum()))){
                somme = this.getNum() + LSerie[i].getLSerieCarte()[LSerie[i].getLSerieCarte().length - 1].getNum();
                Seriep = i;
            }
        }
        return(Seriep);
    }

    @Override
    public String toString() {
        return "{[" + getNum() + "](" + getTeteDeBoeuf() + ")}";
    }
    public int getNum() {
        return numero;
    }
    public void setNum(int numero) {
        this.numero = numero;
    }
    public int getTeteDeBoeuf() {
        return teteDeBoeuf;
    }
    public void setTeteDeBoeuf(int teteDeBoeuf) {
        this.teteDeBoeuf = teteDeBoeuf;
    }


}
