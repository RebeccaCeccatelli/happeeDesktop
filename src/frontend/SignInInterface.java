package frontend;

import database.BusinessDAO;
import database.UserDAO;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignInInterface extends Interface {

    private TextField emailTextField = new TextField();
    private PasswordField passwordField = new PasswordField();

    @Override
    public void start(Stage primaryStage) {
        setPrimaryStage(primaryStage);
        setGridPane();

        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");
        Button loginButton = createButton("Login", e -> login());
        Button backButton = createButton("Back", e -> goBack());

        addToGridPane(emailLabel, 0, 0);
        addToGridPane(passwordLabel, 0, 1);
        addToGridPane(emailTextField, 1, 0);
        addToGridPane(passwordField, 1, 1);
        addToGridPane(backButton, 0, 2);
        addToGridPane(loginButton, 1, 2);

        showCurrentInterface("Login");
    }

    private void clearPersonalFields() {
        emailTextField.clear();
        passwordField.clear();
    }

    private void login() {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        UserDAO userDAO = new UserDAO();
        //TODO qui c'è codice che può essere messo tutto insieme, sistemare i table managers
        BusinessDAO businessDAO = new BusinessDAO();
        if (userDAO.checkUserExistence(email, password)) {
            clearPersonalFields();
            UserDashboard userDashboard = new UserDashboard(userDAO.getAccountId(email));
            showNextInterface(userDashboard);
        }
        else if (businessDAO.checkBusinessExistence(email, password)) {
            clearPersonalFields();
            BusinessDashboard businessDashboard = new BusinessDashboard(businessDAO.getAccountId(email));
            showNextInterface(businessDashboard);
        }
        else {
            showAlert("Invalid credentials", "Incorrect username or password. ");
        }
    }
}
