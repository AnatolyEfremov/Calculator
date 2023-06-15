import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
           System.out.println(calc(new Scanner(System.in).nextLine()));
        }
    }
    static Boolean isRealNumber(String s) {

        return s.replaceAll("\\d", "").equals(".");
    }

    static Boolean isNumber(String s) {
        if (s.replaceAll("\\d", "").isEmpty()) {
            return (Integer.parseInt(s) > 10 | Integer.parseInt(s) <= 0);
        }
        return false;
    }

    public static String calc(String input) {
        String a, b;
        int count, count1;
        int i = 0;
        count1 = 0;
        count = 0;
        input = input.toUpperCase().replaceAll(" ", "");
        while (i < input.length()) {
            switch (input.charAt(i)) {
                case '+':
                case '-':
                case '/':
                case '*':
                    count = count + 1;
                    count1 = i;
                    if (count1 == 0 | count1 == input.length() - 1) {
                        throw new RuntimeException("2Строка не является математической операцией");
                    }
                default:
                    i++;
            }
        }
        if (count == 0) {
            throw new RuntimeException("Строка не является математической операцией");
        }
        if (count > 1) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (count == 1) {
            a = input.substring(0, count1);
            b = input.substring(count1 + 1);


            if (isArab(a) & isArab(b)) {
                switch (input.charAt(count1)) {
                    case '+':
                        return "" + (Integer.parseInt(a) + Integer.parseInt(b));
                    case '-':
                        return "" + (Integer.parseInt(a) - Integer.parseInt(b));
                    case '/':
                        return "" + (Integer.parseInt(a) / Integer.parseInt(b));
                    case '*':
                        return "" + (Integer.parseInt(a) * Integer.parseInt(b));
                }
            }
            if (isRoman(a) & isRoman(b)) {
                switch (input.charAt(count1)) {
                    case '+':
                        return getRomanResult(getRoman(a) + getRoman(b));

                    case '-':
                        return getRomanResult(getRoman(a) - getRoman(b));

                    case '/':
                        return getRomanResult(getRoman(a) / getRoman(b));

                    case '*':
                        return getRomanResult(getRoman(a) * getRoman(b));

                }
            }
            if (isArab(a) & isRoman(b) | (isRealNumber(a) & isRoman(b)) | (isRoman(a) & isRealNumber(b)) | (isRoman(a) & isArab(b)) | (isRoman(a) & isNumber(b)) | (isNumber(a) & isRoman(b))) {

                throw new RuntimeException("Используются одновременно разные системы счисления");
            }
            if ((isArab(a) & !isArab(b) & !isRoman(b) & !isRealNumber(b) & (isNumber(b) | !isNumber(b))) | (isArab(b) & !isArab(a) & !isRoman(a) & !isRealNumber(a) & (isNumber(a) | !isNumber(a))) | (isNumber(a) & isNumber(b)) | (isRealNumber(a) & isNumber(b)) | (isNumber(a) & isRealNumber(b))) {
                throw new RuntimeException("Формат математической операции не удовлетворяет заданию - Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            }
            if ((isRoman(a) & !isArab(b) & !isRoman(b) & !isRealNumber(b) & !isNumber(b)) | (isRoman(b) & !isArab(a) & !isRoman(a) & !isRealNumber(a) & !isNumber(a))) {
                throw new RuntimeException("Формат математической операции не удовлетворяет заданию - Калькулятор должен принимать на вход числа от I до X включительно");
            }
            if (!isArab(a) & !isRoman(a) & !isArab(b) & !isRoman(b) & !isRealNumber(a) & !isRealNumber(b) & !isNumber(a) & !isNumber(b)) {
                throw new RuntimeException("Формат математической операции не удовлетворяет заданию - Калькулятор должен принимать на вход числа от 1 до 10 включительно или от I до X включительно");
            }
            if ((isRealNumber(a) & isArab(b)) | (isArab(a) & isRealNumber(b)) | (isRealNumber(a) & isRealNumber(b))) {
                throw new RuntimeException("Формат математической операции не удовлетворяет заданию - Калькулятор умеет работать только с целыми числами.");
            }

        }
        throw new RuntimeException("Ошибка");
    }
    static int getRoman(String s) {
        for (int i = 0; i < 10; i++)
            if (Roman.values()[i].toString().equals(s))
                return i + 1;
        return 0;
    }

    static Boolean isArab(String s) {
        if (s.replaceAll("\\d", "").isEmpty()) return (Integer.parseInt(s) <= 10 & Integer.parseInt(s) != 0);
        return false;
    }

    static Boolean isRoman(String s) {
        for (int i = 0; i < 10; i++) {
            if (Roman.values()[i].toString().equals(s)) return true;
        }
        return false;
    }

    static String getRomanResult(int s1) {
        String s = "" + s1;
        if (s1 > 0) {
            switch (s.length()) {
                case 3:
                    return TenRoman.C.toString();
                case 2:
                    if (s.charAt(1) == '0') {
                        return TenRoman.values()[Integer.parseInt(s.substring(0, 1))].toString();
                    } else {
                        return TenRoman.values()[Integer.parseInt(s.substring(0, 1))].toString() + Roman.values()[Integer.parseInt(s.substring(1)) - 1].toString();
                    }
                case 1:
                    return Roman.values()[Integer.parseInt(s) - 1].toString();
            }
            throw new RuntimeException("Ошибка");
        }
        throw new RuntimeException("В римской системе исчисления нет отрицательных чисел и нуля");
    }
}
enum Roman {
    I, II, III, IV, V, VI, VII, VIII, IX, X
}

enum TenRoman {
    C, X, XX, XXX, XL, L, LX, LXX, LXXX, XC
}
