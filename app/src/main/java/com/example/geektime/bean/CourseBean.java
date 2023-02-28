package com.example.geektime.bean;

public class CourseBean {
    String imageUrl;    // 课程海报
    String name;        // 课程名称
    String desc;        // 课程描述
    String teacher;     // 课程老师
    String detail;      // 课程详细
    String courseList;  // 课程章节名称
    int price;          // 课程价格
    int total;          // 课程章节总数
    int update;         // 课程已更新数量
    int studentCount;   // 课程学习人数

    public CourseBean(String imageUrl, String name, String desc, String teacher, String detail, String courseList, int price, int total, int update, int studentCount) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.desc = desc;
        this.teacher = teacher;
        this.detail = detail;
        this.courseList = courseList;
        this.price = price;
        this.total = total;
        this.update = update;
        this.studentCount = studentCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCourseList() {
        return courseList;
    }

    public void setCourseList(String courseList) {
        this.courseList = courseList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "CourseBean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", teacher='" + teacher + '\'' +
                ", detail='" + detail + '\'' +
                ", courseList='" + courseList + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", update=" + update +
                ", studentCount=" + studentCount +
                '}';
    }
}
