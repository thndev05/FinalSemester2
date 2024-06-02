package vku.FinalSemester2;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String plainPassword = "123456789";
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        System.out.println("Mật khẩu đã hash: " + hashedPassword);
    }
}
