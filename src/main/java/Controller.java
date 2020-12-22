import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private final Model model = new Model();
    private BufferedReader reader;

    public void askUser() {
        while (true){
            System.out.println("Enter login:");
            String login = createInput();
            try {
                model.checkLogin(login);
            } catch (NotUniqueLoginException e) {
                System.out.printf("This login \"%s\" already exist, please try again%n", login);
                askUser();
            }
            System.out.println("Create a password:");
            String password = createInput();
            System.out.println("Enter your name");
            String name = createInput();
            model.writeData(name, login, password);
            System.out.println("Do you want to exit? (y/n)");
            String question = createInput();
            if (question.equalsIgnoreCase("y")) {
                break;
            }
        }
    }

    private String createInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
