package enums;

public enum GerarAutomaticoCliente {
    CLIENTE_1("João Silva", new Data(1980, 1, 15), Genero.MASCULINO, 123456789, "P123456", "joao.silva@email.com", 5.0),
    CLIENTE_2("Maria Oliveira", new Data(1990, 2, 20), Genero.FEMENINO, 987654321, "P987654", "maria.oliveira@email.com", 10.0),
    CLIENTE_3("Pedro Costa", new Data(1985, 5, 10), Genero.MASCULINO, 112233445, "P112233", "pedro.costa@email.com", 0.0),
    CLIENTE_4("Ana Pereira", new Data(1992, 8, 25), Genero.FEMENINO, 223344556, "P223344", "ana.pereira@email.com", 15),
    CLIENTE_5("Carlos Souza", new Data(1975, 10, 5), Genero.MASCULINO, 334455667, "P334455", "carlos.souza@email.com", 1.0),
    CLIENTE_6("Cláudia Martins", new Data(1995, 6, 30), Genero.FEMENINO, 445566778, "P445566", "claudia.martins@email.com", 5.0),
    CLIENTE_7("Ricardo Alves", new Data(1988, 12, 12), Genero.MASCULINO, 556677889, "P556677", "ricardo.alves@email.com", 3.0),
    CLIENTE_8("Fernanda Lima", new Data(1993, 3, 18), Genero.FEMENINO, 667788990, "P667788", "fernanda.lima@email.com", 7.5),
    CLIENTE_9("Lucas Rocha", new Data(1982, 9, 22), Genero.MASCULINO, 778899001, "P778899", "lucas.rocha@email.com", 10.0),
    CLIENTE_10("Juliana Costa", new Data(1998, 7, 9), Genero.FEMENINO, 889900112, "P889900", "juliana.costa@email.com", 0.0);

    private final String nomeCliente;
    private final Data dataNascimento;
    private final Genero genero;
    private final int nif;
    private final String numPassaporte;
    private final String email;
    private final double percentagemDesconto;

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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getNif() {
        return nif;
    }

    public String getNumPassaporte() {
        return numPassaporte;
    }

    public String getEmail() {
        return email;
    }

    public double getPercentagemDesconto() {
        return percentagemDesconto;
    }
}