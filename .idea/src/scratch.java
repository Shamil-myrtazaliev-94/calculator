import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {

        System.out.println("Введите данные: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calculate(input));


        System.out.println();


    }


    public static String calculate(String input) throws Exception {
        String[] split = input.split(" ");
        if(split.length == 1) {
            try {
                throw new  Exception("строка не является математической операцией");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(split.length > 3) {
            try {
                throw new  Exception("формат математической операции не удовлетворяет заданию");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }


        int number_one;
        int number_two;
        boolean isRoman = false;
        String strNumberOne = split[0];
        String strNumberTwo = split[2];

        try {
            number_one = Integer.parseInt(split[0]);
            number_two = Integer.parseInt(split[2]);




        }
        catch (NumberFormatException e) {

            try {
                boolean isRomanOne = strNumberOne.matches("[IVXLCDM]+");
                boolean isRomanTwo = strNumberTwo.matches("[IVXLCDM]+");

                if (!isRomanOne && strNumberOne.chars().anyMatch(Character::isDigit) ||
                        !isRomanTwo && strNumberTwo.chars().anyMatch(Character::isDigit)) {
                    throw new Exception ("используются одновременно разные системы счисления");
                }
                number_one = parseRome(strNumberOne);
                number_two = parseRome(strNumberTwo);



                number_one = parseRome(split[0]);
                number_two = parseRome(split[2]);
                isRoman = true;








            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
                throw exception;
            }
        }
        if (number_one > 11 || number_two > 11 || number_one <= 0 || number_two <= 0) {
            throw new Exception("Числа должы быть больше 1 и меньше 10");
        }
        String operation = split[1];
        int result = 0;
        String resultArabic = "";
        switch (operation) {
            case "*" -> result = number_one * number_two;
            case "+" -> result = number_one + number_two;
            case "-" -> result = number_one - number_two;
            case "/" -> {
                if (number_two == 0) {
                    throw new IllegalArgumentException("Can't divide by 0");
                }
                result = number_one / number_two;
            }
            default -> throw new IllegalArgumentException("Unknown operator '%s'".formatted(input));
        }
        if (isRoman) {
            try {

                if(!(number_one > number_two) && operation.equals("-")){
                    throw  new Exception("в римской системе нет отрицательных чисел");

                }
                resultArabic = toArabic(result);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                throw new Exception();
            }

        }

        return isRoman ? resultArabic : String.valueOf(result);


    }


    public static int parseRome(String s) {
        int result = 0;

        Map<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            if(i + 1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))){
                result -= map.get(s.charAt(i));

            }
            else {
                result += map.get(s.charAt(i));
            }
        }
        return result;

    }

    public static String toArabic(int number) {
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

        return hundreds[number / 100] + tens[(number % 100) / 10] + ones[number % 10];
    }



}