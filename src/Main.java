import org.antlr.v4.runtime.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        boolean flag = true;

        do{
            try {
                var reader = new BufferedReader(new InputStreamReader(System.in));
                var errorListener = new DefaultErrorListener();

                var lexer = new ArithmeticLexer(CharStreams.fromString(reader.readLine()));
                lexer.removeErrorListeners();
                lexer.addErrorListener(errorListener);

                var parser = new ArithmeticParser(new CommonTokenStream(lexer));
                parser.removeErrorListeners();
                parser.addErrorListener(errorListener);

                //Teste
                //System.out.println(parser.operation().getText());

                // Tentar o acesso ao driver...

                // Deve ser sempre a ultima coisa a ser feita
                flag = false;
            } catch (RecognitionException e){
                System.out.println("Incorrect input!");
            } catch (IOException e){
                System.out.println("Failed to read input!");
                break;
            }
        }while(flag);
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
