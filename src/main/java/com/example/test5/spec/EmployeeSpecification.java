package com.example.test5.spec;

import com.example.test5.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class EmployeeSpecification {
    public static Specification<Employee> hasFullName(String fullName) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(fullName)) {
                return criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%");
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Employee> hasAge(Integer age) {
        return (root, query, criteriaBuilder) -> {
            if (age != null) {
                return criteriaBuilder.equal(root.get("age"), age);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Employee> hasSalarySalary(Double minSalary, Double maxSalary) {
        return (root, query, criteriaBuilder) -> {
            if (minSalary != null && maxSalary != null) {
                return criteriaBuilder.between(root.get("basicSalary"), minSalary, maxSalary);
            } else if (minSalary != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("basicSalary"), minSalary);
            } else if (maxSalary != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("basicSalary"), maxSalary);
            }
            return criteriaBuilder.conjunction();
        };
    }


}
