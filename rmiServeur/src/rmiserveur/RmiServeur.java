/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rmiserveur;

import entities.Coordinateur;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author hp
 */
public class RmiServeur {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws RemoteException{
       
        try {
            CoordinateurInt od = new CoordinateurImpl();
            Registry rgsty = LocateRegistry.createRegistry(7001);
            System.out.println(od.toString());
            rgsty.rebind("coordinateur", od);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
