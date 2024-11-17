package entidades;

import enums.CompanhiaAerea;
import enums.Genero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utilidades.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de teste para o cálculo de custos de reservas de voo de ida e volta.
 * Esta classe contém vários testes que verificam o cálculo de custos para reservas de voo de ida e volta,
 * levando em consideração promoções, múltiplos de 5 e outras condições específicas (desconto percentual a cada
 * 1000kms entre os aeroportos, para todos os voos de volta).
 */
public class ReservaVooIdaVoltaTest {
    private Data data1 = new Data(2025, 1, 5);
    private Data data2 = new Data(2024, 12, 25);
    private Data data4 = new Data(2024, 3, 9);
    private Data data5 = new Data(2024, 11, 15);
    private LocalTime horario = LocalTime.of(22, 30);

    private Cliente client = new Cliente("Maira", data2, Genero.FEMININO, 322259339, "GF815694", "xxx@gmail.com", 5);

    private Aeroporto aero1 = new Aeroporto("lalala", "www.gru.com.br", "GRU");
    private Aeroporto aero3 = new Aeroporto("lalala", "www.opo.com.br", "OPO");

    private Voo v2 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 14000, 200, data4, horario);
    private Voo v3 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 10000, 100, data5, horario);
    private Voo v4 = new Voo("VOO-120", CompanhiaAerea.LATAM, 150, aero3, aero1, 14000, 100, data2, horario);
    private Voo v5 = new Voo("VOO-280", CompanhiaAerea.AZUL, 120, aero1, aero3, 10000, 100, data1, horario);

    private ReservaVooIdaVolta reserva1 = new ReservaVooIdaVolta(data4, 2, client, v2, v3);
    private ReservaVooIdaVolta reserva2 = new ReservaVooIdaVolta(data2, 1, client, v4, v5);
    private ReservaVooIdaVolta reserva3 = new ReservaVooIdaVolta(data2, 2, client, v2, v4);
    private ReservaVooIdaVolta reserva4 = new ReservaVooIdaVolta(data2, 2, client, v2, v2);
    private ReservaVooIdaVolta reserva5 = new ReservaVooIdaVolta(data2, 1, client, v2, v2);

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aeroporto> aeroportos = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Hotel> hoteis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private Empresa empresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);

    /**
     * Testa o cálculo do custo de uma reserva de voo de ida e volta quando a reserva está dentro da promoção de 30% de desconto.
     * O custo esperado é 420, dado que a reserva se qualifica para o desconto.
     */
    @Test
    public void calcularCustoReservaVooIVDentroPromocao30() {

        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 420;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    /**
     * Testa o cálculo do custo de uma reserva de voo de ida e volta quando a reserva está fora de uma promoção.
     * O custo esperado é 210, dado que a reserva não se qualifica para o desconto.
     */
    @Test
    public void calcularCustoReservaVooIVForaPromocao30() {

        double resultado = reserva2.calcularCustoReserva();
        double resultadoEsperado = 210;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    /**
     * Testa o cálculo do custo de uma reserva de voo de ida e volta quando uma parte da reserva está dentro da promoção
     * e outra parte está fora da promoção. O custo esperado é 472.
     */
    @Test
    public void calcularCustoReservaVooIVDentroEForaPromocao30() {

        double resultado = reserva3.calcularCustoReserva();
        double resultadoEsperado = 472;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    /**
     * Testa o cálculo do custo de uma reserva de voo de ida e volta dentro de uma promoção e com múltiplas reservas
     * concretizadas, aplicando o desconto adicional para reservas múltiplas de 5. O custo esperado é 399.
     */
    @Test
    public void calcularCustoReservaVooIVDentroPromocao30EReservasMultiplasDe5() {
        empresa.adicionarReserva(reserva1);
        empresa.adicionarReserva(reserva2);
        empresa.adicionarReserva(reserva3);
        empresa.adicionarReserva(reserva4);
        empresa.adicionarReserva(reserva5);

        empresa.atualizarReservasConcretizadas(reserva1);
        empresa.atualizarReservasConcretizadas(reserva2);
        empresa.atualizarReservasConcretizadas(reserva3);
        empresa.atualizarReservasConcretizadas(reserva4);
        empresa.atualizarReservasConcretizadas(reserva5);

        ReservaVooIdaVolta reserva6 = new ReservaVooIdaVolta(data4, 2, client, v2, v3);
        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 399;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }
}