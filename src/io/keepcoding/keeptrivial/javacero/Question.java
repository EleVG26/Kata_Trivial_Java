package io.keepcoding.keeptrivial.javacero;

public class Question 
{
	//Atributos privados
    private String category;
    private String question;
    private String[] answers;
    private int correctAnswer;

    //Constructor
    public Question(String category, String question, String[] answers, int correctAnswer) 
    {
        this.category = category;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    //M´étodo getter
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

    //método que opera sobre los atributos
    public boolean isCorrect(int answer) 
    {
        return answer == correctAnswer;
    }
}

