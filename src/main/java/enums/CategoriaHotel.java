package enums;

/**
 * Enum que representa as categorias de classificação de hotéis.
 * Cada categoria é associada a uma quantidade de estrelas, que pode variar de 1 a 5.
 * A classe sobrescreve o método {@link #toString()} para fornecer uma representação textual
 * personalizada de cada categoria, facilitando a visualização do nome da categoria como uma
 * string amigável.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */
public enum CategoriaHotel {
    /**
     * Categoria de hotel com 1 estrela.
     */
    UMA_ESTRELA {
        @Override
        public String toString() {
            return "Uma Estrela";
        }
    },

    /**
     * Categoria de hotel com 2 estrelas.
     */
    DUAS_ESTRELAS {
        @Override
        public String toString() {
            return "Duas Estrelas";
        }
    },

    /**
     * Categoria de hotel com 3 estrelas.
     */
    TRES_ESTRELAS {
        @Override
        public String toString() {
            return "Três Estrelas";
        }
    },

    /**
     * Categoria de hotel com 4 estrelas.
     */
    QUATRO_ESTRELAS {
        @Override
        public String toString() {
            return "Quatro Estrelas";
        }
    },

    /**
     * Categoria de hotel com 2 estrelas.
     */
    CINCO_ESTRELAS {
        @Override
        public String toString() {
            return "Cinco Estrelas";
        }
    }
}