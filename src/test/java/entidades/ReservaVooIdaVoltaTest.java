package entidades;

import enums.CompanhiaAerea;
import enums.Genero;
import org.junit.Test;
import utilidades.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReservaVooIdaVoltaTest {
    private Data data1 = new Data(1997, 2, 20);
    private Data data2 = new Data(1997, 12, 25);
    private Data data3 = new Data(2024, 4, 8);
    private Data data4 = new Data(2024, 4, 9);
    private Data data5 = new Data(2024, 11, 15);
    private LocalTime horario = LocalTime.of(22, 30);

    private Cliente client = new Cliente("Maira", data2, Genero.FEMENINO, 322259339, "GF815694", "xxx@gmail.com", 5);

    private Aeroporto aero1 = new Aeroporto("lalala", "www.gru.com.br", "GRU");
    private Aeroporto aero3 = new Aeroporto("lalala", "www.opo.com.br", "OPO");


    private Voo v2 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 14000, 250, data4, horario);
    private Voo v3 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 10000, 100, data4, horario);

    ReservaVooIdaVolta reserva1 = new ReservaVooIdaVolta(data2, 1, client, v3,v2);
    ReservaVooIdaVolta reserva2 = new ReservaVooIdaVolta(data2, 2, client, v3,v2);
    ReservaVooIdaVolta reserva3 = new ReservaVooIdaVolta(data2, 4, client, v2,v2);
    ReservaVooIdaVolta reserva4 = new ReservaVooIdaVolta(data2, 2, client, v2,v2);
    ReservaVooIdaVolta reserva5 = new ReservaVooIdaVolta(data2, 1, client, v2,v2);

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aeroporto> aeroportos = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Hotel> hoteis = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    private Empresa empresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);

    @Test
    public void calcularCustoReserva() {
        Data data1 = new Data(1997, 2, 20);
        Voo v1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);

    }
}