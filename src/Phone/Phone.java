package Phone;

import Contact.Contact;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Phone {

    private static Long lastId = 0L;  // Rastreia o último ID atribuído

    private Long id;
    private String ddd;
    private Long numero;

    public Phone() {
        this.id = generateId();
    }

    private static synchronized Long generateId() {
        return ++lastId;
    }

    public Long getId() {
        return id;
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

    public static List<Phone> readPhonesFromUser(List<Contact> contacts) {
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
                boolean phoneEquals = false;
                phoneEquals = isPhoneEquals(contacts, phone, phoneEquals);

                if (!phoneEquals){
                    phones.add(phone);
                }else {
                    phones.clear();
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Forneça valores válidos para o número e DDD do telefone.");
            scanner.nextLine();
        }

        return phones;
    }

    public static boolean isPhoneEquals(List<Contact> contacts, Phone phone, boolean phoneEquals) {
        for (Contact contact : contacts) {
            List<Phone> phonesVerify = contact.getPhones();
            for (Phone contactPhone : phonesVerify)
                if (contactPhone.getNumero().equals(phone.getNumero()) && contactPhone.getDdd().equals(phone.getDdd())) {
                    phoneEquals = true;
                    break;
                }
        }
        return phoneEquals;
    }
}
