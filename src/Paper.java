import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Paper implements Serializable {
    private static final long serialVersionUID = 3443443246L;
    private ArrayList<Paper> mPapers = new ArrayList<Paper>();
    private static Paper isMe = null;
    private ArrayList<User> mAuthors = new ArrayList<User>();
    private User mFirstAuthor;
    private String mContent;
    private String mLevel;
    private String mTitle;
    private URL url = this.getClass().getResource("/data/Paper");
    private static final String PAPER_FILE_PATH = "./src/data/Paper";
    private InputStream inputStream = this.getClass().getResourceAsStream(
            "/data/Paper");
    private FileOutputStream outputStream;

    Paper(ArrayList<User> authors, User firstAuthor, String content,
            String level, String title) {
        mAuthors = authors;
        mFirstAuthor = firstAuthor;
        mContent = content;
        mLevel = level;
        mTitle = title;
    }

    private Paper() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            mPapers = (ArrayList<Paper>) ois.readObject();
            for (Paper u : mPapers) {
                System.out.println(u.getmContent());
            }
            System.out.println(mPapers.size());
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
        // //
        // 1******************************************************************
        // ArrayList<User> authors = new ArrayList<User>();
        // User firstAuthor = new User("÷‹÷«ª€", "2012532155");
        // User author2 = new User("¡ıπœº", "2012532155");
        // User author3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author4 = new User("„∆¬€", "2012532155");
        // User author5 = new User("∫´¡¡", "2012532155");
        // authors.add(firstAuthor);
        // authors.add(author2);
        // authors.add(author3);
        // authors.add(author4);
        // authors.add(author5);
        // String content =
        // "Zhou Z, Liu G, Su L, et al. Cchi: An efficient cloud epistasis test model in human genome wide association studies [C]//Biomedical Engineering and Informatics (BMEI), 2013 6th International Conference on. IEEE, 2013: 787-791.";
        // String level = "EI";
        // Paper paper = new Paper(authors, firstAuthor, content, level,
        // "Cchi: An efficient cloud epistasis test model in human genome wide association studies");
        // addPaper(paper);
        // //
        // 2******************************************************************
        // ArrayList<User> authors2 = new ArrayList<User>();
        // User firstAuthor2 = new User("÷‹÷«ª€", "2012532155");
        // User author2_2 = new User("¡ıπœº", "2012532155");
        // User author2_3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author2_4 = new User("∫´¡¡", "2012532155");
        // User author2_5 = new User("„∆¬€", "2012532155");
        // authors2.add(firstAuthor2);
        // authors2.add(author2_2);
        // authors2.add(author2_3);
        // authors2.add(author2_4);
        // authors2.add(author2_5);
        // String content2 =
        // "Zhou Z, Liu G, Su L, et al. A New Epistasis Detecting Algorithm Based on Ant Colony Optimization [C]//Proceedings of International Conference on Internet Multimedia Computing and Service. ACM, 2014: 226.";
        // String level2 = "EI";
        // Paper paper2 = new Paper(authors2, firstAuthor2, content2, level2,
        // "A New Epistasis Detecting Algorithm Based on Ant Colony Optimization");
        // addPaper(paper2);
        // //
        // 3******************************************************************
        // ArrayList<User> authors3 = new ArrayList<User>();
        // User firstAuthor3 = new User("÷‹÷«ª€", "2012532155");
        // User author3_2 = new User("¡ıπœº", "2012532155");
        // User author3_3 = new User("À’¡ÓÃŒ", "2012532155");
        // authors3.add(firstAuthor3);
        // authors3.add(author3_2);
        // authors3.add(author3_3);
        // String content3 =
        // "Zhou Z, Liu G, Su L. A new approach to detect epistasis utilizing parallel implementation of ant colony optimization by MapReduce framework [J]. International Journal of Computer Mathematics, 2015 (ahead-of-print): 1-13.";
        // String level3 = "SCI";
        // Paper paper3 = new Paper(authors3, firstAuthor3, content3, level3,
        // "A new approach to detect epistasis utilizing parallel implementation of ant colony optimization by MapReduce framework");
        // addPaper(paper3);
        // //
        // 4******************************************************************
        // ArrayList<User> authors4 = new ArrayList<User>();
        // User firstAuthor4 = new User("÷‹÷«ª€", "2012532155");
        // User author4_2 = new User("¡ıπœº", "2012532155");
        // User author4_3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author4_4 = new User("∫´¡¡", "2012532155");
        // User author4_5 = new User("„∆¬€", "2012532155");
        // authors4.add(firstAuthor4);
        // authors4.add(author4_2);
        // authors4.add(author4_3);
        // authors4.add(author4_4);
        // authors4.add(author4_5);
        // String content4 =
        // "ZHOU Z, LIU G, SU L, et al. Using a Random Forest Method for Epistasis Detection in Genome-wide Association Studies [J]. Journal of Computational Information Systems, 2014, 10(18): 8043-8050.";
        // String level4 = "EI";
        // Paper paper4 = new Paper(authors4, firstAuthor4, content4, level4,
        // "Using a Random Forest Method for Epistasis Detection in Genome-wide Association Studies");
        // addPaper(paper4);
        // //
        // 5******************************************************************
        // ArrayList<User> authors5 = new ArrayList<User>();
        // User firstAuthor5 = new User("÷‹÷«ª€", "2012532155");
        // User author5_2 = new User("¡ıπœº", "2012532155");
        // User author5_3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author5_4 = new User("∫´¡¡", "2012532155");
        // User author5_5 = new User("„∆¬€", "2012532155");
        // authors5.add(firstAuthor5);
        // authors5.add(author5_2);
        // authors5.add(author5_3);
        // authors5.add(author5_4);
        // authors5.add(author5_5);
        // String content5 =
        // "Zhou Z H, Liu G X, Su L T, et al. Detecting Epistasis by LASSO-Penalized-Model Search Algorithm in Human Genome-Wide Association Studies [C]//Advanced Materials Research. 2014, 989: 2426-2430.";
        // String level5 = "EI";
        // Paper paper5 = new Paper(authors5, firstAuthor5, content5, level5,
        // "Detecting Epistasis by LASSO-Penalized-Model Search Algorithm in Human Genome-Wide Association Studies");
        // addPaper(paper5);
        // //
        // 6******************************************************************
        // ArrayList<User> authors6 = new ArrayList<User>();
        // User firstAuthor6 = new User("÷‹÷«ª€", "2012532155");
        // User author6_2 = new User("¡ıπœº", "2012532155");
        // User author6_3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author6_4 = new User("ÃÔ‘¥", "2012532155");
        // authors6.add(firstAuthor6);
        // authors6.add(author6_2);
        // authors6.add(author6_3);
        // authors6.add(author6_4);
        // String content6 =
        // "Zhihui Zhou, Guixia Liu, Lingtao Su and Yuan Tian. Bayesian Inference for Epistasis Association Mapping in Case-Control Studies [J]. ICIC Express Letters, 2015, 9(1): 261-268.";
        // String level6 = "EI";
        // Paper paper6 = new Paper(authors6, firstAuthor6, content6, level6,
        // "Bayesian Inference for Epistasis Association Mapping in Case-Control Studies");
        // addPaper(paper6);
        // //
        // 7******************************************************************
        // ArrayList<User> authors7 = new ArrayList<User>();
        // User firstAuthor7 = new User("÷‹÷«ª€", "2012532155");
        // User author7_2 = new User("¡ıπœº", "2012532155");
        // User author7_3 = new User("À’¡ÓÃŒ", "2012532155");
        // User author7_4 = new User("ÃÔ‘¥", "2012532155");
        // authors7.add(firstAuthor7);
        // authors7.add(author7_2);
        // authors7.add(author7_3);
        // authors7.add(author7_4);
        // String content7 =
        // "Zhihui Zhou, Guixia Liu, Lingtao Su and Yuan Tian. Detect Epistasis Based on Optimization of Artificial Neural Network Architecture And Parameters [J]. ICIC Express Letters, 2015, 9(6): 1-8.";
        // String level7 = "EI";
        // Paper paper7 = new Paper(authors7, firstAuthor7, content7, level7,
        // "Detect Epistasis Based on Optimization of Artificial Neural Network Architecture And Parameters");
        // addPaper(paper7);
        // //
        // 8******************************************************************
        // ArrayList<User> authors8 = new ArrayList<User>();
        // User firstAuthor8 = new User("À’¡ÓÃŒ", "2012532155");
        // User author8_2 = new User("¡ıπœº", "2012532155");
        // User author8_3 = new User("÷‹÷«ª€", "2012532155");
        // User author8_4 = new User("∫´¡¡", "2012532155");
        // User author8_5 = new User("„∆¬€", "2012532155");
        // authors8.add(firstAuthor8);
        // authors8.add(author8_2);
        // authors8.add(author8_3);
        // authors8.add(author8_4);
        // authors8.add(author8_5);
        // String content8 =
        // "SU L, LIU G, ZHOU Z, et al. PPCMP: A Method for Protein Complex Prediction [J]. Journal of Computational Information Systems, 2014, 10(13): 5657-5664.";
        // String level8 = "EI";
        // Paper paper8 = new Paper(authors8, firstAuthor8, content8, level8,
        // "PPCMP: A Method for Protein Complex Prediction");
        // addPaper(paper8);
        // //
        // 9******************************************************************
        // ArrayList<User> authors9 = new ArrayList<User>();
        // User firstAuthor9 = new User("À’¡ÓÃŒ", "2012532155");
        // User author9_2 = new User("¡ıπœº", "2012532155");
        // User author9_3 = new User("Õı∫≠", "2012532155");
        // User author9_4 = new User("ÃÔ‘¥", "2012532155");
        // User author9_5 = new User("÷‹÷«ª€", "2012532155");
        // authors9.add(firstAuthor9);
        // authors9.add(author9_2);
        // authors9.add(author9_3);
        // authors9.add(author9_4);
        // authors9.add(author9_5);
        // String content9 =
        // "Su L, Liu G, Wang H, et al. GECluster: a novel protein complex prediction method [J]. Biotechnology & Biotechnological Equipment, 2014, 28(4): 753-761.";
        // String level9 = "SCI";
        // Paper paper9 = new Paper(authors9, firstAuthor9, content9, level9,
        // "GECluster: a novel protein complex prediction method");
        // addPaper(paper9);
    }

    public void addPaper(Paper p) {
        mPapers.add(p);
        // ObjectOutputStream oos = null;
        // try {
        // // String jarFilePath =
        // Paper.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        // // // URL Decoding
        // // jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
        // // outputStream = new FileOutputStream(jarFilePath);
        // oos = new ObjectOutputStream(new FileOutputStream(PAPER_FILE_PATH));
        // oos.writeObject(mPapers);
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

    public void deletePaper(Paper p) {
        mPapers.remove(p);
    }

    public static Paper createPaper() {
        if (isMe == null) {
            isMe = new Paper();
        }
        return isMe;
    }

    public static void main(String[] args) {
        Paper p = createPaper();
    }

    void updatePaper(int index, String title, String content) {
        Paper p = mPapers.get(index);
        p.setmTitle(title);
        p.setmContent(content);
        // TODO: update file or database
    }

    public ArrayList<Paper> getmPapers() {
        return mPapers;
    }

    public void setmPapers(ArrayList<Paper> mPapers) {
        this.mPapers = mPapers;
    }

    public User getmFirstAuthor() {
        return mFirstAuthor;
    }

    public void setmFirstAuthor(User mFirstAuthor) {
        this.mFirstAuthor = mFirstAuthor;
    }

    public String getmContent() {
        return mContent;
    }

    public ArrayList<User> getmAuthors() {
        return mAuthors;
    }

    public void setmAuthors(ArrayList<User> mAuthors) {
        this.mAuthors = mAuthors;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

}
