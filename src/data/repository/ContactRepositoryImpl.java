package data.repository;

import data.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository{
    private long count;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public Contact findById(int id) {
        for (Contact contact:contacts) {
            if (contact.getId() == id) return contact;
        }
        return null;
    }

    @Override
    public Iterable<Contact> findAll() {
        return contacts;
    }

    @Override
    public Contact save(Contact contact) {
        boolean contactDoesNotExist = contact.getId() == 0;
        if (contactDoesNotExist) saveNew(contact);
        else update(contact);
        return contact;
    }

    private void update(Contact contact) {
        Contact newContact = findById(contact.getId());
        newContact.setContact(contact);
    }

    private void saveNew(Contact contact) {
        contact.setId(generateId());
        contacts.add(contact);
        count++;
    }

    private int generateId() {
        return (int) (count + 1);
    }

    @Override
    public void delete(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void clear() {
        count -= contacts.size();
        contacts.removeAll(contacts);
    }

    @Override
    public long count() {
        return count;
    }
}
