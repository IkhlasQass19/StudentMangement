/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmiserveur;

import entities.Coordinateur;
import entities.Enseignant;
import entities.Etudiant;
import entities.Module;
import entities.Note;
import entities.Response;
import java.rmi.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mimif
 */
public class CoordinateurImpl extends UnicastRemoteObject implements CoordinateurInt {

    public CoordinateurImpl() throws RemoteException {
    }

    @PersistenceContext(unitName = "rmiServeurPU")
    private EntityManager em;

    //@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //inscrire coordinateur
    @Override
    public void create(Coordinateur c) throws RemoteException {
        em.persist(c);
    }

    //releve de note d'un etudiant
    public List<Object[]> getEtudiantNotesBySem(int id_etd, int sem) throws RemoteException {
        String sqlQuery = "SELECT * "
                + "FROM etudiant e "
                + "JOIN note n ON e.id = n.id_etd "
                + "JOIN module m ON n.idmod = m.id "
                + "WHERE e.id = :id_etd AND n.semester = :sem";

        Query query = em.createNativeQuery(sqlQuery);
        query.setParameter("idetd", id_etd);
        query.setParameter("sem", sem);

        return query.getResultList();
    }

    //liste des coordinateurs
    @Override
    public List<Coordinateur> findAll() throws RemoteException {
        TypedQuery<Coordinateur> query = em.createQuery("SELECT c FROM Coordinateur c", Coordinateur.class);
        return query.getResultList();
    }

