import java.util.ArrayList;
import java.util.List;

public class Distributore {

    private List<Prodotto> catalogo;
    private double credito;

    public Distributore() {
        catalogo = new ArrayList<>();
        credito = 0;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        catalogo.add(prodotto);
    }

    public void inserisciCredito(double importo) {
        credito += importo;
    }

    public double getCredito() {
        return credito;
    }

    public Prodotto cercaProdotto(String nome) {
        for (Prodotto prodotto : catalogo) {
            if (prodotto.getNome().equalsIgnoreCase(nome)) {
                return prodotto;
            }
        }

        return null;
    }

    public Prodotto acquista(String nome)
            throws ProdottoEsauritoException, CreditoInsufficienteException {

        Prodotto prodotto = cercaProdotto(nome);

        if (prodotto == null) {
            throw new ProdottoEsauritoException(
                    "Prodotto non presente nel catalogo."
            );
        }

        if (prodotto.getQuantita() == 0) {

            Prodotto alternativo = trovaAlternativa();

            if (alternativo != null) {
                throw new ProdottoEsauritoException(
                        "Il prodotto " + prodotto.getNome()
                        + " è finito. Puoi comprare "
                        + alternativo.getNome()
                        + " che costa "
                        + String.format("%.2f", alternativo.getPrezzo())
                        + " euro."
                );
            }

            throw new ProdottoEsauritoException(
                    "Il prodotto " + prodotto.getNome()
                    + " è finito e non ci sono alternative disponibili."
            );
        }

        if (credito < prodotto.getPrezzo()) {
            double mancante = prodotto.getPrezzo() - credito;
            throw new CreditoInsufficienteException(mancante);
        }

        prodotto.diminuisciQuantita();
        credito -= prodotto.getPrezzo();

        return prodotto;
    }

    private Prodotto trovaAlternativa() {
        for (Prodotto prodotto : catalogo) {
            if (prodotto.getQuantita() > 0
                    && prodotto.getPrezzo() <= credito) {
                return prodotto;
            }
        }

        return null;
    }

    public void mostraCatalogo() {
        System.out.println("\n--- CATALOGO ---");

        for (Prodotto prodotto : catalogo) {
            System.out.println(prodotto);
        }
    }
}