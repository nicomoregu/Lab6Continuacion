/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.jdbcimpl.JDBCDaoFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author nicolas
 */
public class ServiciosPacientesPropio extends ServiciosPacientes{
    Connection con;
    Properties properties;
    InputStream input ;
    JDBCDaoFactory daof;
    DaoPaciente dao;
    List<Paciente> lista = new ArrayList<Paciente>();
    public ServiciosPacientesPropio() {
        input = null;
        
        try{
            //input = ServiciosPacientes.class.getResource("applicationconfig.properties").openStream();
            input=ServiciosPacientes.class.getResource("applicationconfig_Test.properties").openStream();
            properties=new Properties();
            System.out.println("Paso 2");
            properties.load(input);
            System.out.println("Paso 3");
            daof=new JDBCDaoFactory(properties);
            dao=daof.getDaoPaciente();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
        Paciente p=null;
        
        try{
            daof.beginSession();
            dao =daof.getDaoPaciente();
            p=dao.load(idPaciente, tipoid);
            daof.commitTransaction();
            daof.endSession();
        }catch (PersistenceException e){
            
        }
        return p;
    }

    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
        try{
            daof.beginSession();
            dao =daof.getDaoPaciente();
            dao.save(p);
            daof.commitTransaction();
            daof.endSession();
            lista.add(p);
        }catch (PersistenceException e){
            
        }
    }

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
        try{
            Paciente p = dao.load(idPaciente, tipoid);
            Set<Consulta> consultas = p.getConsultas();
            consultas.add(c);
            dao.update(p);
        }catch (Exception e){
        
        }
    }

    @Override
    public List<Paciente> getPacientes() {
        List<Paciente> lista = new ArrayList<>();
        try{
            lista= dao.cargarPacientes();
            System.out.println("Tamañan de la lista "+lista.size());
        }catch(Exception e){
        }
        return lista;
    }
    
}
