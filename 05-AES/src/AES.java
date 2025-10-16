import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public static final int MIDA_IV = 16;
    public static final byte[] iv = new byte[MIDA_IV];
    public static final String CLAU = "HolaBonaTarda";
    
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
    public static String desxifraAES(byte[] bMsgXifrat, String password){
        return "holaprofe";
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
