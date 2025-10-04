package model;


public class Carrello {
    private int id;
    private int idUtente;

    public Carrello() {}

    public Carrello(int id, int idUtente) {
        this.id = id;
        this.idUtente = idUtente;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }
}

