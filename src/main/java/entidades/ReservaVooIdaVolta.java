package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaVooIdaVolta extends ReservaHotelVoo {
    private Voo vooRegresso;

    private static final String PREFIXO_RESERVA_VOO_IDA_VOLTA = "R_VOO_IV-";

    private int reservaVooIdaVoltaCount = 0;

    public ReservaVooIdaVolta(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo, Voo vooRegresso) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia, voo);
        ++reservaVooIdaVoltaCount;
        this.setCodigoReserva(gerarIdentificador());
        this.vooRegresso = vooRegresso;
    }

    public ReservaVooIdaVolta() {
        super();
        ++reservaVooIdaVoltaCount;
        this.setCodigoReserva(gerarIdentificador());
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
        return 1;
    }
}
