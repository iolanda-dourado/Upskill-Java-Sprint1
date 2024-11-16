package excecoes;

public class NifInvalidoException extends IllegalArgumentException {
    public NifInvalidoException() {
        super("O número de identificação fiscal deve ter 9 dígitos.");
    }

    public NifInvalidoException(String mensagem) {
        super(mensagem);
    }
}
