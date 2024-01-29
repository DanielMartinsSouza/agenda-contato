import java.util.Scanner;

import static Contact.Contact.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean exitMenu = false;
        Scanner scanner = new Scanner(System.in);

        while (!exitMenu) {
            listContact();
            menuUI();
            String result = scanner.nextLine();
            switch (result) {
                case "1":
                    addContact();
                    break;
                case "2":
                    removeContact();
                    break;
                case "3":
                    editContact();
                    break;
                case "4":
                    completeListContact();
                    break;
                case "5":
                    System.out.println("Saindo da aplicação...");
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Você digitou um valor diferente das opções");
            }
            Thread.sleep(2000);
        }

    }

    public static void menuUI() {
        System.out.println("""
                                    
                >>>>>>>>>>>> Menu <<<<<<<<<<<<                    
                Escolha uma das opções abaixo:
                1 - Adicionar Contatos.
                2 - Apagar Contato.
                3 - Editar Contato.
                4 - Listar Todas Informações dos Contatos.
                5 - Sair da Aplicação
                """
        );
    }
}