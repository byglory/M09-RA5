package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    final char[] alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private Random randomNum;
    String alfabetPermutat;
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            initRandom(parseClau(clau));
            String resultat = xifraPoliAlfa(msg);
            return new TextXifrat(resultat.getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabetic ha de ser un String convertible a long");
        }
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            initRandom(parseClau(clau));
            String textXifrat = new String(xifrat.getBytes());
            return desxifraPoliAlfa(textXifrat);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabetic ha de ser un String convertible a long");
        }
    }
    
    private long parseClau(String clau) throws ClauNoSuportada {
        try {
            if (clau == null || clau.isEmpty()) {
                throw new ClauNoSuportada("La clau per xifrat Polialfabetic ha de ser un String convertible a long");
            }
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabetic ha de ser un String convertible a long");
        }
    }

    public String xifraPoliAlfa(String msg) { 
        return usaPoliAlfa(msg, true); 
    }
    
    public String desxifraPoliAlfa(String msg) { 
        return usaPoliAlfa(msg, false); 
    }

    public String usaPoliAlfa(String msg, boolean xifrar){
        StringBuilder xifrat = new StringBuilder();
        for (int i = 0; i < msg.length(); i++){  
            permutaAlfabet();
            char c = msg.charAt(i);
            boolean especial = true; 
            
            if (Character.isLowerCase(c)){
                for(int j = 0; j < alfabet.length; j++){
                    if (xifrar) {
                        if(Character.toLowerCase(alfabet[j]) == c){
                            xifrat.append(Character.toLowerCase(alfabetPermutat.charAt(j)));
                            especial = false;
                            break;
                        }
                    } else { 
                        if(Character.toLowerCase(alfabetPermutat.charAt(j)) == c){
                            xifrat.append(Character.toLowerCase(alfabet[j]));
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
                            xifrat.append(alfabetPermutat.charAt(j));
                            especial = false;
                            break;
                        }  
                    } else { 
                        if(alfabetPermutat.charAt(j) == c){
                            xifrat.append(alfabet[j]);
                            especial = false;
                            break;
                        }  
                    }
                }
            }
            if (especial){
                xifrat.append(c);
            }      
        }
        return xifrat.toString();
    }

    public void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) llista.add(c);
        Collections.shuffle(llista, randomNum);
        StringBuilder sb = new StringBuilder();
        for (char c : llista) sb.append(c);
        alfabetPermutat = sb.toString();
    }

    public void initRandom(long clauSecreta){
        randomNum = new Random(clauSecreta);
    }   
}