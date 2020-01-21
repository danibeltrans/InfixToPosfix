package infixToPosfix.convert;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static infixToPosfix.convert.Utils.*;

public class Analysis {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static long convert(String infix) {

        var operatorStack = new Stack<String>();
        var expresion = new ArrayList<String>();

        if (infix.contains(PARENTHESIS_CLOSE) && infix.contains(PARENTHESIS_OPEN)) {

            var remainingExpression = List.of(infix.split(" ")).stream().collect(Collectors.toUnmodifiableList());
            for (String character : remainingExpression) {

                switch (character){
                    case PARENTHESIS_OPEN, ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
                            -> operatorStack.push(character);
                    case PARENTHESIS_CLOSE -> {

                        while (!operatorStack.peek().equals(PARENTHESIS_OPEN)){
                            String operator = operatorStack.pop();
                            expresion.add(operator);
                        }

                        if(!operatorStack.empty()){
                            operatorStack.pop();
                        }
                    }

                    default -> {
                        if(isNumeric(character)){
                            expresion.add(character);
                        }
                        else {
                            throw new IllegalStateException("Unexpected value: " + character);
                        }
                    }
                }

            }
            expresion.addAll(operatorStack);
            expresion.stream().forEach(System.out::println);

        } else if (!infix.contains(PARENTHESIS_CLOSE) && !infix.contains(PARENTHESIS_OPEN)) {

        } else {
            System.out.println("Error! ");
        }


        return 0L;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}