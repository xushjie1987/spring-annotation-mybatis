package com.oneapm.annotation.test.domain;

public class TblA {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_a.a1
     *
     * @mbggenerated
     */
    private Integer a1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_a.a2
     *
     * @mbggenerated
     */
    private String a2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_a.a1
     *
     * @return the value of tbl_a.a1
     *
     * @mbggenerated
     */
    public Integer getA1() {
        return a1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_a.a1
     *
     * @param a1 the value for tbl_a.a1
     *
     * @mbggenerated
     */
    public void setA1(Integer a1) {
        this.a1 = a1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_a.a2
     *
     * @return the value of tbl_a.a2
     *
     * @mbggenerated
     */
    public String getA2() {
        return a2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_a.a2
     *
     * @param a2 the value for tbl_a.a2
     *
     * @mbggenerated
     */
    public void setA2(String a2) {
        this.a2 = a2 == null ? null : a2.trim();
    }
}