package entidades;

public class ReservaHotelVooIdaVolta extends Reserva{
    private ReservaVoo reservaVooRegresso;
    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO_IV-";
    /**
     * @return
     */
    @Override
    public double calcularCustoReserva() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int gerarIdentificador() {
        return 0;
    }
}
