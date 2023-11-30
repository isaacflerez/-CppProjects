package proyecto;



import jssc.SerialPort;
import jssc.SerialPortException;
import java.util.Scanner;

public class logicaServos {
    private final SerialPort serialPort;

    public logicaServos(String puerto) {
        serialPort = new SerialPort(puerto);
    }

    public void iniciar() {
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            try (Scanner scanner = new Scanner(System.in)) {
                String input;

                do {
                    System.out.println("Ingresa los grados en el eje X o 'q' para salir:");
                    input = scanner.nextLine();

                    if (!input.equalsIgnoreCase("q")) {
                        try {
                            int gradosServoX = Integer.parseInt(input);

                            if (gradosServoX >= 0 && gradosServoX <= 180) {
                                System.out.println("Ingresa los grados en el eje Y:");
                                int gradosServoY = Integer.parseInt(scanner.nextLine());

                                if (gradosServoY >= 0 && gradosServoY <= 180) {
                                    System.out.println("Ingresa los grados en el eje Z:");
                                    int gradosServoZ = Integer.parseInt(scanner.nextLine());

                                    if (gradosServoZ >= 70 && gradosServoZ <= 180) {
                                        enviarGrados(gradosServoX, gradosServoY, gradosServoZ);
                                    } else {
                                        System.out.println("Ingresa un valor entre 70 y 180 para el eje Z.");
                                    }
                                } else {
                                    System.out.println("Ingresa un valor entre 0 y 180 para el eje Y.");
                                }
                            } else {
                                System.out.println("Ingresa un valor entre 0 y 180 para el eje X.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ingresa un número válido.");
                        }
                    }
                } while (!input.equalsIgnoreCase("q"));
            }
            serialPort.closePort();

        } catch (SerialPortException e) {
        }
    }

    private void enviarGrados(int gradosServoX, int gradosServoY, int gradosServoZ) {
        try {
            String comando = gradosServoX + "," + gradosServoY + "," + gradosServoZ;
            serialPort.writeString(comando);
        } catch (SerialPortException e) {
        }
    }
}

    

    
   
