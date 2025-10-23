package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    final char[] maChar = "AÁÀBCDEÉÈFGHIÍÌÏJKLMNOÓÒPQRS TUÚÙÜVWXYZ".toCharArray();
    final char[] alfabetPermutat = permutaAlfabet(maChar);
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        validarClau(clau);
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }   
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        validarClau(clau);
        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }
    
    private void validarClau(String clau) throws ClauNoSuportada {
        if (clau != null && !clau.isEmpty()) {
            throw new ClauNoSuportada("No utilitza clau");
        }
    }
    public char[] permutaAlfabet(char[] alfabet){
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
    
    public String xifraMonoAlfa(String cadena){
        return usaMono(cadena, true);
    }
    
    public String desxifraMonoAlfa(String cadena){
        return usaMono(cadena, false);
    }
    
    public String usaMono(String cadena, boolean xifra){
        String xifrat = "";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                if(xifra){
                    for(int j = 0; j < maChar.length; j++){
                        if(Character.toLowerCase(maChar[j]) == cadena.charAt(i)){
                            xifrat = xifrat + Character.toLowerCase(alfabetPermutat[j]);
                            especial = false;
                            break;
                        }
                    }
                } else {
                    for(int j = 0; j < maChar.length; j++){
                        if(Character.toLowerCase(alfabetPermutat[j]) == cadena.charAt(i)){
                            xifrat = xifrat + Character.toLowerCase(maChar[j]);
                            especial = false;
                            break;
                        }
                    }
                }
            } else if (Character.isUpperCase(cadena.charAt(i))){
                if(xifra){
                    for(int j = 0; j < maChar.length; j++){
                        if(maChar[j] == cadena.charAt(i)){
                            xifrat = xifrat + alfabetPermutat[j];
                            especial = false;
                            break;
                        }  
                    }
                } else {
                    for(int j = 0; j < maChar.length; j++){
                        if(alfabetPermutat[j] == cadena.charAt(i)){
                            xifrat = xifrat + maChar[j];
                            especial = false;
                            break;
                        }  
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