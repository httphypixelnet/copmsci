package compsci.labs;

import compsci.Utils;

import java.time.LocalDateTime;
import java.util.Scanner;

import static compsci.Utils.prompt;
import static compsci.Utils.safe;

public class Budgeter {
    private static double handleIncome(Scanner sc) {
        double totalIncome = 0;
        Utils.Result<Integer> res = safe(() -> Integer.parseInt(Utils.prompt("How many categories of income?", sc)));

        if (res.hasError() || res.data() < 0) {
            System.out.println("Invalid number! (hint: you should enter a positive whole number)");
            handleIncome(sc);
        }
        int categories = res.data();
        for (int i = 0; i < categories; i++) {
            Utils.Result<Double> incomeRes = safe(() -> Double.parseDouble(Utils.prompt("Next income amount?", sc, '$')));
            if (incomeRes.hasError()) {
                System.out.println("Invalid number! (hint: your number should look like: 1 or 1.5)");
                i--;
            } else {
                totalIncome += incomeRes.data();
            }
        }
        return totalIncome;
    }
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("This program asks you your monthly income and expenses, then tells you your net monthly income.");
        double income = handleIncome(sc);
        String md = prompt("Would you like to enter your monthly or daily expenses?", sc);
        double expenses;
        if (md.equalsIgnoreCase("daily")) {
            LocalDateTime d = LocalDateTime.now();
            int daysInMonth = d.getMonth().length(d.toLocalDate().isLeapYear());
            expenses = 0;
            for (int i = 0; i < daysInMonth; i++) {
                expenses += Double.parseDouble(prompt("Enter your daily expenses for day %s".formatted(i), sc, '$'));
            }
        } else {
            expenses = Double.parseDouble(prompt("Enter your monthly expenses", sc, '$'));
        }
        // tiers: over 250: big saver
        // 0-250: saver
        // -250 to 0: spender
        // under -250: big spender
        if (expenses > income) {
            if (expenses - income > 250) {
                System.out.println("Wrap it up 💔 you're going into DEEP debt this month");
            }
            else {
                System.out.println("You're going into debt this month...");
            }
        }
        else {
            if (income - expenses > 250) {
                System.out.println("🤑🤑🤑🤑🤑🤑🤑 you're doing GREAT this month!");
            }
            else {
                System.out.println("You're doing well this month!");
            }
        }
        sc.close();
        System.out.printf("Your net monthly income is: $%.2f\n", income - expenses);
    }
}
