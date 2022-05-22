import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Введите числа большее 0 и меньше либо равное 10\nФормат ввода Х + Y");
        Scanner console = new Scanner(System.in);
        String inputCalc = console.nextLine();

        System.out.println(calc(inputCalc));

        console.close();
    }

    public static String calc(String input) {
        String result = "";
        String[] subStr = input.split(" ");

        if (subStr.length != 3) { // Проверяем что пользователь ввел все правильно, т.е 2 цифры и знак действия
            throw new ArithmeticException(
                    "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        } else if (romanToDecimal(subStr[0]) > 0 && romanToDecimal(subStr[2]) > 0) { // Проверяем что римские цифры
                                                                                     // больше 0

            if (romanToDecimal(subStr[0]) <= 10 && romanToDecimal(subStr[2]) <= 10) { // Проверяем что римские цифры
                                                                                      // меньше либо равны 10
                int temp = calculate(romanToDecimal(subStr[0]), romanToDecimal(subStr[2]), subStr[1]);
                if (temp > 0) {
                    result = convert(temp);
                } else {
                    throw new ArithmeticException("т.к. в римской системе нет отрицательных чисел");
                }
            } else {
                throw new ArithmeticException("Введено неподходящее значение!!!");
            }
        } else if (subStr[0].matches("^-?\\d+$") && subStr[2].matches("^-?\\d+$")) {
            if (Integer.parseInt(subStr[0]) > 0 && Integer.parseInt(subStr[0]) <= 10 && Integer.parseInt(subStr[2]) > 0
                    && Integer.parseInt(subStr[2]) <= 10) {
                int temp = calculate(Integer.parseInt(subStr[0]), Integer.parseInt(subStr[2]), subStr[1]);
                result = Integer.toString(temp);
            } else {
                throw new ArithmeticException("Введено неподходящее значение!!!");
            }

        } else {
            throw new ArithmeticException("Введено неподходящее значение!!!");
        }
        return result;
    }

    // Преобразовываем римские цифры в арабские
    public static int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        // Так как числа у нас до 10, то дальнейшее преобразование бессмысленно.
        return -1;
    }

    public static int romanToDecimal(String str) {
        str = str.toUpperCase();
        int res = 0;

        for (int i = 0; i < str.length(); i++) {
            int s1 = value(str.charAt(i));
            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));
                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
            }
        }
        return res;
    }

    // Преобразовываем арабские цифры в римские
    public static String romanDigit(int n, String one, String five, String ten) {

        if (n >= 1) {
            switch (n) {
                case 1:
                    return one;
                case 2:
                    return one + one;

                case 3:
                    return one + one + one;

                case 4:
                    return one + five;

                case 5:
                    return five;

                case 6:
                    return five + one;

                case 7:
                    return five + one + one;

                case 8:
                    return five + one + one + one;

                case 9:
                    return one + ten;
            }
        }
        return "";
    }

    public static String convert(int number) {
        // Так как максимально возможное число в нашем случае это 100, то мы и ограничим
        // вычисление 100
        if (number < 0 || number > 100) {
            return "This number cannot be converted";
        }

        String romanOnes = romanDigit(number % 10, "I", "V", "X");
        number /= 10;

        String romanTens = romanDigit(number % 10, "X", "L", "C");
        number /= 10;

        String romanHundreds = romanDigit(number % 10, "C", "D", "M");
        number /= 10;

        String result = romanHundreds + romanTens + romanOnes;
        return result;
    }

    // Метод вычисления введенных значений
    public static int calculate(int num1, int num2, String oper) {
        int result = 0;
        switch (oper) {
            case ("+"):
                result = num1 + num2;
                break;
            case ("-"):
                result = num1 - num2;
                break;
            case ("*"):
                result = num1 * num2;
                break;
            case ("/"):
                result = num1 / num2;
                break;
        }

        return result;
    }

}
