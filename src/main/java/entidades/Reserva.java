package entidades;

import interfaces.Descontavel;
import interfaces.Identificacavel;
import utilidades.Data;

import java.util.Objects;

public abstract class Reserva implements Identificacavel, Comparable<Reserva>, Descontavel {
    private String codigoReserva;
    private Data dataReserva;
    private int qntPessoas;
    private Cliente cliente;
    private boolean isConcretizada;

    private static final Data DATA_RESERVA_OMISSAO = Data.dataAtual();
    private static final int QNT_PESSOAS_OMISSAO = 0;
    private static final Cliente CLIENTE_OMISSAO = new Cliente();
    private static final boolean ISCONCRETIZADA_OMISSAO = false;

    private static int reservaCount = 0;
    private static double taxaReserva = 20;
    private static int numMultiploDeX = 5;


    public Reserva(Data dataReserva, int qntPessoas, Cliente cliente) {
        this.codigoReserva = gerarIdentificador();
        this.dataReserva = dataReserva;
        this.qntPessoas = qntPessoas;
        this.cliente = cliente;
        this.isConcretizada = false;
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

    public String getCodigoReserva() {
        return codigoReserva;
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

    protected void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
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
        return String.format("--- Informações da reserva ---\nData da reserva: %s\nCliente: %s\nQuantidade de pessoas: %d\nReserva concretizada? %s\n", dataReserva, cliente.getNomeCliente(), qntPessoas, resposta);

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

    public abstract double calcularCustoReserva();

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

    @Override
    public int formatarData(Data umaData) {
        String st = umaData.toAnoMesDiaString().replace("/", "");
        return Integer.parseInt(st);
    }


    @Override
    public boolean isPromocao(int a, Data umaData) {
        int dataTemp1 = Integer.parseInt(umaData.getAno() + Descontavel.INICIO_TEMP1);
        int dataTemp2 = Integer.parseInt(umaData.getAno() +  Descontavel.FINAL_TEMP1);
        int dataTemp3 = Integer.parseInt(umaData.getAno() +  Descontavel.INICIO_TEMP2);
        int dataTemp4 = Integer.parseInt(umaData.getAno() + Descontavel.FINAL_TEMP2);
        int dataTemp5 = Integer.parseInt(umaData.getAno() + Descontavel.INICIO_TEMP3);
        int dataTemp6 = Integer.parseInt(umaData.getAno() + Descontavel.FINAL_TEMP3);

        if (a >= dataTemp1 || a <= dataTemp2){
            return true;
        } else if (a >= dataTemp3 || a <= dataTemp4) {
            return true;
        } else return a >= dataTemp5 || a <= dataTemp6;

    }
}
