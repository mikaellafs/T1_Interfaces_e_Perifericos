import org.antlr.v4.runtime.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import Arithmetic.ArithmeticLexer;
import Arithmetic.ArithmeticParser;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Main {
    public static void main(String[] args) {

        boolean flag = true;

        do{
            try {
                var reader = new BufferedReader(new InputStreamReader(System.in));
                var lexer = new ArithmeticLexer(CharStreams.fromString(reader.readLine()));
                var parser = new ArithmeticParser(new CommonTokenStream(lexer));

                parser.removeErrorListeners();
                parser.setErrorHandler(new BailErrorStrategy());

                // Código de teste
                var expressionContext = parser.expression();

                var expr = getExpression(expressionContext);
                System.out.println(expr);

                // Pra testar a concorrencia da pra pedir uma confirmação aqui.
                // Tipo pedir pra pressionar enter...

                // Deve ser sempre a ultima coisa a ser feita
                flag = false;
            } catch (ParseCancellationException e){
                System.out.println("Incorrect input!");
            } catch (Exception e){
                System.out.println("Failed to read input!");
                break;
            }
        }while(flag);
    }

    // Só pra teste
    private static Expression getExpression (ArithmeticParser.ExpressionContext expr) {
        var numberExpressionList = expr.numberExpression();

        String fOp, sOp, op;

        fOp = expr.numberExpression(0).getText();

        op = expr.operation().getText();

        if(expr.NUMBER() != null)
            sOp = expr.NUMBER().getText();
        else
            sOp = numberExpressionList.get(1).getText();

        return new Expression(fOp,sOp, op);
    }
}

// Só pra teste
class Expression{
    public final String firstOperand, secondOperand, operation;

    Expression(String firstOperand, String secondOperand, String operation) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "firstOperand='" + firstOperand + '\'' +
                ", secondOperand='" + secondOperand + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
