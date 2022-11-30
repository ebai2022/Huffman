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
      this.character = character;
      this.frequency = frequency;
   }
   
   public HuffmanNode(int frequency, HuffmanNode right, HuffmanNode left){
      this.frequency = frequency;
      this.right = right;
      this.left = left;
   }
   
   public int compareTo(HuffmanNode other){
      return (this.frequency - other.frequency);
   }

}