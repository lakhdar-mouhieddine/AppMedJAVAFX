import java.io.*;
import java.util.HashMap;

public class App {
    static private HashMap<String, Orthophoniste> Orthophonistes = new HashMap<String, Orthophoniste>();
    static private Orthophoniste currentOrthophoniste;

    public App() {
        Orthophonistes = new HashMap<String, Orthophoniste>();
    }

    public static boolean Inscription(Orthophoniste utilisateur) {
        if (!Orthophonistes.containsKey(utilisateur.getEmail())) {
            Orthophonistes.put(utilisateur.getEmail(), utilisateur);
            currentOrthophoniste = utilisateur; // Set the current orthophoniste
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<String, Orthophoniste> getOrthophonistes() {
        return Orthophonistes;
    }

    public static Orthophoniste Connexion(String email, String motdepasse) {
        if (Orthophonistes.containsKey(email)) {
            Orthophoniste utilisateur = Orthophonistes.get(email);
            if (utilisateur.getMotDePasse().equals(motdepasse)) {
                currentOrthophoniste = utilisateur;
                return utilisateur;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public static void sauvegarderOrthophonistes() {
        try (FileOutputStream fos = new FileOutputStream("FichierOrthophonistes.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Orthophonistes);
            System.out.println("Utilisateurs sauvegardés avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void chargerOrthophonistes() {
        try (FileInputStream fis = new FileInputStream("FichierOrthophonistes.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Orthophonistes = (HashMap<String, Orthophoniste>) ois.readObject();
            System.out.println("Utilisateurs chargés avec succès.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    }


    public static Orthophoniste getCurrentOrthophoniste() {
        return currentOrthophoniste; // Add this method
    }
}