package iticbcn.xifratge;

public class XifradorRotX {
    final char[] miChar = "aáàbcdeéèfghiíìïjklmnóòoôpqrstuúùüvwxyz".toCharArray();
    final char[] maChar = new String(miChar).toUpperCase().toCharArray();
    
    public String usaRotX(String cadena, boolean mas, int desplacament){
        String xifrat = "";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < miChar.length; j++){
                    if(miChar[j] == cadena.charAt(i)){
                        if(mas){
                            xifrat = xifrat + miChar[(j + desplacament) % miChar.length];
                            especial = false;
                            break;
                        } else {
                            xifrat = xifrat + miChar[(j - desplacament + miChar.length) % miChar.length];
                            especial = false;
                            break;
                        }
                    }
                }
            } else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length; j++){
                    if(maChar[j] == cadena.charAt(i)){
                        if(mas){
                            xifrat = xifrat + maChar[(j + desplacament) % maChar.length];
                            especial = false;
                            break;
                        } else {
                            xifrat = xifrat + maChar[(j - desplacament + maChar.length) % maChar.length];
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
    
    public void forcaBrutaRotX(String cadenaXifrada){
        for (int i = 0; i <= miChar.length; i++) {
            System.out.println("(" + i + ")->" + desxifraRotX(cadenaXifrada, i));
        }
    }
    
    public String xifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, true, desplacament);
    }
   
    public String desxifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, false, desplacament);
    }
}