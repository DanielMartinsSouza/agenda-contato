package File;

import Contact.Contact;
import Phone.Phone;
import Service.ServiceFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MyFile {

    private static final String FILE_PATH = "D:\\AdaTech\\agenda-contato\\contatos.txt";

    private static File createFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }

    public static void writeFile(Contact contact) {
        try {
            File file = createFile();
            StringBuilder line;
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            line = new StringBuilder(contact.getId().toString() + ";");
            line.append(contact.getNome()).append(";");
            line.append(contact.getSobreNome()).append(";");

            List<Phone> phones = contact.getPhones();
            for (Phone phone : phones) {
                line.append(phone.getId()).append(";");
                line.append(phone.getDdd()).append(";");
                line.append(phone.getNumero()).append(";");
            }

            bw.write(line.toString());
            bw.newLine();



            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rewritingFile(List<Contact> contacts) {
        try (
                PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.print(contact.getId() + ";");
                writer.print(contact.getNome() + ";");
                writer.print(contact.getSobreNome() + ";");

                List<Phone> phones = contact.getPhones();
                for (Phone phone : phones) {
                    writer.print(phone.getId() + ";");
                    writer.print(phone.getDdd() + ";");
                    writer.print(phone.getNumero() + ";");
                }

                writer.println();
            }

            System.out.println("Contatos salvos no arquivo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar contatos no arquivo: " + e.getMessage());
        }
    }

    public static List<Contact> readFile() {
        List<Contact> contacts = new ArrayList<>();
        File file = createFile();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(fileReader);
            String line = bf.readLine();

            while (line != null) {
                Contact contact = ServiceFile.getContact(line);
                contacts.add(contact);
                line = bf.readLine();
            }

            bf.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

}
