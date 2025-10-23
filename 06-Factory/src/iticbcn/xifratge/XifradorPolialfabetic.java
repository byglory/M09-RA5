package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic {
    final char[] alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private final long CLAUSECRETA = 8;
    private Random randomNum;
    String alfabetPermutat;
    
    public String xifraPoliAlfa(String msg) { 
        return usaPoliAlfa(msg, true); 
    }
    
    public String desxifraPoliAlfa(String msg) { 
        return usaPoliAlfa(msg, false); 
    }

    public String usaPoliAlfa(String msg, boolean xifrar){
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

    public void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) llista.add(c);
        Collections.shuffle(llista, randomNum);
        StringBuilder sb = new StringBuilder();
        for (char c : llista) sb.append(c);
        alfabetPermutat = sb.toString();
    }

    public void initRandom(long CLAUSECRETA){
        randomNum = new Random(CLAUSECRETA);
    }   
}