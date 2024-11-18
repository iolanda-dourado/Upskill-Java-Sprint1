package utilidades;

import entidades.*;
import enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe responsável por gerar dados automaticamente para a empresa, incluindo aeroportos, voos,
 * hotéis, clientes e reservas.
 *
 * Os métodos desta classe preenchem as listas da empresa com valores pré-definidos
 * obtidos a partir de enumerados e criam instâncias automáticas para diferentes tipos de
 * objetos, garantindo diversidade e consistência nos dados.
 *
 * @author: Iolanda Dourado e Marianna Ramos
 */
public class GeradorAutomatico implements Serializable {
    /**
     * Número total de aeroportos a serem gerados automaticamente.
     */
    private static final int NUM_AEROPORTOS = 4;

    /**
     * Número total de voos a serem gerados automaticamente.
     */
    private static final int NUM_VOOS = 6;

    /**
     * Número total de hotéis a serem gerados automaticamente.
     */
    private static final int NUM_HOTEIS = 4;

    /**
     * Número total de clientes a serem gerados automaticamente.
     */
    private static final int NUM_CLIENTES = 3;

    /**
     * Construtor padrão da classe GeradorAutomatico.
     */
    public GeradorAutomatico() {
    }

    /**
     * Gera automaticamente uma lista de aeroportos e os adiciona na empresa.
     *
     * @param empresa a instância da empresa onde os aeroportos serão adicionados.
     */
    public void gerarAeroportosAuto(Empresa empresa) {
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

    /**
     * Gera automaticamente uma lista de voos e os adiciona na empresa.
     *
     * @param empresa a instância da empresa onde os voos serão adicionados.
     */
    public void gerarVoosAuto(Empresa empresa) {
        int cont = 0;
        Random gerador = new Random();

        do {
            Voo voo = new Voo();
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

            if (empresa.adicionarVoo(voo)) {
                cont++;
            }
        } while (cont != NUM_VOOS);
    }

    /**
     * Gera automaticamente uma lista de hotéis e os adiciona na empresa.
     *
     * @param empresa a instância da empresa onde os hotéis serão adicionados.
     */
    public void gerarHoteisAuto(Empresa empresa) {
        int cont = 0;
        do {
           GerarAutomaticoHotel infoHotel = GerarAutomaticoHotel.values()[cont];
            Hotel hotel = new Hotel(infoHotel.getNomeHotel(),
                    infoHotel.getCategoria(), infoHotel.getLocalidade(), infoHotel.getTransfer(),
                    infoHotel.getPrecoPorQuarto());

            if (empresa.adicionarHotel(hotel)) {
                cont++;
            }
        } while (cont != NUM_HOTEIS);
    }

    /**
     * Gera automaticamente uma lista de clientes e os adiciona na empresa.
     *
     * @param empresa a instância da empresa onde os clientes serão adicionados.
     */
    public void gerarClientesAuto(Empresa empresa) {
        int cont = 0;
        do {

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

    /**
     * Gera automaticamente reservas de hotéis, voos e combinações entre eles.
     *
     * Os dados gerados incluem diferentes tipos de reservas, como:
     * - Reservas apenas de hotéis;
     * - Reservas de hotéis com voos;
     * - Reservas de voos de ida e volta;
     *
     * @param empresa a instância da empresa onde as reservas serão adicionadas.
     */
    public void gerarReservas(Empresa empresa) {
        Data dataAtual = Data.dataAtual();
        gerarAeroportosAuto(empresa);
        gerarVoosAuto(empresa);
        gerarHoteisAuto(empresa);
        gerarClientesAuto(empresa);
        List<Cliente> clientes = empresa.getListaClientes();
        List<Hotel> hoteis = empresa.getListaHoteis();
        List<Voo> voos = empresa.getListaVoos();

        ReservaHotel resHotel1 = new ReservaHotel(DatasReserva.DATA4.getData(), 1, clientes.getFirst(),
                hoteis.getFirst(), DatasReserva.DATA1.getData(), 25);
        ReservaHotel resHotel2 = new ReservaHotel(DatasReserva.DATA3.getData(), 3, clientes.get(1),
                hoteis.get(1), DatasReserva.DATA2.getData(), 5);

        ReservaHotelVoo resHotelVoo1 = new ReservaHotelVoo(DatasReserva.DATA1.getData(), 4, clientes.get(2),
                hoteis.get(2), DatasReserva.DATA2.getData(), 6, voos.getFirst());
        ReservaHotelVoo resHotelVoo2 = new ReservaHotelVoo(dataAtual, 7, clientes.get(2), hoteis.get(3),
                DatasReserva.DATA3.getData(), 7, voos.get(1));

        ReservaHotelVooIdaVolta resHotVooIV1 = new ReservaHotelVooIdaVolta(dataAtual, 4, clientes.get(2),
                hoteis.get(2), DatasReserva.DATA3.getData(), 6, voos.getFirst(), voos.get(3));
        ReservaHotelVooIdaVolta resHotVooIV2 = new ReservaHotelVooIdaVolta(DatasReserva.DATA6.getData(), 1,
                clientes.getFirst(), hoteis.get(3), DatasReserva.DATA4.getData(), 6, voos.get(1), voos.get(4));

        ReservaVoo resVoo1 = new ReservaVoo(DatasReserva.DATA5.getData(), 8, clientes.getFirst(), voos.get(2));
        ReservaVoo resVoo2 = new ReservaVoo(DatasReserva.DATA6.getData(), 2, clientes.get(1), voos.get(3));

        ReservaVooIdaVolta resVooIV1 = new ReservaVooIdaVolta(dataAtual, 3, clientes.get(1), voos.getFirst(),
                voos.get(4));
        ReservaVooIdaVolta resVooIV2 = new ReservaVooIdaVolta(DatasReserva.DATA2.getData(), 4, clientes.get(2),
                voos.get(1), voos.get(4));

        empresa.adicionarReserva(resHotel1);
        empresa.adicionarReserva(resHotel2);
        empresa.adicionarReserva(resHotelVoo1);
        empresa.adicionarReserva(resHotelVoo2);
        empresa.adicionarReserva(resHotVooIV1);
        empresa.adicionarReserva(resHotVooIV2);
        empresa.adicionarReserva(resVoo1);
        empresa.adicionarReserva(resVoo2);
        empresa.adicionarReserva(resVooIV1);
        empresa.adicionarReserva(resVooIV2);
    }
}
