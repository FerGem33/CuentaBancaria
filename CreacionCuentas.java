package Unidad6.Archivos_de_objetos.CuentaBancaria;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class CreacionCuentas
{
    private String nombreArchivo;
    public CreacionCuentas (String nombreArchivo)
    {
        this.nombreArchivo = nombreArchivo;
    }


    /**
     * método crearCuenta
     */
    public void crearCuentas () throws FileNotFoundException , IOException
    {
        File archivoSalida = new File (nombreArchivo);
        FileOutputStream flujoSalida = new FileOutputStream (archivoSalida);
        ObjectOutputStream archivoObjetos = new ObjectOutputStream (flujoSalida);

        Scanner lector = new Scanner (System.in);
        int numCuenta = 1;
        String titular;
        double saldoInicial = 0;

        while (numCuenta > 0)
        {
            while(true){
                try {
                    numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Numero De cuenta o cero para terminar"));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            if(numCuenta > 0)
            {
                titular = JOptionPane.showInputDialog("Nombre del Titular ");
                while(true){
                    try {
                        saldoInicial = Double.parseDouble(JOptionPane.showInputDialog("Saldo Inicial"));
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                Cuenta unaCuenta = new Cuenta (numCuenta, titular,saldoInicial);
                archivoObjetos.writeObject(unaCuenta);
            }
        }
        archivoObjetos.close();
    }

    public void depositar() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        int numCuenta = 1;
        boolean encontro, continuar;
        while (numCuenta > 0)
        {
            numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Numero de la Cuenta o cero para terminar"));

            if(numCuenta > 0)
            {
                encontro = false;
                continuar = true;

                File archivoEntrada = new File (nombreArchivo);
                FileInputStream flujoEntrada = new FileInputStream(archivoEntrada);
                ObjectInputStream archivoObjetos = new ObjectInputStream (flujoEntrada);
                do
                {
                    try
                    {
                        Cuenta unaCuenta =  (Cuenta)archivoObjetos.readObject ();
                        if(numCuenta == unaCuenta.getNumCuenta ())
                        {
                            double monto = Double.parseDouble(JOptionPane.showInputDialog("Cantidad a depositar"));
                            unaCuenta.deposito(monto);
                            encontro = true;
                            continuar = false;
                        }
                    }
                    catch(EOFException e)
                    {
                        continuar = false;
                    }
                }while(continuar);
                if(encontro == false)
                {
                    JOptionPane.showMessageDialog (null, "No se encontro el numero de cuenta " + numCuenta);
                    continuar = false;
                }
                archivoObjetos.close();
            }
        }
    }

    public void retiro () throws FileNotFoundException, IOException, ClassNotFoundException
    {
        int numCuenta = 1;
        boolean encontro, continuar;
        while(numCuenta > 0)
        {
            numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Numero de la cuenta o cero para terminar"));

            if (numCuenta > 0)
            {
                encontro = false;
                continuar = true;

                File archivoEntrada = new File (nombreArchivo);
                FileInputStream flujoEntrada = new FileInputStream(archivoEntrada);
                ObjectInputStream archivoObjetos = new ObjectInputStream (flujoEntrada);
                do
                {
                    try
                    {
                        Cuenta unaCuenta = (Cuenta)archivoObjetos.readObject();
                        if(numCuenta == unaCuenta.getNumCuenta())
                        {
                            double monto = Double.parseDouble(JOptionPane.showInputDialog("Cantidad a retirar "));
                            unaCuenta.retiro(monto);
                            encontro = true;
                            continuar = false;
                        }
                    }
                    catch(EOFException e)
                    {
                        continuar = false;
                    }
                }while (continuar);
                if(encontro == false)
                {
                    JOptionPane.showMessageDialog (null, "No se encontro el numero de cuenta " + numCuenta);
                    continuar = false;
                }
                archivoObjetos.close();
            }
        }
    }

    public void consultarCuentas() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File archivoEntrada = new File (nombreArchivo);
        FileInputStream flujoEntrada = new FileInputStream(archivoEntrada);
        ObjectInputStream archivoObjetos = new ObjectInputStream (flujoEntrada);
        boolean continuar = true;
        do
        {
            try
            {
                Cuenta unaCuenta = (Cuenta)archivoObjetos.readObject();
                JOptionPane.showMessageDialog(null , " Numero de Cuenta " + unaCuenta.getNumCuenta() +
                        "\nTitular "         + unaCuenta.getTitular() +
                        "\nSaldo Inicial "   + unaCuenta.getSaldoInicial() +
                        "\n Saldo Actual "   + unaCuenta.getSaldo());
            }
            catch (EOFException e)
            {
                continuar = false;
            }
        } while (continuar);
        archivoObjetos.close();
    }

    public void buscarCuenta() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File archivoEntrada = new File (nombreArchivo);
        FileInputStream flujoEntrada = new FileInputStream(archivoEntrada);


        int numCuenta = 1;
        boolean encontro, continuar;
        while (numCuenta > 0) {
            numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Numero de la cuenta o cero para terminar"));
            if (numCuenta > 0) {
                ObjectInputStream archivoObjetos = new ObjectInputStream (flujoEntrada);
                continuar = true;
                encontro = false;
                do {
                    try {
                        Cuenta unaCuenta = (Cuenta) archivoObjetos.readObject();
                        if (numCuenta == unaCuenta.getNumCuenta()) {
                            JOptionPane.showInputDialog(null, unaCuenta);
                            encontro = true;
                            continuar = false;
                        } else {
                            continuar = true;
                        }
                    } catch (EOFException e) {
                        continuar = false;
                    }
                } while (continuar);
                if (encontro == false) {
                    JOptionPane.showMessageDialog(null, "No se encontro el numero de cuenta " + numCuenta);
                    continuar = false;
                }
                archivoObjetos.close();
            }
        }
    }
}