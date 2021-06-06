package Test;

public class Question {
    private String question;
    private String[] answers;


    public String correctAnswers() {
        return this.answers[answers.length - 1];
    }

    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

}
