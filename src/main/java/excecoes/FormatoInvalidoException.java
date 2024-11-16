package excecoes;

public class FormatoInvalidoException extends RuntimeException {
    public FormatoInvalidoException() {
        super("Dia é inválido!!");
    }

    public FormatoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
