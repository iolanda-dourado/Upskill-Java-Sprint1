package criterios;

import entidades.Cliente;
import entidades.Reserva;
import java.util.Comparator;

public class Criterio3_ReservaCustoCrescente implements Comparator<Reserva> {
    public int compare(Reserva reserva1, Reserva reserva2) {
        if (reserva1.calcularCustoReserva() < reserva2.calcularCustoReserva()) {
            return -1;
        } else if (reserva1.calcularCustoReserva() > reserva2.calcularCustoReserva()) {
            return 1;
        } else {
            return 0;
        }
    }
}
