import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FileMerger {

    // корневая папка, в которой мы работаем
    private final String directory;
    // список всех файлов, с которыми мы работаем
    private ArrayList<File> filesList = new ArrayList<>();
    // путь к файлу, в который мы запишем результат работы программы
    private final String resultPath;


    public FileMerger() {
        this.directory = UI.inputDirectory();
        this.resultPath = directory + "\\result.txt";
    }

    public String getDirectory() {
        return directory;
    }

    public ArrayList<File> getFilesList() {
        return filesList;
    }

    public String getResultPath() {
        return resultPath;
    }

    /**
     * Метод считывает весь текст из файла
     *
     * @param path путь к файлу
     * @return возвращает текст из файла в виде строки
     */
    public static String readAllText(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Рекурсивный метод, который находит все файлы в указанной директории и заносит их в fileList
     *
     * @param directory директория, в которой осуществляется поиск
     */
    public void searchAndAddAllFiles(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile() && (file.getName().endsWith(".txt") || file.getName().endsWith(".TXT"))) {
                    filesList.add(file);
                } else {
                    searchAndAddAllFiles(file);
                }
            }
        }
    }

    /**
     * Метод сортирует файлы в filesList по их названию
     */
    public void sort() {
        Comparator<File> comparator = Comparator.comparing(File::getName);
        this.filesList.sort(comparator);
    }

    /**
     * Метод записывает все файлы в результирующий,
     * обрабатывая включения файлов друг в друга через require
     */
    public void mergeFiles() {
        try {
            String buffer;
            String tempDir;
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.resultPath));
            for (File file : this.filesList) {
                try (BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {
                    while (br.ready()) {
                        buffer = br.readLine();
                        if (buffer.split(" ")[0].equals("require")) {
                            tempDir = buffer.split(" ")[1];
                            bw.write(readAllText(directory + tempDir));
                        } else {
                            bw.write(buffer);
                        }
                        bw.newLine();
                    }
                }
            }
            bw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } //метод записи содержимого файлов в результирующий файл
}