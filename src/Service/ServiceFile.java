package Service;

import Contact.Contact;
import Phone.Phone;
import java.util.ArrayList;
import java.util.List;

public class ServiceFile {

    public static Contact getContact(String line) {
        String[] data = line.split(";");

        Contact contact = new Contact();
        contact.setId(Long.parseLong(data[0]));
        contact.setNome(data[1]);
        contact.setSobreNome(data[2]);

        List<Phone> phones = new ArrayList<>();
        for (int i = 3; i < data.length; i += 3) {
            Phone phone = new Phone();
            phone.setDdd(data[i + 1]);
            phone.setNumero(Long.parseLong(data[i + 2]));
            phones.add(phone);
        }
        contact.setPhones(phones);
        return contact;
    }
}
