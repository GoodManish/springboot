package com.sample.app.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.util.EmployeeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/")
@Api(tags = { "This section contains all user Speicifc Operations" })
public class EmployeeController {

	@ApiOperation(value = "Retrieve all the employee details", notes = "This API Retrieve all the employee details")
	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> all() {
		return ResponseEntity.ok(EmployeeUtil.all());
	}

	@ApiOperation(value = "Create new employee", notes = "Create new employee")
	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> create(@RequestBody Employee emp) {
		return new ResponseEntity<>(EmployeeUtil.create(emp), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Get user", notes = "Get employee by id")
	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> byId(@PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(emp);

	}

	@ApiOperation(value = "Delete employee by id", notes = "Delete employee by id")
	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteById(@PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.delete(id));
	}

	@ApiOperation(value = "Update employee by id", notes = "Update employee by id")
	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateById(@PathVariable int id, @RequestBody Employee emp) {

		Employee currentEmp = EmployeeUtil.byId(id);

		if (currentEmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.updateById(id, emp));
	}
	
	@ApiOperation(value = "Retrieve all the employee that contains this name", notes = "This API Retrieve all the employee that contains this name")
	@RequestMapping(value = "employees/by-name", method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> getEmployeeContaintsThisName(@RequestParam(value = "empName") String empName) {
		return ResponseEntity.ok(EmployeeUtil.getEmployeesContainThisName(empName));
	}

}	