package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller	// This means that this class is a Controller
@RequestMapping(path="/employee") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@RequestMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestBody User u) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request


		userRepository.save(u);
		return "response saved";
	}


	@PutMapping("/update")
	public @ResponseBody String updateuser(@RequestBody User E){
		if(E.getId()!=null){
			userRepository.save(E);
		}
		return "Updated";
	}


//	public @ResponseBody String updateEmployee(@PathVariable(value = "id") int id,
//												    @RequestBody User UserDetails) throws ResourceNotFoundException {
//		User employee = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
//
//		employee.setFirstname(userRepository.getFirstname());
//		employee.setLastname(userRepository.getLastname());
//		employee.setRole(userRepository.getRole());
//		employee.setAddress(userRepository.getAddress());
//		final User updatedEmployee = userRepository.save(employee);
//		return "updated";
//	}
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	@DeleteMapping("/del/{id}")
	public String deleteEmpDetails(@PathVariable int id)
	{
		userRepository.deleteById(id);
		return "Deleted";
	}
}