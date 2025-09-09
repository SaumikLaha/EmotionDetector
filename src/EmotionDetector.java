import java.util.*;

public class EmotionDetector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mapping emotions to related keywords
        HashMap<String, List<String>> emotionKeywords = new HashMap<>();
        emotionKeywords.put("Joy", Arrays.asList("happy", "joy", "glad", "delighted", "smile", "cheerful"));
        emotionKeywords.put("Sadness", Arrays.asList("sad", "unhappy", "down", "depressed", "cry"));
        emotionKeywords.put("Anger", Arrays.asList("angry", "mad", "furious", "annoyed", "irritated"));
        emotionKeywords.put("Fear", Arrays.asList("afraid", "scared", "fearful", "nervous"));
        emotionKeywords.put("Surprise", Arrays.asList("surprised", "shocked", "amazed", "astonished"));

        System.out.println("🤖 Welcome to the Emotion Detector!");
        System.out.println("Type a sentence or phrase describing your feelings.");
        System.out.println("Type 'exit' to quit the program.");

        while (true) {
            System.out.print("\nEnter your text: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Goodbye! Stay expressive! 👋");
                break;
            }

            detectEmotion(input, emotionKeywords);
        }

        scanner.close();
    }

    public static void detectEmotion(String input, HashMap<String, List<String>> emotionKeywords) {
        HashMap<String, Integer> emotionCount = new HashMap<>();

        // Initialize counts
        for (String emotion : emotionKeywords.keySet()) {
            emotionCount.put(emotion, 0);
        }

        // Clean input by removing punctuation
        input = input.replaceAll("[^a-zA-Z ]", " ");
        String[] words = input.split("\\s+");

        // Count keywords
        for (String word : words) {
            for (String emotion : emotionKeywords.keySet()) {
                if (emotionKeywords.get(emotion).contains(word)) {
                    emotionCount.put(emotion, emotionCount.get(emotion) + 1);
                }
            }
        }

        // Find dominant emotion
        String dominantEmotion = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : emotionCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                dominantEmotion = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        if (dominantEmotion != null && maxCount > 0) {
            System.out.println("Detected Emotion: " + dominantEmotion + getEmoji(dominantEmotion));
        } else {
            System.out.println("No specific emotion detected. 🤔");
        }
    }

    public static String getEmoji(String emotion) {
        switch (emotion) {
            case "Joy":
                return " 😊";
            case "Sadness":
                return " 😢";
            case "Anger":
                return " 😡";
            case "Fear":
                return " 😱";
            case "Surprise":
                return " 😲";
            default:
                return "";
        }
    }
}
