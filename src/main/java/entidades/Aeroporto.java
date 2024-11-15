package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Aeroporto implements Serializable {
    private String endereco;
    private String paginaWeb;
    private String codigoAeroporto;

    private static final String ENDERECO_OMISSAO = "Desconhecido";
    private static final String  PAGINAWEB_OMISSAO = "Não informada";
    private static final String  CODIGO_AEROPORTO_OMISSAO = "XXX";


    public Aeroporto(String endereco, String paginaWeb, String codigoAeroporto) {
        this.endereco = endereco;
        this.paginaWeb = paginaWeb;
        this.codigoAeroporto = codigoAeroporto;
    }

    public Aeroporto() {
        this.endereco = ENDERECO_OMISSAO;
        this.paginaWeb = PAGINAWEB_OMISSAO;
        this.codigoAeroporto = CODIGO_AEROPORTO_OMISSAO;
    }

    public Aeroporto (Aeroporto outro) {
        this.endereco = outro.endereco;
        this.paginaWeb = outro.paginaWeb;
        this.codigoAeroporto = outro.codigoAeroporto;
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

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public void setCodigoAeroporto(String codigoAeroporto) {
        this.codigoAeroporto = codigoAeroporto;
    }

    @Override
    public String toString() {
        return String.format("--- Aeroporto ---\nCódigo do Aeroporto: %s\nEndereço: %s\nPágina Web: %s", codigoAeroporto, endereco, paginaWeb);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeroporto aeroporto = (Aeroporto) o;
        return Objects.equals(endereco, aeroporto.endereco) && Objects.equals(paginaWeb, aeroporto.paginaWeb) && Objects.equals(codigoAeroporto, aeroporto.codigoAeroporto);
    }
}
