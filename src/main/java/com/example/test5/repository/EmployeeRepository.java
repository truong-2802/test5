//package com.example.test5.repository;
//
//import com.example.test5.entity.Employee;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
//
//    @Query("SELECT e FROM Employee e WHERE e.fullName LIKE %:keyword% ")
//    Page<Employee> findByName(String keyword, Pageable pageable);
//
//    @Query ("select max (e.basicSalary) from Employee e")
//    Double getMaxSalary();
//}
package com.example.test5.repository;

import com.example.test5.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query("SELECT e FROM Employee e WHERE " +
//            "(:keyword IS NULL OR CONCAT(e.fullName, ' ', e.age) LIKE %:keyword%) AND " +
//            "(:minSalary IS NULL OR e.basicSalary >= :minSalary) AND " +
//            "(:maxSalary IS NULL OR e.basicSalary <= :maxSalary)")
//    Page<Employee> searchEmployees(@Param("keyword") String keyword,
//                                   @Param("minSalary") Double minSalary,
//                                   @Param("maxSalary") Double maxSalary,
//                                   Pageable pageable);

    Page<Employee> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);

    Page<Employee> findByAgeBetweenAndBasicSalaryBetween(
            Integer minAge,
            Integer maxAge,
            Double minSalary,
            Double maxSalary,
            Pageable pageable
    );
}
