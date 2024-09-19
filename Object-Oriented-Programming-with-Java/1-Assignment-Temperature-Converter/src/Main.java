import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Program för temperaturomvandling

        // PSEUDOKOD
        // importera scanner
        // fråga användare om input
        // omvandla input till Farenheit
        // omvandla Farenheit till Kelvin
        // omvandla Kelvin till Celsius
        // skriv ut Farenheit, Kelvin & Celcius
        // Program avslutas

        Scanner input = new Scanner(System.in);

        System.out.print("Skriv antal grader Celsius i heltalsvärde: ");
        int userInput = input.nextInt();

        float fahrenheit = (float) ((userInput) * 1.8 + 32);
        float kelvin = (float) ((fahrenheit + 459.67) / 1.8);
        double celsius = (kelvin - 273.15);


        System.out.println(
                "\nAntal grader i Celsius: " + Math.round(celsius) +
                "\nAntal grader i Fahrenheit: " + fahrenheit +
                "\nAntal grader i Kelvin: " + kelvin
        );
    }
}