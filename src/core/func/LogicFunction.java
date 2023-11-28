package core.func;

public interface LogicFunction {

    /**
     * Evaluates the supplied logic function on the given inputs, and returns
     * the resultant that should be assigned to RX
     * 
     * @param rx The value of RX
     * @param ry The value of RY
     * @return The result
     */
    public char evaluate(char rx, char ry);
}
