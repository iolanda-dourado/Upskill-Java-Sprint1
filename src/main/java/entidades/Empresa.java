package entidades;

import criterios.Criterio1;
import criterios.Criterio2_ReservaCustoDecrescente;

import java.io.Serializable;
import java.util.*;

/**
 * Representa uma empresa com funcionalidades para gerenciar clientes, hotéis, aeroportos, voos e reservas.
 * A classe implementa Serializable, permitindo sua serialização.
 *
 * @author Iolanda Dourado & Marianna Ramos
 */
public class Empresa implements Serializable {

    /**
     * Nome da empresa.
     */
    private String nomeEmpresa;

    /**
     * Morada da empresa.
     */
    private String morada;

    /**
     * Lista de clientes associados à empresa.
     */
    private List<Cliente> listaClientes;

    /**
     * Lista de hotéis associados à empresa.
     */
    private List<Hotel> listaHoteis;

    /**
     * Lista de aeroportos associados à empresa.
     */
    private List<Aeroporto> listaAeroportos;

    /**
     * Lista de voos associados à empresa.
     */
    private List<Voo> listaVoos;

    /**
     * Lista de reservas associadas à empresa.
     */
    private List<Reserva> listaReservas;

    /**
     * Valor padrão para o nome da empresa quando não fornecido (por omissão).
     */
    private static final String NOME_EMPRESA_POR_OMISSAO = "Não informado";

    /**
     * Valor padrão para a morada da empresa quando não fornecida (por omissão).
     */
    private static final String MORADA_POR_OMISSAO = "Desconhecida";

    /**
     * Construtor que inicializa uma empresa com o nome, morada e listas de clientes, hotéis, aeroportos, voos e reservas.
     *
     * @param nomeEmpresa O nome da empresa.
     * @param morada A morada da empresa.
     * @param listaClientes A lista de clientes associados à empresa.
     * @param listaHoteis A lista de hotéis associados à empresa.
     * @param listaAeroportos A lista de aeroportos associados à empresa.
     * @param listaVoos A lista de voos associados à empresa.
     * @param reservas A lista de reservas associadas à empresa.
     */
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

    /**
     * Construtor padrão que inicializa a empresa com valores omissão.
     */
    public Empresa() {
        this.nomeEmpresa = NOME_EMPRESA_POR_OMISSAO;
        this.morada = MORADA_POR_OMISSAO;
        this.listaClientes = new ArrayList<>();
        this.listaHoteis = new ArrayList<>();
        this.listaAeroportos = new ArrayList<>();
        this.listaVoos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
    }

    /**
     * Obtém o nome da empresa.
     *
     * @return Nome da empresa.
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * Obtém a morada da empresa.
     *
     * @return Morada da empresa.
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Obtém uma cópia da lista de clientes da empresa.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> getListaClientes() {
        return new ArrayList<>(listaClientes);
    }

    /**
     * Obtém uma cópia da lista de hotéis da empresa.
     *
     * @return Lista de hotéis.
     */
    public List<Hotel> getListaHoteis() {
        return new ArrayList<>(listaHoteis);
    }

    /**
     * Obtém uma cópia da lista de aeroportos da empresa.
     *
     * @return Lista de aeroportos.
     */
    public List<Aeroporto> getListaAeroportos() {
        return new ArrayList<>(listaAeroportos);
    }

    /**
     * Obtém uma cópia da lista de voos da empresa.
     *
     * @return Lista de voos.
     */
    public List<Voo> getListaVoos() {
        return new ArrayList<>(listaVoos);
    }

    /**
     * Obtém uma cópia da lista de reservas da empresa.
     *
     * @return Lista de reservas.
     */
    public List<Reserva> getListaReservas() {
        return new ArrayList<>(listaReservas);
    }

