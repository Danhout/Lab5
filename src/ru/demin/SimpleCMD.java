package ru.demin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/** Abstract class for command string*/
public abstract class SimpleCMD implements Commands {
    protected final Map<String, Method> mapCommands;
    protected Stack<BufferedReader> stackReaders = new Stack<>();
    protected Set<String> set = new HashSet<>();
    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /** Constructor without parameters*/
    public SimpleCMD() {
        mapCommands = new HashMap<>();

        for (Method method : Commands.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                mapCommands.put(cmd.name(), method);
            }
        }
    }

    /** Method, passing a string to the terminal for processing */
    public void runCommand(String strLine) {
        try {
            String[] strs  = strLine.split(" ");
            String command = strs[0];
            String[] args = Arrays.copyOfRange(strs, 1, strs.length);
            Method method = mapCommands.get(command);
            if (method == null) {
                System.out.println("\"" + command + "\" не является коммандой. help - вывод списка доступных команд.");
                return;
            }
            Command cmd = method.getAnnotation(Command.class);
            method.invoke(this, (Object) args);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Данной команды не существует. help - вывод списка доступных команд.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Ошибка. "+ e.getMessage()+ ". help - вывод списка доступных команд");
        }
    }

    @Override
    public void executeScript(String[] args) {

    }
}
