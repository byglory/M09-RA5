import java.util.Random;

public class Polialfabetic {
    static final char[] alfabet = "AÀÁBCÇDEÈÉFGHIÌÍïJKLMNOÒÓPQRSTUÚÙÜVWXYZ".toCharArray();
    private static long clauSecreta = 300;
    private static Random randomNum;
    static String alfabetPermutat;
    

    public static String xifraPoliAlfa(String msg){
        return usaPoliAlfa(msg, true);
    }
    public static String usaPoliAlfa(String msg, boolean bool){
        String xifrat="";
        for (int i = 0; i < msg.length(); i++){  
            permutaAlfabet();
            boolean especial = true; 
            if (Character.isLowerCase(msg.charAt(i))){
                if(bool){
                for(int j = 0; j < alfabet.length;j++){
                    if(Character.toLowerCase(alfabet[j]) == msg.charAt(i)){
                        xifrat = xifrat + Character.toLowerCase(alfabetPermutat.charAt(j));
                        especial = false;
                        break;
                    }
                }
                }else{
                    for(int j = 0; j < alfabet.length;j++){
                    if(Character.toLowerCase(alfabetPermutat.charAt(j)) == msg.charAt(i)){
                        xifrat = xifrat + Character.toLowerCase(alfabet[j]);
                        especial = false;
                        break;
                    }
                }
                }
            }
            else if (Character.isUpperCase(msg.charAt(i))){
                if(bool){
                for(int j = 0; j < alfabet.length;j++){
                    if(alfabet[j] == msg.charAt(i)){
                        xifrat = xifrat + alfabetPermutat.charAt(j);
                        especial = false;
                        break;
                    }  
                }}else{
                    for(int j = 0; j < alfabet.length;j++){
                    if(alfabetPermutat.charAt(j) == msg.charAt(i)){
                        xifrat = xifrat + alfabet[j];
                        especial = false;
                        break;
                    }  
                }
                }
            }
            if (especial){
                xifrat = xifrat + msg.charAt(i);
            }      
        }
        return xifrat;
    
    }
    public static String desxifraPoliAlfa(String msg){
        return usaPoliAlfa(msg, false);
    }
    public static void permutaAlfabet() {
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat = alfabetPermutat + alfabet[(i + randomNum.nextInt(alfabet.length)) % alfabet.length];

        }
    }
    
    public static void initRandom(long clauSecreta){
        randomNum = new Random(clauSecreta);
    }

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Orrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }    
}