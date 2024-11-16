package enums;

import utilidades.Data;

/**
 * Enum {@code GerarAutomaticoCliente} representa os clientes com suas informações pessoais,
 * incluindo nome, data de nascimento, gênero, número de identificação fiscal (NIF), número de passaporte,
 * email e percentagem de desconto associada.
 */
public enum GerarAutomaticoCliente {

    /** Cliente João Silva, com informações pessoais e 5% de desconto. */
    CLIENTE_1("João Silva", new Data(1980, 1, 15), Genero.MASCULINO, 123456789, "P123456", "joao.silva@email.com", 5.0),

    /** Cliente Maria Oliveira, com informações pessoais e 10% de desconto. */
    CLIENTE_2("Maria Oliveira", new Data(1990, 2, 20), Genero.FEMENINO, 987654321, "P987654", "maria.oliveira@email.com", 10.0),

    /** Cliente Pedro Costa, com informações pessoais e sem desconto. */
    CLIENTE_3("Pedro Costa", new Data(1985, 5, 10), Genero.MASCULINO, 112233445, "P112233", "pedro.costa@email.com", 0.0),

    /** Cliente Ana Pereira, com informações pessoais e 15% de desconto. */
    CLIENTE_4("Ana Pereira", new Data(1992, 8, 25), Genero.FEMENINO, 223344556, "P223344", "ana.pereira@email.com", 15.0),

    /** Cliente Carlos Souza, com informações pessoais e 1% de desconto. */
    CLIENTE_5("Carlos Souza", new Data(1975, 10, 5), Genero.MASCULINO, 334455667, "P334455", "carlos.souza@email.com", 1.0),

    /** Cliente Cláudia Martins, com informações pessoais e 5% de desconto. */
    CLIENTE_6("Cláudia Martins", new Data(1995, 6, 30), Genero.FEMENINO, 445566778, "P445566", "claudia.martins@email.com", 5.0),

    /** Cliente Ricardo Alves, com informações pessoais e 3% de desconto. */
    CLIENTE_7("Ricardo Alves", new Data(1988, 12, 12), Genero.MASCULINO, 556677889, "P556677", "ricardo.alves@email.com", 3.0),

    /** Cliente Fernanda Lima, com informações pessoais e 7.5% de desconto. */
    CLIENTE_8("Fernanda Lima", new Data(1993, 3, 18), Genero.FEMENINO, 667788990, "P667788", "fernanda.lima@email.com", 7.5),

    /** Cliente Lucas Rocha, com informações pessoais e 10% de desconto. */
    CLIENTE_9("Lucas Rocha", new Data(1982, 9, 22), Genero.MASCULINO, 778899001, "P778899", "lucas.rocha@email.com", 10.0),

    /** Cliente Juliana Costa, com informações pessoais e sem desconto. */
    CLIENTE_10("Juliana Costa", new Data(1998, 7, 9), Genero.FEMENINO, 889900112, "P889900", "juliana.costa@email.com", 0.0);

    /** O nome do cliente. */
    private final String nomeCliente;

    /** A data de nascimento do cliente. */
    private final Data dataNascimento;

    /** O gênero do cliente. */
    private final Genero genero;

    /** O número de identificação fiscal (NIF) do cliente. */
    private final int nif;

    /** O número de passaporte do cliente. */
    private final String numPassaporte;

    /** O endereço de email do cliente. */
    private final String email;

    /** A percentagem de desconto associada ao cliente. */
    private final double percentagemDesconto;

    /**
     * Constrói uma nova instância de {@code GerarAutomaticoCliente} com as informações pessoais do cliente.
     *
     * @param nomeCliente O nome do cliente.
     * @param dataNascimento A data de nascimento do cliente.
     * @param genero O gênero do cliente.
     * @param nif O número de identificação fiscal (NIF) do cliente.
     * @param numPassaporte O número de passaporte do cliente.
     * @param email O endereço de email do cliente.
     * @param percentagemDesconto A percentagem de desconto associada ao cliente.
     */
    GerarAutomaticoCliente(String nomeCliente, Data dataNascimento, Genero genero,
                           int nif, String numPassaporte, String email, double percentagemDesconto) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.nif = nif;
        this.numPassaporte = numPassaporte;
        this.email = email;
        this.percentagemDesconto = percentagemDesconto;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Retorna a data de nascimento do cliente.
     *
     * @return A data de nascimento do cliente.
     */
    public Data getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Retorna o gênero do cliente.
     *
     * @return O gênero do cliente.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Retorna o número de identificação fiscal (NIF) do cliente.
     *
     * @return O número de identificação fiscal do cliente.
     */
    public int getNif() {
        return nif;
    }

    /**
     * Retorna o número de passaporte do cliente.
     *
     * @return O número de passaporte do cliente.
     */
    public String getNumPassaporte() {
        return numPassaporte;
    }

    /**
     * Retorna o endereço de email do cliente.
     *
     * @return O email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna a percentagem de desconto associada ao cliente.
     *
     * @return A percentagem de desconto.
     */
    public double getPercentagemDesconto() {
        return percentagemDesconto;
    }
}
