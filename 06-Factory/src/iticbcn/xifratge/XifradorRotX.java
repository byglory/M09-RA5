package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {  // <- Añadir "implements Xifrador"
    final char[] miChar = "aáàbcdeéèfghiíìïjklmnóòoôpqrstuúùüvwxyz".toCharArray();
    final char[] maChar = new String(miChar).toUpperCase().toCharArray();
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int desplacament = parseClau(clau);
            String resultat = xifraRotX(msg, desplacament);
            return new TextXifrat(resultat.getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en xifrar: " + e.getMessage());
        }
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int desplacament = parseClau(clau);
            String textXifrat = new String(xifrat.getBytes());
            return desxifraRotX(textXifrat, desplacament);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en desxifrar: " + e.getMessage());
        }
    }
    
    private int parseClau(String clau) throws ClauNoSuportada {
        try {
            if (clau == null || clau.isEmpty()) {
                throw new ClauNoSuportada("Clau buida");
            }
            int desplacament = Integer.parseInt(clau);
            if (desplacament < 0 || desplacament >= miChar.length) {
                throw new ClauNoSuportada("Desplaçament fora de rang");
            }
            return desplacament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau ha de ser un número entre 0 i " + (miChar.length - 1));
        }
    }
    
    private String usaRotX(String cadena, boolean mas, int desplacament){
        StringBuilder xifrat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            char currentChar = cadena.charAt(i);
            
            if (Character.isLowerCase(currentChar)){
                for(int j = 0; j < miChar.length; j++){
                    if(miChar[j] == currentChar){
                        if(mas){
                            xifrat.append(miChar[(j + desplacament) % miChar.length]);
                        } else {
                            xifrat.append(miChar[(j - desplacament + miChar.length) % miChar.length]);
                        }
                        especial = false;
                        break;
                    }
                }
            } else if (Character.isUpperCase(currentChar)){
                for(int j = 0; j < maChar.length; j++){
                    if(maChar[j] == currentChar){
                        if(mas){
                            xifrat.append(maChar[(j + desplacament) % maChar.length]);
                        } else {
                            xifrat.append(maChar[(j - desplacament + maChar.length) % maChar.length]);
                        }
                        especial = false;
                        break;
                    }  
                }
            }
            if (especial){
                xifrat.append(currentChar);
            }      
        }
        return xifrat.toString();
    }
    
    public void forcaBrutaRotX(String cadenaXifrada){
        for (int i = 0; i < miChar.length; i++) {
            System.out.println("(" + i + ")->" + desxifraRotX(cadenaXifrada, i));
        }
    }
    
    private String xifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, true, desplacament);
    }
   
    private String desxifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, false, desplacament);
    }
}