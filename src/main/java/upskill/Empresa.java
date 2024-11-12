package upskill;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
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


}
