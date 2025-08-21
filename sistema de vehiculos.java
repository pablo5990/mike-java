public class Main {

    static class Vehiculo {
        private String marca;
        private String modelo;
        private int velocidadMaxima;

        public Vehiculo(String marca, String modelo, int velocidadMaxima) {
            this.marca = marca;
            this.modelo = modelo;
            this.velocidadMaxima = velocidadMaxima;
        }

        public String getMarca() { return marca; }
        public void setMarca(String marca) { this.marca = marca; }

        public String getModelo() { return modelo; }
        public void setModelo(String modelo) { this.modelo = modelo; }

        public int getVelocidadMaxima() { return velocidadMaxima; }
        public void setVelocidadMaxima(int velocidadMaxima) { this.velocidadMaxima = velocidadMaxima; }

        public void mostrarInfo() {
            System.out.println("Vehículo: " + marca + " " + modelo +
                               " | Velocidad Máxima: " + velocidadMaxima + " km/h");
        }

        public void acelerar() {
            System.out.println("El vehículo acelera de forma genérica...");
        }
    }

    static class Auto extends Vehiculo {
        public Auto(String marca, String modelo, int velocidadMaxima) {
            super(marca, modelo, velocidadMaxima);
        }

        @Override
        public void acelerar() {
            System.out.println("El auto acelera suavemente en carretera ");
        }
    }

    static class Moto extends Vehiculo {
        public Moto(String marca, String modelo, int velocidadMaxima) {
            super(marca, modelo, velocidadMaxima);
        }

        @Override
        public void acelerar() {
            System.out.println("La moto acelera rápidamente entre el tráfico ");
        }
    }

    public static void main(String[] args) {

        Vehiculo vehiculoGenerico = new Vehiculo("Genérico", "X", 150);

        Auto auto = new Auto("Toyota", "Corolla", 200);
        Moto moto = new Moto("Yamaha", "R1", 300);

        Vehiculo[] listaVehiculos = {vehiculoGenerico, auto, moto};

        for (Vehiculo v : listaVehiculos) {
            v.mostrarInfo();
            v.acelerar();
            System.out.println("----");
        }
    }
}
