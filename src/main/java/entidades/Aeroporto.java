package entidades;

import java.io.Serializable;
import java.util.Objects;
/**
 * Representa um aeroporto com endereço, página web e código do aeroporto.
 * Inclui informações padrão para valores omitidos, métodos de acesso,
 * construtores e métodos utilitários como equals e toString.
 * A classe implementa Serializable, permitindo sua serialização.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */

public class Aeroporto implements Serializable {

    /**
     * Endereço do aeroporto.
     */
    private String endereco;

    /**
     * Página web associada ao aeroporto.
     */
    private String paginaWeb;

    /**
     * Código único do aeroporto.
     */
    private String codigoAeroporto;

    /**
     * Endereço padrão quando não especificado.
     */
    private static final String ENDERECO_OMISSAO = "Desconhecido";

    /**
     * Página web padrão quando não especificada.
     */
    private static final String  PAGINAWEB_OMISSAO = "Não informada";

    /**
     * Código do aeroporto padrão quando não especificado.
     */
    private static final String  CODIGO_AEROPORTO_OMISSAO = "XXX";

    /**
     * Construtor completo para criar um objeto Aeroporto com todos os campos preenchidos.
     *
     * @param endereco        o endereço do aeroporto.
     * @param paginaWeb       a página web associada ao aeroporto.
     * @param codigoAeroporto o código único do aeroporto.
     */
    public Aeroporto(String endereco, String paginaWeb, String codigoAeroporto) {
        this.endereco = endereco;
        this.paginaWeb = paginaWeb;
        this.codigoAeroporto = codigoAeroporto;
    }

    /**
     * Construtor por omissão (sem parâmetros), que inicializa os campos com valores padrão.
     */
    public Aeroporto() {
        this.endereco = ENDERECO_OMISSAO;
        this.paginaWeb = PAGINAWEB_OMISSAO;
        this.codigoAeroporto = CODIGO_AEROPORTO_OMISSAO;
    }

    /**
     * Construtor de cópia que cria uma nova instância com os mesmos valores de um outro objeto Aeroporto.
     *
     * @param outro o objeto Aeroporto a ser copiado.
     */
    public Aeroporto (Aeroporto outro) {
        this.endereco = outro.endereco;
        this.paginaWeb = outro.paginaWeb;
        this.codigoAeroporto = outro.codigoAeroporto;
    }

    /**
     * Obtém o endereço do aeroporto.
     *
     * @return o endereço do aeroporto.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Obtém a página web do aeroporto.
     *
     * @return a página web do aeroporto.
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * Obtém o código único do aeroporto.
     *
     * @return o código do aeroporto.
     */
    public String getCodigoAeroporto() {
        return codigoAeroporto;
    }

    /**
     * Define o endereço do aeroporto.
     *
     * @param endereco o novo endereço do aeroporto.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Define a página web associada ao aeroporto.
     *
     * @param paginaWeb a nova página web do aeroporto.
     */
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Define o código único do aeroporto.
     *
     * @param codigoAeroporto o novo código do aeroporto.
     */
    public void setCodigoAeroporto(String codigoAeroporto) {
        this.codigoAeroporto = codigoAeroporto;
    }

    /**
     * Gera uma representação textual do aeroporto, incluindo código, endereço e página web.
     *
     * @return uma String representando o aeroporto.
     */
    @Override
    public String toString() {
        return String.format("--- Aeroporto ---\nCódigo do Aeroporto: %s\nEndereço: %s\nPágina Web: %s", codigoAeroporto, endereco, paginaWeb);
    }

    /**
     * Verifica a igualdade entre este aeroporto e outro objeto.
     * A comparação é feita com base no endereço, página web e código do aeroporto.
     *
     * @param outroAeroporto O objeto a ser comparado.
     * @return {@code true} se os aeroportos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object outroAeroporto) {
        if (this == outroAeroporto) return true;
        if (outroAeroporto == null || getClass() != outroAeroporto.getClass()) return false;
        Aeroporto aeroporto = (Aeroporto) outroAeroporto;
        return Objects.equals(endereco, aeroporto.endereco) && Objects.equals(paginaWeb, aeroporto.paginaWeb) && Objects.equals(codigoAeroporto, aeroporto.codigoAeroporto);
    }
}
