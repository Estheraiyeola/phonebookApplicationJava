package data.repository;

import data.model.Contact;

public interface ContactRepository {
    Contact findById(int id);
    Iterable<Contact> findAll();
    Contact save(Contact contact);
    void delete(Contact contact);
    void clear();
    long count();

}
