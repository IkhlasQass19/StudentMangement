package client;

import Remote.UserFacadeRemote;
import entities.Etudiant;
import entities.User;
import java.net.MalformedURLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import rmiserveur.CoordinateurInt;
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // Configure JNDI properties
        /*   Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        // Replace "hostname" and "port" with the actual values
        
        // Create the initial context
        Context context = new InitialContext(properties);
        
        // Lookup the remote EJB
        UserFacadeRemote userFacade = (UserFacadeRemote) context.lookup("ejb:/SR/User!Remote.UserFacadeRemote");
        // Replace "SR" with the actual deployment name of the EJB
        
        // Invoke a method on the remote EJB
        User user = userFacade.signIn("123", "1234");
        
        // Process the result
        if (user != null) {
        System.out.println(user.getLogin());
        } else {
        System.out.println("Invalid credentials");
        }
        
        //rmi test :
         CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");

            // Invoke remote methods on the server
            List<Etudiant> etudiants = stub.allEtudiants();
            System.out.println("Total etudiants: " + etudiants.size());*/
            CoordinateurInt stub = (CoordinateurInt) Naming.lookup("rmi://localhost:7001/coordinateur");
        String[][] profData = new String[stub.getProfs().length][3];

    }
}
