package entidades;

import interfaces.Identificacavel;
import utilidades.Data;

import java.io.Serializable;
import java.util.Objects;

public abstract class Reserva implements Identificacavel, Comparable<Reserva>, Serializable {
    private String codigoReserva;
    private Data dataReserva;
    private int qntPessoas;
    private Cliente cliente;
    private boolean isConcretizada;
    private boolean isAtributoSet;

    private static final Data DATA_RESERVA_OMISSAO = Data.dataAtual();
    private static final int QNT_PESSOAS_OMISSAO = 0;
    private static final Cliente CLIENTE_OMISSAO = new Cliente();
    private static final boolean ISCONCRETIZADA_OMISSAO = false;

    private static int reservaCount = 0;
    private static double taxaReserva = 20;
    private static int numMultiploDeX = 5;



    public Reserva(Data dataReserva, int qntPessoas, Cliente cliente) {
        this.codigoReserva = gerarIdentificador();
        this.dataReserva = new Data (dataReserva);
        this.qntPessoas = qntPessoas;
        this.cliente = cliente;
        this.isConcretizada = false;
        isAtributoSet = false;
        ++reservaCount;
    }

    public Reserva() {
        this.codigoReserva = gerarIdentificador();
        this.dataReserva = DATA_RESERVA_OMISSAO;
        this.qntPessoas = QNT_PESSOAS_OMISSAO;
        this.cliente = CLIENTE_OMISSAO;
        this.isConcretizada = ISCONCRETIZADA_OMISSAO;
        ++reservaCount;
    }

    public Reserva(Reserva outra) {
        this.codigoReserva = outra.codigoReserva;
        this.dataReserva = new Data (outra.dataReserva);
        this.qntPessoas = outra.qntPessoas;
        this.cliente = new Cliente (outra.cliente);
        this.isConcretizada = outra.isConcretizada;
        isAtributoSet = outra.isAtributoSet;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public Data getDataReserva() {
        return new Data(dataReserva);
    }

    public int getQntPessoas() {
        return qntPessoas;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public boolean isConcretizada() {
        return isConcretizada;
    }

    protected void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public void setDataReserva(Data dataReserva) {
        this.dataReserva.setData(dataReserva.getAno(), dataReserva.getMes(), dataReserva.getDia());
    }

    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setConcretizada(boolean concretizada) {
        if (!isAtributoSet) {
            this.isConcretizada = concretizada;
            isAtributoSet = true;
        } else {
            throw new UnsupportedOperationException("A reserva já foi concretizada  e não pode ser alterada.");
        }
    }

    @Override
    public String toString() {
        String resposta = isConcretizada ? "Sim" : "Não";
        return String.format("--- Informações da reserva ---\nData da reserva: %s\nCliente: %s\nQuantidade de pessoas: %d\nReserva concretizada? %s\n", dataReserva, cliente.getNomeCliente(), qntPessoas, resposta);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return qntPessoas == reserva.qntPessoas && isConcretizada == reserva.isConcretizada && isAtributoSet == reserva.isAtributoSet && Objects.equals(dataReserva, reserva.dataReserva) && Objects.equals(cliente, reserva.cliente);
    }


    @Override
    public abstract String gerarIdentificador();

    // Implementa Comparable (ordenar por ordem crescente de Data)

    @Override
    public int compareTo(Reserva outraReserva) {
        if (dataReserva.isMaior(outraReserva.dataReserva)) {
            return -1;
        } else if (!dataReserva.isMaior(outraReserva.dataReserva)) {
            return 1;
        } else {
            return 0;
        }
    }
    public boolean saoReservasMultiplasDe5() {
        return getCliente().getNumReservasConcretizadas() != 0 && getCliente().getNumReservasConcretizadas() % getNumMultiploDeX() == 0;
    }

    public abstract double calcularCustoReserva();

    public static int getReservaCount() {
        return reservaCount;
    }

    public static double getTaxaReserva() {
        return taxaReserva;
    }

    public static int getNumMultiploDeX() {
        return numMultiploDeX;
    }

    public void setTaxaReserva(double taxaReserva) {
        Reserva.taxaReserva = taxaReserva;
    }

    public static void setNumMultiploDeX(int numMultiploDeX) {
        Reserva.numMultiploDeX = numMultiploDeX;
    }
}
