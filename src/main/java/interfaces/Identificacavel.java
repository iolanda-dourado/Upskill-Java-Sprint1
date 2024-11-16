package interfaces;

/**
 * Interface que define um método para gerar um identificador único.
 * Qualquer classe que implemente esta interface deve fornecer a lógica para gerar um identificador.
 */
public interface Identificacavel {

    /**
     * Método responsável por gerar um identificador único.
     * O identificador gerado pode ser usado para distinguir objetos ou entidades.
     *
     * @return Um identificador único, representado como uma String.
     */
    String gerarIdentificador();
}
