package excecoes;

public class MesInvalidoException extends IllegalArgumentException {
    public MesInvalidoException(String message) { super(message); }
  public MesInvalidoException() { super("Mês é inválido!");}
}
