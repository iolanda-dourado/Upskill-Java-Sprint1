package enums;

/**
 * Enum {@code GerarAutomaticoAeroporto} representa os aeroportos automáticos disponíveis,
 * cada um com seu endereço, página web e código de aeroporto.
 */
public enum GerarAutomaticoAeroporto {

    /** Aeroporto localizado na Avenida Central, 123, Cidade Alta, SP. */
    AERO1("Avenida Central, 123 - Cidade Alta, SP", "https://www.aeroporto-cidadealta.com.br/", "CTA"),

    /** Aeroporto localizado na Rua das Flores, 456, Nova Esperança, RJ. */
    AERO2("Rua das Flores, 456 - Nova Esperança, RJ", "https://www.aeroporto-novaesperanca.com/", "NVE"),

    /** Aeroporto localizado na Praça das Nuvens, 789, Horizonte Azul, MG. */
    AERO3("Praça das Nuvens, 789 - Horizonte Azul, MG", "https://www.aeroporto-horizonteazul.com/", "HAZ"),

    /** Aeroporto localizado na Alameda do Aviador, 101, Aeroporto, PR. */
    AERO4("Alameda do Aviador, 101 - Aeroporto, PR", "https://www.aeroporto-alamedadoaviador.net/", "ALD"),

    /** Aeroporto localizado na Rodovia do Vento, 202, Aeroporto Norte, RS. */
    AERO5("Rodovia do Vento, 202 - Aeroporto Norte, RS", "https://www.aeroporto-aeroportonorte.org/", "AON"),

    /** Aeroporto localizado na Estrada dos Pilotos, 303, Centro Aéreo, SC. */
    AERO6("Estrada dos Pilotos, 303 - Centro Aéreo, SC", "https://www.aeroporto-centroaereo.com/", "CTR"),

    /** Aeroporto localizado na Travessa do Pouso, 404, Zona Sul, BA. */
    AERO7("Travessa do Pouso, 404 - Zona Sul, BA", "https://www.aeroporto-zonasul.net/", "ZSL"),

    /** Aeroporto localizado na Rua Horizonte, 505, Cidade Aérea, PE. */
    AERO8("Rua Horizonte, 505 - Cidade Aérea, PE", "https://www.aeroporto-cidadeaerea.com.br/", "CDA"),

    /** Aeroporto localizado na Avenida das Asas, 606, Campo Livre, CE. */
    AERO9("Avenida das Asas, 606 - Campo Livre, CE", "https://www.aeroporto-campolivre.org/", "CPL"),

    /** Aeroporto localizado na Rua do Destino, 707, Vila Aeroporto, AM. */
    AERO10("Rua do Destino, 707 - Vila Aeroporto, AM", "https://www.aeroporto-vilaaeroporto.com/", "VAR");

    /** O endereço do aeroporto. */
    private final String endereco;

    /** A página web oficial do aeroporto. */
    private final String paginaWeb;

    /** O código do aeroporto. */
    private final String codigoAeroporto;

    /**
     * Constrói uma nova instância de {@code GerarAutomaticoAeroporto} com o endereço,
     * página web e código do aeroporto fornecidos.
     *
     * @param endereco O endereço do aeroporto.
     * @param paginaWeb A página web oficial do aeroporto.
     * @param codigoAeroporto O código do aeroporto.
     */
    GerarAutomaticoAeroporto(String endereco, String paginaWeb, String codigoAeroporto) {
        this.endereco = endereco;
        this.paginaWeb = paginaWeb;
        this.codigoAeroporto = codigoAeroporto;
    }

    /**
     * Retorna o endereço do aeroporto.
     *
     * @return O endereço do aeroporto.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Retorna a página web oficial do aeroporto.
     *
     * @return A página web do aeroporto.
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * Retorna o código do aeroporto.
     *
     * @return O código do aeroporto.
     */
    public String getCodigoAeroporto() {
        return codigoAeroporto;
    }
}
