package entidades;

import java.util.Objects;

public class ReservaHotelVooIdaVolta extends ReservaHotel{
    private Voo vooRegresso;

    private static final String PREFIXO_RESERVA_HOTEL_VOO_IV = "R_HTL_VOO_IV-";
    private static int reservaHotelVooIdaVoltaCount = 0;

//    public ReservaHotelVooIdaVolta(Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo vooRegresso) {
//        super(hotel, dataChegada, numNoitesEstadia);
//        ++reservaHotelVooIdaVoltaCount;
//        this.vooRegresso = vooRegresso;
//    }

    public ReservaHotelVooIdaVolta() {
        super();
        ++reservaHotelVooIdaVoltaCount;
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
        return "ReservaHotelVooIdaVolta{" +
                "vooRegresso=" + vooRegresso +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotelVooIdaVolta that = (ReservaHotelVooIdaVolta) o;
        return Objects.equals(vooRegresso, that.vooRegresso);
    }

    public static int getReservaHotelVooIdaVoltaCount() {
        return reservaHotelVooIdaVoltaCount;
    }

    /**
     * @return
     */
    @Override
    public double calcularCustoReserva() {
        return 0;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO_IV, reservaHotelVooIdaVoltaCount);
    }
}
