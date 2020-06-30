This is a Java solution of BoggleGame by Chiqu Li.

I implemented a prefix trie to solve the boggle game. For each word in the dictionary, I added it to my trie. And I
search the node to check if the temporary word I encountered is a valid word. In this way, it could perform well with
large dictionary set and big board compared to simple dfs algorithm.

To test the code, run "src/p2/example_driver.java".
