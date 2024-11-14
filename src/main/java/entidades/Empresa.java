package entidades;

import criterios.Criterio1;
import criterios.Criterio3_ReservaCustoCrescente;
import enums.GerarAutomaticoAeroporto;
import enums.GerarAutomaticoVoo;

import java.io.Serializable;
import java.util.*;

public class Empresa implements Serializable {
    private String nomeEmpresa;
    private String morada;
    private List<Cliente> listaClientes;
    private List<Hotel> listaHoteis;
    private List<Aeroporto> listaAeroportos;
    private List<Voo> listaVoos;
    private List<Reserva> listaReservas;

    private static final String NOME_EMPRESA_POR_OMISSAO = "Não informado";
    private static final String MORADA_POR_OMISSAO = "Desconhecida";

    public Empresa(String nomeEmpresa, String morada) {
        this.nomeEmpresa = nomeEmpresa;
        this.morada = morada;
        this.listaClientes = new ArrayList<>();
        this.listaHoteis = new ArrayList<>();
        this.listaAeroportos = new ArrayList<>();
        this.listaVoos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
    }

    public Empresa() {
        this.nomeEmpresa = NOME_EMPRESA_POR_OMISSAO;
        this.morada = MORADA_POR_OMISSAO;
        this.listaClientes = new ArrayList<>();
        this.listaHoteis = new ArrayList<>();
        this.listaAeroportos = new ArrayList<>();
        this.listaVoos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getMorada() {
        return morada;
    }

    public List<Cliente> getListaClientes() {
        return new ArrayList<>(listaClientes);
    }

    public List<Hotel> getListaHoteis() {
        return new ArrayList<>(listaHoteis);
    }

    public List<Aeroporto> getListaAeroportos() {
        return new ArrayList<>(listaAeroportos);
    }

    public List<Voo> getListaVoos() {
        return new ArrayList<>(listaVoos);
    }

    public List<Reserva> getListaReservas() {
        return new ArrayList<>(listaReservas);
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public boolean adicionarCliente(Cliente novoCliente) {
        if (listaClientes.contains(novoCliente)) return false;

        listaClientes.add(novoCliente);
        return true;
    }

    public boolean adicionarHotel(Hotel novoHotel) {
        if (listaHoteis.contains(novoHotel)) return false;

        listaHoteis.add(novoHotel);
        return true;
    }

    public boolean adicionarAeroporto(Aeroporto novoAeroporto) {
        if (listaAeroportos.contains(novoAeroporto)) return false;

        listaAeroportos.add(novoAeroporto);
        return true;
    }

    public boolean adicionarVoo(Voo novoVoo) {
        if (listaVoos.contains(novoVoo)) return false;

        listaVoos.add(novoVoo);
        return true;
    }

    public boolean adicionarReserva(ReservaVoo novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;

        listaReservas.add(novaReserva);
        return true;
    }
    public boolean adicionarReserva(ReservaVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;

        listaReservas.add(novaReserva);
        return true;
    }
    public boolean adicionarReserva(ReservaHotel novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;

        listaReservas.add(novaReserva);
        return true;
    }
    public boolean adicionarReserva(ReservaHotelVoo novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;

        listaReservas.add(novaReserva);
        return true;
    }
    public boolean adicionarReserva(ReservaHotelVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;

        listaReservas.add(novaReserva);
        return true;
    }

    public void listarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE CLIENTES **\n");
        for(Cliente cliente : listaClientes) {
            sb.append(cliente);
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Clientes = %d", listaClientes.size()));
        System.out.println(sb.toString());
    }

    public void listarAeroportos() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE AEROPORTOS **\n");
        for(Aeroporto aero : listaAeroportos) {
            sb.append(aero);
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Aeroportos = %d", listaAeroportos.size()));
        System.out.println(sb.toString());
    }

    public void listarHoteis() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE HOTÉIS **\n");
        for(Hotel htl : listaHoteis) {
            sb.append(htl);
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Hotéis = %d", listaHoteis.size()));
        System.out.println(sb.toString());
    }

    public void listarVoos() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE VOOS **\n");
        for(Voo v : listaVoos) {
            sb.append(v);
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Voos = %d", listaVoos.size()));
        System.out.println(sb.toString());
    }

    public void listarReservas() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE RESERVAS **\n");
        for(Reserva res : listaReservas) {
            sb.append(res);
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Reservas = %d", listaReservas.size()));
        System.out.println(sb.toString());
    }

    public void listarReservaCliente(String codigoCliente) {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        for(Cliente cliente : listaClientes) {
            if (codigoCliente.equals(cliente.getCodigoCliente())) {
                sb.append(String.format("** LISTA DE RESERVAS DO CLIENTE - %s **\n", cliente.getNomeCliente()));
                for(Reserva res : listaReservas) {
                    if(res.getCliente() == cliente) {
                    sb.append(res);
                    sb.append("------------------------");
                    sb.append("\n");
                    countTemp++;
                    }
                }
            }
            sb.append(String.format("\nTotal de Reservas do Cliente %s = %d", cliente.getNomeCliente(), countTemp));
            break;
        }
        System.out.println(sb.toString());
    }

    public void listarHoteisComTransfer() {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        sb.append("** LISTA DE HOTÉIS QUE FORNECEM O SERVIÇO DE TRANSFER **\n");
        for(Hotel htl : listaHoteis) {
            if (htl.isTransfer()) {
                sb.append(htl);
                countTemp++;
            }
            sb.append("------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Hoteis com Transfer = %d", countTemp));
        System.out.println(sb.toString());
    }


    public boolean eliminarReserva(String codigoReserva) {
        Reserva reservaEncontrada = null;

        for (Reserva reserva : listaReservas) {
            if (Objects.equals(reserva.getCodigoReserva(), codigoReserva)) {
                reservaEncontrada = reserva;
                break;
            }
        }

        return listaReservas.remove(reservaEncontrada);
    }

    public Cliente pesquisarCliente(String codigoCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCodigoCliente().equals(codigoCliente)) {
                return cliente;
            }
        }

        return null;
    }

