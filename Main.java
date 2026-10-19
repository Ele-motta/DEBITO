public class Main {

    public static void main(String[] args) {

        Distributore distributore = new Distributore();

        distributore.aggiungiProdotto(
                new Prodotto("Patatine", 1.70, 3)
        );

        distributore.aggiungiProdotto(
                new Prodotto("Coca Cola", 3.00, 2)
        );

        distributore.aggiungiProdotto(
                new Prodotto("Acqua", 7.00, 5)
        );

        distributore.aggiungiProdotto(
                new Prodotto("Cioccolata", 12.00, 0)
        );

        distributore.mostraCatalogo();

        System.out.println("\n--- CASO 1: DENARO INSUFFICIENTE ---");

        distributore.inserisciCredito(2.00);

        try {
            distributore.acquista("Coca Cola");

        } catch (CreditoInsufficienteException e) {
            System.out.println("ERRORE: " + e.getMessage());

        } catch (ProdottoEsauritoException e) {
            System.out.println("ERRORE: " + e.getMessage());
        }


        System.out.println("\n--- CASO 2: PRODOTTO FINITO ---");

        try {
            distributore.acquista("Cioccolata");

        } catch (ProdottoEsauritoException e) {
            System.out.println("ERRORE: " + e.getMessage());

        } catch (CreditoInsufficienteException e) {
            System.out.println("ERRORE: " + e.getMessage());
        }


        System.out.println("\n--- CASO 3: ACQUISTO CORRETTO ---");

        distributore.inserisciCredito(1.00);

        try {
            Prodotto prodotto = distributore.acquista("Coca Cola");

            System.out.println(
                    "Acquisto effettuato: " + prodotto.getNome()
            );

            System.out.println(
                    "Credito rimanente: "
                    + String.format("%.2f", distributore.getCredito())
                    + " euro"
            );

        } catch (ProdottoEsauritoException e) {
            System.out.println("ERRORE: " + e.getMessage());

        } catch (CreditoInsufficienteException e) {
            System.out.println("ERRORE: " + e.getMessage());
        }
    }
}