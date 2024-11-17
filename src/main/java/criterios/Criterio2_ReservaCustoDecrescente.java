package criterios;

import entidades.Reserva;
import java.util.Comparator;

/**
 * Implementa um critério de comparação entre objetos do tipo {@link Reserva} baseado no custo da reserva.
 * As reservas são ordenadas em ordem decrescente de custo.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */
public class Criterio2_ReservaCustoDecrescente implements Comparator<Reserva> {

    /**
     * Compara duas reservas com base no custo calculado de cada uma.
     *
     * @param reserva1 Primeira reserva a ser comparada.
     * @param reserva2 Segunda reserva a ser comparada.
     * @return Um valor negativo se o custo da reserva1 for maior que o da reserva2,
     * um valor positivo se o custo da reserva1 for menor que o da reserva2,
     * e zero se ambos os custos forem iguais.
     */
    public int compare(Reserva reserva1, Reserva reserva2) {
        return Double.compare(reserva1.calcularCustoReserva(), reserva2.calcularCustoReserva());
    }
}
