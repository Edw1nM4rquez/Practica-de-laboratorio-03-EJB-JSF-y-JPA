package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente>{

    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public ClienteFacade() { super(Cliente.class); }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Cliente buscar(Cliente cli){
        Cliente cliente = null;
        String consulta;
        try {
            consulta = "SELECT c FROM Cliente c WHERE c.cedula = ?1";
            Query query = entityManager.createQuery(consulta);
            query.setParameter(1,cli.getCedula());
            List<Cliente> lista = query.getResultList();
            if (!lista.isEmpty()){
                cliente = lista.get(0);
            }
        }catch (Exception e){

        }
        return cliente;
    }

}
