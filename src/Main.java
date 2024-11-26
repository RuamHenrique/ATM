import service.ATM;
import service.Banco;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        ATM atm = new ATM(banco);
        atm.iniciar();  // Inicia o sistema ATM
    }
}
