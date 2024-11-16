package serializacao;

import entidades.Empresa;

import java.io.*;

/**
 * Classe responsável pela serialização e desserialização de objetos da classe {@link Empresa}.
 * Esta classe permite salvar e carregar informações de uma instância de {@link Empresa} em/para um arquivo.
 */
public class FicheiroEmpresa {

    /**
     * Serializa o objeto {@link Empresa} em um arquivo especificado pelo nome do arquivo.
     *
     * @param nomeFicheiro O nome do arquivo onde o objeto será salvo.
     * @param empresa O objeto da classe {@link Empresa} a ser serializado.
     * @return {@code true} se a serialização for bem-sucedida, {@code false} em caso de erro.
     */
    public boolean serializar(String nomeFicheiro, Empresa empresa) {
        return serializar(new File (nomeFicheiro), empresa);
    }

    /**
     * Serializa o objeto {@link Empresa} em um arquivo especificado.
     *
     * @param ficheiro O arquivo onde o objeto será salvo.
     * @param empresa O objeto da classe {@link Empresa} a ser serializado.
     * @return {@code true} se a serialização for bem-sucedida, {@code false} em caso de erro.
     */
    public boolean serializar(File ficheiro, Empresa empresa) {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheiro))) {
                out.writeObject(empresa);

                return true;
            }
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Desserializa o objeto {@link Empresa} a partir de um arquivo especificado pelo nome do arquivo.
     *
     * @param nomeFicheiro O nome do arquivo de onde o objeto será carregado.
     * @return A instância de {@link Empresa} desserializada, ou uma nova instância de {@link Empresa} caso ocorra erro.
     */
    public Empresa desserializar (String nomeFicheiro) {return desserializar(new File(nomeFicheiro));}

    /**
     * Desserializa o objeto {@link Empresa} a partir de um arquivo especificado.
     *
     * @param ficheiro O arquivo de onde o objeto será carregado.
     * @return A instância de {@link Empresa} desserializada, ou uma nova instância de {@link Empresa} caso ocorra erro.
     */
    public Empresa desserializar (File ficheiro) {
        Empresa empresa;
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheiro))) {
                empresa = (Empresa) in.readObject();
                return empresa;
            }
        } catch (IOException | ClassNotFoundException ex) {
            return new Empresa();
        }
    }
}
