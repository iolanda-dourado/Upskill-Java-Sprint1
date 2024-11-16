package excecoes;

/**
 * Exceção personalizada {@code NifInvalidoException} que estende {@link IllegalArgumentException}.
 * Esta exceção é lançada quando o número de identificação fiscal (NIF) fornecido é inválido.
 * Um NIF válido deve ter exatamente 9 dígitos.
 */
public class NifInvalidoException extends IllegalArgumentException {

    /**
     * Constrói uma nova instância de {@code NifInvalidoException} com uma mensagem padrão.
     * A mensagem padrão é: "O número de identificação fiscal deve ter 9 dígitos."
     */
    public NifInvalidoException() {
        super("O número de identificação fiscal deve ter 9 dígitos.");
    }

    /**
     * Constrói uma nova instância de {@code NifInvalidoException} com a mensagem fornecida.
     *
     * @param mensagem A mensagem personalizada para a exceção.
     */
    public NifInvalidoException(String mensagem) {
        super(mensagem);
    }
}
