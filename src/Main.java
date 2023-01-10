import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileMerger searcher = new FileMerger();
        File file = new File(searcher.getDirectory());
        searcher.searchAndAddAllFiles(file);
        searcher.sort();
        searcher.mergeFiles();
        System.out.println(String.format("Файлов найдено : %d, мы объединили их согласно условию и " +
                "записали в %s", searcher.getFilesList().size(),searcher.getResultPath()));
    }
}