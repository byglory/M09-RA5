import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Hashes {
    int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String combined = pw + salt;
        byte[] hash = md.digest(combined.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}   
    public String getPBKDF2AmbSalt(String pw, String salt) {
    try {
        int iterations = 1000;
        int keyLength = 128; // 16 bytes = 128 bits
        
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 
                                        iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public String forcaBruta(String alg, String hash, String salt) {
    npass = 0; // Resetear contador
    String charset = "abcdefABCDEF1234567890!";
    char[] password = new char[6];
    
    // Probar longitudes de 1 a 6 caracteres
    for (int length = 1; length <= 6; length++) {
        if (tryCombinations(alg, hash, salt, charset, password, 0, length)) {
            return new String(password, 0, length);
        }
    }
    return null;
}

private boolean tryCombinations(String alg, String hash, String salt, 
                               String charset, char[] password, 
                               int position, int length) {
    if (position == length) {
        npass++;
        String candidate = new String(password, 0, length);
        String candidateHash;
        
        if (alg.equals("SHA-512")) {
            candidateHash = getSHA512AmbSalt(candidate, salt);
        } else {
            candidateHash = getPBKDF2AmbSalt(candidate, salt);
        }
        
        return candidateHash.equals(hash);
    }
    
    for (int i = 0; i < charset.length(); i++) {
        password[position] = charset.charAt(i);
        if (tryCombinations(alg, hash, salt, charset, password, position + 1, length)) {
            return true;
        }
    }
        return false;
    }

    public String getInterval(long t1, long t2) {
    long diff = t2 - t1;
    long millis = diff % 1000;
    long seconds = (diff / 1000) % 60;
    long minutes = (diff / (1000 * 60)) % 60;
    long hours = (diff / (1000 * 60 * 60)) % 24;
    long days = diff / (1000 * 60 * 60 * 24);
    
    return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                        days, hours, minutes, seconds, millis);
}



    
    public static void main(String[] args) throws Exception {
    String salt = "qpoweiruañslkdfjz";
    String pw = "aaabF!";
    Hashes h = new Hashes();
    String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
    h.getPBKDF2AmbSalt(pw, salt) };
    String pwTrobat = null;
    String[] algorismes = {"SHA-512", "PBKDF2"};
    for(int i=0; i< aHashes.length; i++){
        System.out.printf("==============================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("------------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");

        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();

        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("------------------------------\n\n");
    }
}
}
