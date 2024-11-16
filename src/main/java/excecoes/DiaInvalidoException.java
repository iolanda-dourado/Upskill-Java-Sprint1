package excecoes;

/**
 * Exceção personalizada {@code DiaInvalidoException} que estende {@link IllegalArgumentException}.
 * Esta exceção é lançada quando um dia fornecido é considerado inválido.
 */
public class DiaInvalidoException extends IllegalArgumentException {

    /**
     * Constrói uma nova instância de {@code DiaInvalidoException} com uma mensagem padrão.
     * A mensagem padrão é: "Dia é inválido!!".
     */
    public DiaInvalidoException() {
        super("Dia é inválido!!");
    }

    /**
     * Constrói uma nova instância de {@code DiaInvalidoException} com a mensagem fornecida.
     *
     * @param mensagem A mensagem personalizada para a exceção.
     */
    public DiaInvalidoException(String mensagem) {
        super(mensagem);
    }

}
