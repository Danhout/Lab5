package ru.demin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.PriorityQueue;
import java.util.Queue;

/** Class with really commands*/
public class ModifiedCMD extends SimpleCMD {
    private Queue<SpaceMarine> queue = new PriorityQueue<>();
    private final long creationTime;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**Constructor without parameters*/
    public ModifiedCMD() {
        creationTime = System.currentTimeMillis();
        new ModifiedCMD(new PriorityQueue<SpaceMarine>());
    }

    /** Constructor that accepts a priority queue of SpaceMarine elements as input*/
    public ModifiedCMD(PriorityQueue<SpaceMarine> queue) {
        super();
        this.queue = queue;
        creationTime = System.currentTimeMillis();
    }

    /** Method that outputs information about the collection to the standard output stream
     *(type, initialization date, number of elements, etc.)*/
    @Override
    public void info(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("info: у данной комманды нет параметров");
            return;
        }
        StringBuilder strB = new StringBuilder("collectionType: PriorityQueue<SpaceMarine>, " +
                "createTime: " + new SimpleDateFormat("hh:mm:ss dd-MM-yyyy").format(creationTime) +
                ", length: " + queue.size() + ".");
        System.out.println(strB.toString());
    }

    /** Method, output to standard output stream all
     * (the elements of the collection in the string representation)*/
    @Override
    public void show(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("show: у данной комманды нет параметров");
            return;
        }
        System.out.println(gson.toJson(queue).toString());
    }

    /** Method that adds a new element to the collection*/
    @Override
    public void add(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("add: у данной комманды один составной параметр {element}");
            return;
        }
        SpaceMarine spaceMarine = new SpaceMarine();
        inputName: while (true) {
            try {
                System.out.println("Введите имя: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setName(str);
                break inputName;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCoordinates: {
            System.out.println("Введите координаты: ");
            Coordinates coordinates = new Coordinates();
            inputX:
            while (true) {
                try {
                    System.out.println("Введите x: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setX(Long.parseLong(str));
                    break inputX;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                }catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputY:
            while (true) {
                try {
                    System.out.println("Введите y: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setY(Long.parseLong(str));
                    break inputY;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setCoordinates(coordinates);
            break inputCoordinates;
        }
        inputHealth: while (true) {
            try {
                System.out.println("Введите количество здоровья: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHealth(Long.parseLong(str));
                break inputHealth;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            }  catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputHeight: while (true) {
            try {
                System.out.println("Введите рост: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHeight(Integer.parseInt(str));
                break inputHeight;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCategory: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите категорию: ");
                for (AstartesCategory category : AstartesCategory.class.getEnumConstants()) {
                    strB.append(category + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setCategory(AstartesCategory.valueOf(str));
                break inputCategory;
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputMeleeWeapon: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите оружие ближнего боя: ");
                for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
                    strB.append(meleeWeapon + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(str));
                break inputMeleeWeapon;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Chapter chapter = new Chapter();
        inputChapter: while (true) {
            System.out.println("Введите главу: ");
            inputName: while (true) {
                try {
                    System.out.println("Введите название главы: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setName(str);
                    break inputName;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputParentLegion: while (true) {
                try {
                    System.out.println("Введите название легиона: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setParentLegion(str);
                    break inputParentLegion;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputMarinesCount: while (true) {
                try {
                    System.out.println("Введите количество морких пехотинцев: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setMarinesCount(Integer.parseInt(str));
                    break inputMarinesCount;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputWorld: while (true) {
                try {
                    System.out.println("Введите название мира: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setWorld(str);
                    break inputWorld;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setChapter(chapter);
            break inputChapter;
        }
        spaceMarine.update();
        queue.add(spaceMarine);
    }

    /** Method that updates the value of a collection element
     * whose id is equal to the specified one*/
    @Override
    public void update(String[] args) {
        SpaceMarine oldSpaceMarine;
        inditification:
        {
            if (args == null || args.length != 1) {
                System.out.println("update: у данной комманды один простой параметр \"ID\" и один составной {element} ");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(args[0]);
                if (id <= 0) {
                    throw new IllegalArgumentException();
                }
                for (SpaceMarine spaceMarine : queue) {
                    if (spaceMarine.getId() == id) {
                        oldSpaceMarine = spaceMarine;
                        break inditification;
                    }
                }
                System.out.println("update: ни одного элемента с данным \"ID\" не обнаружено.");
                return;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("update: у данной комманды один простой параметр \"ID\" и один составной {element} ");
                return;
            }
        }
        replaceSpaceMarine:
        {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            SpaceMarine newSpaceMarine = new SpaceMarine();
            inputName:
            while (true) {
                try {
                    System.out.println("Введите имя: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    newSpaceMarine.setName(str);
                    break inputName;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputCoordinates:
            {
                System.out.println("Введите координаты: ");
                Coordinates coordinates = new Coordinates();
                inputX:
                while (true) {
                    try {
                        System.out.println("Введите x: ");
                        String str = Parser.normalise(reader.readLine());
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        coordinates.setX(Long.parseLong(str));
                        break inputX;
                    } catch (NumberFormatException e) {
                        System.out.print("Неверный формат входных данных, повторите ввод. ");
                    } catch (IllegalArgumentException e) {
                        System.out.print(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                inputY:
                while (true) {
                    try {
                        System.out.println("Введите y: ");
                        String str = Parser.normalise(reader.readLine());
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        coordinates.setY(Long.parseLong(str));
                        break inputY;
                    } catch (NumberFormatException e) {
                        System.out.print("Неверный формат входных данных, повторите ввод. ");
                    } catch (NullPointerException e) {
                        System.out.print(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                newSpaceMarine.setCoordinates(coordinates);
                break inputCoordinates;
            }
            inputHealth:
            while (true) {
                try {
                    System.out.println("Введите количество здоровья: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    newSpaceMarine.setHealth(Long.parseLong(str));
                    break inputHealth;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputHeight:
            while (true) {
                try {
                    System.out.println("Введите рост: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    newSpaceMarine.setHeight(Integer.parseInt(str));
                    break inputHeight;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputCategory:
            while (true) {
                try {
                    StringBuilder strB = new StringBuilder("Выберите категорию: ");
                    for (AstartesCategory category : AstartesCategory.class.getEnumConstants()) {
                        strB.append(category + " ");
                    }
                    System.out.println(strB.toString());
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    newSpaceMarine.setCategory(AstartesCategory.valueOf(str));
                    break inputCategory;
                } catch (NullPointerException | IllegalArgumentException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputMeleeWeapon:
            while (true) {
                try {
                    StringBuilder strB = new StringBuilder("Выберите оружие ближнего боя: ");
                    for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
                        strB.append(meleeWeapon + " ");
                    }
                    System.out.println(strB.toString());
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    newSpaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(str));
                    break inputMeleeWeapon;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            Chapter chapter = new Chapter();
            inputChapter:
            while (true) {
                System.out.println("Введите главу: ");
                inputName:
                while (true) {
                    try {
                        System.out.println("Введите название главы: ");
                        String str = Parser.normalise(reader.readLine());
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        chapter.setName(str);
                        break inputName;
                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.print(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                inputParentLegion:
                while (true) {
                    try {
                        System.out.println("Введите название легиона: ");
                        String str = Parser.normalise(reader.readLine());
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        chapter.setParentLegion(str);
                        break inputParentLegion;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                inputMarinesCount:
                while (true) {
                    try {
                        System.out.println("Введите количество морких пехотинцев: ");
                        String str = reader.readLine();
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        chapter.setMarinesCount(Integer.parseInt(str));
                        break inputMarinesCount;
                    } catch (NumberFormatException e) {
                        System.out.print("Неверный ввод. ");
                    } catch (IllegalArgumentException e) {
                        System.out.print(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                inputWorld:
                while (true) {
                    try {
                        System.out.println("Введите название мира: ");
                        String str = Parser.normalise(reader.readLine());
                        if (!stackReaders.isEmpty()) {
                            System.out.println(str);
                        }
                        chapter.setWorld(str);
                        break inputWorld;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                newSpaceMarine.setChapter(chapter);
                break inputChapter;
            }
            newSpaceMarine.update();
            queue.remove(oldSpaceMarine);
            queue.add(newSpaceMarine);
        }
    }

    /** Method that deletes an item from the collection by its id*/
    @Override
    public void removeById(String[] args) {
        inditification:
        {
            try {
                if (args == null || args.length != 1) {
                    System.out.println("remove_by_id: у данной комманды один параметр \"ID\". ");
                    return;
                }
                int id = Integer.parseInt(args[0]);
                if (id <= 0) {
                    throw new IllegalArgumentException();
                }
                for (SpaceMarine spaceMarine : queue) {
                    if (spaceMarine.getId() == id) {
                        queue.remove(spaceMarine);
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("remove_by_id: у данной комманды параметр является положительным числом. ");
                return;
            }
        }
    }

    /** Method that clears the collection*/
    @Override
    public void clear(String[] args) {
        queue.clear();
    }

    /** Method that saves the collection to a file*/
    @Override
    public void save(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("save: у данной комманды нет параметров");
            return;
        }
        try {
            FileOutputStream stream = new FileOutputStream("output.json");
            stream.write(gson.toJson(queue).getBytes());
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Метод, считывающий и исполняющий скрипт из указанного файла.
     *В скрипте содержатся команды в таком же виде,
     *в котором их вводит пользователь в интерактивном режиме. */
    @Override
    public void executeScript(String[] args) {
        try {
            File file = new File(args[0]);
            stackReaders.push(reader);
            if (set.contains(args[0])) {
                throw new IOException("Зафиксировано выполнение рекурсии.");
            }
            reader = new BufferedReader(new FileReader(file));
            if (!file.canRead()) {
                file.setReadable(true);
            }
            String line;
            set.add(args[0]);
            while (reader.ready()) {
                String str;
                try {
                    str = Parser.normalise(reader.readLine());
                    if (str == null) {
                        throw new FileNotFoundException("The stream's end");
                    }
                } catch (FileNotFoundException e) {
                    return;
                }
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                runCommand(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл на чтение не найден");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (!stackReaders.empty()) {
                reader = stackReaders.pop();
            }
            if (set.contains(args[0])) {
                set.remove(args[0]);
            }
        }
    }

    /** Method that outputs the first item in the collection and deletes it*/
    @Override
    public void removeHead(String[] args) {
        System.out.println(queue.poll());
    }

    /** Method, add a new item to the collection if its value is less than,
     * than the smallest item in this collection*/
    @Override
    public void addIfMin(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("add_if_min: у данной комманды один составной параметр {element}");
            return;
        }
        SpaceMarine spaceMarine = new SpaceMarine();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        inputName: while (true) {
            try {
                System.out.println("Введите имя: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setName(str);
                break inputName;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCoordinates: {
            System.out.println("Введите координаты: ");
            Coordinates coordinates = new Coordinates();
            inputX:
            while (true) {
                try {
                    System.out.println("Введите x: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setX(Long.parseLong(str));
                    break inputX;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                }catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputY:
            while (true) {
                try {
                    System.out.println("Введите y: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setY(Long.parseLong(str));
                    break inputY;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setCoordinates(coordinates);
            break inputCoordinates;
        }
        inputHealth: while (true) {
            try {
                System.out.println("Введите количество здоровья: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHealth(Long.parseLong(str));
                break inputHealth;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            }  catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputHeight: while (true) {
            try {
                System.out.println("Введите рост: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHeight(Integer.parseInt(str));
                break inputHeight;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCategory: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите категорию: ");
                for (AstartesCategory category : AstartesCategory.class.getEnumConstants()) {
                    strB.append(category + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setCategory(AstartesCategory.valueOf(str));
                break inputCategory;
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputMeleeWeapon: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите оружие ближнего боя: ");
                for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
                    strB.append(meleeWeapon + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(str));
                break inputMeleeWeapon;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Chapter chapter = new Chapter();
        inputChapter: while (true) {
            System.out.println("Введите главу: ");
            inputName: while (true) {
                try {
                    System.out.println("Введите название главы: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setName(str);
                    break inputName;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputParentLegion: while (true) {
                try {
                    System.out.println("Введите название легиона: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setParentLegion(str);
                    break inputParentLegion;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputMarinesCount: while (true) {
                try {
                    System.out.println("Введите количество морких пехотинцев: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setMarinesCount(Integer.parseInt(str));
                    break inputMarinesCount;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputWorld: while (true) {
                try {
                    System.out.println("Введите название мира: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setWorld(str);
                    break inputWorld;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setChapter(chapter);
            break inputChapter;
        }
        spaceMarine.update();
        for (SpaceMarine queueSpaceMarine : queue) {
            if (spaceMarine.compareTo(queueSpaceMarine) >= 0) {
                return;
            }
        }
        queue.add(spaceMarine);
    }

    /** Method to remove all items from the collection that exceed the specified value*/
    @Override
    public void removeGreater(String[] args) {
        if (args == null || args.length != 0) {
            System.out.println("remove_greater: у данной комманды один составной параметр {element}");
            return;
        }
        SpaceMarine spaceMarine = new SpaceMarine();
        inputName: while (true) {
            try {
                System.out.println("Введите имя: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setName(str);
                break inputName;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCoordinates: {
            System.out.println("Введите координаты: ");
            Coordinates coordinates = new Coordinates();
            inputX:
            while (true) {
                try {
                    System.out.println("Введите x: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setX(Long.parseLong(str));
                    break inputX;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                }catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputY:
            while (true) {
                try {
                    System.out.println("Введите y: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    coordinates.setY(Long.parseLong(str));
                    break inputY;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат входных данных, повторите ввод. ");
                } catch (NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setCoordinates(coordinates);
            break inputCoordinates;
        }
        inputHealth: while (true) {
            try {
                System.out.println("Введите количество здоровья: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHealth(Long.parseLong(str));
                break inputHealth;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print(e.getMessage());
            }  catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputHeight: while (true) {
            try {
                System.out.println("Введите рост: ");
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setHeight(Integer.parseInt(str));
                break inputHeight;
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputCategory: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите категорию: ");
                for (AstartesCategory category : AstartesCategory.class.getEnumConstants()) {
                    strB.append(category + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setCategory(AstartesCategory.valueOf(str));
                break inputCategory;
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        inputMeleeWeapon: while (true) {
            try {
                StringBuilder strB = new StringBuilder("Выберите оружие ближнего боя: ");
                for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
                    strB.append(meleeWeapon + " ");
                }
                System.out.println(strB.toString());
                String str = Parser.normalise(reader.readLine());
                if (!stackReaders.isEmpty()) {
                    System.out.println(str);
                }
                spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(str));
                break inputMeleeWeapon;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.print("Неверный формат входных данных, повторите ввод. ");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Chapter chapter = new Chapter();
        inputChapter: while (true) {
            System.out.println("Введите главу: ");
            inputName: while (true) {
                try {
                    System.out.println("Введите название главы: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setName(str);
                    break inputName;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputParentLegion: while (true) {
                try {
                    System.out.println("Введите название легиона: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setParentLegion(str);
                    break inputParentLegion;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputMarinesCount: while (true) {
                try {
                    System.out.println("Введите количество морких пехотинцев: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setMarinesCount(Integer.parseInt(str));
                    break inputMarinesCount;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный ввод. ");
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            inputWorld: while (true) {
                try {
                    System.out.println("Введите название мира: ");
                    String str = Parser.normalise(reader.readLine());
                    if (!stackReaders.isEmpty()) {
                        System.out.println(str);
                    }
                    chapter.setWorld(str);
                    break inputWorld;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            spaceMarine.setChapter(chapter);
            break inputChapter;
        }
        spaceMarine.update();
        for(SpaceMarine queueSpaceMarine : queue) {
            if (queueSpaceMarine.compareTo(spaceMarine) > 0) {
                queue.remove(queueSpaceMarine);
            }
        }
    }

    /** Method to delete a single item from the collection,
     * the value of the field whose height is equivalent to the specified one*/
    @Override
    public void removeAnyByHight(String[] args) {
        if (args == null || args.length == 0 || args[0].equals("")) {
            for (SpaceMarine spaceMarine : queue) {
                if (spaceMarine.getHeight() == null) {
                    queue.remove(spaceMarine);
                    return;
                }
            }
        }
        try {
            if (args.length > 1) {
                throw new IllegalArgumentException();
            }
            int height = Integer.parseInt(args[0]);
            for (SpaceMarine spaceMarine : queue) {
                if (spaceMarine.getHeight().equals(height)) {
                    queue.remove(spaceMarine);
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("remove_any_by_height: комманда имеет один параметр \"height\"");
            return;
        }

    }

    /** Method that outputs the average value of the height field for all items in the collection*/
    @Override
    public void averageOfHeight(String[] args) {
        int result = 0;
        if (args == null || args.length == 0) {
            for (SpaceMarine spaceMarine : queue) {
                if (spaceMarine.getHeight() != null) {
                    result += spaceMarine.getHeight().intValue();
                }
            }
            if (queue == null || queue.size() == 0) {
                System.out.println("Среднее значение высоты: 0");;
            } else {
                System.out.println("Среднее значение высоты: " + ((double) result) / queue.size()) ;
            }
            return;
        } else {
            System.out.println("average_of_height: комманда не имеет параметров");
            return;
        }
    }

    /** Метод, выводящий количество элементов, значение поля meleeWeapon которых больше заданного*/
    @Override
    public void countGreaterThanMeleeWeapon(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("count_greater_than_melee_weapon: имеет один параметр \"MeleeWeapon\"");
            return;
        }
        try {
            MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(args[0]);
            if (meleeWeapon == null) {
                System.out.println("count_greater_than_melee_weapon: имеет один параметр \"MeleeWeapon\" типа перечисление");
                return;
            }
            int result = 0;
            for (SpaceMarine spaceMarine : queue) {
                if (spaceMarine.getMeleeWeapon() == null) {

                }
                if (spaceMarine.getMeleeWeapon().compareTo(meleeWeapon) > 0) {
                    result++;
                }
            }
            System.out.println(result + " элементов очереди имеет значение \"MeleeWeapon\", большего данного");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("count_greater_than_melee_weapon: имеет один параметр \"MeleeWeapon\" типа перечисление");
            return;
        }
    }
}