    /**
     * Define o nome da empresa.
     *
     * @param nomeEmpresa Nome a ser definido.
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * Define a morada da empresa.
     *
     * @param morada Morada a ser definida.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Retorna a representação textual da {@code Empresa}, incluindo informações
     * sobre o {@code Nome}, {@code Morada}, e as listas de {@code Clientes}, {@code Hotéis}, {@code Aeroportos}, {@code Voos} e {@code Reservas}.
     *
     * @return Uma string formatada contendo os detalhes da empresa.
     */
    @Override
    public String toString() {
        return String.format(
                """
                --- Empresa ---
                Nome da Empresa: %s
                Morada: %s
                Lista de Clientes: %s
                Lista de Hotéis: %s
                Lista de Aeroportos: %s
                Lista de Voos: %s
                Lista de Reservas: %s
                """,
                nomeEmpresa, morada, listarClientes(), listarHoteis(), listarAeroportos(), listarVoos(), listarReservas()
        );
    }


    /**
     * Compara este objeto com outro para verificar a igualdade.
     * A comparação é baseada nos atributos {@code nomeEmpresa}, {@code morada},
     * {@code listaClientes}, {@code listaHoteis}, {@code listaAeroportos},
     * {@code listaVoos} e {@code listaReservas}.
     *
     * @param umaOutraEmpresa O objeto a ser comparado.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object umaOutraEmpresa) {
        if (umaOutraEmpresa == null || getClass() != umaOutraEmpresa.getClass()) return false;
        Empresa empresa = (Empresa) umaOutraEmpresa;
        return Objects.equals(nomeEmpresa, empresa.nomeEmpresa) && Objects.equals(morada, empresa.morada) && Objects.equals(listaClientes, empresa.listaClientes) && Objects.equals(listaHoteis, empresa.listaHoteis) && Objects.equals(listaAeroportos, empresa.listaAeroportos) && Objects.equals(listaVoos, empresa.listaVoos) && Objects.equals(listaReservas, empresa.listaReservas);
    }

    /**
     * Adiciona um novo cliente à lista de clientes.
     *
     * @param novoCliente Cliente a ser adicionado.
     * @return {@code true} se o cliente foi adicionado com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarCliente(Cliente novoCliente) {
        if (listaClientes.contains(novoCliente)) return false;
        listaClientes.add(novoCliente);
        return true;
    }

    /**
     * Adiciona um novo hotel à lista de hotéis.
     *
     * @param novoHotel Hotel a ser adicionado.
     * @return {@code true} se o hotel foi adicionado com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarHotel(Hotel novoHotel) {
        if (listaHoteis.contains(novoHotel)) return false;
        listaHoteis.add(novoHotel);
        return true;
    }

    /**
     * Adiciona um novo aeroporto à lista de aeroportos.
     *
     * @param novoAeroporto Aeroporto a ser adicionado.
     * @return {@code true} se o aeroporto foi adicionado com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarAeroporto(Aeroporto novoAeroporto) {
        if (listaAeroportos.contains(novoAeroporto)) return false;
        listaAeroportos.add(novoAeroporto);
        return true;
    }

    /**
     * Adiciona um novo voo à lista de voos.
     *
     * @param novoVoo Voo a ser adicionado.
     * @return {@code true} se o voo foi adicionado com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarVoo(Voo novoVoo) {
        if (listaVoos.contains(novoVoo)) return false;
        listaVoos.add(novoVoo);
        return true;
    }

    /**
     * Adiciona uma nova reserva do tipo {@link ReservaVoo} à lista de reservas.
     *
     * @param novaReserva Reserva a ser adicionada.
     * @return {@code true} se a reserva foi adicionada com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarReserva(ReservaVoo novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaVoo(novaReserva));
        return true;
    }

    /**
     * Adiciona uma nova reserva do tipo {@link ReservaVooIdaVolta} à lista de reservas.
     *
     * @param novaReserva Reserva a ser adicionada.
     * @return {@code true} se a reserva foi adicionada com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarReserva(ReservaVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaVooIdaVolta(novaReserva));
        return true;
    }

    /**
     * Adiciona uma nova reserva do tipo {@link ReservaHotel} à lista de reservas.
     *
     * @param novaReserva Reserva a ser adicionada.
     * @return {@code true} se a reserva foi adicionada com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarReserva(ReservaHotel novaReserva) {
        if (listaReservas.contains((novaReserva))) return false;
        listaReservas.add(new ReservaHotel(novaReserva));
        return true;
    }

    /**
     * Adiciona uma nova reserva do tipo {@link ReservaHotelVoo} à lista de reservas.
     *
     * @param novaReserva Reserva a ser adicionada.
     * @return {@code true} se a reserva foi adicionada com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarReserva(ReservaHotelVoo novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaHotelVoo(novaReserva));
        return true;
    }

    /**
     * Adiciona uma nova reserva do tipo {@link ReservaHotelVooIdaVolta} à lista de reservas.
     *
     * @param novaReserva Reserva a ser adicionada.
     * @return {@code true} se a reserva foi adicionada com sucesso, {@code false} caso já exista na lista.
     */
    public boolean adicionarReserva(ReservaHotelVooIdaVolta novaReserva) {
        if (listaReservas.contains(novaReserva)) return false;
        listaReservas.add(new ReservaHotelVooIdaVolta(novaReserva));
        return true;
    }

