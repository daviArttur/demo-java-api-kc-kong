package davi.api.demo.application.interfaces;

public interface PasswordService {
    public String encode(String rawPassword);
    public boolean compare(String rawPassword, String hashedPassword);
}