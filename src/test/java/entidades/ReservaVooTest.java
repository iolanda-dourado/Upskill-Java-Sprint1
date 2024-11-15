package entidades;

import enums.CompanhiaAerea;
import enums.Genero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utilidades.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReservaVooTest {
    private Data data1 = new Data(1997, 2, 20);
    private Data data2 = new Data(1997, 12, 25);
    private Data data3 = new Data(2024, 4, 8);
    private Data data4 = new Data(2024, 4, 9);
    private Data data5 = new Data(2024, 11, 15);
    private LocalTime horario = LocalTime.of(22, 30);

    private Cliente client = new Cliente("Maira", data2, Genero.FEMENINO, 322259339, "GF815694", "xxx@gmail.com", 5);

    private Aeroporto aero1 = new Aeroporto("lalala", "www.gru.com.br", "GRU");
    private Aeroporto aero3 = new Aeroporto("lalala", "www.opo.com.br", "OPO");


    private Voo v1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);
    private Voo v2 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 14000, 250, data4, horario);

    ReservaVoo reserva1 = new ReservaVoo(data2, 1, client, v1);
    ReservaVoo reserva2 = new ReservaVoo(data2, 2, client, v1);
    ReservaVoo reserva3 = new ReservaVoo(data2, 4, client, v2);
    ReservaVoo reserva4 = new ReservaVoo(data2, 2, client, v2);
    ReservaVoo reserva5 = new ReservaVoo(data2, 1, client, v2);

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aeroporto> aeroportos = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Hotel> hoteis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private Empresa empresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);

    @Test
    public void calcularCustoReservaVooDentroPromocao() {
        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 195;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    @Test
    public void calcularCustoReservaVooForaPromocao() {
        Data data1 = new Data(2024, 12, 10);
        Voo voo1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);
        ReservaVoo reserva10 = new ReservaVoo(data1, 2, client, voo1);
        double resultado = reserva10.calcularCustoReserva();
        double resultadoEsperado = 520;

        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    @Test
    public void calcularCustoReservaVooSemPromocaoEComMultiplo5() {
        Data data1 = new Data(2024, 12, 10);
        Voo voo1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);
        empresa.atualizarReservasConcretizadas(reserva1);
        empresa.atualizarReservasConcretizadas(reserva2);
        empresa.atualizarReservasConcretizadas(reserva3);
        empresa.atualizarReservasConcretizadas(reserva4);
        empresa.atualizarReservasConcretizadas(reserva5);

        ReservaVoo reserva10 = new ReservaVoo(data1, 2, client, voo1);

        double resultado = reserva10.calcularCustoReserva();
        double resultadoEsperado = 494;

        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    @Test
    public void calcularCustoReservaVooComPromocaoEComMultiplo5() {
        Data data1 = new Data(2024, 2, 10);
        Voo voo1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);
        empresa.atualizarReservasConcretizadas(reserva1);
        empresa.atualizarReservasConcretizadas(reserva2);
        empresa.atualizarReservasConcretizadas(reserva3);
        empresa.atualizarReservasConcretizadas(reserva4);
        empresa.atualizarReservasConcretizadas(reserva5);

        ReservaVoo reserva10 = new ReservaVoo(data1, 2, client, voo1);

        double resultado = reserva10.calcularCustoReserva();
        double resultadoEsperado = 351.5;

        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }
}