    /**
     * Lista todos os clientes cadastrados.
     *
     * @return String formatada contendo a lista de clientes e o total de clientes.
     */
    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== LISTA DE CLIENTES ==========\n");
        for (Cliente cliente : listaClientes) {
            sb.append(cliente);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Clientes = %d", listaClientes.size()));
        return sb.toString();
    }

    /**
     * Lista todos os aeroportos cadastrados.
     *
     * @return String formatada contendo a lista de aeroportos e o total de aeroportos.
     */
    public String listarAeroportos() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== LISTA DE AEROPORTOS ==========\n\n");
        for (Aeroporto aero : listaAeroportos) {
            sb.append(aero);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Aeroportos = %d", listaAeroportos.size()));
        return sb.toString();
    }

    /**
     * Lista todos os hotéis cadastrados.
     *
     * @return String formatada contendo a lista de hotéis e o total de hotéis.
     */
    public String listarHoteis() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== LISTA DE HOTÉIS ==========\n\n");
        for (Hotel htl : listaHoteis) {
            sb.append(htl);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Hotéis = %d", listaHoteis.size()));
        return sb.toString();
    }

    /**
     * Lista todos os voos cadastrados.
     *
     * @return String formatada contendo a lista de voos e o total de voos.
     */
    public String listarVoos() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== LISTA DE VOOS ==========\n\n");
        for (Voo v : listaVoos) {
            sb.append(v);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Voos = %d", listaVoos.size()));
        return sb.toString();
    }

    /**
     * Lista todas as reservas registradas na {@link Empresa}.
     *
     * @return String formatada contendo a lista de reservas e o total delas.
     */
    public String listarReservas() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== LISTA DE RESERVAS ==========\n\n");
        for (Reserva res : listaReservas) {
            sb.append(res);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Reservas = %d\n", listaReservas.size()));
        return sb.toString();
    }

