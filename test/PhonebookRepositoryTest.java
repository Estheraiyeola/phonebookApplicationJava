import data.model.Phonebook;
import data.repository.PhonebookRepository;
import data.repository.PhonebookRepositoryImpl;
import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PhonebookRepositoryTest {
    private PhonebookRepository phonebookRepository;

    @BeforeEach
    public void startWithThis(){
        phonebookRepository = new PhonebookRepositoryImpl();
    }
    @Test
    public void testThatAPhonebook_CanBeSaved(){
        Phonebook phonebook = new Phonebook();
        phonebookRepository.save(phonebook);

        assertEquals(1, phonebookRepository.count());
    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Be_Found(){
        Phonebook phonebook = new Phonebook();
        phonebook.setUsername("esther");
        phonebook.setPassword("password");
        phonebookRepository.save(phonebook);

        assertEquals(1, phonebookRepository.count());
        assertEquals("esther", phonebookRepository.findById(1).getUsername());
    }
    @Test
    public void testThatAPhonebook_CanBeSaved_And_Can_Be_Updated(){
        Phonebook phonebook = new Phonebook();
        phonebook.setUsername("esther");
        phonebook.setPassword("password");
        phonebookRepository.save(phonebook);

        assertEquals(1, phonebookRepository.count());
        assertEquals("esther", phonebookRepository.findById(1).getUsername());

        Phonebook updatedPhonebook = phonebookRepository.findById(1);
        updatedPhonebook.setUsername("Esther");

        assertEquals("Esther", phonebookRepository.findById(1).getUsername());
    }
    @Test
    public void testThatPhonebooks_CanBeSaved_And_Can_All_Be_Printed_Out(){
        Phonebook phonebook = new Phonebook();
        phonebook.setUsername("esther");
        phonebook.setPassword("password");
        phonebookRepository.save(phonebook);

        Phonebook phonebook1 = new Phonebook();
        phonebook1.setUsername("Esther");
        phonebook1.setPassword("password");
        phonebookRepository.save(phonebook1);

        Phonebook phonebook2 = new Phonebook();
        phonebook2.setUsername("Deborah");
        phonebook2.setPassword("password");
        phonebookRepository.save(phonebook2);

        assertEquals(3, phonebookRepository.count());
        assertEquals("esther", phonebookRepository.findById(1).getUsername());

        List<Phonebook> expectedList = List.of(new Phonebook[]{phonebook, phonebook1, phonebook2});
        assertEquals(expectedList, phonebookRepository.findAll());
    }

    @Test
    public void testThat_A_Phonebook_CanBeSaved_And_Can_Be_Deleted(){
        Phonebook phonebook = new Phonebook();
        phonebook.setUsername("esther");
        phonebook.setPassword("password");
        phonebookRepository.save(phonebook);

        Phonebook phonebook1 = new Phonebook();
        phonebook1.setUsername("Esther");
        phonebook1.setPassword("password");
        phonebookRepository.save(phonebook1);

        Phonebook phonebook2 = new Phonebook();
        phonebook2.setUsername("Deborah");
        phonebook2.setPassword("password");
        phonebookRepository.save(phonebook2);

        assertEquals(3, phonebookRepository.count());
        assertEquals("esther", phonebookRepository.findById(1).getUsername());
        assertEquals("Esther", phonebookRepository.findById(2).getUsername());
        assertEquals("Deborah", phonebookRepository.findById(3).getUsername());

        phonebookRepository.delete(phonebook1);
        assertNull(phonebookRepository.findById(2));
    }
    @Test
    public void testThat_All_PhonebooksCanBeDeleted(){
        Phonebook phonebook = new Phonebook();
        phonebook.setUsername("esther");
        phonebook.setPassword("password");
        phonebookRepository.save(phonebook);

        Phonebook phonebook1 = new Phonebook();
        phonebook1.setUsername("Esther");
        phonebook1.setPassword("password");
        phonebookRepository.save(phonebook1);

        Phonebook phonebook2 = new Phonebook();
        phonebook2.setUsername("Deborah");
        phonebook2.setPassword("password");
        phonebookRepository.save(phonebook2);

        assertEquals(3, phonebookRepository.count());
        assertEquals("esther", phonebookRepository.findById(1).getUsername());
        assertEquals("Esther", phonebookRepository.findById(2).getUsername());
        assertEquals("Deborah", phonebookRepository.findById(3).getUsername());

        phonebookRepository.clear();
        assertNull(phonebookRepository.findById(2));
    }
}
