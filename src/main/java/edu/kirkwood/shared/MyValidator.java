package edu.kirkwood.shared;

import java.util.regex.Pattern;

public class MyValidator {
    
    public static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");
    
    // requires min 8 characters, 3 of 4 (uppercase, lowercase, number, or character)
    public static Pattern passwordPattern = Pattern.compile("^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9~`!\\@#\\$%\\^&*()_\\-+={}\\[\\]\\|\\\\:;\"'<>,.?\\/]{8,128}$"); 
}
