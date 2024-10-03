package ivytech.fp.com;

import entites.Doctor;
import entites.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PatientQueries implements CommonQueries {

    private EntityManager entityManager;
    public PatientQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Patient> findAll() {
        TypedQuery<Patient> query = entityManager.createQuery("SELECT p FROM Patient p", Patient.class);
        return query.getResultList();


    }

    @Override
    public Patient findById(int id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void save(Object patient) {

        Patient conv_patient = (Patient) patient;
        entityManager.getTransaction().begin();
        if(conv_patient.getId() == null){
            entityManager.persist(conv_patient);
        }
        else{
            entityManager.merge(conv_patient);
        }
        entityManager.getTransaction().commit();



    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Patient patient = findById(id);
        if(patient != null){
            entityManager.remove(patient);
        }
        entityManager.getTransaction().commit();

    }
    public List<Patient> findPatientsByDoctor(int doctor){
        TypedQuery<Patient> query = entityManager.createQuery("select p from Patient p where p.dr.id = :doctor ", Patient.class)
                .setParameter("doctor",doctor);
        return query.getResultList();
    }
    public void saveDrToPatient(Object patient, int drID) {

        Patient patientAddDr = (Patient) patient;
        Doctor drToAdd = entityManager.find(Doctor.class,drID);
        patientAddDr.setDr(drToAdd);
        entityManager.getTransaction().begin();
        if(patientAddDr.getId() == null){
            entityManager.persist(patientAddDr);
        }
        else{
            entityManager.merge(patientAddDr);
        }
        entityManager.getTransaction().commit();



    }


}
