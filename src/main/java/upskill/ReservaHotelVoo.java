package upskill;

public class ReservaHotelVoo extends Reserva {
    private ReservaVoo reservaVoo;

    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO-";
    @Override
    public int gerarIdentificador() {
        return 0;
    }

    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
