package infixToPosfix.convert;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static infixToPosfix.convert.Utils.*;

public class Analysis {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static double convert(String infix) {

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

            while(!operatorStack.empty()){
                expresion.add(operatorStack.pop());
            }

            return calculate(expresion);

        } else if (!infix.contains(PARENTHESIS_CLOSE) && !infix.contains(PARENTHESIS_OPEN)) {
            //Under Construction
        } else {
            System.out.println("Error! ");
        }


        return 0L;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private static double calculate(List<String> expresion){

        ArrayDeque<Double> pile = new ArrayDeque();

        expresion.forEach(s -> {

            if (!isNumeric(s)){
                double number1 = pile.pollLast();
                double number2 = pile.pollLast();

                Actions actions = Actions.searchBySign(s);
                if(actions != null){
                    Operation operation = actions.getOperation();
                    double numberCalculated = operation.calculate(number2, number1);
                    pile.add(numberCalculated);
                }

            }else{
                pile.add(Double.parseDouble(s));
            }

        });

        return pile.getFirst();
    }
}