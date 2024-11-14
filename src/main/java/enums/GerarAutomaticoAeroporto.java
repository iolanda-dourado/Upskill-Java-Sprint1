package enums;

public enum GerarAutomaticoAeroporto {
    AERO1("Avenida Central, 123 - Cidade Alta, SP", "https://www.aeroporto-cidadealta.com.br/", "CTA"),
    AERO2("Rua das Flores, 456 - Nova Esperança, RJ", "https://www.aeroporto-novaesperanca.com/", "NVE"),
    AERO3("Praça das Nuvens, 789 - Horizonte Azul, MG", "https://www.aeroporto-horizonteazul.com/", "HAZ"),
    AERO4("Alameda do Aviador, 101 - Aeroporto, PR", "https://www.aeroporto-alamedadoaviador.net/", "ALD"),
    AERO5("Rodovia do Vento, 202 - Aeroporto Norte, RS", "https://www.aeroporto-aeroportonorte.org/", "AON"),
    AERO6("Estrada dos Pilotos, 303 - Centro Aéreo, SC", "https://www.aeroporto-centroaereo.com/", "CTR"),
    AERO7("Travessa do Pouso, 404 - Zona Sul, BA", "https://www.aeroporto-zonasul.net/", "ZSL"),
    AERO8("Rua Horizonte, 505 - Cidade Aérea, PE", "https://www.aeroporto-cidadeaerea.com.br/", "CDA"),
    AERO9("Avenida das Asas, 606 - Campo Livre, CE", "https://www.aeroporto-campolivre.org/", "CPL"),
    AERO10("Rua do Destino, 707 - Vila Aeroporto, AM", "https://www.aeroporto-vilaaeroporto.com/", "VAR");

    private final String endereco;
    private final String paginaWeb;
    private final String codigoAeroporto;

    GerarAutomaticoAeroporto(String endereco, String paginaWeb, String codigoAeroporto) {
        this.endereco = endereco;
        this.paginaWeb = paginaWeb;
        this.codigoAeroporto = codigoAeroporto;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public String getCodigoAeroporto() {
        return codigoAeroporto;
    }
}
