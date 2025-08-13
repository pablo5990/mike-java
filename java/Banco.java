import java.util.Scanner;

public class Banco {
    private String titular;
    private String numeroCuenta;
    private double saldo;                   
    


public Banco(String titular, String numeroCuenta, double saldo) {
    this.titular = titular;
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldo;
} 

    public void mostrarInfoCuenta() {
        System.out.println("Titular: " + titular);
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Saldo: " + saldo);
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Deposito exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("la cantidad para el desposito tiene que ser mayor a 0$.");
        }
    }

    

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Fondos insuficientes o cantidad invalida para el retiro.");
        }           
    }
 

    public void transferir(CuentaBancaria cuentaDestino, double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            cuentaDestino.depositar(cantidad);
            System.out.println("Transferencia exitosa. Nuevo saldo: " + saldo);
        }
        else {
            System.out.println("Fondos insuficientes o cantidad invalida para la transferencia.");
        }       
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el nombre del titular: ");
        String titular = scanner.nextLine();
        System.out.print("Ingresa el numero de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingresa el saldo inicial: ");
        double saldo = scanner.nextDouble();
        CuentaBancaria cuenta = new CuentaBancaria(titular, numeroCuenta, saldo);
        cuenta.mostrarCuenta();



        System.out.print("Ingresa la cantidad a depositar: ");
        double cantidadDeposito = scanner.nextDouble();
        cuenta.depositar(cantidadDeposito);

        System.out.print("Ingresa la cantidad a retirar: ");
        double cantidadRetiro = scanner.nextDouble();
        cuenta.retirar(cantidadRetiro);
        cuenta.mostrarCuenta();
        System.out.print("Ingresa el nombre del titular de la cuenta destino: ");   
        scanner.nextLine(); 
        String titularDestino = scanner.nextLine();
        System.out.print("Ingresa el numero de cuenta destino: ");
        String numeroCuentaDestino = scanner.nextLine();
        CuentaBancaria cuentaDestino = new CuentaBancaria(titularDestino, numeroCuentaDestino, 0);
        System.out.print("Ingresa la cantidad a transferir: ");
        double cantidadTransferir = scanner.nextDouble();
        cuenta.transferir(cuentaDestino, cantidadTransferir);
        System.out.println("Informacion de la cuenta destino:");
        cuentaDestino.mostrarCuenta();
        System.out.println("Informacion de la cuenta origen:");
        cuenta.mostrarCuenta(); 

        scanner.close();
    }
     
}