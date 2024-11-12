package upskill;

import interfaces.Identificacavel;

import java.util.Objects;

public abstract class Reserva implements Identificacavel {
    private Data dataReserva;
    private int qntPessoas;
    private Cliente cliente;
    private boolean isConcretizada;

    private static final Data DATA_RESERVA_OMISSAO = Data.dataAtual();
    private static final int QNT_PESSOAS_OMISSAO = 0;
    private static final Cliente CLIENTE_OMISSAO = new Cliente();
    private static final boolean ISCONCRETIZADA_OMISSAO = false;

    private static int reservaCount = 0;

    public Reserva (Data dataReserva, int qntPessoas, Cliente cliente, boolean isConcretizada) {
        this.dataReserva = dataReserva;
        this.qntPessoas = qntPessoas;
        this.cliente = cliente;
        this.isConcretizada = isConcretizada;
        ++reservaCount;
    }

    public Reserva () {
        this.dataReserva = DATA_RESERVA_OMISSAO;
        this.qntPessoas = QNT_PESSOAS_OMISSAO;
        this.cliente = CLIENTE_OMISSAO;
        this.isConcretizada = ISCONCRETIZADA_OMISSAO;
    }

    public Data getDataReserva() {
        return dataReserva;
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


    public void setDataReserva(Data dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setConcretizada(boolean concretizada) {
        isConcretizada = concretizada;
    }

    @Override
    public String toString() {
        String resposta = isConcretizada ? "Sim" : "Não";
        return String.format("Informações da reserva:\nData da reserva: %s\nCliente: %s\nQuantidade de pessoas: %d\nReserva concretizada? %s\n", dataReserva, cliente.getNomeCliente(), qntPessoas, resposta );

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return getQntPessoas() == reserva.getQntPessoas() &&
                isConcretizada() == reserva.isConcretizada() &&
                Objects.equals(getDataReserva(), reserva.getDataReserva()) &&
                Objects.equals(getCliente(), reserva.getCliente());
    }

    public static int getReservaCount() {
        return reservaCount;
    }
}
