/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.util.regex.Pattern;
/**
 *
 * @author cassa
 */

//
class Login {
    //A private is an access modifier that restricts variables or 
    //methods from accessing it without permission (Farrel, 2018).
    //The private variables store the user's registration details.
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;
    
  //The constuctor initializes the new Login object,
    //by assigning all the input values to the instance field (Farrel, 2018).
   public Login(String firstName, String lastName, String username, String password, String cellPhoneNumber){
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.cellPhoneNumber = cellPhoneNumber;
   }
   
   //Get methods are used to retreive or return the value of a private variable, 
   //while sets method change or assign a new value to a preivate variable (Farrel, 2018).
    //Returns the user's first name
   public String getFirstName(){
       return firstName;
   }
   //Updates the user's first name with the provided value
   public void setFirstName(String firstName){
       this.firstName = firstName;
   }
     //Returns the user's last name
   public String getLastName(){
       return lastName;
   }
    //Updates the user's last name with the provided value
   public void setLastName(String lastName){
       this.lastName = lastName;
   }
    //Returns the user's username
   public String getUsername(){
       return username;
   }
   //Updates the user's username with the provided value
   public void setUsername(String username){
       this.username = username;
   }
     //Returns the user's passwod
   public String getPassword(){
       return password;
   }
    //Updates the user's password with the provided value
   public void setPassword(String password){
       this.password = password;
   }
    //Returns the user's cellphone number
   public String getCellPhoneNumber(){
       return cellPhoneNumber;
   }
    //Updates the user's cellphone number with the provided value
   public void setCellPhoneNumber(String cellPhoneNumber){
       this.cellPhoneNumber = cellPhoneNumber;
   }
   //Checks if the username is correctly formatted.
   public boolean checkUserName(String username){
       //Checks if the username is empty.
       if (username == null){
         return false;  
       }
       //Checks if the username contains an underscore '_' and
       //the username has 5 characters or less.
        return username.contains("_") && username.length() <= 5;
   }
   //Checks if the password meets the password complesity rules.
   //If the password is empty, it is invalid.
     public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }
        //Checks if the password contains a capital letter, a number and a special character.
        //(Farrel, 2018).
        boolean hasCapitalLetter = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecialCharacter =
                Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();

        //The password must contain: 
        //1.)At least eight characters long
        //2.)A capital letter,
        //3.)A number
        //4.)A special charater
       

        return password.length() >= 8
                && hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

     //Checks if the cellphone number is formatted correctly.
    //The cellphone number must start with (+27), and followed by 9 numbers
    public boolean checkCellPhoneNumber(String cellPhoneNumber){
     return cellPhoneNumber != null && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }
    //Registers the user's username and password 
    //Validates the if the username and password are correctly formated, 
    //the method will store them to the object's field if they are valid and return as message of success
    //If the username and password are invalid the method will display an error message
    public String registerUser(String username, String password){
        //Checks if the username is valid and
        //if the username is invalid the method will return an error message
        if (!checkUserName(username)){
           return "Username is not correctly formatted; "
                   + "please ensure that your username contains an underscore "
                   + "and is no more than five characters in length.";
        } 
        //Checks if the password is valid and
        //if the username is invalid the method will return an error message
        if (!checkPasswordComplexity(password)){
          return "Password is not correctly formatted; "
                  + "please ensure that the password contains at least eight characters,"
                  + " a capital letter, a number, and a special character.";
        } 
       //Stores valid username and password
       this.username = username;
       this.password = password;
       return "Username and password successfully captured";
    }
    //Checks and registers the user's cellphone number, 
    //by checking if the cellphone number is valid and the method will retun a  message of success,
    //if it is not valid the method will return a error message
    public String registerCellPhoneNumber(String cellPhoneNumber){
        if (checkCellPhoneNumber(cellPhoneNumber)){
            return "Cell number is successfully captured";
        }else{
            return "Cell number is incorrectly formatted oo does not contain an international code;"
                    + " please correct the number and try again.";
        }
    }
    //Checks if the entered username and password match the registered username and password
    public boolean loginUser(String username,String password){
        //If both username and password are empty, the login will fail to run
        if (username == null || password == null){
            return false;
        }
        //Compare the entered username and password with the registered username and password
        return username.equals(this.username) && password.equals(this.password);
    }
    //Returns a message whether the login was successful or not
      public String returnLoginStatus(String enteredUsername, String enteredPassword){
          //Checks if the entered username and password are correct and
          //display a welcome message when the login is successful
          if (loginUser(enteredUsername, enteredPassword)){
              return "Welcome " + firstName + " " + lastName
                      + ", it is great to see you.";
          }
          //Displays an error message when login fails
          return "Username or password is incorrect, please try again";
      }
}
        
//Farell J., 2024. Programming Logic and Design. 10th ed. Boston: Cengage Learning