package org.sid.dao.repository;

import org.sid.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

//                                                     <type,typeOfId> 
public interface ClientRepository extends JpaRepository<Client, Long>{
	

}
