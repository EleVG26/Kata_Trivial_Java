package io.keepcoding.keeptrivial.javacero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; //Es una clase para generar números aleatorios
import java.util.Scanner;

public class Main 
{
    private static List<Question> questions = new ArrayList<>(); //Para almacenar las preguntas
    private static List<Team> teams = new ArrayList<>(); //Para almacenar los equipos
    private static Random random = new Random();  
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        printTitle("CONFIGUREMOS LOS EQUIPOS.");
        initializeQuestions();
        initializeTeams();
        
        boolean exit = false; // centinela para controlar el final del juego
        do  //Iterar los equipos hasta que gane uno de los equipos, se sale del bucle
        {
            for (Team team : teams) 
            {
                if (exit) break;
                printTitle("Turno de: " + team.getName());
                playTurn(team);
                if (team.hasAllQuesitos()) 
                {
                    exit = true;
                    printTitle("Ha ganado: " + team.getName());
                }
            }
        } while (!exit);
        scanner.close();
    }

    //Método para cargar las preguntas desde el artivo que las contiene, tipo .txt
    private static void initializeQuestions() 
    {
        File folder = new File("questions"); //Directorio donde se enquientran las preguntas
        if (!folder.exists()) 
        {
            printTitle("Error al cargar el fichero");
            return;
        }

        File[] filesList = folder.listFiles();
        if (filesList == null) 
        {
            printTitle("No se encontraron archivos de preguntas");
            return;
        }

        for (File file : filesList) 
        {
            if (file.isFile() && file.getName().endsWith(".txt")) 
            {
                String category = file.getName().substring(0, file.getName().length() - 4); // Asume que el nombre del archivo es la categoría y el -4 sería para borrar el .txt
                try (BufferedReader br = new BufferedReader(new FileReader(file))) 
                {
                    String line;
                    List<String> block = new ArrayList<>();
                    while ((line = br.readLine()) != null) 
                    {
                        block.add(line);

                        if (block.size() == 6) // número de lineas que componen una pregunta
                        { 
                            String question = block.get(0);
                            String[] answers = new String[4];
                            for (int i = 0; i < 4; i++) 
                            {
                                answers[i] = block.get(i + 1);
                            }
                            int correctAnswer = Integer.parseInt(block.get(5));
                            questions.add(new Question(category, question, answers, correctAnswer));
                            block.clear();
                        }
                    }
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        
        // Agregar mensaje de depuración para verificar la carga de preguntas
        System.out.println("Preguntas cargadas: " + questions.size());
    }

    //Método que interactua con el usuario para saber la cantidad de equipos y el nombre de cada equipo y luego se almacena en una lista
    private static void initializeTeams() 
    {
        printLine();
        System.out.print("Ingrese el número de equipos: ");
        int numTeams = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 0; i < numTeams; i++) 
        {
            System.out.print("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String teamName = scanner.nextLine();
            teams.add(new Team(teamName));
        }
        printLine();
    }

    //Método para gestionar el turno de un equipo durante el juego
    private static void playTurn(Team team) 
    {
        Question question = getRandomQuestion();
        System.out.println("Pregunta: " + question.getQuestion());
        String[] answers = question.getAnswers();
        for (int i = 0; i < answers.length; i++) 
        {
            System.out.println((i + 1) + ". " + answers[i]);
        }
        System.out.print("Ingrese el número de la respuesta: ");
        int answer = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        printLine();

        if (question.isCorrect(answer)) 
        {
            System.out.println("¡Correcto! :)");
            String quesito = question.getCategory(); // Usar la categoría de la pregunta como quesito
            team.addQuesito(quesito);
            System.out.println("Has ganado el quesito de: " + quesito);
        } else 
        {
            System.out.println("Incorrecto :(");
        }
        printLine();

        System.out.println("Quesitos del equipo " + team.getName() + ": " + team.getQuesitos());
        printLine();
    }

    //Método para seleccionar y devolver una pregunta aleatoria de la lista de preguntas 
    private static Question getRandomQuestion() 
    {
        if (questions.isEmpty()) 
        {
            throw new IllegalStateException("No hay preguntas disponibles");
        }
        return questions.get(random.nextInt(questions.size()));
    }

    private static void printTitle(String text) 
    {
        System.out.println(text.toUpperCase());
        printLine();
    }

    private static void printLine() 
    {
        System.out.println("########################");
    }

    //Método para verificar si una cadena de texto se puede convertir a un numero entero
    public static boolean esTransformableAEntero(String cadena) 
    {
        try 
        {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) 
        {
            return false;
        }
    }

    private static int getRandomInt(int max) 
    {
        return new Random().nextInt(max);
    }
}


