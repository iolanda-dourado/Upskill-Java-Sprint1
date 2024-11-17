package entidades;

import enums.Genero;
import excecoes.NifInvalidoException;
import interfaces.Identificavel;
import utilidades.Data;

import java.io.Serializable;
import java.util.Objects;
/**
 * Representa um cliente com informações pessoais e histórico de interações.
 * Implementa a interface Identificacavel para geração de identificadores únicos e Serializable, permitindo sua serialização.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */

public class Cliente implements Identificavel, Serializable {

    /**
     * Código único do cliente.
     */
    private String codigoCliente;

    /**
     * Nome do cliente.
     */
    private String nomeCliente;

    /**
     * Data de nascimento do cliente.
     */
    private Data dataNascimento;

    /**
     * Gênero do cliente.
     */
    private Genero genero;

    /**
     * Número de identificação fiscal (NIF) do cliente.
     */
    private int nif;

    /**
     * Número do passaporte do cliente.
     */
    private String numPassaporte;

    /**
     * Endereço de email do cliente.
     */
    private String email;

    /**
     * Percentagem de desconto do cliente acordada com a empresa.
     */
    private double percentagemDesconto;

    /**
     * Número de reservas do cliente que já foram concretizadas.
     */
    private int numReservasConcretizadas;

    /**
     * Valor padrão para o nome do cliente quando não informado.
     */
    private static final String NOME_CLIENTE_POR_OMISSAO = "Sem nome";

    /**
     * Valor padrão para o gênero do cliente quando não informado.
     */
    private static final Genero GENERO_POR_OMISSAO = Genero.OUTRO;

    /**
     * Valor padrão para o NIF do cliente quando não informado.
     */
    private static final int NIF_POR_OMISSAO = 999999999;

    /**
     * Valor padrão para o número de passaporte do cliente quando não informado.
     */
    private static final String NUM_PASSAPORTE_POR_OMISSAO = "P000";

    /**
     * Valor padrão para o email do cliente quando não informado.
     */
    private static final String EMAIL_POR_OMISSAO = "Não informado";

    /**
     * Valor padrão para a percentagem de desconto aplicada ao cliente.
     */
    private static final double PERCENTAGEM_DESCONTO_POR_OMISSAO = -1;

    /**
     * Contador utilizado para gerar identificadores únicos para os clientes.
     */
    private static int contadorCliente = 0;

    /**
     * Construtor completo que inicializa todas as informações de um cliente (nome, data de nascimento, gênero,
     * NIF (número de identificação fiscal), número do passaporte, email, percentagem de desconto.
     *
     * @param nomeCliente Nome do cliente.
     * @param data Data de nascimento do cliente.
     * @param genero Gênero do cliente.
     * @param nif Número de identificação fiscal (NIF) do cliente.
     * @param numPassaporte Número do passaporte do cliente.
     * @param email Endereço de email do cliente.
     * @param percentagemDesconto Percentagem de desconto aplicada ao cliente.
     */
    public Cliente(String nomeCliente, Data data, Genero genero, int nif, String numPassaporte, String email, double percentagemDesconto) {
        this.codigoCliente = gerarIdentificador();
        setNomeCliente(nomeCliente);
        this.dataNascimento = new Data(data);
        this.genero = genero;
        setNif(nif);
        this.numPassaporte = numPassaporte;
        this.email = email;
        this.percentagemDesconto = percentagemDesconto;
        this.numReservasConcretizadas = 0;
    }

    /**
     * Construtor por omissão que inicializa os atributos com valores padrões.
     */
    public Cliente() {
        this.codigoCliente = gerarIdentificador();
        this.nomeCliente = NOME_CLIENTE_POR_OMISSAO;
        this.dataNascimento = new Data();
        this.genero = GENERO_POR_OMISSAO;
        this.nif = NIF_POR_OMISSAO;
        this.numPassaporte = NUM_PASSAPORTE_POR_OMISSAO;
        this.email = EMAIL_POR_OMISSAO;
        this.percentagemDesconto = PERCENTAGEM_DESCONTO_POR_OMISSAO;
    }

    /**
     * Construtor de cópia que cria uma nova instância com base em outro cliente.
     *
     * @param outro Cliente a ser copiado.
     */
    public Cliente(Cliente outro) {
        this.codigoCliente = outro.getCodigoCliente();
        setNomeCliente(outro.nomeCliente);
        this.dataNascimento = new Data(outro.dataNascimento);
        this.genero = outro.genero;
        setNif(outro.nif);
        this.numPassaporte = outro.numPassaporte;
        this.email = outro.email;
        this.percentagemDesconto = outro.percentagemDesconto;
        this.numReservasConcretizadas = outro.numReservasConcretizadas;
    }

    /**
     * Retorna o código único do cliente.
     *
     * @return Código único do cliente.
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return Nome do cliente.
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Retorna a data de nascimento do cliente.
     *
     * @return Data de nascimento do cliente.
     */
    public Data getDataNascimento() {
        return new Data(dataNascimento);
    }

