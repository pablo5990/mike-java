public class CuentaBancaria {
    String titular;
    String numeroCuenta;
    double saldo;

    public CuentaBancaria(String titular, String numeroCuenta, double saldoInicial) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }
    public void mostrarCuenta() {
        System.out.println("Titular: " + titular);
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo actual: $" + saldo);
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito exitoso de $" + cantidad + ". Nuevo saldo: $" + saldo);
        } else {
            System.out.println("La cantidad a depositar debe ser positiva.");
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Retiro exitoso de $" + cantidad + ". Nuevo saldo: $" + saldo);
        } else if (cantidad > saldo) {
            System.out.println("Fondos insuficientes para retirar $" + cantidad);
        } else {
            System.out.println("La cantidad a retirar debe ser positiva.");
        }
    }

    public void transferir(CuentaBancaria destino, double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            destino.saldo += cantidad;
            System.out.println("Transferencia de $" + cantidad + " a " + destino.titular + " realizada con éxito.");
            System.out.println("Nuevo saldo de " + titular + ": $" + saldo);
        } else {
            System.out.println("Transferencia fallida. Verifica el monto y tu saldo disponible.");
        }
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new CuentaBancaria("Juan Pablo", "123456789", 5000);
        CuentaBancaria cuenta2 = new CuentaBancaria("Laura Gómez", "987654321", 3000);

        System.out.println("Cuenta 1:");
        cuenta1.mostrarCuenta();
        System.out.println("\nCuenta 2:");
        cuenta2.mostrarCuenta();

        System.out.println("\n--- Operaciones ---");
        cuenta1.depositar(1000);
        cuenta1.retirar(2000);
        cuenta1.transferir(cuenta2, 1500);

        System.out.println("\n--- Estado final ---");
        cuenta1.mostrarCuenta();
        System.out.println();
        cuenta2.mostrarCuenta();
    }
}