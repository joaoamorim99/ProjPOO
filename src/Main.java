import Controller.Controller;
import Controller.LinhaIncorretaException;
import Model.Sistema;
import View.ViewInicial;
import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, LinhaIncorretaException {
        Sistema s = new Sistema();
        ViewInicial view = new ViewInicial();
        Controller control = new Controller(s,view);

        control.controllerStart();

        System.exit(0);
    }
}
