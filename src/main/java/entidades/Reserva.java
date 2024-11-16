package entidades;

import interfaces.Identificacavel;
import utilidades.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe abstrata que representa uma reserva genérica.
 * Inclui informações sobre a data, cliente, quantidade de pessoas,
 * e o estado de concretização da reserva.
 *
 * Esta classe também rastreia estatísticas globais como o número de reservas criadas,
 * bem como implementa a Interface Identificavel, Comparable e Serializable.
 * *
 * @author Iolanda Dourado e Marianna Ramos
 * @version 1.0
 */
public abstract class Reserva implements Identificacavel, Comparable<Reserva>, Serializable {
    /**
     * Código identificador único da reserva.
     */
    private String codigoReserva;

    /**
     * Data associada à reserva.
     */
    private Data dataReserva;

    /**
     * Quantidade de pessoas incluídas na reserva.
     */
    private int qntPessoas;

    /**
     * Cliente associado à reserva.
     */
    private Cliente cliente;

    /**
     * Indica se a reserva foi concretizada.
     */
    private boolean isConcretizada;

    /**
     * Indica se o atributo de concretização foi definido.
     */
    private boolean isAtributoSet;

    /**
     * Valor padrão para a data da reserva.
     */
    private static final Data DATA_RESERVA_OMISSAO = Data.dataAtual();

    /**
     * Valor padrão para a quantidade de pessoas na reserva.
     */
    private static final int QNT_PESSOAS_OMISSAO = 0;

    /**
     * Cliente padrão associado à reserva.
     */
    private static final Cliente CLIENTE_OMISSAO = new Cliente();

    /**
     * Valor padrão para o estado de concretização.
     */
    private static final boolean ISCONCRETIZADA_OMISSAO = false;

    /**
     * Contador estático que rastreia o número total de reservas criadas.
     */
    private static int reservaCount = 0;

    /**
     * Taxa padrão aplicada a todas as reservas.
     */
    private static double taxaReserva = 20;

    /**
     * Valor múltiplo usado para determinar condições especiais.
     */
    private static int numMultiploDeX = 5;

    /**
     * Construtor parametrizado para criar uma reserva.
     *
     * @param dataReserva Data da reserva.
     * @param qntPessoas  Quantidade de pessoas incluídas.
     * @param cliente     Cliente associado à reserva.
     */
    public Reserva(Data dataReserva, int qntPessoas, Cliente cliente) {
        this.codigoReserva = gerarIdentificador();
        this.dataReserva = new Data (dataReserva);
        this.qntPessoas = qntPessoas;
        this.cliente = cliente;
        this.isConcretizada = false;
        isAtributoSet = false;
        ++reservaCount;
    }

    /**
     * Construtor padrão que inicializa a reserva com valores padrão.
     */
    public Reserva() {
        this.codigoReserva = gerarIdentificador();
        this.dataReserva = DATA_RESERVA_OMISSAO;
        this.qntPessoas = QNT_PESSOAS_OMISSAO;
        this.cliente = CLIENTE_OMISSAO;
        this.isConcretizada = ISCONCRETIZADA_OMISSAO;
        ++reservaCount;
    }

    /**
     * Construtor de cópia que cria uma nova reserva baseada em outra.
     *
     * @param outra Reserva a ser copiada.
     */
    public Reserva(Reserva outra) {
        this.codigoReserva = outra.codigoReserva;
        this.dataReserva = new Data (outra.dataReserva);
        this.qntPessoas = outra.qntPessoas;
        this.cliente = new Cliente (outra.cliente);
        this.isConcretizada = outra.isConcretizada;
        isAtributoSet = outra.isAtributoSet;
    }

    /**
     * Retorna o código da reserva.
     *
     * @return Código da reserva.
     */
    public String getCodigoReserva() {
        return codigoReserva;
    }

    /**
     * Retorna uma cópia da data da reserva.
     *
     * @return Data da reserva.
     */
    public Data getDataReserva() {
        return new Data(dataReserva);
    }

    /**
     * Retorna a quantidade de pessoas na reserva.
     *
     * @return Quantidade de pessoas.
     */
    public int getQntPessoas() {
        return qntPessoas;
    }

    /**
     * Retorna o cliente associado à reserva.
     *
     * @return Cliente da reserva.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Verifica se a reserva foi concretizada.
     *
     * @return true se concretizada, false caso contrário.
     */
    public boolean isConcretizada() {
        return isConcretizada;
    }

