import entidades.*;
import enums.CategoriaHotel;
import enums.CompanhiaAerea;
import enums.Genero;
import utilidades.Data;
import utilidades.InteracaoEmpresaUtilizador;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
////        private List<Cliente> listaClientes;
////    private List<Hotel> listaHoteis;
////    private List<Aeroporto> listaAeroportos;
////    private List<Voo> listaVoos;
////    private List<Reserva> listaReservas;
//        List<Cliente> clientes = new ArrayList<>();
//        List<Aeroporto> aeroportos = new ArrayList<>();
//        List<Voo> voos = new ArrayList<>();
//        List<Hotel> hoteis = new ArrayList<>();
//        List<Reserva> reservas = new ArrayList<>();
//
//        Empresa novaEmpresa = new Empresa("Casinha verde", "Rua do Mangue", clientes, hoteis, aeroportos, voos, reservas);
//        Aeroporto aero1 = new Aeroporto("lalala","www.gru.com.br", "GRU" );
//        Aeroporto aero2 = new Aeroporto("lalala","www.ssa.com.br", "SSA" );
//        Aeroporto aero3 = new Aeroporto("lalala","www.opo.com.br", "OPO" );
//
//        Data data = new Data(1997, 3, 20);
//        Data data1 = new Data(1997, 2, 20);
//        Data data3 = new Data(1997, 3, 10);
//        Data data4 = new Data(1980, 5, 10);
//        Data data2 = Data.dataAtual();
//        Data data7 = new Data(2025, 02, 10);
//        Data data8 = new Data(2025, 03, 14);
//
//        LocalTime horario = LocalTime.of(22,30);
//
//        Cliente client = new Cliente("Maira", data1, Genero.FEMENINO, 322259339, "GF815694", "xxx@gmail.com", 5);
//        Cliente client1 = new Cliente("Luis", data2, Genero.MASCULINO, 322259193, "GF155692", "xx1@gmail.com", 10);
//        Cliente client2 = new Cliente("Amalia", data3, Genero.FEMENINO, 322259183, "GF955692", "xx11@gmail.com", 4);
////        Cliente client3 = new Cliente("Dante", data4, Genero.MASCULINO, 312259193, "GF858692", "xx12@gmail.com", 8);
//
//
//
//        Hotel hotel1 = new Hotel("Hotel 1", CategoriaHotel.UMA_ESTRELA, "Fim", true, 200);
//        Hotel hotel2 = new Hotel("Hotel 2", CategoriaHotel.DUAS_ESTRELAS, "Fim", true, 150);
//        Voo v1 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data, horario );
//        Voo v2 = new Voo("VOO-350", CompanhiaAerea.EASYJET, 100, aero3, aero1, 14000, 250, data8, horario );
//        // (Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia)
//        // (Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia)
////        System.out.println(client);
//        ReservaHotel reserva1 = new ReservaHotel(data, 1, client, hotel2, data1, 25);
////        ReservaHotel reserva2 = new ReservaHotel(data2, 4, client, hotel1, data7, 10);
////        ReservaHotelVoo reserva3 = new ReservaHotelVoo (data2, 4, client, hotel2, data7, 10, v1);
//        ReservaHotelVooIdaVolta reserva4 = new ReservaHotelVooIdaVolta (data2, 4, client, hotel2, data7, 10, v1, v2);
//        ReservaVoo reserva5 = new ReservaVoo (data2, 4, client, v1);
//
//
//        System.out.println("O valor da reserva 1 é igual a " + reserva1.calcularCustoReserva()+"euros. Dias de promoção: " + reserva1.verificaDiariasPromocao());
//
//        System.out.println("*****-----------------------------------*****");
////        System.out.println("O valor da reserva 2 é igual a " + reserva2.calcularCustoReserva()+"euros");
//        System.out.println("O valor da reserva 4 é igual a " + reserva4.calcularCustoReserva()+"euros");
//        System.out.println("O valor da reserva Voo é igual a " + reserva5.calcularCustoReserva()+"euros");
//        //System.out.println(v1.calcularCustoBilheteIda());
//
////        Empresa empresa1 = new Empresa("Empresa 1", "Rua Sem Nome");
////       // GeradorAutomatico.gerarVooAuto(empresa1);
////        empresa1.listarAeroportos();
////        empresa1.listarVoos();
//
//        System.out.println("\n");
//        System.out.println("\n");
//        novaEmpresa.adicionarCliente(client);
//        novaEmpresa.adicionarCliente(client1);
//        novaEmpresa.adicionarCliente(client2);
//        novaEmpresa.listarClientes();
//
////        System.out.println("Resultado de adicionar empresa repetida: " + empresa1.adicionarReserva(reserva1));
////        empresa1.adicionarReserva(reserva2);
////        empresa1.atualizarReservasConcretizadas(reserva1);
////        empresa1.atualizarReservasConcretizadas(reserva2);
////        System.out.println(client);
////        empresa1.listarReservas();
////
////        Criterio1 criterio = new Criterio1();
////        Collections.sort(lista1, criterio);
//////        System.out.println(lista1);
//
////        Aeroporto aeroportoSaida = new Aeroporto("Goiânia", "www.none.com", "AERO10231");
////        Aeroporto aeroportoChegada = new Aeroporto("Espírito Santo", "www.nothing.com", "AERO3451");
////        LocalTime hora1 = LocalTime.of(14, 12);
////
////        Voo voo1 = new Voo(
////                "V123", CompanhiaAerea.TAP, 150, aeroportoSaida, aeroportoChegada,
////                500, 200.0, data1, hora1);
////        System.out.println(voo1);
//////      Data dataReserva, int qntPessoas, Cliente cliente, Voo voo
////        ReservaVoo reservaVoo1 = new ReservaVoo(data1, 10, client1, voo1);
////        System.out.println(voo1);
//
//        Voo v3 = new Voo("VOO-250", CompanhiaAerea.EASYJET, 100, aero1, aero3, 14000, 250, data1, horario);
//        ReservaVoo reserva8 = new ReservaVoo(data2, 1, client, v3);
//        System.out.println("RESERVA TESTE: " + reserva8.calcularCustoReserva());
//
//        novaEmpresa.adicionarReserva(reserva1);
//        boolean retorno = novaEmpresa.atualizarReservasConcretizadas(reserva1);
//        System.out.print(retorno);

        Empresa empresa = new Empresa("Travel Plus", "Rua do Mangue", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        InteracaoEmpresaUtilizador interacao = new InteracaoEmpresaUtilizador(empresa);
        interacao.gerenciarResposta();
        System.out.println(empresa.listarReservas());


    }
}