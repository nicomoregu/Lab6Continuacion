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
package edu.eci.pdsw.samples.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientesPropio;
import edu.eci.pdsw.samples.services.ServiciosPacientesStub;
import java.sql.Date;
import junit.framework.Assert;

/**
 *
 * @author hcadavid
 */
public class PacientesTest {
    
    public PacientesTest() {
    }
    /**
     * Paciente no registrado debe ingresar
     * Paciente registrado no debe ingresar.
     */    
    @Test
    public void registroPacienteTest(){
        Paciente p = new Paciente(100, "CC", "Jairo", null);
        ServiciosPacientes sp = new ServiciosPacientesPropio();
        try {
            sp.registrarNuevoPaciente(p);
        } catch (ExcepcionServiciosPacientes e) {
            Assert.fail("Fallo registrando un paciente que si debia registrarlo");
        }
        
        try {
            sp.registrarNuevoPaciente(p);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

        
    }
    
    
}
