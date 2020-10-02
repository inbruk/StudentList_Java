import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger( Main.class.getName() );

    private static String addCommand = "add Василий Петров vasily.petrov@gmail.com +79787775747";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров\n\thelp\n\tquit";
    private static String commandError = "Wrong command! Input 'help' for command list\n";
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) throws IOException {
        InputStream is = Main.class.getResourceAsStream("/logging.properties");
        LogManager.getLogManager().readConfiguration(is);

        logger.info("Приложение запуcкается...");

        Scanner scanner = new Scanner(System.in);
        StudentStorage executor = new StudentStorage();
        logger.info("Хранилище создано.");

        System.out.println(helpText);

        try {
            while (true) {
                logger.info("Считвыаем строку с консоли.");
                System.out.print('>');
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);


                if (tokens[0].equals("add")) {
                    executor.addStudent(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listStudent();
                } else if (tokens[0].equals("get")) {
                    executor.getStudentByName(tokens[1]).toString();
                } else if (tokens[0].equals("remove")) {
                    executor.removeStudent(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else if (tokens[0].equals("quit")) {
                    logger.info("Получена команда на выход. ");
                    break;
                } else {
                    logger.severe(commandError);
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("In main() array tokens[] is out of bound: " + e);
            logger.log( Level.SEVERE, "In main() array tokens[] is out of bound ! ", e );
        } finally {
            logger.info("Приложение завершается...");
        }
    }
}
