package infixToPosfix.convert;

public enum Actions {

    ADDITION(Utils.ADDITION, (double x1, double x2) -> x1+x2),
    SUBTRACTION(Utils.SUBTRACTION, (double x1, double x2) -> x1-x2),
    MULTIPLICATION(Utils.MULTIPLICATION, (double x1, double x2) -> x1*x2),
    DIVISION(Utils.DIVISION, (double x1, double x2) -> x1/x2);

    private String sign;
    private Operation operation;

    Actions(String sign, Operation operation){
        this.sign = sign;
        this.operation = operation;
    }

    public String getSign() {
        return sign;
    }

    public Operation getOperation() {
        return operation;
    }

    public static Actions searchBySign(String sign){
        for(Actions action: Actions.values()){
            if(action.getSign().equals(sign)){
                return action;
            }
        }
        return null;
    }
}
