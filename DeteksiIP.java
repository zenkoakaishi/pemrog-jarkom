import java.net.InetAddress;
import java.util.Scanner;

public class DeteksiIP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan URL atau IP : ");
        String userInput = input.nextLine().trim();

        if (userInput.isEmpty()) {
            System.out.println("Input tidak boleh kosong.");
            return;
        }

        try {
            String ip = getIPAddress(userInput);

            System.out.println("\n===== HASIL DETEKSI =====");
            System.out.println("IP Address : " + ip);
            System.out.println("Kelas IP   : " + determineClass(ip));
            System.out.println("IP Biner   : " + convertToBinary(ip));

        } catch (Exception e) {
            System.out.println("URL atau IP tidak valid.");
        }

        input.close();
    }

    // Mengambil IP dari URL (DNS Resolution)
    private static String getIPAddress(String domain) throws Exception {
        InetAddress address = InetAddress.getByName(domain);
        return address.getHostAddress();
    }

    // Menentukan kelas IP berdasarkan oktet pertama
    private static String determineClass(String ip) {
        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);

        if (firstOctet >= 1 && firstOctet <= 126)
            return "Kelas A";
        else if (firstOctet >= 128 && firstOctet <= 191)
            return "Kelas B";
        else if (firstOctet >= 192 && firstOctet <= 223)
            return "Kelas C";
        else if (firstOctet >= 224 && firstOctet <= 239)
            return "Kelas D ";
        else if (firstOctet >= 240 && firstOctet <= 255)
            return "Kelas E";
        else
            return "Tidak diketahui";
    }

    // Konversi IP ke bentuk biner 8-bit per oktet
    private static String convertToBinary(String ip) {

        String[] parts = ip.split("\\.");
        StringBuilder binaryIP = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {

            int number = Integer.parseInt(parts[i]);

            String binary = String.format("%8s",
                    Integer.toBinaryString(number)).replace(' ', '0');

            binaryIP.append(binary);

            if (i < parts.length - 1) {
                binaryIP.append(".");
            }
        }

        return binaryIP.toString();
    }
}