package Unidad6.Archivos_de_objetos.CuentaBancaria;
import java.io.*;
import java.util.*;

public class CreacionCuentas implements Serializable{
    private final String archivo;

    public CreacionCuentas(String archivo){
        this.archivo = archivo;
    }
    public int readInt(Scanner sc, String text){
        while(true){
            try{
                System.out.println(text);
                return sc.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Debe introducir un entero.");
                sc.next();
            }
        }
    }
    public double readDouble(Scanner sc, String text){
        while(true){
            try{
                System.out.println(text);
                return sc.nextDouble();
            } catch(InputMismatchException e){
                System.out.println("Debe introducir un número.");
                sc.next();
            }
        }
    }
    public void crearCuentas() throws IOException{

        File f = new File(archivo);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Scanner sc = new Scanner(System.in);

        int num;
        String titular;
        double saldo;
        Cuenta cuenta;
        do{
            num = readInt(sc, "Número de cuenta, [0] para terminar.");
            if(num == 0){
                break;
            }
            System.out.println("Titular de la cuenta.");
            titular = sc.next();
            saldo = readDouble(sc, "Saldo inicial.");
            cuenta = new Cuenta(num, titular, saldo);
            oos.writeObject(cuenta);
        } while(true);
        oos.close();
    }
    public void actualizar(ArrayList<Cuenta> arr) throws IOException{
        File f = new File(archivo);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Cuenta cuenta: arr){
            oos.writeObject(cuenta);
        }
        oos.close();
    }
    public void depositar() throws IOException, ClassNotFoundException{
        File f = new File(archivo);
        Scanner sc = new Scanner(System.in);

        do{
            int num = readInt(sc, "Número de cuenta a depositar, [0] para terminar.");
            if(num == 0){
                break;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Cuenta> cuentas = new ArrayList<>();

            boolean continuar = true, encontrado = false;
            do{
                try{
                    Cuenta cuenta = (Cuenta) ois.readObject();
                    if(num == cuenta.getNumero()){
                        double monto = readDouble(sc, "Monto a depositar.");
                        cuenta.depositar(monto);
                        encontrado = true;
                        System.out.println("Depósito realizado exitosamente.");
                    }
                    cuentas.add(cuenta);
                } catch(EOFException e){
                    continuar = false;
                }
            } while(continuar);
            ois.close();
            actualizar(cuentas);

            if(!encontrado){
                System.out.println("No se encontró la cuenta.");
            }
        }while(true);
    }
    public void retirar() throws IOException, ClassNotFoundException {
        File f = new File(archivo);
        Scanner sc = new Scanner(System.in);

        do{
            int num = readInt(sc, "Número de cuenta a retirar, [0] para terminar.");
            if(num == 0){
                break;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Cuenta> cuentas = new ArrayList<>();

            boolean continuar = true, encontrado = false;
            do{
                try{
                    Cuenta cuenta = (Cuenta) ois.readObject();
                    if(num == cuenta.getNumero()){
                        double monto = readDouble(sc, "Monto a retirar.");
                        cuenta.retirar(monto);
                        encontrado = true;
                    }
                    cuentas.add(cuenta);
                } catch(EOFException e){
                    continuar = false;
                }
            } while(continuar);
            ois.close();
            actualizar(cuentas);

            if(!encontrado){
                System.out.println("No se encontró la cuenta.");
            }
        }while(true);
    }
    public void consultarCuentas() throws IOException, ClassNotFoundException{
        File f = new File(archivo);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        boolean continuar = true;
        do{
            try{
                Cuenta cuenta = (Cuenta) ois.readObject();
                System.out.println("\n" + cuenta.toString());
            } catch(EOFException e){
                continuar = false;
            }
        } while(continuar);
    }
    public void buscarCuenta() throws IOException, ClassNotFoundException{
        File f = new File(archivo);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Scanner sc = new Scanner(System.in);
        int num = 1;

        do{
            num = readInt(sc,"Cuenta a consultar, [0] para terminar.");
            if(num == 0){
                break;
            }
            boolean continuar = true, encontrado = false;

            do {
                try {
                    Cuenta cuenta = (Cuenta) ois.readObject();
                    if (num == cuenta.getNumero()) {
                        System.out.println("\n" + cuenta.toString());
                        encontrado = true;
                    }
                } catch (EOFException e) {
                    continuar = false;
                }
            } while (continuar);
            if(!encontrado) {
                System.out.println("No se encontró la cuenta.");
            }
        } while(true);
        ois.close();
    }
}