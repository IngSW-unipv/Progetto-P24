package NextFit;

public class Esercizio {
    private String nome;
    private int serie;
    private int ripetizioni;
    private int peso; //kg
    private int riposoTraSerie; //secondi

    public Esercizio(String nome) {
        this.nome = nome;
        this.serie = 4;
        this.ripetizioni = 10;
        this.peso = 0;
        this.riposoTraSerie = 30;
    }

    public String getNome() {
        return nome;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getRiposoTraSerie() {
        return riposoTraSerie;
    }

    public void setRiposoTraSerie(int riposoTraSerie) {
        this.riposoTraSerie = riposoTraSerie;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

