package Unidad6.Archivos_de_objetos.CuentaBancaria;
import java.io.*;

public class Cuenta implements Serializable{
    private int num;
    private String titular;
    private double saldo0, saldo;

    public Cuenta(int num, String titular, double saldo0) {
        this.num = num;
        this.titular = titular;
        this.saldo0 = saldo0;
        this.saldo = saldo0;
    }
    public int getNumCuenta() {
        return num;
    }
    public void setNumero(int num) {
        this.num = num;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public double getSaldoInicial(){
        return saldo0;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void deposito(double monto){
        saldo += monto;
    }
    public void retiro(double monto){
        if(saldo >= monto){
            saldo -= monto;
            System.out.println("Retiro realizado exitosamente.");
        } else{
            System.out.println("Saldo insuficiente.");
        }
    }

    public String toString() {
        return ("Número de cuenta " + num + "\nTitular " + titular + "\nSaldo inicial $" + saldo0 + "\nSaldo actual $" + saldo);
    }
}