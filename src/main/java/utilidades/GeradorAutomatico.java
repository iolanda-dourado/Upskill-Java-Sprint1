package utilidades;
import entidades.Empresa;
import entidades.Aeroporto;
import entidades.Voo;
import entidades.Hotel;
import entidades.Cliente;
import enums.*;
import java.util.List;
import java.util.Random;

public class GeradorAutomatico {
    private static final int NUM_AEROPORTOS = 4;
    private static final int NUM_VOOS = 6;
    private static final int NUM_HOTEIS = 4;
    private static final int NUM_CLIENTES = 3;
    private static final int NUM_RESERVAS = 2;

    public static void gerarAeroportoAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            GerarAutomaticoAeroporto infoAero = GerarAutomaticoAeroporto.values()[gerador.nextInt(GerarAutomaticoAeroporto.values().length)];
            Aeroporto aeroporto = new Aeroporto(infoAero.getEndereco(), infoAero.getPaginaWeb(), infoAero.getCodigoAeroporto());
            if (empresa.adicionarAeroporto(aeroporto)) {
                cont++;
            }
        } while (cont != NUM_AEROPORTOS);
    }

    public static void gerarVooAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            Voo voo = new Voo();
            GerarAutomaticoVoo infoVoo = GerarAutomaticoVoo.values()[gerador.nextInt(GerarAutomaticoVoo.values().length)];
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

    public static void gerarHoteisAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            GerarAutomaticoHotel infoHotel = GerarAutomaticoHotel.values()[gerador.nextInt(GerarAutomaticoHotel.values().length)];
            Hotel hotel = new Hotel(infoHotel.getNomeHotel(),
                    infoHotel.getCategoria(), infoHotel.getLocalidade(), infoHotel.getTransfer(),
                    infoHotel.getPrecoPorQuarto());

            if (empresa.adicionarHotel(hotel)) {
                cont++;
            }
        } while (cont != NUM_HOTEIS);
    }

    public static Cliente gerarUmClienteAuto(Empresa empresa) {
        Random gerador = new Random();
        GerarAutomaticoCliente infoCliente = GerarAutomaticoCliente.values()[gerador.nextInt(GerarAutomaticoCliente.values().length)];

        return new Cliente(infoCliente.getNomeCliente(), infoCliente.getDataNascimento(),
                infoCliente.getGenero(),infoCliente.getNif(), infoCliente.getNumPassaporte(),
                infoCliente.getEmail(), infoCliente.getPercentagemDesconto());
    }

    public static void gerarClientesAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            Cliente cliente = gerarUmClienteAuto(empresa);
            if (empresa.adicionarCliente(cliente)) {
                cont++;
            }
        } while (cont != NUM_CLIENTES);
    }
}
