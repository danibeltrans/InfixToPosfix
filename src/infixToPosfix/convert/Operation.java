package infixToPosfix.convert;

@FunctionalInterface
public interface Operation {

    double calculate (double x1, double x2);

}
