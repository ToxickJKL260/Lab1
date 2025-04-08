import java.util.Scanner;
 
 public class Main{
    public static class BigVigenere{
       
       private int[] key;
       private char[][] alphabet;
       private String matriz = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ñÑ";
      
       public void crearmatriz(){
           alphabet = new char[64][64];
           for(int i=0;i<64;i++){
               for(int j=0;j<64;j++){
                   alphabet[i][j] = matriz.charAt((i+j)%64);
               }
           }
       }
       
       
       public BigVigenere(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la clave:");
            String num = scanner.nextLine();
            char[] tca = num.toCharArray();
            key = new int[tca.length];
            for(int i=0;i<tca.length;i++){
                key[i] = Character.getNumericValue(tca[i]);
            }
            crearmatriz();
       }
       
       public BigVigenere(String NumericKey){
            char[] tca = NumericKey.toCharArray();
            key = new int[tca.length];
            for(int i=0;i<tca.length;i++){
                key[i] = Character.getNumericValue(tca[i]);
            }
            crearmatriz();
       }
       
       public String encrypt(String message){
           String en = "";
           for(int i=0;i<message.length();i++){
               char aux1 = message.charAt(i);
               int aux2 = matriz.indexOf(aux1);
               int aux3 = key[i%key.length];
               en += alphabet[aux2][aux3];
           }
           return en;
       }        
       
       public String decrypt (String encryptedMessage){
            String en = "";
            for(int i=0;i<encryptedMessage.length();i++){
                char aux1 = encryptedMessage.charAt(i);
                int aux2 = key[i % key.length];
                for(int j=0;j<64;j++){
                    if(alphabet[j][aux2] == aux1){
                        en += matriz.charAt(j);
                    }
                }
            }
            return en;
       }
       
       public void reEncryp(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el mensaje: ");
            String message = scanner.nextLine();
            String messageDecrypt = decrypt(message);
            System.out.print("Ingrese la nuva clave: ");
            String clave = scanner.nextLine();
            BigVigenere nuevo = new BigVigenere(clave);
            String encrypt2 = nuevo.encrypt(messageDecrypt);
            System.out.print(encrypt2);
        }
        
        public char search(int position){
            int num=0;
            for(int i=0;i<64;i++){
                for(int j=0;j<64;j++){
                    if(num == position){
                        return alphabet[i][j];
                    }
                    num++;
                }
            }
            return '0';
        }
        
        public char optimalSearch(int position){
            int fila=position/64;
            int columna=position%64;
            return alphabet[fila][columna];
        }
    }
    public static void main(String[] args) {
        int[] L = {10,50,100,500,1000,5000};
        String matriz = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ñÑ";
        String message = "";
        
        for(int i=0;i<10000;i++){
            message += matriz.charAt((int)(Math.random()*64));
        }
        
        for(int i=0;i<L.length;i++){
            int t = L[i];
            String clave = "";
            for(int j=0;j<t;j++){
                clave += (int)(Math.random()*10);
            }
        
        BigVigenere prueba = new BigVigenere(clave);
        long encTi = System.currentTimeMillis();
        String prueba1 = prueba.encrypt(message);
        long encTf = System.currentTimeMillis();
        long decTi = System.currentTimeMillis();
        String prueba2 = prueba.decrypt(message);
        long decTf = System.currentTimeMillis();
        double encTt = (encTf-encTi)/1000.0;
        double decTt = (decTf-decTi)/1000.0;

        System.out.print("tiempo de encriptado: "+encTt+" segundos"+" tiempo de desencriptado: "+decTt+" segundos \n");
        }
    }
}