    public int EditNote(int idEtudiant, int idmod, double note) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE note SET note = ? WHERE idetud = ? AND idMod=?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, note);
            statement.setLong(2, idEtudiant);
            statement.setLong(3, idmod);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int addEtudiant(Etudiant etudiant) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO etudiant (id, age, cin, date_naissance, email, nom, prenom, role, sem, telephone) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, etudiant.getAge());
            statement.setString(2, etudiant.getCin());
            statement.setDate(3, (Date) etudiant.getDateNaissance());
            statement.setString(4, etudiant.getEmail());
            statement.setString(5, etudiant.getNom());
            statement.setString(6, etudiant.getPrenom());
            statement.setString(7, etudiant.getRole());
            statement.setInt(8, etudiant.getSem());
            statement.setString(9, etudiant.getTelephone());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                String userQuery = "INSERT INTO user (id, login, password, role) VALUES (null, ?, '1234', '3')";
                PreparedStatement userStatement = connection.prepareStatement(userQuery);
                userStatement.setString(1, etudiant.getCin());
                userStatement.executeUpdate();
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Etudiant> allEtudiants() throws RemoteException {
        List<Etudiant> etudiants = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM etudiant";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setAge(rs.getInt("age"));
                etudiant.setCin(rs.getString("cin"));
                etudiant.setDateNaissance(rs.getDate("date_naissance"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setRole(rs.getString("role"));
                etudiant.setSem(rs.getInt("sem"));
                etudiant.setTelephone(rs.getString("telephone"));
                etudiants.add(etudiant);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;

    }

    public int addEnseignant(Enseignant enseignant) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO enseignant (id, age, cin,date_naissance,email,nom,prenom,role,telephone) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, null);
            statement.setInt(2, enseignant.getAge());
            statement.setString(3, enseignant.getCin());
            statement.setDate(4, (Date) enseignant.getDateNaissance());
            statement.setString(5, enseignant.getEmail());
            statement.setString(6, enseignant.getNom());
            statement.setString(7, enseignant.getPrenom());
            statement.setString(8, enseignant.getRole());
            statement.setString(9, enseignant.getTelephone());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                String userQuery = "INSERT INTO user (id, login, password, role) VALUES (null, ?, '1234', '2')";
                PreparedStatement userStatement = connection.prepareStatement(userQuery);
                userStatement.setString(1, enseignant.getCin());
                userStatement.executeUpdate();
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Enseignant> allEnseignants() throws RemoteException {
        List<Enseignant> enseignants = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM enseignant";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setAge(rs.getInt("age"));
                enseignant.setCin(rs.getString("cin"));
                enseignant.setDateNaissance(rs.getDate("date_naissance"));
                enseignant.setEmail(rs.getString("email"));
                enseignant.setId(rs.getInt("id"));
                enseignant.setNom(rs.getString("nom"));
                enseignant.setPrenom(rs.getString("prenom"));
                enseignant.setRole(rs.getString("role"));
                enseignant.setTelephone(rs.getString("telephone"));
                enseignants.add(enseignant);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enseignants;

    }

    public int UpdateEtudiant(Etudiant etudiant) throws RemoteException {

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String updateQuery = "UPDATE etudiant SET age=?, cin=?, date_naissance=?, email=?, nom=?, prenom=?, role=?, sem=?, telephone=? WHERE cin=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, etudiant.getAge());
            updateStatement.setString(2, etudiant.getCin());
            updateStatement.setDate(3, (Date) etudiant.getDateNaissance());
            updateStatement.setString(4, etudiant.getEmail());
            updateStatement.setString(5, etudiant.getNom());
            updateStatement.setString(6, etudiant.getPrenom());
            updateStatement.setString(7, etudiant.getRole());
            updateStatement.setInt(8, etudiant.getSem());
            updateStatement.setString(9, etudiant.getTelephone());
            updateStatement.setString(10, etudiant.getCin());
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int UpdateEnseignant(Enseignant enseignant) throws RemoteException {

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String updateQuery = "UPDATE enseignant SET age=?, cin=?, date_naissance=?, email=?, nom=?, prenom=?, role=?, telephone=? WHERE cin=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, enseignant.getAge());
            updateStatement.setString(2, enseignant.getCin());
            updateStatement.setDate(3, (Date) enseignant.getDateNaissance());
            updateStatement.setString(4, enseignant.getEmail());
            updateStatement.setString(5, enseignant.getNom());
            updateStatement.setString(6, enseignant.getPrenom());
            updateStatement.setString(7, enseignant.getRole());
            updateStatement.setString(8, enseignant.getTelephone());
            updateStatement.setString(9, enseignant.getCin());
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void deleteEnseignantByCin(String cin) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String deleteQuery = "DELETE FROM enseignant WHERE cin=?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, cin);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                String userQuery = "DELETE FROM  WHERE  login= ?)";
                PreparedStatement userStatement = connection.prepareStatement(userQuery);
                userStatement.setString(1, cin);
                userStatement.executeUpdate();
                System.out.println("Enseignant deleted successfully!");
            } else {
                // No rows deleted
                System.out.println("No rows deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions
        }
    }

    public void deleteEtudiantByCin(String cin) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String deleteQuery = "DELETE FROM etudiant WHERE cin=?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, cin);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                String userQuery = "DELETE FROM  WHERE  login= ?)";
                PreparedStatement userStatement = connection.prepareStatement(userQuery);
                userStatement.setString(1, cin);
                userStatement.executeUpdate();
                System.out.println("Enseignant deleted successfully!");
            } else {
                // No rows deleted
                System.out.println("No rows deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions
        }
    }

    public String[][] getProfs() throws RemoteException {

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String table[][] = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String countQuery = "SELECT COUNT(*) AS total_rows FROM enseignant";
            PreparedStatement countStatement = connection.prepareStatement(countQuery);
            ResultSet countResult = countStatement.executeQuery();
            countResult.next(); // Avance vers la première ligne du résultat
            int rowCount = countResult.getInt("total_rows");

            String selectQuery = "SELECT enseignant.cin, enseignant.nom, enseignant.prenom FROM enseignant";
            preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rowCount);
            table = new String[rowCount][3];
            int i = 0;
            while (rs.next()) {
                table[i][0] = rs.getString("cin");
                table[i][1] = rs.getString("nom");
                table[i][2] = rs.getString("prenom");
                i++;
            }
            rs.close();
            preparedStatement.close();

            return table;

        } catch (SQLException ex) {
            Logger.getLogger(CoordinateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;

    }

    public void insertModule(String semestre, String intervenant, String idprof, String nom) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);

            // Complétez avec votre requête d'insertion
            String query = "INSERT INTO ds.module (id, semestre,idprof,intervenant, nom) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, null); // Remplacez moduleId par la valeur de l'ID du module
            preparedStatement.setString(2, semestre);
            preparedStatement.setString(3, idprof);
            preparedStatement.setString(4, intervenant); // Remplacez intervenant par la valeur de l'intervenant
            preparedStatement.setString(5, nom); // Remplacez nom par la valeur du nom du module
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Module> allModules() throws RemoteException {
        List<Module> Modules = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM module";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setEtablissement(rs.getString("semestre"));
                module.setIntervenant(rs.getString("intervenant"));
                module.setIdprof(rs.getString("idprof"));
                module.setLibelleDuModule(rs.getString("nom"));
                Modules.add(module);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Modules;
        }

        return Modules;

    }

    public void deleteModule(int cin) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String deleteQuery = "DELETE FROM module WHERE id=?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, cin);

            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Enseignant deleted successfully!");
            } else {
                // No rows deleted
                System.out.println("No rows deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions
        }
    }

    public int UpdateModule(String semestre, String intervenant, String idprof, String nom, int id) throws RemoteException {

        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url, username, password);
            String updateQuery = "UPDATE module SET semestre=?, intervenant=?, nom=? ,idprof=? WHERE id=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, semestre); // Remplacez moduleId par la valeur de l'ID du module
            updateStatement.setString(2, intervenant);
            updateStatement.setString(3, nom);
            updateStatement.setString(4, idprof); // Remplacez intervenant par la valeur de l'intervenant
            updateStatement.setInt(5, id); // Remplacez nom par la valeur du nom du module

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie
                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Response> allResponse(String idprof) throws RemoteException {
        List<Response> Responses = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "Select etudiant.nom as nom ,etudiant.id as idetud,etudiant.prenom as prenom,etudiant.sem as sem,module.nom as module,module.idprof as idprof,\n"
                    + "module.id as idmod from module,etudiant,enseignant where\n"
                    + " enseignant.cin=module.idprof and etudiant.sem=module.semestre and enseignant.cin=" + idprof;
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Response reponse = new Response();
                reponse.setIdetud(String.valueOf(rs.getInt("idetud")));
                reponse.setIdmod(String.valueOf(rs.getInt("idmod")));
                reponse.setIdprof(rs.getString("idprof"));
                reponse.setModule(rs.getString("module"));
                reponse.setNom(rs.getString("nom"));
                reponse.setPrenom(rs.getString("prenom"));
                reponse.setSem(rs.getString("sem"));

                Responses.add(reponse);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Responses;
        }

        return Responses;

    }

    public List<Module> Modules(String cin) throws RemoteException {
        List<Module> Modules = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM module where idprof=" + cin;
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setEtablissement(rs.getString("semestre"));
                module.setIntervenant(rs.getString("intervenant"));
                module.setIdprof(rs.getString("idprof"));
                module.setLibelleDuModule(rs.getString("nom"));
                Modules.add(module);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Modules;
        }

        return Modules;

    }

    public int addNote(Note note) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO note (id, idens, idetud, idMod, note) VALUES (?, ?, ?, ?,?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, null);
            statement.setInt(2, note.getIdEns());
            statement.setInt(3, note.getIdEtd());
            statement.setInt(4, note.getIdMod());
            statement.setDouble(5, note.getNote());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Mise à jour réussie

                return 0;
            } else {
                // Aucune ligne mise à jour
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public double getNote(int idetud) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/ds";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        double note=0;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT count(note) as total_rows  FROM note where idetud=" + idetud;
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next(); // Avance vers la première ligne du résultat
            int rowCount = rs.getInt("total_rows");
            if (rowCount == 0) {
                note = rowCount;
            }
            else{
            query = "SELECT note   FROM note where idetud=" + idetud;
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
             while (rs.next()) {
                note = rs.getDouble("note");
               
            }
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return note;
        }

        return note;

    }
}
