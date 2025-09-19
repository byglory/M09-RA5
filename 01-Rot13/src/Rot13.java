public class Rot13 {
     static String lletresMin = "aàábcdeèéfghiìíïjklmnoóòpqrstuúùüvwxyz";
     static String lletresMaj = lletresMin.toUpperCase();
     static char[] miChar = lletresMin.toCharArray();
     static char[] maChar = lletresMaj.toCharArray();
    public static void main(String[] args) {
        int largo = args.length;
        if (largo <= 0 ){
            return;
        }
        System.out.println("Xifrat\n---------");
        for (String encriptame : args){
            System.out.println(encriptame + " => " + xifraRot13(encriptame));
        }
        System.out.println("Desxifrat\n---------");
        for (String desencriptame : args){
            System.out.println(xifraRot13(desencriptame) + " => " + desxifraRot13(xifraRot13(desencriptame)));
        }
    }
    
    public static String xifraRot13(String texto) {
        String xifrat="";
        for (int i = 0; i < texto.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(texto.charAt(i))){
                for(int j = 0; j < miChar.length;j++){
                    if(miChar[j] == texto.charAt(i)){
                        xifrat = xifrat + miChar[(j + 13) % miChar.length];
                        especial = false;
                        break;
                    }
                }
            }
            else if (Character.isUpperCase(texto.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == texto.charAt(i)){
                        xifrat = xifrat + maChar[(j + 13) % maChar.length];
                        especial = false;
                        break;
                    }  
                }
            }
            else if (especial){
                xifrat = xifrat + texto.charAt(i);
            }      
        }
        return xifrat;
    }

   public static String desxifraRot13(String texto) {
        String desxifrat="";
        for (int i = 0; i < texto.length(); i++){   
            boolean especial = true;
            if (Character.isLowerCase(texto.charAt(i))){
                for(int j = 0; j < miChar.length;j++){
                    if(miChar[j] == texto.charAt(i)){
                        desxifrat = desxifrat + miChar[(j - 13 + miChar.length) % miChar.length];
                        especial = false;
                        break;
                    }
                    
                }
            }
            if (Character.isUpperCase(texto.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == texto.charAt(i)){
                        desxifrat = desxifrat + maChar[(j - 13 + maChar.length) % maChar.length];
                        especial = false;
                        break;
                    }
                    
                }
            }
            else if (especial){
                desxifrat = desxifrat + texto.charAt(i);
            }   
        }
        return desxifrat;
        

    
        
   }
}


