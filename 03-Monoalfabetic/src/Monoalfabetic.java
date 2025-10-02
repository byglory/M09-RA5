import java.util.*;
public class Monoalfabetic {
static final char[] maChar = "AÀÁBCDEÈÉFGHIÌÍïJKLMNOÒÓPQRSTUÚÙÜVWXYZ".toCharArray();
static final char[] alfabetPermutat = permutaAlfabet(maChar);
    public static void main(String[] args) {
        String[] xifra = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        String[] cadenaXifra = new String[xifra.length];
        for (int i = 0; i < xifra.length; i++) {
            cadenaXifra[i] = xifraMonoAlfa(xifra[i]);
        }
        System.out.println("Permutacio: " + new String(alfabetPermutat) +"\n");
        System.out.println("Correspondencia:\n" + new String(maChar));
        System.out.println("||||||||||||||||||||||||||||||||||||||");
        System.out.println(alfabetPermutat);
        System.out.println("Exemples:\n");
        for (int i = 0; i < xifra.length; i++) {
            System.out.println(xifra[i]+" -> "+cadenaXifra[i]);            
        }
        for (int i = 0; i < xifra.length; i++) {
            System.out.println(cadenaXifra[i]+" -> "+desxifraMonoAlfa(cadenaXifra[i]));
        }

    }
    public static char[] permutaAlfabet(char[] alfabet){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < maChar.length; i++) {
            list.add(maChar[i]); 
        }
        Collections.shuffle(list);
        char[] resultado = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultado[i] = list.get(i);
        }
        return resultado;
    }
    public static  String xifraMonoAlfa(String cadena){
        String xifrat="";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(Character.toLowerCase(maChar[j]) == cadena.charAt(i)){
                        xifrat = xifrat + Character.toLowerCase(alfabetPermutat[j]);
                        especial = false;
                        break;
                    }
                }
            }
            else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == cadena.charAt(i)){
                        xifrat = xifrat + alfabetPermutat[j];
                        especial = false;
                        break;
                    }  
                }
            }
            if (especial){
                xifrat = xifrat + cadena.charAt(i);
            }      
        }
        return xifrat;
    }
    public static String desxifraMonoAlfa(String cadena){
        String xifrat="";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(Character.toLowerCase(alfabetPermutat[j]) == cadena.charAt(i)){
                        xifrat = xifrat + Character.toLowerCase(maChar[j]);
                        especial = false;
                        break;
                    }
                }
            }
            else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(alfabetPermutat[j] == cadena.charAt(i)){
                        xifrat = xifrat + maChar[j];
                        especial = false;
                        break;
                    }  
                }
            }
            if (especial){
                xifrat = xifrat + cadena.charAt(i);
            }      
        }
        return xifrat;
    }
}