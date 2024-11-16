package enums;

/**
 * Enum que representa as companhias aéreas disponíveis.
 * Cada companhia aérea é representada por um valor específico no enum, com um método
 * {@link #toString()} sobrescrito para fornecer uma representação textual amigável para cada companhia.
 */
public enum CompanhiaAerea {
    /**
     * Companhia aérea TAP Air Portugal.
     */
    TAP {
        @Override
        public String toString() {
            return "TAP";
        }
    },

    /**
     * Companhia aérea Ryanair.
     */
    RYANAIR{
        @Override
        public String toString() {
            return "RyanAir";
        }
    },

    /**
     * Companhia aérea EasyJet.
     */
    EASYJET {
        @Override
        public String toString() {
            return "EasyJet";
        }
    },

    /**
     * Companhia aérea LATAM.
     */
    LATAM {
        @Override
        public String toString() {
            return "LATAM";
        }
    },

    /**
     * Companhia aérea Azul Linhas Aéreas.
     */
    AZUL{
        @Override
        public String toString() {
            return "Linhas Azul";
        }
    }
}
