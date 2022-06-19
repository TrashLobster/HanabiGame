import com.company.StormTokens;
import com.company.TokenCollection;

public class TokenTesting {
    public static void main(String[] args) {
        StormTokens tokens = new StormTokens();
        
        for (int i = 0; i < tokens.getTokens().length; i++) {
            tokens.flipToken(false);
        }    

        tokens.flipToken(false);

        printTokens(tokens);

    }

    public static void printTokens(TokenCollection tokens) {
        for (boolean token : tokens.getTokens()) {
            System.out.println(token);
        }    
    }
}
