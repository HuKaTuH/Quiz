package Test;

import java.io.*;
import java.util.Scanner;

public class Test {
    private Question questionList[];

    public Test(String domaine) {
        questionList = new Question[10];
        try {

            BufferedReader br = new BufferedReader(new FileReader("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/Question/" + domaine + ".txt"));

            String line;
            String que;
            int i = 0;

            while ((line = br.readLine()) != null) {
                que = line;
                String[] anwers = new String[4];
                for (int j = 0; j < 4; j++) {
                    anwers[j] = br.readLine();
                }
                questionList[i] = new Question(que, anwers);
                i++;
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Question[] getQuestionList(){
        return questionList;
    }
}
