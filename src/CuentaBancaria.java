public class CuentaBancaria extends Cliente{
    private static String sucursal[] = {"Norte", "Sur", "Occidente"};
    private double saldo;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int id, String nombre, String telefono, String direccion, double saldo) {
        super(id, nombre, telefono, direccion);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int debitar(double dinero){
        if (dinero < saldo) {
            saldo =- dinero;
            return 0;
        } else {
            System.out.println("El saldo no es suficiente.");
            return 1;
        }
    }

    public void acreditar(double dinero) {
        saldo =+ dinero;
    }

    @Override
    public String toString() {
        return super.toString() +
                "saldo= " + saldo;
    }
}
