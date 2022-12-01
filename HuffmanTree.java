// Ethan Bai
// CSE 143 AR with Adithi Srikanth Raghavan
// Homework 8
import java.util.*;
import java.io.*;
public class HuffmanTree{
   private HuffmanNode overallRoot;
   private Queue<HuffmanNode> nodeStorage;
   
   public HuffmanTree(int[] count){
      nodeStorage = new PriorityQueue<>();
      for (int i = 0; i < count.length; i++){
         if (count[i] > 0){
            HuffmanNode letter = new HuffmanNode(count[i], i);
            nodeStorage.add(letter);
         }
      }
      nodeStorage.add(new HuffmanNode(1, count.length));
      /*while (!nodeStorage.isEmpty()){
         System.out.println(nodeStorage.remove().frequency);
      }*/
      while (nodeStorage.size() > 1){
         HuffmanNode first = nodeStorage.remove();
         HuffmanNode second = nodeStorage.remove();
         nodeStorage.add(new HuffmanNode(first.frequency + second.frequency, -1, first, second));
      }
      overallRoot = nodeStorage.remove();
      nodeStorage.add(overallRoot);
   }
   
   // parameters : 
   public HuffmanTree(Scanner input){
      int n = Integer.parseInt(input.nextLine());
      String code = input.nextLine();
      while (input.hasNextLine()){
         overallRoot = treeConstructor(overallRoot, n, code);
         n = Integer.parseInt(input.nextLine());
         code = input.nextLine();
      }
   }
   
   // pre :
   private HuffmanNode treeConstructor(HuffmanNode root, int n, String code){
      if (code.length() == 0){
         return new HuffmanNode(-1, n);
      } 
      if (root == null){
         root = new HuffmanNode(-1, -1);
      }
      if (code.charAt(0) == '0'){
         root.left = treeConstructor(root.left, n, code.substring(1, code.length()));
      } else{
         root.right = treeConstructor(root.right, n, code.substring(1, code.length()));
      }
      return root;
   }
   
   // parameters : takes a PrintStream output to write into
   // pre : the given PrintStream is able to be written into
   // post : writes the data from the current AOFIJOIJRWF tree into
   // the given "file" (PrintStream output)
   public void write(PrintStream output){
      writer(overallRoot, output, "");
   }
   
   // helper method for write. Does not check for errors. Writes the data
   // given from theDFJAODJFOIADJOFJAE tree into the given "file" (PrintStream output)
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
   
   // yee
   public void decode(BitInputStream input, PrintStream output, int eof){
      int nextBit = input.readBit();
      while (nextBit != -1){
         nextBit = decoder(input, output, eof, overallRoot, nextBit);
      }
   }
   
   // blah blah IM CALLING readBit ONE EXTRA TIME PER ROTATION - HOW TO FIX? Stop 1 before?
   private int decoder(BitInputStream input, PrintStream output, int eof, HuffmanNode root, int bit){
      if (root != null && bit != -1 && root.character != eof){
         if (root.left == null && root.right == null){
            output.write(root.character);
            return bit;
         } else if (bit == 0){
            bit = decoder(input, output, eof, root.left, input.readBit());            
         } else{
            bit = decoder(input, output, eof, root.right, input.readBit()); 
         }
      }
      return bit;
   }
}