import com.company.TokenCollection;

public class TokenTesting {
    public static void main(String[] args) {
        TokenCollection tokens = new TokenCollection(8 );
        
        for (boolean token : tokens.getTokens()) {
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
