// Ethan Bai
// CSE 143 AR with Adithi Srikanth Raghavan
// Homework 8
import java.util.*;
import java.io.*;
public class HuffmanTree{
   private HuffmanNode overallRoot;
   private Queue<HuffmanNode> nodeStorage;
   
   public HuffmanTree(int[] count){
      // adding everything to the priorityqueue
      nodeStorage = new PriorityQueue<>();
      for (int i = 0; i < count.length; i++){
         if (count[i] > 0){
            HuffmanNode letter = new HuffmanNode(count[i], i);
            //System.out.println(letter.character + " " + letter.frequency);
            nodeStorage.add(letter);
         }
      }
      // adding last character
      nodeStorage.add(new HuffmanNode(1, count.length));
      //HuffmanNode test = new HuffmanNode(1, count.length);
      //System.out.println(test.character + " " + test.frequency);
      // building the tree
      while (nodeStorage.size() > 1){
         HuffmanNode first = nodeStorage.remove();
         HuffmanNode second = nodeStorage.remove();
         nodeStorage.add(new HuffmanNode(first.frequency + second.frequency, first, second));
      }
      overallRoot = nodeStorage.remove();
      nodeStorage.add(overallRoot);
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
            writer(root.right, output, tracker + "0");         
            writer(root.left, output, tracker + "1");         
         }
      }
   }
}