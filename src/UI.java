import java.io.*;

public class UI {
    public static String inputDirectory() {
        String directory;
        try {
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите директорию, которую считать корневой:");
            directory = bufferedInputStream.readLine().trim();
            File file = new File(directory);
            if (!file.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
                System.out.println("Вы ввели несуществующую директорию, давайте попробуем заново");
                return inputDirectory();
        } catch (IOException ex) {
            System.out.println("Что-то пошло не так, проверьте данные и попробуйте ещё раз");
            return inputDirectory();
        }
        return directory;
    }
}
