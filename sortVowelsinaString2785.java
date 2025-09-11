class Solution {
    public String sortVowels(String s) {
        int n=s.length();
        ArrayList<Character> vowels=new ArrayList<>();
        //Collect vowels
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(isVowel(ch)){
               vowels.add(ch);
            }
        }
        //Sort vowels
        Collections.sort(vowels);
        //Build new String keeping constants at same place but replacing vowels 
        StringBuilder sb=new StringBuilder();
        
        int k=0;
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(isVowel(ch)){
              sb.append(vowels.get(k));
              k++;
              continue;
            }
            sb.append(ch); 
        }
        return sb.toString();

    }
    //check for vowel - constant operation o(1);
    private boolean isVowel(char ch){
        char[]vowels={'a','e','i','o','u','A','E','I','O','U'};
        for(int i=0;i<vowels.length;i++){
            if(ch==vowels[i])return true;
        }
        return false;
    }
}
