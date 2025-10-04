    package model;


    import java.math.BigDecimal;
    import java.sql.Timestamp;

    public class Ordine {
        private int id;
        private int idUtente;
        private int idIndirizzo;
        private int idMetodoPagamento;
        private Timestamp dataOrdine;
        private BigDecimal totale;


        public Ordine() {}

        public Ordine(int id, int idUtente, int idIndirizzo, int idMetodoPagamento, Timestamp dataOrdine, BigDecimal totale) {
            this.id = id;
            this.idUtente = idUtente;
            this.idIndirizzo = idIndirizzo;
            this.idMetodoPagamento = idMetodoPagamento;
            this.dataOrdine = dataOrdine;
            this.totale = totale;
        }

        // Getters & Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getIdUtente() { return idUtente; }
        public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

        public int getIdIndirizzo() { return idIndirizzo; }
        public void setIdIndirizzo(int idIndirizzo) { this.idIndirizzo = idIndirizzo; }

        public int getIdMetodoPagamento() { return idMetodoPagamento; }
        public void setIdMetodoPagamento(int idMetodoPagamento) { this.idMetodoPagamento = idMetodoPagamento; }

        public Timestamp getDataOrdine() { return dataOrdine; }
        public void setDataOrdine(Timestamp dataOrdine) { this.dataOrdine = dataOrdine; }

        public BigDecimal getTotale() { return totale; }
        public void setTotale(BigDecimal totale) { this.totale = totale; }
    }
