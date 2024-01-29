package Contact;

import File.MyFile;
import Phone.Phone;

import java.util.List;
import java.util.Scanner;

public class Contact {

    private static Long lastId = 0L;

    private Long id;
    private String nome;
    private String sobreNome;
    private List<Phone> phones;

    public Contact() {
        this.id = generateId();
    }

    public static synchronized Long generateId() {
        return ++lastId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public static List<Contact> contacts = MyFile.readFile();

    public static void completeListContact() {
        System.out.println("-- Listando Contatos com todas informações: --");
        for (Contact contact : contacts) {
            System.out.println("ID: " + contact.getId());
            System.out.println("Nome: " + contact.getNome());
            System.out.println("Sobrenome: " + contact.getSobreNome());

            List<Phone> phones = contact.getPhones();
            for (Phone phone : phones) {
                System.out.println("Telefone: (" + phone.getDdd() + ") " + phone.getNumero());
            }

            System.out.println("-------------------------");
        }
    }

    public static void listContact() {
        System.out.println("-- Listando Contatos com todas informações: --");
        System.out.print("""
                    ##############################
                    ##### AGENDA DE CONTATOS #####
                    ##############################
                    
                    >>>>>>>>>> Contatos <<<<<<<<<<
                    Id | Nome
                    """);
        for (Contact contact : contacts) {

            System.out.printf("""
                    %d | %s %s
                    """, contact.getId(), contact.getNome(), contact.getSobreNome());
        }
    }

    public static void addContact() {

        Scanner scanner = new Scanner(System.in);
        Contact newContact = new Contact();
        boolean idEquals = false;
        for (Contact contact : contacts) {
            if (contact.getId().equals(newContact.getId())) {
                idEquals = true;
                break;
            }
        }
        if (!idEquals) {
            System.out.println("Digite o Nome do novo contato:");
            newContact.setNome(scanner.next());

            System.out.println("Digite o Sobrenome do novo contato:");
            newContact.setSobreNome(scanner.next());

            // Adicionando telefones ao novo contato
            List<Phone> phones = Phone.readPhonesFromUser(contacts);
            newContact.setPhones(phones);

            if (!phones.isEmpty()) {
                // Adicionando o novo contato à lista de contatos
                contacts.add(newContact);

                System.out.println("Novo contato adicionado com sucesso!");
                MyFile.writeFile(newContact);// Salva a lista atualizada no arquivo

            } else {
                System.out.println("ERRO: Telefone(s) cadastrado(s), impossivel salvar o contato");
            }


        } else {
            System.out.println("ERRO: Você inseriu um id já existente");
        }


    }

    public static void removeContact() {
        listContact();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do contato que deseja remover:");
        Long contactIdToRemove = scanner.nextLong();

        Contact contactToRemove = null;

        for (Contact contact : contacts) {
            if (contact.getId().equals(contactIdToRemove)) {
                contactToRemove = contact;
                break;
            }
        }

        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("Contato removido com sucesso!");
            MyFile.rewritingFile(contacts);
        } else {
            System.out.println("Contato não encontrado com o ID informado.");
        }
    }

    public static void editContact() {
        listContact();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do contato que deseja alterar:");
        Long contactIdToEdit = scanner.nextLong();

        Contact contactToEdit = null;

        boolean idExist = false;

        for (Contact contact : contacts) {
            if (contact.getId().equals(contactIdToEdit)) {
                idExist = true;
                contactToEdit = contact;

                System.out.println("Digite o Nome do novo contato:");
                contactToEdit.setNome(scanner.next());

                System.out.println("Digite o Sobrenome do novo contato:");
                contactToEdit.setSobreNome(scanner.next());


                List<Phone> phonesVerify = contact.getPhones();
                for (Phone contactPhone : phonesVerify) {
                    System.out.println("Alterando telefone " + contactPhone.getId().toString());
                    System.out.println("Digite o DDD do telefone" + ":");
                    contactPhone.setDdd(scanner.next());

                    System.out.println("Digite o número do telefone" + ":");
                    contactPhone.setNumero(scanner.nextLong());
                }

                MyFile.rewritingFile(contacts);
            }
        }

        if (!idExist){
            System.out.println("ERRO: ID não encontrado");
        }
    }
}
