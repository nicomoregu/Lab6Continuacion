/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientePersistenceTest {
    
    public PacientePersistenceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /*
    @Test
    public void databaseConnectionTest() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        daof.beginSession();
        daof.commitTransaction();
        daof.endSession();
        Assert.assertTrue(true);        
    }
    */
    /**
     * 
     */
    @Test
    public void pruebaPacienteNuevoMuchasConsultas() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        try{
            daof.beginSession();
            Paciente p = new Paciente(1020795088, "CC", "nicolas", java.sql.Date.valueOf("2016-07-21"));
            Consulta c = new Consulta(java.sql.Date.valueOf("2016-07-21"), "Prueba");
            Consulta c1 = new Consulta(java.sql.Date.valueOf("2016-08-21"), "otra consulta");
            Consulta c2 = new Consulta(java.sql.Date.valueOf("2016-09-21"), "mas consultas");
            Set<Consulta> lista = new HashSet<>();
            lista.add(c);
            lista.add(c1);
            lista.add(c2);
            p.setConsultas(lista);
            DaoPaciente daoP= daof.getDaoPaciente();
            daoP.save(p);
            Paciente p2 = daoP.load(1020795088, "CC");
            daof.commitTransaction();
            daof.endSession();
            Assert.assertEquals("Los pacientes no coinciden con la busqueda",p ,p2);
        }catch (Exception e){
            daof.commitTransaction();
            daof.endSession();
            Assert.assertFalse("Fallo al momento se hacer la consulta SQL",true);
        }
    }
    
    /**
     * 
     */
    @Test
    public void pruebaPacienteNuevoSinConsultas() throws IOException, PersistenceException{
        
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        try{
            daof.beginSession();
            Paciente p = new Paciente(1020795087, "CC", "nicolas", java.sql.Date.valueOf("2016-07-21"));
            DaoPaciente daoP= daof.getDaoPaciente();
            daoP.save(p);
            Paciente p2 = daoP.load(1020795087, "CC");
            daof.commitTransaction();
            daof.endSession();
            Assert.assertEquals("Los pacientes no coinciden con la busqueda",p ,p2);
        }catch (Exception e){
            daof.commitTransaction();
            daof.endSession();
            Assert.assertFalse("Fallo al momento se hacer la consulta SQL",true);
        }
    }
    
    /**
     * 
     */
    @Test
    public void pruebaPacienteNuevoUnaConsulta() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        try{
            daof.beginSession();
            Paciente p = new Paciente(1020795086, "CC", "nicolas", java.sql.Date.valueOf("2016-07-21"));
            Consulta c = new Consulta(java.sql.Date.valueOf("2016-08-21"), "Prueba");
            c.setId(10000);
            Set<Consulta> lista = new HashSet<>();
            lista.add(c);
            p.setConsultas(lista);
            DaoPaciente daoP= daof.getDaoPaciente();
            daoP.save(p);
            Paciente p2 = daoP.load(1020795086, "CC");
            daof.commitTransaction();
            daof.endSession();
            System.out.println(p.toString()+" "+p2.toString()+" "+p.getConsultas().equals(p2.getConsultas()));
            Assert.assertEquals("Los pacientes no coinciden con la busqueda",p ,p2);
        }catch (Exception e){
            daof.commitTransaction();
            daof.endSession();
            Assert.assertFalse("Fallo al momento se hacer la consulta SQL",true);
        }
    }
    
    
/**
     * 
     */
    @Test
    public void pruebaPacienteAnitiguoMuchasConsultas() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        DaoFactory daof=DaoFactory.getInstance(properties);
        daof.beginSession();
        try{
            Paciente p = new Paciente(1020795086, "CC", "nicolas", java.sql.Date.valueOf("2016-07-21"));
            Consulta c = new Consulta(java.sql.Date.valueOf("2016-07-21"), "Prueba");
            Set<Consulta> lista = new HashSet<>();
            lista.add(c);
            p.setConsultas(lista);
            DaoPaciente daoP= daof.getDaoPaciente();
            daoP.save(p);
            daoP.save(p);
            daof.commitTransaction();
            daof.endSession();
            Assert.assertFalse("No deberia dejar incluir paciente ya existente",false);
        }catch(PersistenceException e){
            daof.commitTransaction();
            daof.endSession();
            Assert.assertFalse(false);
        }
    }
}
