package ch.sbb.nets.favorites.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by ELIAS_LEVIS on 30/7/2017.
 */
public class GenericJpaController<T> {
  private JpaRepository<T, Long> dataRepository;

  public GenericJpaController(final JpaRepository<T, Long> oracleRepository) {
    this.dataRepository = oracleRepository;
  }

  public Iterable<T> getAllRecords() {
    return dataRepository.findAll();
  }

  public ResponseEntity<T> getRecordById(final Long id) {
    final T record = dataRepository.findOne(id);
    HttpStatus responseStatus = HttpStatus.OK;
    if (record == null) {
      responseStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<T>(record, responseStatus);
  }

  public ResponseEntity<T> createRecord(final T record) {
    dataRepository.save(record);
    return new ResponseEntity<T>(record, HttpStatus.OK);
  }

  private boolean isIdMethod(final  Method method) {
    boolean isId = false;
    final Annotation[] annotations = method.getAnnotations();
    for (final Annotation annotation : annotations) {
      if (annotation.toString().contains("Id")) {
        isId = true;
      }
    }
    return isId;
  }

  private void copyAllAttributes(final Object fromRecord, final Object toRecord) {
    final Method[] allMethods = fromRecord.getClass().getDeclaredMethods();
    for (final Method method : allMethods) {
      if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
        if (!isIdMethod(method)) {
          final String setterMethodName = method.getName().replace("get", "set");
          try {
            final Method setterMethod = fromRecord.getClass().getMethod(setterMethodName, method.getReturnType());
            final T retVal = (T) method.invoke(fromRecord);
            setterMethod.invoke(toRecord, retVal);
          } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException nse) {

          }
        }
      }
    }
  }

  public ResponseEntity<T> updateRecord(final Long id, final T record) {
    final ResponseEntity<T> responseEntity;
    final T recordToUpdate = dataRepository.findOne(id);
    if (recordToUpdate == null) {
      responseEntity = new ResponseEntity<T>(HttpStatus.NOT_FOUND);
    } else {
      copyAllAttributes(record, recordToUpdate);
      dataRepository.save(recordToUpdate);
      responseEntity = new ResponseEntity<T>(recordToUpdate, HttpStatus.OK);
    }
    return responseEntity;
  }

  public ResponseEntity<T> deleteRecord(final Long id) {
    final ResponseEntity<T> responseEntity;
    final T recordToDelete = dataRepository.findOne(id);
    if (recordToDelete == null) {
      responseEntity = new ResponseEntity<T>(HttpStatus.NOT_FOUND);
    } else {
      dataRepository.delete(recordToDelete);
      responseEntity = new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }
    return responseEntity;
  }
}
