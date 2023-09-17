import data.model.Contact;
import data.repository.ContactRepository;
import data.repository.ContactRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryImplTest {
    private ContactRepository contactRepository;

    @BeforeEach
    public void startWithThis(){
        contactRepository = new ContactRepositoryImpl();
    }
    @Test
    public void testThatA_Contact_Can_BeSaved() {
        Contact contact = new Contact();
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
    }
    @Test
    public void testThatA_Contact_Can_BeSaved_And_Can_Found() {
        Contact contact = new Contact();
        contact.setName("Esther");
        contact.setTelephoneNumber("08138112782");
        contact.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esther", contactRepository.findById(1).getName());
    }
    @Test
    public void testThatA_Contact_Can_BeSaved_And_Can_Be_Updated() {
        Contact contact = new Contact();
        contact.setName("Esther");
        contact.setTelephoneNumber("08138112782");
        contact.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esther", contactRepository.findById(1).getName());

        Contact updatedContact = contactRepository.findById(1);
        updatedContact.setName("Esteri");
        contactRepository.save(updatedContact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esteri", contactRepository.findById(1).getName());
    }

    @Test
    public void testThatAll_ContactsSavedCan_BePrintedOut() {
        Contact contact = new Contact();
        contact.setName("Esther");
        contact.setTelephoneNumber("08138112782");
        contact.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esther", contactRepository.findById(1).getName());

        Contact contact1 = new Contact();
        contact1.setName("Esteri");
        contact1.setTelephoneNumber("08138112782");
        contact1.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact1);

        assertEquals(2, contactRepository.count());
        assertEquals("Esteri", contactRepository.findById(2).getName());

        List<Contact> contactList = List.of(new Contact[]{contact, contact1});
        assertEquals(contactList, contactRepository.findAll());
    }



    @Test
    public void testThatA_Contact_Can_BeDeletedFromTheContactList() {
        Contact contact = new Contact();
        contact.setName("Esther");
        contact.setTelephoneNumber("08138112782");
        contact.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esther", contactRepository.findById(1).getName());

        Contact contact1 = new Contact();
        contact1.setName("Esteri");
        contact1.setTelephoneNumber("08138112782");
        contact1.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact1);

        assertEquals(2, contactRepository.count());
        assertEquals("Esteri", contactRepository.findById(2).getName());

        contactRepository.delete(contact1);
        assertNull(contactRepository.findById(2));
    }

    @Test
    public void testThatAll_Contacts_CanBe_Cleared() {
        Contact contact = new Contact();
        contact.setName("Esther");
        contact.setTelephoneNumber("08138112782");
        contact.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact);

        assertEquals(1, contactRepository.count());
        assertEquals("Esther", contactRepository.findById(1).getName());

        Contact contact1 = new Contact();
        contact1.setName("Esteri");
        contact1.setTelephoneNumber("08138112782");
        contact1.setEmail("estheraiyeola@yahoo.com");
        contactRepository.save(contact1);

        assertEquals(2, contactRepository.count());
        assertEquals("Esteri", contactRepository.findById(2).getName());

        contactRepository.clear();
        assertNull(contactRepository.findById(1));
        assertNull(contactRepository.findById(2));

    }


}