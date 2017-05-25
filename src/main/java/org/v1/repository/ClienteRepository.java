package org.v1.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.v1.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	List<Cliente> findAll();
	
//	select count(l.id_cliente), l.id_cliente from locacao l inner join cliente c on c.id = l.id_cliente
//			 group by l.id_cliente
	@Query("select COUNT(l.cliente.id), l.cliente.id FROM Locacao l WHERE MONTH(l.dataLocacao) = :mes group by l.cliente.id")
	List<Object[]> findByLocacao(@Param("mes") Integer mes);

	@Query(value = "SELECT DISTINCT l.cliente FROM Locacao l WHERE MONTH(l.dataLocacao) = :mes")
	List<Cliente> findByMesLocacao(@Param("mes") Integer mes);
	
	@Query("SELECT c from Cliente c where c.cpf LIKE :cpf")
	List<Cliente> findByCpf(@Param("cpf") String cpf);
	
}
