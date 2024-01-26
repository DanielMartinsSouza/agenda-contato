import java.util.Scanner;
import static Contact.Contact.*;

public class Main {
    public static void main(String[] args) {
        boolean exitMenu = false;
        Scanner scanner = new Scanner(System.in);

        while (!exitMenu){
            menuUI();
            String result = scanner.nextLine();
            switch (result){
                case "1":
                    listContact();
                    break;
                case "2":
                    addContact();
                    break;
                case "3":
                    removeContact();
                    break;
                case "4":
                    editContact();
                    break;
                case "5":
                    System.out.println("Saindo da aplicação...");
                    exitMenu = true;
                default:
                    System.out.println("Você digitou um valor diferente das opções");
            }
        }

    }

    public static void menuUI(){
        System.out.println("""
                    
                    ##############################
                    ##### AGENDA DE CONTATOS #####
                    ##############################
                    
                    Escolha uma das opções abaixo:
                    1 - Listar Contatos.
                    2 - Adicionar Contatos.
                    3 - Apagar Contato.
                    4 - Editar Contato.
                    5 - Sair da Aplicação
                    """
        );
    }
}