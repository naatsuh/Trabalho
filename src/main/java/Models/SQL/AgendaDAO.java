package Models.SQL;

import Models.Agenda;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AgendaDAO {

    private static AgendaDAO instance;
    protected EntityManager entityManager;

    public static AgendaDAO getInstance(){
        if (instance == null){
            instance = new AgendaDAO();
        }

        return instance;
    }

    private AgendaDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Agenda getById(final int id) {
        return entityManager.find(Agenda.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Agenda> findAll() {
        return entityManager.createQuery("FROM " + Agenda.class.getName()).getResultList();
    }

    public void persist(Agenda agenda) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(agenda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Agenda agenda) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(agenda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Agenda agenda) {
        try {
            entityManager.getTransaction().begin();
            agenda = entityManager.find(Agenda.class, agenda.get_id());
            entityManager.remove(agenda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
        try {
            Agenda agenda = getById(id);
            remove(agenda);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}