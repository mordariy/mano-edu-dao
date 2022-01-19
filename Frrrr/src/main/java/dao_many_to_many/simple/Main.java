package dao_many_to_many.simple;

import dao_many_to_many.dao.imple.PersonDao;

public class Main {

    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();
        personDao.getAll().forEach(n -> System.out.println(n.getName() + n.getSurname() + n.getID()));
    }

}
