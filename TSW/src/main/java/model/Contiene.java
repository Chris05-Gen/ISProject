package model;


public class Contiene {
    private int idCarrello;
    private String isbn;
    private int quantita;

    public Contiene() {}

    public Contiene(int idCarrello, String isbn, int quantita) {
        this.idCarrello = idCarrello;
        this.isbn = isbn;
        this.quantita = quantita;
    }

    public int getIdCarrello() { return idCarrello; }
    public void setIdCarrello(int idCarrello) { this.idCarrello = idCarrello; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
}

