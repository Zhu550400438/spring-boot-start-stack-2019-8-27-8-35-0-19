package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
 
 private Employee[] array = new Employee[] {new Employee(0, "Zhanglong", 30, "Male"), 
   new Employee(1, "Zhaohu", 28, "Female"), 
   new Employee(2, "Wangchao", 23, "Female"), 
   new Employee(3, "Mahan", 44, "Female"), 
   new Employee(4, "Baozheng", 50, "Male")};
 private List<Employee> employees = new ArrayList<Employee>(Arrays.asList(array));;

 @GetMapping("/")
 @ResponseStatus(HttpStatus.OK)
 public List<Employee> getAllEmployees(){
  return employees;
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<Employee> getEmployees(@PathVariable int id){
  for(Employee e : employees) {
   if(e.getId() == id) {
    return ResponseEntity.ok(e);
   }
  }
  return ResponseEntity.notFound().build();
 }
 
 @PostMapping("/")
 @ResponseStatus(HttpStatus.CREATED)
 public List<Employee> addEmployee(@RequestBody Employee employee){
  employees.add(employee);
  
  return employees;
 }
 
 @DeleteMapping("/{id}")
 @ResponseStatus(HttpStatus.CREATED)
 public ResponseEntity<String> deleteEmployee(@PathVariable int id){
  Iterator<Employee> iterator = employees.iterator();
  while (iterator.hasNext()) {
   if(iterator.next().getId() == id) {
    iterator.remove();
    return ResponseEntity.ok(id + "");
   }
  }
  return ResponseEntity.notFound().build();
 }
 
 @PutMapping("/{id}")
 @ResponseStatus(HttpStatus.CREATED)
 public ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee employee,@PathVariable int id){
  for(Employee e : employees) {
   if(e.getId() == employee.getId()) {
    e.setAge(employee.getAge());
    e.setGender(employee.getGender());
    e.setName(employee.getName());
   }
  }
  return ResponseEntity.notFound().build();
 }
}