import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<CuentaBancaria> lstCuentas = new ArrayList<CuentaBancaria>();
    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args ) throws InterruptedException {
        Cliente cliente1 = new CuentaBancaria(1234, "Juan", "32199901332", "Carrera 20", 20000);
        lstCuentas.add((CuentaBancaria) cliente1);
        mostrarMenu();
    }

    public static void mostrarMenu() throws InterruptedException {
        boolean menu = true;
        do {
            System.out.println("\nBienvenido al Banco Metésta" +
                    "\nElige la opción que deseas: " +
                    "\n1. Agregar Usuario" +
                    "\n2. Mostrar Usuarios Registrados" +
                    "\n3. Ver Saldo de un Usuario" +
                    "\n4. Salir");
            try {
                int opc = teclado.nextInt();
                switch (opc) {
                    case 1 -> {
                        crearUsuario();
                    }
                    case 2 -> {
                        if (lstCuentas.isEmpty()){
                            System.out.println("No hay usuarios registrados\n");
                        } else {
                            verUsuarios();
                        }
                    }
                    case 3 -> {
                        if (lstCuentas.isEmpty()){
                            System.out.println("No hay usuarios registrados\n");
                        } else {
                            System.out.println("Ingrese la cédula del usuario");
                            int id = teclado.nextInt();
                            verSaldo(id);
                        }
                    }
                    case 4 -> {
                        System.out.println("Gracias por usar el programa.");
                        menu = false;
                    }
                }
            } catch (RuntimeException e){
                System.out.println("Vete a la verga, eso no se puede hacer.");
                teclado.nextLine();
            } catch (InterruptedException e) {
                Thread.sleep(1000);
            }
        } while (menu);
    }

    public static void crearUsuario() throws InterruptedException {
        String nombre, telefono, direccion, sucursal;
        int cedula;
        double saldo = 0;
        teclado.nextLine();
        System.out.println("\nIngrese el nombre de Usuario: ");
        nombre = teclado.nextLine();
        System.out.println("Ingrese su cédula: ");
        cedula = teclado.nextInt();
        System.out.println("Ingrese su teléfono: ");
        telefono = teclado.next();
        System.out.println("Ingrese su dirección:");
        direccion = teclado.next();
        boolean suc = true;
        do {
            teclado.nextLine();
            System.out.println("En que sucursal se encuentra?" +
                    "\n1. Norte" +
                    "\n2. Sur" +
                    "\n3. Occidente");
            sucursal = teclado.nextLine().toLowerCase();
            char opc = sucursal.charAt(0);
            if (opc == '1' || opc == 'n') {
                sucursal = "Norte";
                suc = false;
            } else if (opc == '2' || opc == 's') {
                sucursal = "Sur";
                suc = false;
            } else if (opc == '3' || opc == 'o') {
                sucursal = "Occidente";
                suc = false;
            } else {
                System.out.println("Esa no es una opción.");
                teclado.nextLine();
            }
        } while (suc);
        Cliente nuevoCliente = new CuentaBancaria(cedula, nombre, telefono, direccion, saldo);
        lstCuentas.add((CuentaBancaria) nuevoCliente);
    }

    public static void verUsuarios() throws InterruptedException {
        int i = 1;
        for (CuentaBancaria c : lstCuentas) {
            System.out.println(i + ". " + c);
            i++;
        }
        System.out.println("\n");
        Thread.sleep(1000);
    }

    public static void verSaldo(int id) throws InterruptedException {
        boolean mod = true;
        for (CuentaBancaria c : lstCuentas) {
            CuentaBancaria cuenta = c;
            if (id != c.getId()) {
                System.out.println("Este usuario no existe." +
                        "\n");
                Thread.sleep(1000);
                mostrarMenu();
                break;
            } else {
                System.out.println("Saldo = " + c.getSaldo());

                do {
                    System.out.println("Desea debitar o retirar algo?: " +
                            "\n1. Debitar" +
                            "\n2. Retirar" +
                            "\n3. Cancelar");
                    try {
                        int opc = teclado.nextInt();
                        switch (opc) {
                            case 1 -> {
                                System.out.println("Ingrese cuanto desea debitar.");
                                double dinero = teclado.nextDouble();
                                if (c.debitar(dinero) == 0) {
                                    System.out.println("Se ha debitado con éxito." +
                                            "\nEl nuevo saldo es: " + c.getSaldo());
                                    mod = false;
                                } else {
                                    System.out.println("No se ha podido debitar");
                                }
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Vete a la verga, pon un numero.");
                    }
                } while (mod);
                break;
            }
        }

    }


}