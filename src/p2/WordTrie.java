package p2;
import java.util.*;
import java.io.*;

/*
    class WordTrie can build a prefix trie from wordFile
*/

public class WordTrie {

    //Define global variables
    private int numOfWords;
    final private WordTrie.Node head;

    public WordTrie(){
        this.numOfWords = 0;
        this.head = new WordTrie.Node('*');
    }


    public void addWordFile(String fileLocation){
        try{

            Scanner scanner = new Scanner(new File(fileLocation));
            while(scanner.hasNext()){
                String curWord = scanner.nextLine();
                this.addWord(curWord);
            }
        } catch(FileNotFoundException ex){
            //todo handle exception
            System.out.println("File not found");
        }
    }

    // Filter unqualified words
    public boolean isValid (String word){
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }


    public void addWord(String word){
        if(isValid(word)) {
            char[] letters = word.toCharArray();
            WordTrie.Node curNode = this.head;

            for (char letter : letters) {
                curNode = curNode.addLetter(letter);
            }

            //Add end of word to node
            curNode.endOfWord();
            this.numOfWords++;
        }
    }

    //Traces down trie and returns if the children nodes exist
    public boolean isPrefix(String prefix){
        WordTrie.Node curNode = traceTrie(prefix);
        return(curNode != null);
    }

    //Returns if the word exists in the trie
    public boolean isWord(String word){
        WordTrie.Node curNode = traceTrie(word);
        if(curNode == null ){
            return false;
        }
        return curNode.isWord();

    }

    /*
        Helper function traces trie for given word
        returns ending node of the cur word or null if it doesnt exist
    */
    private WordTrie.Node traceTrie(String word){
        char[] pre = word.toCharArray();

        WordTrie.Node curNode = this.head;
        for(char letter : pre ){
            curNode = curNode.getChild(letter);
            if(curNode == null ){
                return null;
            }
        }

        return curNode;
    }

    //depth first search of all words in trie
    public void printAllWords(){
        WordTrie.Node curNode = this.head;
        StringBuffer sb = new StringBuffer("");
        depthPrinter(sb, curNode, 'a');
    }

    private void depthPrinter(StringBuffer prefix, WordTrie.Node curNode, char curLetter){
        if(curNode.isWord() && prefix.length() > 2){
//            System.out.println(prefix + " ");
        }

        while(curLetter <= 'z'){
            if(curNode.hasChild(curLetter)){
                StringBuffer temp = new StringBuffer(prefix + "" + curLetter);
                depthPrinter(temp, curNode.getChild(curLetter), 'a' );
            }
            curLetter++;
        }
    }

    public class Node{
        public char value;
        public WordTrie.Node[] children;
        public boolean isWord;

        public Node(char character){
            this.value = character;
            this.children = new WordTrie.Node[26];
            this.isWord = false;
        }

        /*
            If needed adds a child node for the given character
            Returns the node of the character to be added
        */
        public WordTrie.Node addLetter(char letter){

            //char node hasn't been used before
            int charValue = this.getCharValue(letter);
            if(children[charValue] == null){
                WordTrie.Node newNode = new WordTrie.Node(letter);
                children[charValue] = newNode;
            }
            return children[charValue];
        }

        //Sets end of word flag
        public void endOfWord(){
            this.isWord = true;
        }


        //returns if a node contains an ending character

        public boolean isWord(){
            return this.isWord;
        }

        //Returns the child node or null if it doesn't exist
        public WordTrie.Node getChild(char letter){
            int charValue = this.getCharValue(letter);
            return(children[charValue]);
        }

        public boolean hasChild(char letter){
            int charValue = this.getCharValue(letter);
            return(children[charValue] != null);
        }

        //Helper class to return value of a character
        public int getCharValue(char letter){
            int offset = 10; //offset for start of alphabet
            return(Character.getNumericValue(letter)-offset);
        }

    }

}
