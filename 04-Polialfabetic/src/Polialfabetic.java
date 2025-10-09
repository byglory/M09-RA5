import java.util.*;

public class Polialfabetic {
    static final char[] alfabet = "AÀÁBCÇDEÈÉFGHIÌÍïJKLMNOÒÓPQRSTUÚÙÜVWXYZ".toCharArray();
    private static final long CLAUSECRETA = 8;
    private static Random randomNum;
    static String alfabetPermutat;
    
    public static String xifraPoliAlfa(String msg) { return usaPoliAlfa(msg, true); }
    public static String desxifraPoliAlfa(String msg) { return usaPoliAlfa(msg, false); }

    public static String usaPoliAlfa(String msg, boolean xifrar){
        String xifrat = "";
        for (int i = 0; i < msg.length(); i++){  
            permutaAlfabet();
            char c = msg.charAt(i);
            boolean especial = true; 
            if (Character.isLowerCase(c)){
                for(int j = 0; j < alfabet.length; j++){
                    if (xifrar) {
                        if(Character.toLowerCase(alfabet[j]) == c){
                            xifrat += Character.toLowerCase(alfabetPermutat.charAt(j));
                            especial = false;
                            break;
                        }
                    } else { 
                        if(Character.toLowerCase(alfabetPermutat.charAt(j)) == c){
                            xifrat += Character.toLowerCase(alfabet[j]);
                            especial = false;
                            break;
                        }
                    }
                }
            } 
            else if (Character.isUpperCase(c)){
                for(int j = 0; j < alfabet.length; j++){
                    if (xifrar) {
                        if(alfabet[j] == c){
                            xifrat += alfabetPermutat.charAt(j);
                            especial = false;
                            break;
                        }  
                    } else { 
                        if(alfabetPermutat.charAt(j) == c){
                            xifrat += alfabet[j];
                            especial = false;
                            break;
                        }  
                    }
                }
            }
            if (especial){
                xifrat += c;
            }      
        }
        return xifrat;
    }

    public static void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) llista.add(c);
        Collections.shuffle(llista, randomNum);
        StringBuilder sb = new StringBuilder();
        for (char c : llista) sb.append(c);
        alfabetPermutat = sb.toString();
    }

    public static void initRandom(long CLAUSECRETA){
        randomNum = new Random(CLAUSECRETA);
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Orrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAUSECRETA);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAUSECRETA);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }    
}