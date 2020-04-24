package ru.demin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/** @version 1.0
 * @author Demin Daniil
 */

/** Class with main method*/
public class Lab5 {

    /** Method main*/
    public static void main(String[] args) {
        try {
            String fileName;
            try {
                fileName = args[0];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Не передан путь исходного файла.");
                fileName = "";
            }
            Parser parser = new Parser();
            BufferedReader fileBfReader;
            PriorityQueue<SpaceMarine> queue;
            try {
                queue = parser.jsonToPriorityQueue(Parser.normalise(fileName));
            } catch (Exception e) {
                throw e;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (queue == null) {
                queue = new PriorityQueue<>();
            }
            ModifiedCMD cmd = new ModifiedCMD(queue);
            while (true) {
                String strLine = null;
                try {
                    strLine = Parser.normalise(reader.readLine());
                } catch (FileNotFoundException e) {
                    System.out.println("Не найден файл.");
                }
//                if(strLine.equals("")) {
//                    return;
//                }
                if (!cmd.stackReaders.isEmpty())
                    System.out.println(strLine);
                cmd.runCommand(strLine);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
