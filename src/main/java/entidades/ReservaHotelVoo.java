package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaHotelVoo extends ReservaHotel {
    private Voo voo;

    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO-";
    private static int reservaHotelVooCount = 0;

    public ReservaHotelVoo(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia);
        ++reservaHotelVooCount;
        this.voo = voo;
    }

    public ReservaHotelVoo() {
        super();
        ++reservaHotelVooCount;
        this.voo = new Voo();
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    @Override
    public String toString() {
        return String.format("--- Reserva Hotel Voo ---\n%s\nVoo: %s", super.toString(), voo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotelVoo that = (ReservaHotelVoo) o;
        return Objects.equals(voo, that.voo);
    }

    public static int getReservaHotelVooCount() {
        return reservaHotelVooCount;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO, reservaHotelVooCount);
    }

    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
