public class Main {
    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.analyzeLogFromFile(args[0]);
    }
}
