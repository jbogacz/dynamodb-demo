package com.chmurowisko.rest;

import com.chmurowisko.model.Training;
import com.chmurowisko.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jbogacz on 24.05.2017.
 */
@RestController
@RequestMapping("/training")
public class TrainingResource {

    @Autowired
    private TrainingRepository trainingRepository;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Training>> list() {
        return new ResponseEntity<>(
                trainingRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Training training) {
        Date now = new Date();
        training.setCreateDate(now);
        training.setUpdateDate(now);
        trainingRepository.save(training);
        return new ResponseEntity<>("SAVED", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Training training) {
        Assert.isTrue(
                trainingRepository.exists(training.getTrainingId()), "Can't update, entity doesn't exist");
        training.setUpdateDate(new Date());
        trainingRepository.save(training);
        return new ResponseEntity<>("UPDATED", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestBody Training training) {
        trainingRepository.delete(training);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

}
