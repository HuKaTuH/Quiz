package DataBase;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;

public class DataBase {
    private String item[] = new String[3];

    protected Boolean loginVerified(String username, String password) {
        BufferedReader br = null;
        try {
            File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/DataBase/data/"+username+".txt");
            if (!file.exists()){

                return false;
            }
            br = new BufferedReader(new FileReader("/Users/dimitrii/IdeaProjects/Test Grila/src/DataBase/data/"+username+".txt"));

            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                item[i] = line;
                i++;
            }

            br.close();

            if ((item[0].equals(username)) && (item[2].equals(password))){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /*
    registred return
        0 - username is too short
        1 - user exist
        2 - email format error
        3 - password is to short
        4 - password != confirmPassword
        5 - succes registred
     */

    protected int registred(String username, String email, String password, String confirmPassword){
        if (username.length() < 4) return 0;
        File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/DataBase/data/"+username+".txt");
        if(file.exists()) return 1;
        if(!isValidEmailAddress(email)) return 2;
        if(password.length()<4) return 3;
        if(!password.equals(confirmPassword)) return 4;

        try{
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.println(username);
            pw.println(email);
            pw.println(password);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 5;
    }
}
