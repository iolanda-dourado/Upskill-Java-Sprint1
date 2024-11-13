package entidades;

import interfaces.Identificacavel;

import java.util.Objects;

public class Cliente implements Identificacavel {
    private String codigoCliente;
    private String nomeCliente;
    private Data dataNascimento;
    private String genero;
    private int nif;
    private String numPassaporte;
    private String email;
    private double percentagemDesconto;
    private int numReservasConcretizadas;

    private static final int CODIGO_CLIENTE_POR_OMISSAO = -2;
    private static final String NOME_CLIENTE_POR_OMISSAO = "Sem nome";
    private static final String GENERO_POR_OMISSAO = "Desconhecido";
    private static final int NIF_POR_OMISSAO = 999999999;
    private static final String NUM_PASSAPORTE_POR_OMISSAO = "P000";
    private static final String EMAIL_POR_OMISSAO = "Não informado";
    private static final double PERCENTAGEM_DESCONTO_POR_OMISSAO = -1;
    private static final int NUM_RESERVAS_CONCRETIZADAS_POR_OMISSAO = -1;

    private static int contadorCliente = 0;


    public Cliente(String nomeCliente, String genero, int nif, String numPassaporte, String email, double percentagemDesconto, int numReservasConcretizadas) {
        this.codigoCliente = gerarIdentificador();
        setNomeCliente(nomeCliente);
        this.genero = genero;
        setNif(nif);
        this.numPassaporte = numPassaporte;
        this.email = email;
        this.percentagemDesconto = percentagemDesconto;
        this.numReservasConcretizadas = numReservasConcretizadas;
    }

    public Cliente() {
        this.codigoCliente = gerarIdentificador();
        this.nomeCliente = NOME_CLIENTE_POR_OMISSAO;
        this.genero = GENERO_POR_OMISSAO;
        this.nif = NIF_POR_OMISSAO;
        this.numPassaporte = NUM_PASSAPORTE_POR_OMISSAO;
        this.email = EMAIL_POR_OMISSAO;
        this.percentagemDesconto = PERCENTAGEM_DESCONTO_POR_OMISSAO;
        this.numReservasConcretizadas = NUM_RESERVAS_CONCRETIZADAS_POR_OMISSAO;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public String getGenero() {
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

    public int getNumReservasConcretizadas() {
        return numReservasConcretizadas;
    }

    public void setNomeCliente(String nomeCliente) {
        char c;
        for(int i=0;i<nomeCliente.length();i++) {
            c = nomeCliente.charAt(i);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c))
                throw new IllegalArgumentException(String.format("O %s tem caracteres que não são letras!", nomeCliente));
        }

        this.nomeCliente = nomeCliente;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNif(int nif) {
        if (nif < 100000000 || nif > 999999999) {
            throw new IllegalArgumentException("Número de identificação fiscal inválido!");
        }
        this.nif = nif;
    }

    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPercentagemDesconto(double percentagemDesconto) {
        this.percentagemDesconto = percentagemDesconto;
    }

    public void setNumReservasConcretizadas(int numReservasConcretizadas) {
        this.numReservasConcretizadas = numReservasConcretizadas;
    }

    @Override
    public String toString() {
        return String.format("--- Cliente ---\nCódigo de Cliente: %d\nNome do Cliente: %s\nData de Nascimento: %s\nGênero: %s\nNIF: %s\nNúmero de Passaporte: %s\nEmail: %s\nPercentagem de Desconto: %.2f\nNúmero de Reservas Concretizadas: %d", codigoCliente, nomeCliente, dataNascimento, genero, nif, numPassaporte, email, percentagemDesconto, numReservasConcretizadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigoCliente, cliente.codigoCliente) && Double.compare(percentagemDesconto, cliente.percentagemDesconto) == 0 && numReservasConcretizadas == cliente.numReservasConcretizadas && Objects.equals(nomeCliente, cliente.nomeCliente) && Objects.equals(dataNascimento, cliente.dataNascimento) && Objects.equals(genero, cliente.genero) && Objects.equals(nif, cliente.nif) && Objects.equals(numPassaporte, cliente.numPassaporte) && Objects.equals(email, cliente.email);
    }

    @Override
    public String gerarIdentificador() {
        return String.valueOf(++contadorCliente);
    }
}
