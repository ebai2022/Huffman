// Ethan Bai
// CSE 143 AR with Adithi Srikanth Raghavan
// Homework 8
// HuffmanNode defines the HuffmanNode used within HuffmanTree. A HuffmanNode
// has a right and left branch and can store the frequency and character values
public class HuffmanNode implements Comparable<HuffmanNode>{
   public HuffmanNode left;
   public HuffmanNode right;
   public int frequency;
   public int character;   
   
   // parameters : takes an int frequency and an int character that define the value of the 
   // frequency and the character
   // post : creates a HuffmanNode for use in the HuffmanTree
   public HuffmanNode(int frequency, int character){
      this(frequency, character, null, null);
   }
   
   // parameters : takes an int frequency that defines the frequency the character appears, an
   // int character that defines the value of the character, a HuffmanNode left that serves as 
   // the left branch, and a HuffmanNode right that serves as the right branch
   // post : creates a HuffmanNode for use in the HuffmanTree
   public HuffmanNode(int frequency, int character, HuffmanNode left, HuffmanNode right){
      this.frequency = frequency;
      this.character = character;
      this.right = right;
      this.left = left;
   }
   
   // parameters : takes a HuffmanNode other to compare to the current HuffmanNode
   // post : returns a negative integer if the frequency of the current HuffmanNode is smaller.
   // Returns zero if the two frequencies are equal. Returns a positive integer if the other
   // frequency is greater than the current frequency
   public int compareTo(HuffmanNode other){
      return (this.frequency - other.frequency);
   }

}