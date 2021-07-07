import services.LogAnalyzerService;

public class Main {
    public static void main(String[] args) {
        LogAnalyzerService logAnalyzerService = new LogAnalyzerService();
        logAnalyzerService.analyzeLogFromFile(args[0]);
    }
}
