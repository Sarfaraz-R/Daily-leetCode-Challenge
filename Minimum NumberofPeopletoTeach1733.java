class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Step 1: Map each person to the set of languages they know
        Map<Integer, Set<Integer>> personLanguages = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            Set<Integer> langSet = new HashSet<>();
            for (int lang : languages[i]) {
                langSet.add(lang);
            }
            personLanguages.put(i + 1, langSet); // people are 1-indexed
        }

        // Step 2: Collect all people who cannot communicate with their friends
        Set<Integer> cannotCommunicate = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0];
            int v = friendship[1];
            if (!canCommunicate(u, v, personLanguages)) {
                cannotCommunicate.add(u);
                cannotCommunicate.add(v);
            }
        }

        // Step 3: For each language, count how many of the "cannotCommunicate" people
        // would need to learn it
        int minTeachings = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int person : cannotCommunicate) {
                if (!personLanguages.get(person).contains(lang)) {
                    count++;
                }
            }
            minTeachings = Math.min(minTeachings, count);
        }

        return minTeachings;
    }

    // Helper: check if two people share at least one language
    private boolean canCommunicate(int u, int v, Map<Integer, Set<Integer>> personLanguages) {
        Set<Integer> langsU = personLanguages.get(u);
        Set<Integer> langsV = personLanguages.get(v);

        for (int lang : langsU) {
            if (langsV.contains(lang)) {
                return true;
            }
        }
        return false;
    }
}
