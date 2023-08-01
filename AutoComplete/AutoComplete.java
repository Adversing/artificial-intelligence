import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
  Map<Character, Node> children;
  boolean isWord;

  public Node() {
    children = new HashMap<>();
    isWord = false;
  }
}

class Solution {
  Node trie;

  public Solution() {
    trie = null;
  }

  public void build(List < String > words) {
    trie = new Node();
    for (String word: words) {
      Node current = trie;
      for (char c : word.toCharArray()) {
        if (!current.children.containsKey(c)) {
          current.children.put(c, new Node());
        }
        current = current.children.get(c);
      }
      current.isWord = true;
    }
  }

  public List<String> autocomplete(String prefix) {
    Node current = trie;
    for (char c : prefix.toCharArray()) {
      if (!current.children.containsKey(c)) {
        return new ArrayList<>();
      }
      current = current.children.get(c);
    }
    return findWordsFromNode(current, prefix);
  }

  private List <String> findWordsFromNode(Node node, String prefix) {
    List<String> words = new ArrayList<>();
    if (node.isWord) words.add(prefix);
    
    for (char c : node.children.keySet()) {
      words.addAll(findWordsFromNode(node.children.get(c), prefix + c));
    }
    
    return words;
  }
}

public class AutoComplete {
  public static void main(String[] args) {
    Solution solution = new Solution();
    List<String> completions = List.of("cat", "dog", "java", "tree", "paper", "panel", "part");
    solution.build(completions);
    System.out.println(solution.autocomplete("pa")); // [paper, part, panel]
    System.out.println(solution.autocomplete("pan")); // [panel]
    System.out.println(solution.autocomplete("c")); // [cat]
    System.out.println(solution.autocomplete("k")); // []
  }
}
