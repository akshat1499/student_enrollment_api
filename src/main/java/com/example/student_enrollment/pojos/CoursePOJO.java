package com.example.student_enrollment.pojos;

public class CoursePOJO {

    private String name;
    private Long fee;
    private Long instId;
    private Long deptId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
