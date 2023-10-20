package cdr.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cdr.demo.Entity.Registration;

public interface Repository extends JpaRepository<Registration, Integer> {

	
	List<Registration> findByphoneNumber(String phoneNumber);

}
