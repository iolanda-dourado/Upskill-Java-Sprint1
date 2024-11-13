package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaVooIdaVolta extends ReservaVoo {
    private Voo vooRegresso;

    private static final String PREFIXO_RESERVA_VOO_IDA_VOLTA = "R_VOO_IV-";
    private static final int KMS_PARA_DAR_DESCONTO = 1000;
    private static final double TAXA_DESCONTO_VOO_REGRESSO = 0.01;

    private int reservaVooIdaVoltaCount = 0;

    public ReservaVooIdaVolta(Data dataReserva, int qntPessoas, Cliente cliente, Voo voo, Voo vooRegresso) {
        super(dataReserva, qntPessoas, cliente, voo);
        ++reservaVooIdaVoltaCount;
        this.vooRegresso = vooRegresso;
    }

    public ReservaVooIdaVolta() {
        super();
        ++reservaVooIdaVoltaCount;
        this.vooRegresso = new Voo();
    }

    public Voo getVooRegresso() {
        return vooRegresso;
    }

    public void setVooRegresso(Voo vooRegresso) {
        this.vooRegresso = vooRegresso;
    }

    @Override
    public String toString() {
        return String.format("--- Reserva Voo Ida e Volta ---\n%s\nVoo de Regresso: %s", super.toString(), vooRegresso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaVooIdaVolta that = (ReservaVooIdaVolta) o;
        return reservaVooIdaVoltaCount == that.reservaVooIdaVoltaCount && Objects.equals(vooRegresso, that.vooRegresso);
    }

    public int getReservaVooIdaVoltaCount() {
        return reservaVooIdaVoltaCount;
    }


    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_VOO_IDA_VOLTA, reservaVooIdaVoltaCount);
    }

    @Override
    public double calcularCustoReserva() {
        double custoBilheteVooIda = getVoo().getPrecoBilhete();
        double custoBilheteVooRegresso = vooRegresso.getPrecoBilhete();
        double distanciaVolta = vooRegresso.getDistanciaKmAeroporto();

        double blocosDe1000km = distanciaVolta / KMS_PARA_DAR_DESCONTO;
        double desconto = blocosDe1000km * TAXA_DESCONTO_VOO_REGRESSO;
        return custoBilheteVooIda + (custoBilheteVooRegresso - (custoBilheteVooRegresso * desconto)) + getTaxaReserva();
    }
}