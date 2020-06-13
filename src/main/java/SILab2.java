import java.util.List;

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public boolean function (User user, List<String> allUsers) {//A
        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}";//A
        if (user==null) {//A
            throw new RuntimeException("The user is not instantiated");//B
        }
        if (user.getUsername()==null || user.getPassword()==null) {//C
            throw new RuntimeException("The user is missing some mandatory information");//D
        }
        String password = user.getPassword();//E
        String passwordLower = password.toLowerCase();//E
        if (passwordLower.contains(user.getUsername().toLowerCase())) {//E
            return false;//F
        }
        else if (passwordLower.length()<8)//G
            return false;//H
        else {//I
            boolean digit = false, upper = false, special = false;//I

            for (int i=0;i<password.length();i++) {//J
                if (Character.isDigit(password.charAt(i)))//K
                    digit = true;//L
                if (Character.isUpperCase(password.charAt(i)))//M
                    upper = true;//N
                if (specialCharacters.contains(String.valueOf(password.charAt(i))))//O
                    special = true;//P
            }
            if (!digit || !upper || !special)//Q
                return false;//R
        }
        return true;//S
    }//T
}

