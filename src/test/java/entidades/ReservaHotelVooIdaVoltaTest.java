package entidades;

import enums.CategoriaHotel;
import enums.CompanhiaAerea;
import enums.Genero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import utilidades.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReservaHotelVooIdaVoltaTest {
    private Data data1 = new Data(2025, 3, 20);
    private Data data2 = new Data(2024, 12, 30);
    private Data data5 = new Data(2024, 11, 15);

    private LocalTime horario = LocalTime.of(22,30);

    private Aeroporto aero1 = new Aeroporto("lalala", "www.gru.com.br", "GRU");
    private Aeroporto aero3 = new Aeroporto("lalala", "www.opo.com.br", "OPO");

    private Cliente client = new Cliente("Maira", data2, Genero.FEMENINO, 322259339, "GF815694", "xxx@gmail.com", 5);

    private Voo v1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 10000, 100, data2, horario);
    private Voo v2 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 10000, 100, data5, horario);

    private Hotel hotel1 = new Hotel("Hotel Luxo", CategoriaHotel.CINCO_ESTRELAS, "Rio de Janeiro", true, 200.00);
    private Hotel hotel2 = new Hotel("Hotel 2", CategoriaHotel.DUAS_ESTRELAS, "Fim", true, 100);

    private ReservaHotelVooIdaVolta reserva1 = new ReservaHotelVooIdaVolta(data1, 2, client, hotel2, data2, 20, v1,v2);
    private ReservaHotelVooIdaVolta reserva2 = new ReservaHotelVooIdaVolta(data1, 2, client, hotel2, data2, 5, v1, v2);
    private ReservaHotelVooIdaVolta reserva3 = new ReservaHotelVooIdaVolta(data1, 4, client, hotel1, data5, 5, v2, v2);
    private ReservaHotelVooIdaVolta reserva4 = new ReservaHotelVooIdaVolta(data1, 2, client, hotel2, data2, 5, v1, v2);
    private ReservaHotelVooIdaVolta reserva5 = new ReservaHotelVooIdaVolta(data1, 4, client, hotel1, data5, 5, v2, v2);
    private ReservaHotelVooIdaVolta reserva10 = new ReservaHotelVooIdaVolta(data1, 4, client, hotel1, data5, 5, v2,v1);


    private List<Cliente> clientes = new ArrayList<>();
    private List<Aeroporto> aeroportos = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Hotel> hoteis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private Empresa empresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);


    @Test
    public void calcularCustoReserva_dataForaEDentroPromocao() {
        //Voo ida fora e voo regreso dentro da promoção e diárias começando fora da promoção e entrando por 4 dias;
        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 2220;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }


    @Test
    public void calcularCustoReserva_dataForaEDentroPromocaoEComMultiplo5() {
        //Mesmo metodo acima + desconto das reservas concretizadas multiplo de 5;
        empresa.adicionarReserva(reserva10);
        empresa.adicionarReserva(reserva2);
        empresa.adicionarReserva(reserva3);
        empresa.adicionarReserva(reserva4);
        empresa.adicionarReserva(reserva5);

        empresa.atualizarReservasConcretizadas(reserva10);
        empresa.atualizarReservasConcretizadas(reserva2);
        empresa.atualizarReservasConcretizadas(reserva3);
        empresa.atualizarReservasConcretizadas(reserva4);
        empresa.atualizarReservasConcretizadas(reserva5);


        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 2109;

        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }
}