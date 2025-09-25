public class RotX {
    final static char[] miChar = "aàábcdeèéfghiìíïjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    final static char[] maChar = new String(miChar).toUpperCase().toCharArray();
    public static void main(String[] args) {
        String[] xifra = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        int[] desplacament = {0,2,4,6};
        int largo = xifra.length;
        if (largo <= 0 || desplacament.length <= 0 ){
            return;
        }
        System.out.println("Xifrat\n---------");
        for (int i = 0; i < largo; i++){
            System.out.println("("+desplacament[i]+") " + xifra[i] +" => "+ xifraRotX(xifra[i], desplacament[i]));
        }
        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i < largo; i++){
            System.out.println("("+desplacament[i]+") "  + xifraRotX(xifra[i], desplacament[i]) +" => "+ desxifraRotX(xifraRotX(xifra[i], desplacament[i]),desplacament[i]));
        }
        System.out.println("\nMissatge xifrat: Úiüht, úiü wx ùxì ív?\n----------------");
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }
    public static String usaRotX(String cadena , boolean mas, int desplacament ){
        String xifrat="";
        for (int i = 0; i < cadena.length(); i++){  
            boolean especial = true; 
            if (Character.isLowerCase(cadena.charAt(i))){
                for(int j = 0; j < miChar.length;j++){
                    if(miChar[j] == cadena.charAt(i)){
                        if(mas){
                            xifrat = xifrat + miChar[(j + desplacament) % miChar.length];
                            especial = false;
                            break;
                        }
                        else{
                            xifrat = xifrat + miChar[(j - desplacament + miChar.length) % miChar.length];
                            especial = false;
                            break;
                        }
                    }
                }
            }
            else if (Character.isUpperCase(cadena.charAt(i))){
                for(int j = 0; j < maChar.length;j++){
                    if(maChar[j] == cadena.charAt(i)){
                        if(mas){
                            xifrat = xifrat + maChar[(j + desplacament) % maChar.length];
                            especial = false;
                            break;
                        }
                        else{
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
    public static void forcaBrutaRotX(String cadenaXifrada){
        for (int i = 0; i <= miChar.length; i++) {
            System.out.println("("+i+")->" + desxifraRotX(cadenaXifrada, i));
        }
    }
    public static String xifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, true, desplacament);
    }
   public static String desxifraRotX(String cadena, int desplacament) {
        return usaRotX(cadena, false, desplacament);
   }
}