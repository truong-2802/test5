//package com.example.test5.controller;
//
//import com.example.test5.entity.Employee;
//import com.example.test5.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/list")
//    public String listEmployees(
//            @RequestParam(defaultValue = "1") int pageNo,
//            @RequestParam(defaultValue = "10") int pageSize,
//            @RequestParam(defaultValue = "id") String sortField,
//            @RequestParam(defaultValue = "asc") String sortDir,
//            @RequestParam(defaultValue = "") String keyword,
//            Model model) {
//
//        Page<Employee> page = employeeService.listAllEmployees(pageNo, pageSize, sortField, sortDir, keyword, null, null, null);
//        model.addAttribute("employees", page.getContent());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("keyword", keyword);
//
//        return "list";
//    }
//
//    @GetMapping("/create")
//    public String createEmployeeForm(Model model) {
//        model.addAttribute("employee", new Employee());
//        return "create";
//    }
//
//    @PostMapping("/create")
//    public String createEmployee(@ModelAttribute Employee employee) {
//        employeeService.createEmployee(employee);
//        return "redirect:/employees/list";
//    }
//
//    @GetMapping("/update")
//    public String updateEmployeeForm(@RequestParam("id") Long id, Model model) {
//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", employee);
//        return "update";
//    }
//
//    @PostMapping("/update")
//    public String updateEmployee(@ModelAttribute Employee employee) {
//        employeeService.updateEmployee(employee);
//        return "redirect:/employees/list";
//    }
//
//    @GetMapping("/delete")
//    public String deleteEmployee(@RequestParam("id") Long id) {
//        employeeService.deleteEmployee(id);
//        return "redirect:/employees/list";
//    }
//}
package com.example.test5.controller;

import com.example.test5.entity.Employee;
import com.example.test5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String listEmployees(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            Model model) {

        Page<Employee> page = employeeService.searchEmployee(keyword, pageNo, pageSize, sortField, sortDir, minSalary, maxSalary);
        model.addAttribute("employees", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);

        return "list";
    }


    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/update")
    public String updateEmployeeForm(@RequestParam("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/list";
    }
    @GetMapping("/filter")
    public String filterEmployees(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        Page<Employee> page = employeeService.filterEmployees(minAge, maxAge, minSalary, maxSalary, pageNo, pageSize, sortField, sortDir);
        model.addAttribute("employees", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("minAge", minAge);
        model.addAttribute("maxAge", maxAge);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);

        return "list";
    }

}
