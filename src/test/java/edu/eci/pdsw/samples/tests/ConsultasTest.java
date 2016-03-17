/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesPropio;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class ConsultasTest {
    
    
    public ConsultasTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Clases de Equivalencia:
     * -Paciente no existe
     * -Paciente Existe
     */
    @Test
    public void registroPacienteTest(){
        ServiciosPacientes sp = new ServiciosPacientesPropio();
        Consulta consul1 = new Consulta(null,"primera consulta paciente");
        Paciente p = new Paciente(1234,"CC","Pepito Perez",null);
        /**
        try{
            sp.agregarConsultaAPaciente(123, "dermatologia", consul1);
        }
        catch (ExcepcionServiciosPacientes e){
            Assert.fail("Fallo agregando una consulta a un paciente que no existe");
        }
        
        try{
            sp.agregarConsultaAPaciente(1234, "dermatologia", consul1);
        }
        catch (ExcepcionServiciosPacientes e){
            Assert.fail("Fallo agregando una consulta a un paciente que no existe");
          
        }
        
        try{
            
            sp.agregarConsultaAPaciente(1234, "dermatologia", consul1);
        }
        catch (ExcepcionServiciosPacientes e){
            Assert.fail("Fallo porque el paciente ya tiene la consulta");
        }
        * * **/
        
    }
    
    
}
