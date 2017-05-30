package com.chmurowisko.repository;

import com.chmurowisko.model.Training;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jbogacz on 25.05.2017.
 */

@EnableScan
public interface TrainingRepository extends CrudRepository<Training, String> {

}
