// Ethan Bai
// CSE 143 AR with Adithi Srikanth Raghavan
// Homework 8
// HuffmanNode
public class HuffmanNode implements Comparable<HuffmanNode>{
   public HuffmanNode left;
   public HuffmanNode right;
   public int frequency;
   public int character;   
   
   public HuffmanNode(int frequency, int character){
      this(frequency, character, null, null);
   }
   
   public HuffmanNode(int frequency, int character, HuffmanNode left, HuffmanNode right){
      this.frequency = frequency;
      this.character = character;
      this.right = right;
      this.left = left;
      //System.out.println(character + " " + frequency);
   }
   
   public int compareTo(HuffmanNode other){
      return (this.frequency - other.frequency);
   }

}