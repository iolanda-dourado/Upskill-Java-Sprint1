package enums;

public enum CompanhiaAerea {
    TAP {
        @Override
        public String toString() {
            return "TAP";
        }
    }, RYANAIR{
        @Override
        public String toString() {
            return "RyanAir";
        }
    }, EASYJET {
        @Override
        public String toString() {
            return "EasyJet";
        }
    }, LATAM {
        @Override
        public String toString() {
            return "LATAM";
        }
    }, AZUL{
        @Override
        public String toString() {
            return "Linhas Azul";
        }
    }
}
