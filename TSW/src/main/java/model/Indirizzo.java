package model;


public class Indirizzo {
    private int id;
    private int idUtente;
    private String via, citta, cap, provincia, nazione, telefono;

    public Indirizzo() {}

    public Indirizzo(int id, int idUtente, String via, String citta, String cap, String provincia, String nazione, String telefono) {
        this.id = id;
        this.idUtente = idUtente;
        this.via = via;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
        this.nazione = nazione;
        this.telefono = telefono;
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
// Getters & Setters
    // (Completi tutti, simili a quelli già mostrati)
}

