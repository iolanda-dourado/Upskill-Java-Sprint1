package excecoes;

/**
 * Exceção personalizada {@code MesInvalidoException} que estende {@link IllegalArgumentException}.
 * Esta exceção é lançada quando um mês fornecido é considerado inválido.
 */
public class MesInvalidoException extends IllegalArgumentException {

    /**
     * Constrói uma nova instância de {@code MesInvalidoException} com a mensagem fornecida.
     *
     * @param message A mensagem personalizada para a exceção.
     */
    public MesInvalidoException(String message) {
        super(message);
    }

    /**
     * Constrói uma nova instância de {@code MesInvalidoException} com uma mensagem padrão.
     * A mensagem padrão é: "Mês é inválido!".
     */
    public MesInvalidoException() {
        super("Erro: O mês é inválido!");
    }
}
