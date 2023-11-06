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
        }if(split.length > 3) {
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
                number_one = toArabic(strNumberOne);
                number_two = toArabic(strNumberTwo);
                isRoman = true;

                if(!(number_one > number_two)){
                    throw  new Exception("в римской системе нет отрицательных чисел");
                }
                number_one = toArabic(split[0]);
                number_two = toArabic(split[2]);
                isRoman = true;

                if(!(number_one > number_two)){
                    throw  new Exception("в римской системе нет отрицательных чисел");

                }






            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
                throw exception;
            }
        }
        if (number_one > 11 || number_two > 11 || number_one <= 0 || number_two <= 0) {
            throw new Exception();
        }
        String operation = split[1];
        int result = 0;
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
                String numberAsString = String.format("%d", result);
                int number = Integer.parseInt(numberAsString);
                return String.valueOf(number);
            } catch (NullPointerException e) {
                throw new Exception();
            }
        }
        return String.valueOf(result);


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

    public static int toArabic(String number) {
        List<String> roman = new ArrayList<>();
        roman.add("нулевое значение");
        roman.add("I");
        roman.add("II");
        roman.add("III");
        roman.add("IV");
        roman.add("V");
        roman.add("VI");
        roman.add("VII");
        roman.add("VIII");
        roman.add("IX");
        roman.add("X");
        return roman.indexOf(number);
    }

}