    /**
     * Permite que apenas as subclasses definam o código da reserva.
     *
     * @param codigoReserva Novo código da reserva.
     */
    protected void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    /**
     * Define a data da reserva.
     *
     * @param dataReserva Nova data da reserva.
     */
    public void setDataReserva(Data dataReserva) {
        this.dataReserva.setData(dataReserva.getAno(), dataReserva.getMes(), dataReserva.getDia());
    }

    /**
     * Define a quantidade de pessoas na reserva.
     *
     * @param qntPessoas Nova quantidade de pessoas.
     */
    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }

    /**
     * Define o cliente da reserva.
     *
     * @param cliente Novo cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Define se a reserva foi concretizada.
     *
     * @param concretizada true se concretizada, false caso contrário.
     * @throws UnsupportedOperationException Se já foi definida previamente.
     */
    public void setConcretizada(boolean concretizada) {
        if (!isAtributoSet) {
            this.isConcretizada = concretizada;
            isAtributoSet = true;
        } else {
            throw new UnsupportedOperationException("A reserva já foi concretizada  e não pode ser alterada.");
        }
    }

    /**
     * Representação em String das informações da reserva.
     *
     * @return Detalhes da reserva.
     */
    @Override
    public String toString() {
        String resposta = isConcretizada ? "Sim" : "Não";
        return String.format("--- Informações da reserva ---\nData da reserva: %s\nCliente: %s\nQuantidade de pessoas: " +
                "%d\nReserva concretizada? %s\n", dataReserva, cliente.getNomeCliente(), qntPessoas, resposta);

    }

    /**
     * Compara esta reserva com outro objeto para verificar igualdade.
     *
     * A igualdade é determinada se:
     * - A quantidade de pessoas é a mesma.
     * - O estado de concretização da reserva é o mesmo.
     * - O atributo de bloqueio de alteração (`isAtributoSet`) é igual.
     * - A data da reserva e o cliente associados são iguais.
     *
     * @param outraReserva O objeto a ser comparado com esta reserva.
     * @return {@code true} se os objetos forem iguais; caso contrário, {@code false}.
     */
    @Override
    public boolean equals(Object outraReserva) {
        if (outraReserva == null || getClass() != outraReserva.getClass()) return false;
        Reserva reserva = (Reserva) outraReserva;
        return qntPessoas == reserva.qntPessoas && isConcretizada == reserva.isConcretizada &&
                isAtributoSet == reserva.isAtributoSet && Objects.equals(dataReserva, reserva.dataReserva) &&
                Objects.equals(cliente, reserva.cliente);
    }

    /**
     * Gera o identificador único da reserva.
     *
     * @return Identificador único.
     */
    @Override
    public abstract String gerarIdentificador();

    /**
     * Compara esta reserva com outra com base na data.
     *
     * @param outraReserva Reserva a ser comparada.
     * @return -1, 0 ou 1 dependendo da ordem.
     */
    @Override
    public int compareTo(Reserva outraReserva) {
        if (dataReserva.isMaior(outraReserva.dataReserva)) {
            return -1;
        } else if (!dataReserva.isMaior(outraReserva.dataReserva)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Verifica se o número de reservas concretizadas do cliente é múltiplo de um valor específico.
     *
     * @return true se múltiplo, false caso contrário.
     */
    public boolean saoReservasMultiplasDe5() {
        return getCliente().getNumReservasConcretizadas() != 0 &&
                getCliente().getNumReservasConcretizadas() % getNumMultiploDeX() == 0;
    }

    /**
     * Calcula o custo da reserva.
     *
     * @return Valor do custo.
     */
    public abstract double calcularCustoReserva();

    /**
     * Retorna o número total de reservas criadas.
     *
     * @return Total de reservas.
     */
    public static int getReservaCount() {
        return reservaCount;
    }

    /**
     * Retorna a taxa de reserva padrão.
     *
     * @return Taxa de reserva.
     */
    public static double getTaxaReserva() {
        return taxaReserva;
    }

    /**
     * Retorna o número múltiplo usado para condições especiais.
     * @return Número múltiplo.
     */
    public static int getNumMultiploDeX() {
        return numMultiploDeX;
    }

    /**
     * Define a taxa de reserva.
     *
     * @param taxaReserva Nova taxa de reserva.
     */
    public void setTaxaReserva(double taxaReserva) {
        Reserva.taxaReserva = taxaReserva;
    }

    /**
     * Define o número múltiplo.
     * @param numMultiploDeX Novo número múltiplo.
     */
    public static void setNumMultiploDeX(int numMultiploDeX) {
        Reserva.numMultiploDeX = numMultiploDeX;
    }
}
