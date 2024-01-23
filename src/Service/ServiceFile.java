package Service;

import Contato.Contact;
import Phone.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceFile {
    //    private static List<Contact> readContactsFile(String filename){
//        List<Contact> contacts = new ArrayList<>();
//        try{
//            Scanner scanner = new Scanner(new File(fileName));
//            while (scanner.hasNextLine()){
//                String line = scanner.nextLine();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return contacts;
    public static List<Contact> readContactsFromFile(String filename) {
        List<Contact> contacts = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(filename));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");

                Contact contact = new Contact();
                contact.setId(Long.parseLong(data[0]));
                contact.setNome(data[1]);
                contact.setSobreNome(data[2]);

                List<Phone> phones = new ArrayList<>();
                for (int i = 3; i < data.length; i += 2) {
                    Phone phone = new Phone();
                    phone.setDdd(data[i]);
                    phone.setNumero(Long.parseLong(data[i + 1]));
                    phones.add(phone);
                }
                contact.setPhones(phones);

                contacts.add(contact);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + filename);
        }

        return contacts;
    }

    public static void saveContactsToFile(List<Contact> contacts, String filename) {
        try (
            PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.print(contact.getId() + ";");
                writer.print(contact.getNome() + ";");
                writer.print(contact.getSobreNome() + ";");

                List<Phone> phones = contact.getPhones();
                for (Phone phone : phones) {
                    writer.print(phone.getDdd() + ";");
                    writer.print(phone.getNumero() + ";");
                }

                writer.println(); // Nova linha para o próximo contato
            }

            System.out.println("Contatos salvos no arquivo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar contatos no arquivo: " + e.getMessage());
        }
    }
}
