package kr.dohoonkim.blog.utilities;

import org.checkerframework.checker.regex.qual.Regex;

import java.util.regex.Pattern;

public class EmailValidator {

  private static EmailValidator instance = new EmailValidator();

  private Pattern pattern;

  private EmailValidator(){
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
            + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    pattern = Pattern.compile(regex);
  }

  public static EmailValidator getInstance() {
    return instance;
  }

  public boolean validate(String email){
    return pattern.matcher(email).matches();
  }
}
