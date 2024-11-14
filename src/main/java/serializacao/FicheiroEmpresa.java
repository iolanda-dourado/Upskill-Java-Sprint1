package serializacao;

import entidades.Empresa;

import java.io.*;

public class FicheiroEmpresa {

    public boolean serializar(String nomeFicheiro, Empresa empresa) {
        return serializar(new File (nomeFicheiro), empresa);
    }
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

    public Empresa desserializar (String nomeFicheiro) {return desserializar(new File(nomeFicheiro));}

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
