package com.example.ContractManager.repository;

import com.example.ContractManager.model.Contract;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Long> {


}
