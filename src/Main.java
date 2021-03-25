import org.antlr.v4.runtime.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        boolean flag = true;

        do{
            try {
                var reader = new BufferedReader(new InputStreamReader(System.in));
                var lexer = new ArithmeticLexer(CharStreams.fromString(reader.readLine()));
                var parser = new ArithmeticParser(new CommonTokenStream(lexer));

                parser.removeErrorListeners();
                parser.addErrorListener(new DefaultErrorListener());

                //Código de teste
                var expressionContext = parser.expression();

                var expr = getExpression(expressionContext);
                System.out.println(expr);


                // Deve ser sempre a ultima coisa a ser feita
                flag = false;
            } catch (RecognitionException e){
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

class DefaultErrorListener extends BaseErrorListener{
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol, int line,
                            int charPositionInLine, String msg,
                            RecognitionException e) {
        // Poderia fazer algo aqui, mas não sei se precisa...
        throw e;
    }
}
