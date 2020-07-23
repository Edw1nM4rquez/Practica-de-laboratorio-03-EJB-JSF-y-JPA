package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Empleado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public EmpleadoFacade() { super(Empleado.class); }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Empleado iniciarSesion(Empleado emp){
        Empleado empleado = null;
        String consulta;
        try {
            consulta = "SELECT u FROM Empleado u WHERE u.correo = ?1 and u.password = ?2";
            Query query = entityManager.createQuery(consulta);
            query.setParameter(1, emp.getCorreo());
            query.setParameter(2,emp.getPassword());
            List<Empleado> lista = query.getResultList();
            if (!lista.isEmpty()){
                empleado = lista.get(0);
            }
        }catch (Exception e){

        }
        return empleado;
    }

}

