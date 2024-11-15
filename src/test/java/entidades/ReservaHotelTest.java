package entidades;

import enums.CategoriaHotel;
import enums.Genero;
import org.junit.Test;
import utilidades.Data;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class ReservaHotelTest {

    private Data data1 = new Data(1997, 3, 20);
    private Data data2 = new Data(2024, 12, 25);
    private Data data3 = new Data(2024, 4, 8);
    private Data data4 = new Data(2024, 4, 9);
    private Data data5 = new Data(2024, 11, 15);

    private Cliente client = new Cliente("Maira", data2, Genero.FEMENINO, 322259339, "GF815694", "xxx@gmail.com", 5);
    private Cliente cliente1 = new Cliente();

    private Hotel hotel1 = new Hotel("Hotel Luxo", CategoriaHotel.CINCO_ESTRELAS, "Rio de Janeiro", true, 200.00);
    private Hotel hotel2 = new Hotel("Hotel 2", CategoriaHotel.DUAS_ESTRELAS, "Fim", true, 150);

    private ReservaHotel reserva1 = new ReservaHotel(data1, 2, client, hotel2, data2, 25);
    private ReservaHotel reserva2 = new ReservaHotel(data3, 4, client, hotel1, data4, 10);
    private ReservaHotel reserva3 = new ReservaHotel(data3, 3, client, hotel1, data5, 10);
    private ReservaHotel reserva4 = new ReservaHotel(data3, 4, client, hotel1, data4, 5);
    private ReservaHotel reserva5 = new ReservaHotel(data3, 3, client, hotel1, data5, 14);
    private ReservaHotel reserva6 = new ReservaHotel(data1, 2, client, hotel2, data2, 25);

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aeroporto> aeroportos = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Hotel> hoteis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private Empresa empresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);


    @Test
    public void calcularCustoReserva_dataForaEDentroPromocao() {
        double resultado = reserva1.calcularCustoReserva();
        double resultadoEsperado = 3590;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    @Test
    public void calcularCustoReserva_todaDataForaPromocao() {
        double resultado = reserva2.calcularCustoReserva();
        double resultadoEsperado = 4020;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }

    @Test
    public void calcularCustoReserva_todaDataDentroPromocao() {
        double resultado = reserva3.calcularCustoReserva();
        double resultadoEsperado = 2820;
        Assertions.assertEquals(resultadoEsperado, resultado, 0.01);
    }


    @Test
    public void calcularCustoReserva_dataForaEDentroPromoComDescontoMultiplos5() {
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
        double resultado = reserva6.calcularCustoReserva();
        double resultadoEsperado = 3410.50;
        assertEquals(resultadoEsperado, resultado, 0.01);
    }
}