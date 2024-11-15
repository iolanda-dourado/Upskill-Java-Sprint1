package entidades;

import criterios.Criterio1;
import criterios.Criterio3_ReservaCustoCrescente;

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

    public Empresa(String nomeEmpresa, String morada, List<Cliente> listaClientes, List<Hotel> listaHoteis, List<Aeroporto> listaAeroportos, List<Voo> listaVoos, List<Reserva> reservas) {
        this.nomeEmpresa = nomeEmpresa;
        this.morada = morada;
        this.listaClientes = new ArrayList<>(listaClientes);
        this.listaHoteis = new ArrayList<>(listaHoteis);
        this.listaAeroportos = new ArrayList<>(listaAeroportos);
        this.listaVoos = new ArrayList<>(listaVoos);
        this.listaReservas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotel")) {
                this.listaReservas.add(new ReservaHotel((ReservaHotel) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVoo")) {
                this.listaReservas.add(new ReservaHotelVoo((ReservaHotelVoo) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVooIdaVolta")) {
                this.listaReservas.add(new ReservaHotelVooIdaVolta((ReservaHotelVooIdaVolta) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVoo")) {
                this.listaReservas.add(new ReservaVoo((ReservaVoo) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVooIdaVolta")) {
                this.listaReservas.add(new ReservaVooIdaVolta((ReservaVooIdaVolta) reserva));
            }
        }
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

    @Override
    public String toString() {
        return String.format("-- Empresa --\nNomeEmpresa: %s\nMorada: %s\nListaClientes: %s\nListaHoteis: %s\nListaAeroportos:%s\nListaVoos: %s\nListaReservas: %s", nomeEmpresa, morada, listarClientes(), listarHoteis(), listarAeroportos(), listarVoos(), listarReservas());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(nomeEmpresa, empresa.nomeEmpresa) && Objects.equals(morada, empresa.morada) && Objects.equals(listaClientes, empresa.listaClientes) && Objects.equals(listaHoteis, empresa.listaHoteis) && Objects.equals(listaAeroportos, empresa.listaAeroportos) && Objects.equals(listaVoos, empresa.listaVoos) && Objects.equals(listaReservas, empresa.listaReservas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeEmpresa, morada, listaClientes, listaHoteis, listaAeroportos, listaVoos, listaReservas);
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
        listaReservas.add(new ReservaVoo(novaReserva));
        return true;
    }

    public boolean adicionarReserva(ReservaVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaVooIdaVolta(novaReserva));
        return true;
    }

    public boolean adicionarReserva(ReservaHotel novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaHotel(novaReserva));
        return true;
    }

    public boolean adicionarReserva(ReservaHotelVoo novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaHotelVoo(novaReserva));
        return true;
    }

    public boolean adicionarReserva(ReservaHotelVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaHotelVooIdaVolta(novaReserva));
        return true;
    }

    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE CLIENTES **\n");
        for (Cliente cliente : listaClientes) {
            sb.append(cliente);
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Clientes = %d", listaClientes.size()));
        return sb.toString();
    }

    public String listarAeroportos() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE AEROPORTOS **\n");
        for (Aeroporto aero : listaAeroportos) {
            sb.append(aero);
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Aeroportos = %d", listaAeroportos.size()));
        return sb.toString();
    }

    public String listarHoteis() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE HOTÉIS **\n");
        for (Hotel htl : listaHoteis) {
            sb.append(htl);
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Hotéis = %d", listaHoteis.size()));
        return sb.toString();
    }

    public String listarVoos() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE VOOS **\n");
        for (Voo v : listaVoos) {
            sb.append(v);
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Voos = %d", listaVoos.size()));
        return sb.toString();
    }

    public String listarReservas() {
        StringBuilder sb = new StringBuilder();
        sb.append("** LISTA DE RESERVAS **\n");
        for (Reserva res : listaReservas) {
            sb.append(res);
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Reservas = %d", listaReservas.size()));
        return sb.toString();
    }

    public String listarReservaCliente(String codigoCliente) {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        for (Cliente cliente : listaClientes) {
            if (codigoCliente.equals(cliente.getCodigoCliente())) {
                sb.append(String.format("** LISTA DE RESERVAS DO CLIENTE - %s **\n", cliente.getNomeCliente()));
                for (Reserva res : listaReservas) {
                    if (res.getCliente() == cliente) {
                        sb.append(res);
                        sb.append("\n------------------------");
                        sb.append("\n");
                        countTemp++;
                    }
                }
            }
            sb.append(String.format("\nTotal de Reservas do Cliente %s = %d", cliente.getNomeCliente(), countTemp));
            break;
        }
        return sb.toString();
    }

    public String listarHoteisComTransfer() {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        sb.append("** LISTA DE HOTÉIS QUE FORNECEM O SERVIÇO DE TRANSFER **\n");
        for (Hotel htl : listaHoteis) {
            if (htl.isTransfer()) {
                sb.append(htl);
                countTemp++;
            }
            sb.append("\n------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Hoteis com Transfer = %d", countTemp));
        return sb.toString();
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
            if (cliente.getCodigoCliente().equalsIgnoreCase(codigoCliente)) {
                return cliente;
            }
        }

        return null;
    }

    public Hotel pesquisarHotel(String codigoHotel) {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getCodigoHotel().equalsIgnoreCase(codigoHotel)) {
                return hotel;
            }
        }

        return null;
    }

    public Aeroporto pesquisarAeroporto(String codigoAeroporto) {
        for (Aeroporto aeroporto : listaAeroportos) {
            if (aeroporto.getCodigoAeroporto().equalsIgnoreCase(codigoAeroporto)) {
                return aeroporto;
            }
        }

        return null;
    }

    public Voo pesquisarVoo(String codigoVoo) {
        for (Voo voo : listaVoos) {
            if (voo.getCodigoVoo().equalsIgnoreCase(codigoVoo)) {
                return voo;
            }
        }

        return null;
    }

    public Reserva pesquisarReserva(String codigoReserva) {
        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotel")) {
                if (reserva.getCodigoReserva().equals(codigoReserva)) {
                    return new ReservaHotel((ReservaHotel) reserva);
                }
            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVoo")) {
                if (reserva.getCodigoReserva().equals(codigoReserva)) {
                    return new ReservaHotelVoo((ReservaHotelVoo) reserva);
                }
            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVooIdaVolta")) {
                if (reserva.getCodigoReserva().equals(codigoReserva)) {
                    return new ReservaHotelVooIdaVolta((ReservaHotelVooIdaVolta) reserva);
                }
            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVoo")) {
                if (reserva.getCodigoReserva().equals(codigoReserva)) {
                    return new ReservaVoo((ReservaVoo) reserva);
                }
            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVooIdaVolta")) {
                if (reserva.getCodigoReserva().equals(codigoReserva)) {
                    return new ReservaVooIdaVolta((ReservaVooIdaVolta) reserva);
                }
            }
        }

        return null;
    }

    public boolean atualizarReservasConcretizadas(Reserva reserva) {
        if (listaReservas.contains(reserva)) {
            reserva.setConcretizada(true);
            reserva.getCliente().setNumReservasConcretizadas(reserva.getCliente().getNumReservasConcretizadas() + 1);
            return true;
        }
        return false;
    }

    public double retornarCustoTodasReservas() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            custoAcumulado += reserva.calcularCustoReserva();
        }

        return custoAcumulado;
    }

    public double retornarCustoTodasReservasHoteis() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotel")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    public double retornarCustoTodasReservasVooIV() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVooIdaVolta")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    public List<Reserva> retornarReservasCustoDecrescente() {
        List<Reserva> listaReservasTemp = criarCopiaListaReservas();

        Criterio3_ReservaCustoCrescente criterio = new Criterio3_ReservaCustoCrescente();
        Collections.sort(listaReservasTemp, criterio);
        Collections.reverse(listaReservasTemp);

        return listaReservasTemp;
    }

    public List<Reserva> retornarReservasEfetuadasDataCrescente() {
        List<Reserva> copia = criarCopiaListaReservas();
        Collections.sort(copia);
        return copia;
    }

    public List<Cliente> retornarClientesOrdenados() {
        List<Cliente> listaClienteTemp = new ArrayList<>(listaClientes);
        Criterio1 crit = new Criterio1();
        Collections.sort(listaClienteTemp, crit);
        return listaClienteTemp;
    }

    public List<Reserva> criarCopiaListaReservas() {
        List<Reserva> listaReservasTemp = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotel")) {
                listaReservasTemp.add(new ReservaHotel((ReservaHotel) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVoo")) {
                listaReservasTemp.add(new ReservaHotelVoo((ReservaHotelVoo) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVooIdaVolta")) {
                listaReservasTemp.add(new ReservaHotelVooIdaVolta((ReservaHotelVooIdaVolta) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVoo")) {
                listaReservasTemp.add(new ReservaVoo((ReservaVoo) reserva));

            } else if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVooIdaVolta")) {
                listaReservasTemp.add(new ReservaVooIdaVolta((ReservaVooIdaVolta) reserva));
            }
        }
        return listaReservasTemp;
    }
}