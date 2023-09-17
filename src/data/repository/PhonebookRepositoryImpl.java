package data.repository;

import data.model.Phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhonebookRepositoryImpl implements PhonebookRepository{
    private int count = 0;
    private List<Phonebook> phonebooks = new ArrayList<>();

    @Override
    public Phonebook findById(int id) {
        for (Phonebook phonebook:phonebooks) {
            if (phonebook.getId() == id) return phonebook;
        }
        return null;
    }

    @Override
    public Iterable<Phonebook> findAll() {
        return phonebooks;
    }

    @Override
    public Phonebook save(Phonebook phonebook) {
        boolean phonebookDoesNotExist = phonebook.getId() == 0;
        if (phonebookDoesNotExist) saveNew(phonebook);
        else update(phonebook);
        return phonebook;
    }

    private void update(Phonebook phonebook) {
        Phonebook newPhonebook = findById(phonebook.getId());
        newPhonebook.setUsername(phonebook.getUsername());
    }

    private void saveNew(Phonebook phonebook) {
        phonebook.setId(generateId());
        phonebooks.add(phonebook);
        count();
    }

    private int generateId() {
        return ++count;
    }

    @Override
    public void delete(Phonebook phonebook) {
        phonebooks.remove(phonebook);
    }

    @Override
    public void clear() {
        count -= phonebooks.size();
        phonebooks.removeAll(phonebooks);
    }

    @Override
    public long count() {
        return count;
    }
}
