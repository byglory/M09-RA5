public class Rot13 {
     String lletresMin = "aàábcdeèéfghiìíïjklmnoóòpqrstuúùüvwxyz";
     String lletresMaj = lletresMin.toUpperCase();
     char[] miChar = lletresMin.toCharArray();
     char[] maChar = lletresMaj.toCharArray();
    public static void main(String[] args) {
        int largo = args.length;
        if (largo <= 0 ){
            return;
        }
        System.out.println("Xifrat\n---------");
        for (String encriptame : args){
            System.out.println(encriptame + " => " + xifraRot13(encriptame));
        }
    }
    public static String xifraRot13(String texto) {
        for (int i = 0; i < texto.length(); i++){   
        }
            return xifrat;
        

    }

    public static String[] desxifraRot13(String[] args) {
        
        
    }
}


