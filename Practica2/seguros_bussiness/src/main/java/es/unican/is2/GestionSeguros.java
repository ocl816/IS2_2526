package es.unican.is2;

import java.util.List;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    IClientesDAO clientesDAO;
    ISegurosDAO segurosDAO;
    
    /**
     * Crea el objeto GestionSeguros
     * @param clientesDAO DAO de los clientes
     * @param segurosDAO DAO de los seguros
     */
    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
    }

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        Cliente cNuevo = clientesDAO.cliente(c.getDni());
        if (cNuevo != null) {
            return null;
        } 
        clientesDAO.creaCliente(c);
        return c;
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida,DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return c;
        }

        if (!c.getSeguros().isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros asociados");    
        }

        clientesDAO.eliminaCliente(dni);
        return c;
    }

    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        Seguro sNuevo = segurosDAO.seguro(s.getId());

        if (sNuevo != null) {
            throw new OperacionNoValida("El seguro ya existe");
        }

        segurosDAO.creaSeguro(s);
        return s;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null;
        }
                
        List<Seguro> seguros = c.getSeguros();

        if (seguros.isEmpty()) {
            return null;
        }
        boolean perteneceSeguro = false;
        Seguro s = null;
        for (Seguro seguro : seguros) {
            if (seguro.getMatricula().equals(matricula)) {
                perteneceSeguro = true;
                s = seguro;
            }
        }

        if (!perteneceSeguro) {
            throw new OperacionNoValida("No existe ningun seguro asociado a dicha matricula para el cliente");
        }

        segurosDAO.eliminaSeguro(s.getId());
        return s;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = segurosDAO.seguroPorMatricula(matricula);

        if (s == null) {
            return null;
        }
        
        s.setConductorAdicional(conductor);
        segurosDAO.actualizaSeguro(s);
        return s;
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        Cliente c = clientesDAO.cliente(dni);

        if (c == null) {
            return null;
        }

        return c;
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        Seguro s = segurosDAO.seguroPorMatricula(matricula);

        if (s == null) {
            return null;
        }

        return s;
    }

}