import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    public static IvParameterSpec generaIvParameterSpec(){
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static byte[] xifraAES(String msg ,String clau) throws Exception{
        //Obtenir els bytes de l'String
        byte[] bytes = msg.getBytes("UTF-8");
        // Genera IvParameterSpec
        IvParameterSpec ivSpec = generaIvParameterSpec();        
        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(hash, ALGORISME_XIFRAT);

        // Encrypt. 
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bytes);
        
        // Combinar IV 1 part xifrada.
        byte[] resultat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);
        // return iv+msgxifrat
        return resultat;
    }
    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        //Extreure 1'IV.
        byte[] ivRebut = new byte[MIDA_IV];
        System.arraycopy(bMsgXifrat, 0, ivRebut, 0, MIDA_IV);
        
        //Extreure la part xifrada.
        byte[] partXifrada = new byte[bMsgXifrat.length - MIDA_IV];
        System.arraycopy(bMsgXifrat, MIDA_IV, partXifrada, 0, partXifrada.length);
        
        //Fer hash de la clau (password)
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] passwordHash = digest.digest(password.getBytes("UTF-8"));
        
        //Desxifrar.
        SecretKeySpec secretKey = new SecretKeySpec(passwordHash, ALGORISME_XIFRAT);
        IvParameterSpec ivSpec = new IvParameterSpec(ivRebut);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] bytesDesxifrats = cipher.doFinal(partXifrada);
        
        //return String desxifrat
        return new String(bytesDesxifrats, "UTF-8");
    }
    public static void main(String[] args) {
    String msgs[] = {"Lorem ipsum dicet",
    "Hola Andrés cómo está tu cuñado",
    "Agora lila Ótto"};

    for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i];
        
        byte[] bXifrats = null;
        String desxifrat = "";
        try {
            bXifrats = xifraAES(msg, CLAU);
            desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
        }
        System.out.println("---");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}
}
