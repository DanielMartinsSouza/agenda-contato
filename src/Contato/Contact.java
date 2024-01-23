package Contato;

import Phone.Phone;
import Service.ServiceFile;

import java.util.List;
import java.util.Scanner;

public class Contact {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Phone> phones;

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

    public static List<Contact> contacts = ServiceFile.readContactsFromFile("/home/daniel/Documentos/AdaTech/agenda-contato/src/DataBase/db.txt");

    public void listContact() {
        System.out.println("-- Listando Contatos: --");
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

    public void addContact() {

        Scanner scanner = new Scanner(System.in);
        Contact newContact = new Contact();

        System.out.println("Digite o ID do novo contato:");
        newContact.setId(scanner.nextLong());

        boolean idEquals = false;
        for (Contact contact : contacts) {
            if (contact.getId().equals(newContact.getId())) {
                idEquals = true;
            }
        }
        if (!idEquals) {
            System.out.println("Digite o Nome do novo contato:");
            newContact.setNome(scanner.next());

            System.out.println("Digite o Sobrenome do novo contato:");
            newContact.setSobreNome(scanner.next());

            // Adicionando telefones ao novo contato
            List<Phone> phones = Phone.readPhonesFromUser();
            newContact.setPhones(phones);

            // Adicionando o novo contato à lista de contatos
            contacts.add(newContact);

            System.out.println("Novo contato adicionado com sucesso!");
            ServiceFile.saveContactsToFile(contacts, "/home/daniel/Documentos/AdaTech/agenda-contato/src/DataBase/db.txt"); // Salva a lista atualizada no arquivo

        }else {
            System.out.println("Você inseriu um id já existente");
        }


    }

    public void removeContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do contato que deseja remover:");
        Long contactIdToRemove = scanner.nextLong();

        Contact contactToRemove = null;

        // Procurando o contato na lista
        for (Contact contact : contacts) {
            if (contact.getId().equals(contactIdToRemove)) {
                contactToRemove = contact;
                break;
            }
        }

        // Removendo o contato se encontrado
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("Contato removido com sucesso!");
            ServiceFile.saveContactsToFile(contacts, "/home/daniel/Documentos/AdaTech/agenda-contato/src/DataBase/db.txt"); // Salva a lista atualizada no arquivo
        } else {
            System.out.println("Contato não encontrado com o ID informado.");
        }
    }
}
