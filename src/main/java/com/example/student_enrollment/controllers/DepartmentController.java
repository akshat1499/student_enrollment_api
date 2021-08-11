package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.InternalServerErrorException;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {


    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    /** Method to handle GET Request.
     * Returns all Departments with Status = Status.ACTIVE
     * */
    @GetMapping("/departments")
    List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }


    /**Method to handle POST Request.
     * Adds a List of new Departments Asynchronously using Completable Future.
     * Adds each new  Department in parallel on different threads to improve efficiency.
     * Returns all new saved Departments.
     * */
    @PostMapping("/departments")
    List<Department> newDepartment(@RequestBody List<DepartmentPOJO> newDepartments) {

        /**CompletableFuture<Department> is created for each DepartmentPOJO received and
         * all such CompletableFutures are stored in a List using Stream API.
         * */
        List<CompletableFuture<Department>> completableFutures=
                newDepartments.stream().map(newDepartmentPOJO->
                        departmentService.saveDepartment(newDepartmentPOJO))
                        .collect(Collectors.toList());

        /**A single CompletableFuture is created using CompletableFuture.allof() method
         * to generate a single CompletableFuture which completes when all other CompletableFuture instances complete.
         * */
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        /**thenApply() runs when the allFutures is completed and then join() is called in each
         * child CompletableFuture to return the saved department which is result of execution of
         * save department method from DepartmentService.
         * Finally a new CompletableFuture instance is returned which contains the List of all new saved
         * Departments.
         * */
        CompletableFuture<List<Department>> allCompletableFuture =
                allFutures.thenApply(future->{
                     return completableFutures.stream()
                            .map(completableFuture-> completableFuture.join())
                            .collect(Collectors.toList());
                });


        /**Finally get() method is called to return the List of all new saved departments.
         * */
        try{
            return allCompletableFuture.get();
        }
        catch (Exception e){
            System.out.println(e);
            throw new InternalServerErrorException("Unable to save departments");
        }
    }


    /**Method to handle GET Request.
     * Returns single department by department_id irrespective of status.
     * Caching is also implemented using Redis.
     * */
    @GetMapping("/departments/{id}")
    @Cacheable(value = "dept-single", key = "#id")
    public Department one(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    /**Method to handle PUT request.
     * Used to update Department using Department Id.
     * @CachePut is used to update the new value of Department if it
     * was already preset in the cache memory.
     * */
    @PutMapping("/departments/{id}")
    @CachePut(value = "dept-single", key = "#id")
    public Department replaceDepartment(@RequestBody DepartmentPOJO newDepartment, @PathVariable Long id) {
        //Todo: Add validation if recieve empty Json request
        return departmentService.updateDepartment(newDepartment,id);
    }

    /**Method to handle DELETE Request.
     * Used to handle Delete Department by Department Id which in turn does not actually
     * Delete the department from database but changes the Status to INACTIVE.
     * @CacheEvict is used to remove the Department from Cache Memory if it were present
     * in it since it will no longer be required frequently.
     * */
    @DeleteMapping("/departments/{id}")
    @CacheEvict(value = "dept-single", key = "#id")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}
