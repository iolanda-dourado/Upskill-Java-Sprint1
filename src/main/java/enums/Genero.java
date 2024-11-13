package enums;

public enum Genero {
    FEMENINO {
        @Override
        public String toString() {

            return "Feminino";
        }
    },

    MASCULINO {
        @Override
        public String toString() {
            return "Masculino";
        }
    }, OUTRO {
        @Override
        public String toString() {
            return "Outro";
        }
    }
}
