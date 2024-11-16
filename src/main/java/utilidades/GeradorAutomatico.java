package utilidades;

import entidades.*;
import enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorAutomatico {
    private static final int NUM_AEROPORTOS = 4;
    private static final int NUM_VOOS = 6;
    private static final int NUM_HOTEIS = 4;
    private static final int NUM_CLIENTES = 3;
    private static final int NUM_RESERVAS = 2;

    public GeradorAutomatico() {
    }

    public void gerarAeroportosAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
//            GerarAutomaticoAeroporto infoAero = GerarAutomaticoAeroporto.values()[gerador.nextInt(GerarAutomaticoAeroporto.values().length)];
            GerarAutomaticoAeroporto infoAero = GerarAutomaticoAeroporto.values()[cont];
            Aeroporto aeroporto = new Aeroporto(infoAero.getEndereco(), infoAero.getPaginaWeb(), infoAero.getCodigoAeroporto());
            if (empresa.adicionarAeroporto(aeroporto)) {
                cont++;
            }
        } while (cont != NUM_AEROPORTOS);
    }

    public void gerarVoosAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            Voo voo = new Voo();
//            GerarAutomaticoVoo infoVoo = GerarAutomaticoVoo.values()[gerador.nextInt(GerarAutomaticoVoo.values().length)];
            GerarAutomaticoVoo infoVoo = GerarAutomaticoVoo.values()[cont];
            List<Aeroporto> listaAeroportos = empresa.getListaAeroportos();

            voo.setCodigoVoo(infoVoo.getCodigoVoo());
            voo.setCompanhiaAerea(infoVoo.getCompanhiaAerea());
            voo.setQntLugares(infoVoo.getQntLugares());
            voo.setQntLugaresDisponiveis(infoVoo.getQntLugaresDisponiveis());

            Aeroporto aeroportoSaida = listaAeroportos.get(gerador.nextInt(4));
            Aeroporto aeroportoChegada;
            do {
                aeroportoChegada = listaAeroportos.get(gerador.nextInt(4));
            } while (aeroportoChegada.equals(aeroportoSaida));

            voo.setAeroportoSaida(aeroportoSaida);
            voo.setAeroportoChegada(aeroportoChegada);
            voo.setDistanciaKmAeroporto(infoVoo.getDistanciaKmAeroporto());
            voo.setPrecoBilhete(infoVoo.getPrecoBilhete());
            voo.setDataPartida(infoVoo.getDataPartida());
            voo.setHoraPartida(infoVoo.getHoraPartida());
            cont++;

            if (empresa.adicionarVoo(voo)) {
                cont++;
            }
        } while (cont != NUM_VOOS);
    }

    public void gerarHoteisAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
//            GerarAutomaticoHotel infoHotel = GerarAutomaticoHotel.values()[gerador.nextInt(GerarAutomaticoHotel.values().length)];
            GerarAutomaticoHotel infoHotel = GerarAutomaticoHotel.values()[cont];
            Hotel hotel = new Hotel(infoHotel.getNomeHotel(),
                    infoHotel.getCategoria(), infoHotel.getLocalidade(), infoHotel.getTransfer(),
                    infoHotel.getPrecoPorQuarto());

            if (empresa.adicionarHotel(hotel)) {
                cont++;
            }
        } while (cont != NUM_HOTEIS);
    }

    public void gerarClientesAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();
        List<Cliente> clientes = new ArrayList<>();

        do {
//            GerarAutomaticoCliente infoCliente = GerarAutomaticoCliente.values()[gerador.nextInt(GerarAutomaticoCliente.values().length)];
            GerarAutomaticoCliente infoCliente = GerarAutomaticoCliente.values()[cont];
            Cliente cliente = new Cliente(infoCliente.getNomeCliente(),
                    infoCliente.getDataNascimento(),
                    infoCliente.getGenero(),
                    infoCliente.getNif(),
                    infoCliente.getNumPassaporte(),
                    infoCliente.getEmail(),
                    infoCliente.getPercentagemDesconto());

            if (empresa.adicionarCliente(cliente)) {
                cont++;
            }
        } while (cont != NUM_CLIENTES);
    }

    public void gerarReservas(Empresa empresa) {
        Data dataAtual = Data.dataAtual();
        gerarAeroportosAuto(empresa);
        gerarVoosAuto(empresa);
        gerarHoteisAuto(empresa);
        gerarClientesAuto(empresa);
        List<Cliente> clientes = empresa.getListaClientes();
        List<Hotel> hoteis = empresa.getListaHoteis();
        List<Voo> voos = empresa.getListaVoos();

        ReservaHotel resHotel1 = new ReservaHotel(dataAtual, 1, clientes.getFirst(), hoteis.getFirst(), DatasReserva.DATA1.getData(), 25);
        ReservaHotel resHotel2 = new ReservaHotel(dataAtual, 1, clientes.get(1), hoteis.get(1), DatasReserva.DATA2.getData(), 5);

        ReservaHotelVoo resHotelVoo1 = new ReservaHotelVoo(dataAtual, 4, clientes.get(2), hoteis.get(2), DatasReserva.DATA2.getData(), 6, voos.getFirst());
        ReservaHotelVoo resHotelVoo2 = new ReservaHotelVoo(dataAtual, 7, clientes.get(2), hoteis.get(3), DatasReserva.DATA3.getData(), 7, voos.get(1));

        ReservaHotelVooIdaVolta resHotVooIV1 = new ReservaHotelVooIdaVolta(dataAtual, 4, clientes.get(2), hoteis.get(2), DatasReserva.DATA3.getData(), 6, voos.getFirst(), voos.get(1));
        ReservaHotelVooIdaVolta resHotVooIV2 = new ReservaHotelVooIdaVolta(dataAtual, 1, clientes.getFirst(), hoteis.get(3), DatasReserva.DATA4.getData(), 6, voos.getFirst(), voos.get(1));

        ReservaVoo resVoo1 = new ReservaVoo(DatasReserva.DATA5.getData(), 8, clientes.getFirst(), voos.getFirst());
        ReservaVoo resVoo2 = new ReservaVoo(DatasReserva.DATA6.getData(), 2, clientes.get(1), voos.get(1));

        ReservaVooIdaVolta resVooIV1 = new ReservaVooIdaVolta(dataAtual, 3, clientes.get(1), voos.getFirst(), voos.get(1));
        ReservaVooIdaVolta resVooIV2 = new ReservaVooIdaVolta(dataAtual, 4, clientes.get(2), voos.get(1), voos.get(2));

        empresa.adicionarReserva(resHotel1);
        empresa.adicionarReserva(resHotel2);
        empresa.adicionarReserva(resHotelVoo1);
        empresa.adicionarReserva(resHotelVoo2);
        empresa.adicionarReserva(resHotVooIV1);
        empresa.adicionarReserva(resHotVooIV2);
        empresa.adicionarReserva(resVoo1);
        empresa.adicionarReserva(resVoo2);
        empresa.adicionarReserva(resVooIV1);
        empresa.adicionarReserva(resVooIV1);
    }
}
