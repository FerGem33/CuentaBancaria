package Unidad6.Archivos_de_objetos.CuentaBancaria;
import java.util.InputMismatchException;
import java.util.*;
import java.io.IOException;

public class ManejoDeCuentas{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la ruta del archivo.");
        CreacionCuentas cc = new CreacionCuentas(sc.next());

        String menu = """
                
                Menú:
                0.Terminar
                1.Crear cuentas
                2.Depositar
                3.Retirar
                4.Consultar cuentas
                5.Consultar una cuenta
                
                """;
        int o;
        do{
            while(true) {
                try {
                    System.out.println(menu);
                    o = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Debes un número entero.");
                    sc.next();
                }
            }
            switch(o) {
                case 1-> cc.crearCuentas();
                case 2-> cc.depositar();
                case 3 -> cc.retiro();
                case 4 -> cc.consultarCuentas();
                case 5 -> cc.buscarCuenta();
            }
        } while(o != 0);
    }
}