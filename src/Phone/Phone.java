package Phone;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Phone {
    private Long id;
    private String ddd;
    private Long numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public static List<Phone> readPhonesFromUser() {
        Scanner scanner = new Scanner(System.in);
        List<Phone> phones = new ArrayList<>();

        try {
            System.out.println("Quantos telefones deseja adicionar ao contato?");
            int numPhones = scanner.nextInt();

            for (int i = 0; i < numPhones; i++) {
                Phone phone = new Phone();

                System.out.println("Digite o DDD do telefone " + (i + 1) + ":");
                phone.setDdd(scanner.next());

                System.out.println("Digite o número do telefone " + (i + 1) + ":");
                phone.setNumero(scanner.nextLong());

                phones.add(phone);
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Forneça valores válidos para o número e DDD do telefone.");
            scanner.nextLine(); // Limpar o buffer do scanner após erro de entrada
        }

        return phones;
    }

}
