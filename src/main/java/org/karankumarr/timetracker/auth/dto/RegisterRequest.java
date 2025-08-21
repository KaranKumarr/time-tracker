package org.karankumarr.timetracker.auth.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public String getName() { return name; }
}
