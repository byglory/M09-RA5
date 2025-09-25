public class RotX {
    final static String lletresMin = "aàábcdeèéfghiìíïjklmnoóòpqrstuúùüvwxyz";
    final static String lletresMaj = lletresMin.toUpperCase();
    final static char[] miChar = lletresMin.toCharArray();
    final static char[] maChar = lletresMaj.toCharArray();
    public static void main(String[] args) {
        String[] xifra = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        int[] desplacament = {0,2,4,6};
        int largo = xifra.length;
        if (largo <= 0 || desplacament.length <= 0 ){
            return;
        }
        System.out.println("Xifrat\n---------");
        for (int i = 0; i<=largo; i++){
            System.out.println(xifraRotX(xifra[i], desplacament[i]));
        }
        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i<=largo; i++){
            System.out.println(desxifraRotX(xifra[i], desplacament[i]));
        }
        System.out.println("\nMissatge xifrat: Úiüht, úiü wx ùxì ív?\n---------");
        System.out.println(forcaBrutaRotX(xifra[4]));
        
    }
    public static String usaRotX(String cadena , boolean mas, int desplacament ){
        String xifrat="";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < miChar.length;j++){
                    if(miChar[j] == cadena.charAt(i)){
                        xifrat = xifrat + miChar[(j + 13) % miChar.length];
                        especial = false;
                        break;
                    }
                }
            }
            else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == cadena.charAt(i)){
                        xifrat = xifrat + maChar[(j + 13) % maChar.length];
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
    public static void forcaBrutaRotX(String cadenaXifrada){
        for (int i = 0; i<= miChar.length; i++) {
            
        }
    }
    public static String xifraRotX(String cadena, int desplacament) {
        String xifrat = usaRotX(cadena, true, desplacament);
        return xifrat;
    }

   public static String desxifraRotX(String cadena, int desplacament) {
        String desxifrat="";
        for (int i = 0; i < cadena.length(); i++){   
            boolean especial = true;
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < miChar.length;j++){
                    if(miChar[j] == cadena.charAt(i)){
                        desxifrat = desxifrat + miChar[(j - 13 + miChar.length) % miChar.length];
                        especial = false;
                        break;
                    }   
                }
            }
            else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == cadena.charAt(i)){
                        desxifrat = desxifrat + maChar[(j - 13 + maChar.length) % maChar.length];
                        especial = false;
                        break;
                    }
                }
            }
            if (especial){
                desxifrat = desxifrat + cadena.charAt(i);
            }   
        }
        return desxifrat;
   }

}
//diferente descifraX cifraX mismo cosa despues
//cambiar    

