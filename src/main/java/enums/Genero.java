package enums;

/**
 * Enum {@code Genero} representa os diferentes gêneros possíveis.
 * Cada valor no enum define um gênero com sua respectiva representação em formato de string.
 */
public enum Genero {

    /**
     * Representa o gênero feminino.
     *
     * @return A string "Feminino".
     */
    FEMENINO {
        @Override
        public String toString() {
            return "Feminino";
        }
    },

    /**
     * Representa o gênero masculino.
     *
     * @return A string "Masculino".
     */
    MASCULINO {
        @Override
        public String toString() {
            return "Masculino";
        }
    },

    /**
     * Representa outro gênero não especificado.
     *
     * @return A string "Outro".
     */
    OUTRO {
        @Override
        public String toString() {
            return "Outro";
        }
    }
}
