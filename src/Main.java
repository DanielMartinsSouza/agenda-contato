import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean exitMenu = false;
        Scanner scanner = new Scanner(System.in);

        while (!exitMenu){
            menuUI();
            String result = scanner.nextLine();
            switch (result){
                case "4":
                    System.out.println("Saindo da aplicação...");
                    exitMenu = true;
                    break;
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
                    4 - Sair da aplicação.
                    
                    """
        );
    }
}