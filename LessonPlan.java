class LessonPlan {
    private String difficulty;
    private List<String> keysToLearn;
    private int currentLevelIndex;

    public LessonPlan(String difficulty, List<String> keysToLearn) {
        this.difficulty = difficulty;
        this.keysToLearn = keysToLearn;
        this.currentLevelIndex = 0;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getKeysToLearn() {
        return keysToLearn;
    }

    public boolean hasNextLevel() {
        return currentLevelIndex < keysToLearn.size() - 1;
    }

    public void moveToNextLevel() {
        currentLevelIndex++;
    }
}