    public Hotel pesquisarHotel(String codigoHotel) {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getCodigoHotel().equals(codigoHotel)) {
                return hotel;
            }
        }

        return null;
    }

    public Aeroporto pesquisarAeroporto(String codigoAeroporto) {
        for (Aeroporto aeroporto : listaAeroportos) {
            if (aeroporto.getCodigoAeroporto().equals(codigoAeroporto)) {
                return aeroporto;
            }
        }

        return null;
    }

    public Voo pesquisarVoo(String codigoVoo) {
        for (Voo voo : listaVoos) {
            if (voo.getCodigoVoo().equals(codigoVoo)) {
                return voo;
            }
        }

        return null;
    }

    public Reserva pesquisarReserva(String codigoReserva) {
        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoReserva().equals(codigoReserva)) {
                return reserva;
            }
        }

        return null;
    }

    public void atualizarReservasConcretizadas(Reserva reserva) {
        reserva.setConcretizada(true);
        reserva.getCliente().setNumReservasConcretizadas(reserva.getCliente().getNumReservasConcretizadas() + 1);
    }

    public double retornarCustoTodasReservas() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            custoAcumulado += reserva.calcularCustoReserva();
        }

        return custoAcumulado;
    }

    public List<Reserva> retornarReservasCustoDecrescente() {
        List<Reserva> listaReservasTemp = new ArrayList<>(listaReservas);
        Criterio3_ReservaCustoCrescente criterio = new Criterio3_ReservaCustoCrescente();
        Collections.sort(listaReservasTemp, criterio);
        Collections.reverse(listaReservasTemp);

        return listaReservasTemp;
    }

    public List<Reserva> retornarReservasEfetuadasDataCrescente() {
        List<Reserva> copia = new ArrayList<>(listaReservas);
        Collections.sort(copia);
        return copia;
    }

    public List<Cliente> retornarClientesOrdenados() {
        List<Cliente> listaClienteTemp = new ArrayList<>(listaClientes);
        Criterio1 crit = new Criterio1();
        Collections.sort(listaClienteTemp, crit);
        return listaClienteTemp;
    }

    public void gerarAeroportoAuto() {
        int cont = 0;
        do {
            Random gerador = new Random();
            GerarAutomaticoAeroporto infoAero = GerarAutomaticoAeroporto.values()[gerador.nextInt(GerarAutomaticoAeroporto.values().length)];

            Aeroporto aeroporto = new Aeroporto(infoAero.getEndereco(), infoAero.getPaginaWeb(), infoAero.getCodigoAeroporto());
            if (adicionarAeroporto(aeroporto)) {
                cont++;
            }
        } while (cont != 4);
    }

//         this.codigoVoo = codigoVoo;
//        companhiaAerea = companhia;
//        this.qntLugares = qntLugares;
//        this.qntLugaresDisponiveis = qntLugares;
//        this.aeroportoSaida = aeroportoSaida;
//        this.aeroportoChegada = aeroportoChegada;
//        this.distanciaKmAeroporto = distanciaKmAeroporto;
//        this.precoBilhete = precoBilhete;
//        this.dataPartida = dataPartida;
//        this.horaPartida = horaPartida;

    public void gerarVooAuto() {
        int cont = 0;
        do {
            Random gerador = new Random();
            GerarAutomaticoVoo infoVoo = GerarAutomaticoVoo.values()[gerador.nextInt(GerarAutomaticoAeroporto.values().length)];
            Voo voo = new Voo();
            voo.setCodigoVoo(infoVoo.getCodigoVoo());
            voo.setCompanhiaAerea(infoVoo.getCompanhiaAerea());
            voo.setQntLugares(infoVoo.getQntLugares());
            voo.setQntLugaresDisponiveis(infoVoo.getQntLugaresDisponiveis());
            voo.setAeroportoSaida(listaAeroportos.get(cont));
            voo.setAeroportoChegada(listaAeroportos.get(cont+1));

            cont++;
        } while (cont != 6);
    }
}