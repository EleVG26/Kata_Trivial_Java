package io.keepcoding.keeptrivial.javacero;

public class Question 
{
    private String category;
    private String question;
    private String[] answers;
    private int correctAnswer;

    public Question(String category, String question, String[] answers, int correctAnswer) 
    {
        this.category = category;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getCategory() 
    {
        return category;
    }

    public String getQuestion() 
    {
        return question;
    }

    public String[] getAnswers() 
    {
        return answers;
    }

    public int getCorrectAnswer() 
    {
        return correctAnswer;
    }

    public boolean isCorrect(int answer) 
    {
        return answer == correctAnswer;
    }
}

