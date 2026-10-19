public class Prodotto {

    private String nome;
    private double prezzo;
    private int quantità;

    public Prodotto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantità = quantita;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantità;
    }

    public void diminuisciQuantita() {
        if (quantità > 0) {
            quantità--;
        }
    }

    @Override
    public String toString() {
        return nome + ", "
                + prezzo + " euro, quantità: "
                + quantità;
    }
}