    /**
     * Lista as reservas registradas na {@link Empresa} por tipo.
     *
     * @return String formatada contendo a lista de reservas de cada tipo e o total delas.
     */
    public String listarReservasPorTipo() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== LISTA DE RESERVAS ==========\n\n");
        for (Reserva res : listaReservas) {
            sb.append(res);
            sb.append("------------------------------------------");
            sb.append("\n");
            count++;
        }
        sb.append(String.format("Total de Reservas = %d\n", count));
        return sb.toString();
    }

    /**
     * Lista todas as reservas associadas a um cliente específico, identificado pelo código do cliente.
     *
     * @param codigoCliente Código do cliente cujas reservas serão listadas.
     * @return String formatada contendo a lista de reservas do cliente e o total dessas reservas.
     */
    public String listarReservaCliente(String codigoCliente) {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        for (Cliente cliente : listaClientes) {
            if (codigoCliente.equals(cliente.getCodigoCliente())) {
                sb.append(String.format("========== LISTA DE RESERVAS DO CLIENTE - %s ==========\n", cliente.getNomeCliente()));
                for (Reserva res : listaReservas) {
                    if (res.getCliente() == cliente) {
                        sb.append(res);
                        sb.append("------------------------------------------");
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

    /**
     * Lista todos os hotéis que fornecem o serviço de transfer.
     *
     * @return String formatada contendo a lista de hotéis com transfer e o total desses hotéis.
     */
    public String listarHoteisComTransfer() {
        StringBuilder sb = new StringBuilder();
        int countTemp = 0;
        sb.append("--- LISTA DE HOTÉIS QUE FORNECEM O SERVIÇO DE TRANSFER ---\n");
        for (Hotel htl : listaHoteis) {
            if (htl.isTransfer()) {
                sb.append(htl);
                countTemp++;
            }
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("\nTotal de Hoteis com Transfer = %d", countTemp));
        return sb.toString();
    }

    /**
     * Elimina uma reserva específica identificada pelo código da reserva.
     *
     * @param codigoReserva Código da reserva a ser eliminada.
     * @return {@code true} se a reserva foi encontrada e removida; {@code false} caso contrário.
     */
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

    /**
     * Pesquisa um cliente na lista de clientes utilizando o código do cliente.
     *
     * @param codigoCliente Código do cliente a ser pesquisado.
     * @return O cliente correspondente ao código, ou {@code null} se não encontrado.
     */
    public Cliente pesquisarCliente(String codigoCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCodigoCliente().equalsIgnoreCase(codigoCliente)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Pesquisa um cliente na lista de clientes utilizando o NIF (Número de Identificação Fiscal).
     *
     * @param nif NIF do cliente a ser pesquisado.
     * @return O cliente correspondente ao NIF, ou {@code null} se não encontrado.
     */
    public Cliente pesquisarCliente(int nif) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNif() == nif) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Pesquisa um hotel na lista de hotéis utilizando o código do hotel.
     *
     * @param codigoHotel Código do hotel a ser pesquisado.
     * @return O hotel correspondente ao código, ou {@code null} se não encontrado.
     */
    public Hotel pesquisarHotel(String codigoHotel) {
        for (Hotel hotel : listaHoteis) {
            if (hotel.getCodigoHotel().equalsIgnoreCase(codigoHotel)) {
                return hotel;
            }
        }

        return null;
    }

    /**
     * Pesquisa um aeroporto na lista de aeroportos utilizando o código do aeroporto.
     *
     * @param codigoAeroporto Código do aeroporto a ser pesquisado.
     * @return O aeroporto correspondente ao código, ou {@code null} se não encontrado.
     */
    public Aeroporto pesquisarAeroporto(String codigoAeroporto) {
        for (Aeroporto aeroporto : listaAeroportos) {
            if (aeroporto.getCodigoAeroporto().equalsIgnoreCase(codigoAeroporto)) {
                return aeroporto;
            }
        }

        return null;
    }

    /**
     * Pesquisa um voo na lista de voos utilizando o código do voo.
     *
     * @param codigoVoo Código do voo a ser pesquisado.
     * @return O voo correspondente ao código, ou {@code null} se não encontrado.
     */
    public Voo pesquisarVoo(String codigoVoo) {
        for (Voo voo : listaVoos) {
            if (voo.getCodigoVoo().equalsIgnoreCase(codigoVoo)) {
                return voo;
            }
        }

        return null;
    }

    /**
     * Pesquisa uma reserva na lista de reservas utilizando o código da reserva.
     *
     * @param codigoReserva Código da reserva a ser pesquisada.
     * @return Uma nova instância da reserva correspondente ao código, ou {@code null} se não encontrado.
     */
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

    /**
     * Atualiza o status de uma reserva para concretizada, caso ela exista na lista de reservas.
     * Também incrementa o número de reservas concretizadas do cliente associado.
     *
     * @param reserva A reserva a ser atualizada como concretizada.
     * @return {@code true} se a reserva foi encontrada e atualizada; {@code false} caso contrário.
     */
    public boolean atualizarReservasConcretizadas(Reserva reserva) {
        if (listaReservas.contains(reserva)) {
            reserva.setConcretizada(true);
            int temp = reserva.getCliente().getNumReservasConcretizadas();
            temp++;
            reserva.getCliente().setNumReservasConcretizadas(temp);
            return true;
        }
        return false;
    }

    /**
     * Calcula o custo acumulado de todas as reservas registradas no sistema.
     *
     * @return O custo total de todas as reservas.
     */
    public double retornarCustoTotalDeTodasReservas() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            custoAcumulado += reserva.calcularCustoReserva();
        }

        return custoAcumulado;
    }

    /**
     * Calcula o custo acumulado de todas as {@link ReservaHotel} registradas no sistema.
     *
     * @return O custo total de todas as reservas de hotéis.
     */
    public double retornarCustoTodasReservasHoteis() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotel")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    /**
     * Calcula o custo acumulado de todas as {@link ReservaHotelVoo} registradas no sistema.
     *
     * @return O custo total de todas as reservas de pacotes (hotel + voo ida).
     */
    public double retornarCustoTodasReservasHoteisVoo() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVoo")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    /**
     * Calcula o custo acumulado de todas as {@link ReservaHotelVooIdaVolta} registradas no sistema.
     *
     * @return O custo total de todas as reservas de pacotes (hotel + voo ida e volta).
     */
    public double retornarCustoTodasReservasHoteisVooIV() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaHotelVooIdaVolta")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    /**
     * Calcula o custo acumulado de todas as {@link ReservaVoo} registradas no sistema.
     *
     * @return O custo total de todas as reservas com apenas de voos ida.
     */
    public double retornarCustoTodasReservasVoo() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVoo")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }

    /**
     * Calcula o custo acumulado de todas as {@link ReservaVooIdaVolta} registradas no sistema.
     *
     * @return O custo total de todas as reservas de voos ida e volta.
     */
    public double retornarCustoTodasReservasVooIV() {
        double custoAcumulado = 0;

        for (Reserva reserva : listaReservas) {
            if (reserva.getClass().getSimpleName().equalsIgnoreCase("ReservaVooIdaVolta")) {
                custoAcumulado += reserva.calcularCustoReserva();
            }
        }

        return custoAcumulado;
    }


    /**
     * Retorna uma lista de reservas ordenadas pelo custo em ordem decrescente.
     *
     * @return Lista de reservas ordenada por custo, da maior para a menor.
     */
    public List<Reserva> retornarReservasCustoDecrescente() {
        List<Reserva> listaReservasTemp = criarCopiaListaReservas();

        Criterio2_ReservaCustoDecrescente criterio = new Criterio2_ReservaCustoDecrescente();
        Collections.sort(listaReservasTemp, criterio);
        Collections.reverse(listaReservasTemp);

        return listaReservasTemp;
    }

    /**
     * Lista todas as reservas registradas na {@link Empresa}.
     *
     * @return String formatada contendo a lista de reservas e o total delas.
     */
    public String listarReservasOrdenadas(List<Reserva> listaReservasTemp) {
        StringBuilder sb = new StringBuilder();
        for (Reserva res : listaReservasTemp) {
            sb.append(res);
            sb.append("------------------------------------------");
            sb.append("\n");
        }
        sb.append(String.format("Total de Reservas = %d\n", listaReservasTemp.size()));
        return sb.toString();
    }

    /**
     * Retorna uma lista de reservas efetuadas, ordenadas pela data em ordem crescente.
     *
     * @return Lista de reservas ordenada por data, da mais antiga para a mais recente.
     */
    public List<Reserva> retornarReservasEfetuadasDataCrescente() {
        List<Reserva> copia = criarCopiaListaReservas();
        Collections.sort(copia);
        return copia;
    }

    /**
     * Retorna uma lista de clientes ordenada pelo critério especificado na classe {@link Criterio1}.
     *
     * {@code Criterio1} = Ordenar os clientes por ordem decrescente de idade. Caso tenha idade igual,
     * ordenar por ordem decrescente do número de reservas concretizadas. Caso ainda haja clientes
     * com o mesmo número de reservas concretizadas, ordenar por ordem alfabética dos nomes.
     *
     *
     * @return Lista de clientes ordenada de acordo com o critério.
     */
    public List<Cliente> retornarClientesOrdenados() {
        List<Cliente> listaClienteTemp = new ArrayList<>(listaClientes);
        Criterio1 crit = new Criterio1();
        Collections.sort(listaClienteTemp, crit);
        return listaClienteTemp;
    }

    /**
     * Cria uma cópia da lista de reservas, instanciando novos objetos para cada reserva de acordo com seu tipo.
     *
     * @return Lista copiada de reservas.
     */
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