package Calculator_V1;

public class DataCalculator {
    private double value1;
    private double value2;
    private char operator;
    private double result;

    public DataCalculator()
    {
        clear();
    }

    public void clear()
    {
        value1 = 0;
        value2 = 0;
        operator = '0';
        result = 0;
    }

    public String calculate()
    {
        switch (operator)
        {
            case '+' -> result = value1 + value2;
            case '-' -> result = value1 - value2;
            case '*' -> result = value1 * value2;
            case '/' -> result = value1 / value2;
            case '^' -> result = Math.pow(value1, value2);
            case 's' -> result = Math.sqrt(value2);
        }
        return Double.toString(result);
    }

    public double getValue1()
    {
        return value1;
    }

    public void setValue1(double value1)
    {
        this.value1 = value1;
    }

    public double getValue2()
    {
        return value2;
    }

    public void setValue2(double value2)
    {
        this.value2 = value2;
    }

    public char getOperator()
    {
        return operator;
    }

    public void setOperator(char operator)
    {
        this.operator = operator;
    }

    public String getResult()
    {
        return Double.toString(result);
    }

    public void setResult(double result)
    {
        this.result = result;
    }
}
