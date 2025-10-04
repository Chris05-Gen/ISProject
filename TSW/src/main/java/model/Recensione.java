package model;



import java.sql.Date;

public class Recensione {
    private int id;
    private int idUtente;
    private String isbn;
    private String titolo;
    private String testo;
    private int valutazione;
    private Date data;
    private String nomeUtente;


    public Recensione() {}

    public Recensione(int id, int idUtente, String isbn, String titolo, String testo, int valutazione, Date data) {
        this.id = id;
        this.idUtente = idUtente;
        this.isbn = isbn;
        this.titolo = titolo;
        this.testo = testo;
        this.valutazione = valutazione;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    // Getters & Setters
}

