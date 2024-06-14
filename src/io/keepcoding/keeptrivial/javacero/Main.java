package io.keepcoding.keeptrivial.javacero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Question> questions = new ArrayList<>();
    private static List<Team> teams = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printTitle("CONFIGUREMOS LOS EQUIPOS.");
        initializeQuestions();
        initializeTeams();
        
        boolean exit = false;
        do {
            for (Team team : teams) {
                if (exit) break;
                printTitle("Turno de: " + team.getName());
                playTurn(team);
                if (team.hasAllQuesitos()) {
                    exit = true;
                    printTitle("Ha ganado: " + team.getName());
                }
            }
        } while (!exit);
        scanner.close();
    }

    private static void initializeQuestions() {
        File folder = new File("questions");
        if (!folder.exists()) {
            printTitle("Error al cargar el fichero");
            return;
        }

        File[] filesList = folder.listFiles();
        if (filesList == null) {
            printTitle("No se encontraron archivos de preguntas");
            return;
        }

        for (File file : filesList) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String category = file.getName().substring(0, file.getName().length() - 4); // Asume que el nombre del archivo es la categoría
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    List<String> block = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        block.add(line);

                        if (block.size() == 6) { // número de lineas que componen una pregunta
                            String question = block.get(0);
                            String[] answers = new String[4];
                            for (int i = 0; i < 4; i++) {
                                answers[i] = block.get(i + 1);
                            }
                            int rightOption = Integer.parseInt(block.get(5));
                            questions.add(new Question(category, question, answers, rightOption));
                            block.clear();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Agregar mensaje de depuración para verificar la carga de preguntas
        System.out.println("Preguntas cargadas: " + questions.size());
    }

    private static void initializeTeams() {
        printLine();
        System.out.print("Ingrese el número de equipos: ");
        int numTeams = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        for (int i = 0; i < numTeams; i++) {
            System.out.print("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String teamName = scanner.nextLine();
            teams.add(new Team(teamName));
        }
        printLine();
    }

    private static void playTurn(Team team) {
        Question question = getRandomQuestion();
        System.out.println("Pregunta: " + question.getQuestion());
        String[] answers = question.getAnswers();
        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ". " + answers[i]);
        }
        System.out.print("Ingrese el número de la respuesta: ");
        int answer = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        printLine();

        if (question.isCorrect(answer - 1)) {
            System.out.println("¡Correcto! :)");
            String quesito = question.getCategory(); // Usar la categoría de la pregunta como quesito
            team.addQuesito(quesito);
            System.out.println("Has ganado el quesito de: " + quesito);
        } else {
            System.out.println("Incorrecto :(");
        }
        printLine();

        System.out.println("Quesitos del equipo " + team.getName() + ": " + team.getQuesitos());
        printLine();
    }

    private static Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("No hay preguntas disponibles");
        }
        return questions.get(random.nextInt(questions.size()));
    }

    private static void printTitle(String text) {
        System.out.println(text.toUpperCase());
        printLine();
    }

    private static void printLine() {
        System.out.println("########################");
    }

    public static boolean esTransformableAEntero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getRandomInt(int max) {
        return new Random().nextInt(max);
    }
}


