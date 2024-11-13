package entidades;

public class ReservaHotelVooIdaVolta extends ReservaHotel{
    private ReservaVoo reservaVooRegresso;
    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO_IV-";
    /**
     * @return
     */
    @Override
    public double calcularCustoReserva() {
        return 0;
    }

    @Override
    public String gerarIdentificador() {
        return "";
    }
}
