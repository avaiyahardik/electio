/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hemant Singh Rawat
 */
public class Feedback_info {

    private String email_id;
    private String first_name;
    private String mobile_no;
    private String message;

    public Feedback_info() {
    }

    public Feedback_info(String email_id, String first_name, String mobile_no, String message) {
        this.email_id = email_id;
        this.first_name = first_name;
        this.mobile_no = mobile_no;
        this.message = message;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

   

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
