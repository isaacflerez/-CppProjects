



package proyecto;



public class testServos{

    public static void main(String[] args) {
         String puerto = "COM3"; 

        logicaServos logicaServosObj = new logicaServos(puerto);
        logicaServosObj.iniciar();
     }
}

