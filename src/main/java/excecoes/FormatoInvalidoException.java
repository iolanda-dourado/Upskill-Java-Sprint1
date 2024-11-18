package excecoes;

/**
 * Exceção personalizada {@code FormatoInvalidoException} que estende {@link RuntimeException}.
 * Esta exceção é lançada quando um formato fornecido é considerado inválido.
 */
public class FormatoInvalidoException extends RuntimeException {

    /**
     * Constrói uma nova instância de {@code FormatoInvalidoException} com uma mensagem padrão.
     * A mensagem padrão é: "Dia é inválido!!".
     */
    public FormatoInvalidoException() {
        super("Erro: O formato é inválido!");
    }

    /**
     * Constrói uma nova instância de {@code FormatoInvalidoException} com a mensagem fornecida.
     *
     * @param mensagem A mensagem personalizada para a exceção.
     */
    public FormatoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
