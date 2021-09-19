package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value =
    "SELECT emp.cpf, emp.enome, dep.dnome " +
            "FROM empregados emp " +
            " INNER JOIN departamentos dep ON emp.dnumero = dep.dnumero " +
            " LEFT JOIN  trabalha ON emp.cpf = trabalha.cpf_emp " +
            "WHERE trabalha.cpf_emp is NULL " +
            "ORDER BY emp.CPF;")
    List<EmpregadoDeptProjection> search1();


    @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(emp.cpf, emp.enome, emp.departamento.dnome) " +
           "FROM Empregado emp " +
            "WHERE emp.cpf NOT IN (" +
            "SELECT emp.cpf  " +
            "FROM Empregado emp " +
           " INNER JOIN  emp.projetosOndeTrabalha ) " +
           "ORDER BY emp.cpf")
    List<EmpregadoDeptDTO> search2();

}
