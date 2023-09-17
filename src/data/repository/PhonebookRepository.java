package data.repository;

import data.model.Phonebook;

public interface PhonebookRepository {
    Phonebook findById(int id);
    Iterable<Phonebook> findAll();
    Phonebook save(Phonebook phonebook);
    void delete(Phonebook phonebook);
    void clear();
    long count();
}
