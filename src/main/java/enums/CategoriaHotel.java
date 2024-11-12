package enums;

public enum CategoriaHotel {
    UMA_ESTRELA {
        @Override
        public String toString() {
            return "Uma Estrela";
        }
    }, DUAS_ESTRELAS {
        @Override
        public String toString() {
            return "Duas Estrelas";
        }
    }, TRES_ESTRELAS {
        @Override
        public String toString() {
            return "Três Estrelas";
        }
    }, QUATRO_ESTRELAS {
        @Override
        public String toString() {
            return "Quatro Estrelas";
        }
    }, CINCO_ESTRELAS {
        @Override
        public String toString() {
            return "Cinco Estrelas";
        }
    }
}
