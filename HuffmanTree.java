// Ethan Bai
// CSE 143 AR with Adithi Srikanth Raghavan
// Homework 8
// HuffmanTree is able to take either an array of frequencies where each index relates to a
// character or a scanner input as its file and create a decision tree based off the 
// information given. HuffmanTree is also able to write text files into their respective code
// values and write (decipher) short (binary) files back into text form. Generally, 
// character and frequency for parts of the HuffmanTree not relating to an explicit character 
// are defaulted to a value of -1 for their respective character and frequency values depending
// on the information given
import java.util.*;
import java.io.*;
public class HuffmanTree{
   private HuffmanNode overallRoot;
   
   // parameters : takes an int[] count that serves as an array of characters that contains the
   // frequency of each respective character at each index
   // post : initializes the HuffmanTree with its respective values (sorted in Huffman order)
   public HuffmanTree(int[] count){
      Queue<HuffmanNode> nodeStorage = new PriorityQueue<>();
      for (int i = 0; i < count.length; i++){
         if (count[i] > 0){
            HuffmanNode letter = new HuffmanNode(count[i], i);
            nodeStorage.add(letter);
         }
      }
      nodeStorage.add(new HuffmanNode(1, count.length));
      while (nodeStorage.size() > 1){
         HuffmanNode first = nodeStorage.remove();
         HuffmanNode second = nodeStorage.remove();
         nodeStorage.add(new HuffmanNode(first.frequency + second.frequency, -1, first, second));
      }
      overallRoot = nodeStorage.remove();
   }
   
   // parameters : takes a Scanner input that serves as the file for the HuffmanTree
   // post : initializes the HuffmanTree with its respective values (sorted in Huffman order) 
   public HuffmanTree(Scanner input){
      while (input.hasNextLine()){
         int n = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         overallRoot = treeConstructor(overallRoot, n, code);
      }
   }
   
   // helper method for HuffmanTree(Scanner input). takes a Scanner input that serves as the 
   // file for the HuffmanTree, an int n for the character value, and a String code which 
   // indicates left or right (based off the given '0's and/or '1's pattern). Constructs the  
   // HuffmanTree with its respective values. Does not check for errors
   private HuffmanNode treeConstructor(HuffmanNode root, int n, String code){
      if (code.isEmpty()){
         return new HuffmanNode(-1, n);
      } 
      if (root == null){
         root = new HuffmanNode(-1, -1);
      }
      if (code.charAt(0) == '0'){
         root.left = treeConstructor(root.left, n, code.substring(1));
      } else{
         root.right = treeConstructor(root.right, n, code.substring(1));
      }
      return root;
   }
   
   // parameters : takes a PrintStream output to write into
   // pre : the given PrintStream is able to be written into
   // post : writes the data from the current HuffmanTree tree into
   // the given "file" (PrintStream output) in standard format
   public void write(PrintStream output){
      writer(overallRoot, output, "");
   }
   
   // helper method for write. Does not check for errors. Takes a PrintStream output to write
   // into, a HuffmanNode root to use to find each character, and a String tracker to track 
   // the left and right motions (using '0' for left or '1' for right). Writes the data
   // given from the HuffmanTree into the given "file" (PrintStream output) standard format
   private void writer(HuffmanNode root, PrintStream output, String tracker){
      if (root != null){
         if (root.left == null && root.right == null){
            output.println(root.character);
            output.println(tracker);
         } else{
            writer(root.left, output, tracker + "0");
            writer(root.right, output, tracker + "1");                  
         }
      }
   }
   
   // parameters : takes a BitInputStream input that is used for decoding the HuffmanTree, a
   // PrintStream output to write into, and an int eof that indicates the eof value
   // post : writes the data from the given input into the given "file" (PrintStream output)
   public void decode(BitInputStream input, PrintStream output, int eof){
      int character = 0;
      while (character != eof){
         HuffmanNode root = overallRoot;
         while (root.left != null || root.right != null){
            int bit = input.readBit();
            if (bit == 0){
               root = root.left;
            } else{
               root = root.right;
            }
         }
         character = root.character;
         if (character != eof){
            output.write(character);
         }
      }
   }   
}