    /**
     * Retorna o gênero do cliente.
     *
     * @return Gênero do cliente.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Retorna o número de identificação fiscal (NIF) do cliente.
     *
     * @return NIF do cliente.
     */
    public int getNif() {
        return nif;
    }

    /**
     * Retorna o número do passaporte do cliente.
     *
     * @return Número do passaporte do cliente.
     */
    public String getNumPassaporte() {
        return numPassaporte;
    }

    /**
     * Retorna o email do cliente.
     *
     * @return Email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna a percentagem de desconto aplicada ao cliente.
     *
     * @return Percentagem de desconto do cliente.
     */
    public double getPercentagemDesconto() {
        return percentagemDesconto;
    }

    /**
     * Retorna o número de reservas do cliente que já foram concretizadas.
     *
     * @return Número de reservas concretizadas.
     */
    public int getNumReservasConcretizadas() {
        return numReservasConcretizadas;
    }

    /**
     * Define o nome do cliente, verificando se contém apenas letras e espaços.
     *
     * @param nomeCliente Nome do cliente.
     * @throws IllegalArgumentException Se o nome contiver caracteres inválidos.
     */
    public void setNomeCliente(String nomeCliente) {
        char c;
        for(int i=0;i<nomeCliente.length();i++) {
            c = nomeCliente.charAt(i);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c))
                throw new IllegalArgumentException(String.format("O %s tem caracteres que não são letras!", nomeCliente));
        }

        this.nomeCliente = nomeCliente;
    }

    /**
     * Define a data de nascimento do cliente.
     *
     * @param dataNascimento Objeto {@link Data} contendo a data de nascimento do cliente.
     */
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento.setData(dataNascimento.getAno(), dataNascimento.getMes(), dataNascimento.getDia());
    }

    /**
     * Define o gênero do cliente.
     *
     * @param genero Gênero do cliente.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Define o número de identificação fiscal (NIF) do cliente.
     *
     * @param nif Número de identificação fiscal.
     * @throws NifInvalidoException Se o NIF for inválido (não tiver 9 dígitos).
     */
    public void setNif(int nif) {
        if (nif < 100000000 || nif > 999999999) {
            throw new NifInvalidoException("Número de identificação fiscal inválido!");
        }
        this.nif = nif;
    }

    /**
     * Define o número do passaporte do cliente.
     *
     * @param numPassaporte Número do passaporte.
     */
    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }

    /**
     * Define o email do cliente.
     *
     * @param email Endereço de email do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Define a percentagem de desconto aplicada ao cliente.
     *
     * @param percentagemDesconto Percentagem de desconto (entre 0 e 100).
     * @throws IllegalArgumentException Se o valor não estiver no intervalo de 1 a 100.
     */
    public void setPercentagemDesconto(double percentagemDesconto) {
        if (percentagemDesconto < 0 && percentagemDesconto > 100) {
            throw new IllegalArgumentException("O valor deve ser entre 0 e 100.");
        }
        this.percentagemDesconto = percentagemDesconto;
    }

    /**
     * Define o número de reservas concretizadas pelo cliente.
     * Método protegido para evitar alterações externas diretas.
     *
     * @param numReservasConcretizadas Número de reservas concretizadas.
     */
    protected void setNumReservasConcretizadas(int numReservasConcretizadas) {
        this.numReservasConcretizadas = numReservasConcretizadas;
    }

    /**
     * Retorna uma representação textual dos detalhes do cliente.
     *
     * @return String contendo os detalhes do cliente formatados.
     */
    @Override
    public String toString() {
        return String.format(
                """
                --- Cliente ---
                Código de Cliente: %s
                Nome do Cliente: %s
                Data de Nascimento: %s
                Gênero: %s
                NIF: %s
                Número de Passaporte: %s
                Email: %s
                Percentagem de Desconto: %.2f%%
                Número de Reservas Concretizadas: %d
                """,
                codigoCliente, nomeCliente, dataNascimento, genero, nif, numPassaporte, email, percentagemDesconto, numReservasConcretizadas
        );
    }

    /**
     * Compara este cliente com outro objeto para verificar igualdade.
     *
     * @param outroCliente Objeto a ser comparado.
     * @return true se os objetos forem iguais; false caso contrário.
     */
    @Override
    public boolean equals(Object outroCliente) {
        if (this == outroCliente) return true;
        if (outroCliente == null || getClass() != outroCliente.getClass()) return false;
        Cliente cliente = (Cliente) outroCliente;
        return Double.compare(percentagemDesconto, cliente.percentagemDesconto) == 0 && Objects.equals(nomeCliente, cliente.nomeCliente) && Objects.equals(dataNascimento, cliente.dataNascimento) && Objects.equals(genero, cliente.genero) && Objects.equals(nif, cliente.nif) && Objects.equals(numPassaporte, cliente.numPassaporte) && Objects.equals(email, cliente.email);
    }

    /**
     * Gera um identificador único para o cliente.
     *
     * @return Identificador único como uma string.
     */
    @Override
    public String gerarIdentificador() {
        return String.valueOf(++contadorCliente);
    }


}
