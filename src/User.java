import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 83483986938L;
    private static User isMe = null;
    private ArrayList<User> mUsers = new ArrayList<User>();
    private String mName;
    private String mNumber;
    // private URL url = this.getClass().getResource("/data/User");
    private InputStream inputStream = this.getClass().getResourceAsStream(
            "/data/User");
    // private FileOutputStream outputStream;
    private static final String USER_FILE_PATH = "./src/data/User";

    private User() {
        User a = new User("÷‹÷«ª€", "2012532155");
        User b = new User("À’¡ÓÃŒ", "2012532154");
        User c = new User("„∆¬€", "2012532153");
        User d = new User("∫´¡¡", "2012532152");
        addUser(a);
        addUser(b);
        addUser(c);
        addUser(d);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            mUsers = (ArrayList<User>) ois.readObject();
            for (User u : mUsers) {
                System.out.println(u.getName() + " " + u.getNumber());
            }
            System.out.println(mUsers.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public User(String name, String number) {
        this.mName = name;
        this.mNumber = number;
    }

    public void addUser(User user) {
        mUsers.add(user);
        // ObjectOutputStream oos = null;
        // try {
        // // String jarFilePath =
        // User.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        // // // URL Decoding
        // // jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
        // // outputStream = new FileOutputStream(jarFilePath);
        // oos = new ObjectOutputStream(new FileOutputStream(USER_FILE_PATH,
        // false));
        // //oos.reset();
        // //oos.flush();
        // oos.writeObject(mUsers);
        // oos.flush();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // oos.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
    }

    public void deleteUser(User user) {
        mUsers.remove(user);
        // ObjectOutputStream oos = null;
        // try {
        // // String jarFilePath =
        // User.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        // // // URL Decoding
        // // jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
        // // outputStream = new FileOutputStream(jarFilePath);
        // oos = new ObjectOutputStream(new FileOutputStream(USER_FILE_PATH,
        // false));
        // oos.reset();
        // oos.flush();
        // oos.writeObject(mUsers);
        // oos.flush();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // oos.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
    }

    public void updateUser(int i, String name, String number) {
        User user = mUsers.get(i);
        user.setName(name);
        user.setNumber(number);
        // TODO: update file or database
    }

    public static User createUser() {
        if (isMe == null) {
            isMe = new User();
        }
        return isMe;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public void setUsers() {

    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public static void main(String[] args) {
        isMe = User.createUser();
    }
}
