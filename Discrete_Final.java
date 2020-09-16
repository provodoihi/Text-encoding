package BI9202;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

    // A Tree node
    class Node
    {
        char c;
        int frequency;
        Node left = null, right = null;
        Node(char c, int frequency)
        {
            this.c = c;
            this.frequency = frequency;
        }
        public Node(char c, int frequency, Node left, Node right) {
            this.c = c;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

    class Discrete_Final {
        // traverse the Huffman Tree and store Huffman Codes
        public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
            if (root == null)
                return;

            // found a leaf node
            if (root.left == null && root.right == null) {
                huffmanCode.put(root.c, str);
            }
            encode(root.left, str + "0", huffmanCode);
            encode(root.right, str + "1", huffmanCode);
        }

        // traverse the Huffman Tree and decode the encoded string
        public static int decode(Node root, int index, StringBuilder strb) {
            if (root == null)
                return index;

            // found a leaf node
            if (root.left == null && root.right == null) {
                System.out.print(root.c);
                return index;
            }
            index++;
            if (strb.charAt(index) == '0')
                index = decode(root.left, index, strb);
            else
                index = decode(root.right, index, strb);

            return index;
        }

        // Builds Huffman Tree and huffmanCode and decode given input text
        public static void buildHuffman(String text) throws IOException {
            // count frequency of appearance of each character and store it in a map
            Map<Character, Integer> frequency = new HashMap<>();
            for (int i = 0; i < text.length(); i++) {
                if (!frequency.containsKey(text.charAt(i))) {
                    frequency.put(text.charAt(i), 0);
                }
                frequency.put(text.charAt(i), frequency.get(text.charAt(i)) + 1);
            }

            // Create a priority queue to store live nodes of Huffman tree
            // Notice that highest priority item has lowest frequency
            PriorityQueue<Node> priq = new PriorityQueue<>((l, r) -> l.frequency - r.frequency);

            // Create a leaf node for each character and add it to the priority queue.
            for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
                priq.add(new Node(entry.getKey(), entry.getValue()));
            }

            // do till there is more than one node in the queue
            while (priq.size() != 1) {
                // Remove the two nodes of highest priority(lowest frequency) from the queue
                Node left = priq.poll();
                Node right = priq.poll();

                // Create a new internal node with these two nodes as children and with frequency equal to the sum of the two nodes frequencies.
                // Add the new node to the priority queue.
                int sum = left.frequency + right.frequency;
                priq.add(new Node('\0', sum, left, right));
            }

            // root stores pointer to root of Huffman Tree
            Node root = priq.peek();

            // traverse the Huffman tree and store the Huffman codes in a map
            Map<Character, String> huffmanCode = new HashMap<>();
            encode(root, "", huffmanCode);

            // print original text
            System.out.println("\nOriginal text is:\n" + text);
            System.out.println("\nEncoding text to binary file is in process");

            // print encoded binary
            StringBuilder strb = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                strb.append(huffmanCode.get(text.charAt(i)));
            }
            System.out.println("\nEncoded binary is :\n" + strb);

            //handle file writing
            File outFile = new File("DiscreteMath.bin");
            FileOutputStream outStream = new FileOutputStream(outFile);
            outStream.close();
        }

            public static void main(String[] args) throws Exception {

            // dialog show welcome
            DialogShow ds = new DialogShow();
            ds.DialogShow();

            // GUI file chooser
            System.out.println("\nPlease choose the text file you want");
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Please choose a text file");
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File(txt)", "txt");
            jfc.addChoosableFileFilter(filter);
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                System.out.println("You choose this file: " + jfc.getSelectedFile().getPath());
                // dialog message file encode here
                ds.DialogShow2();
            }
            else {
                System.out.println("You cancel to choose a text file");
                // dialog show cancel choosing file + terminate program
                ds.DialogShow3();
                System.exit(0);
            }

            // read content of text file
            ReadText rd = new ReadText();
            String text = rd.ReadFile(jfc.getSelectedFile().getPath());
            buildHuffman(text);
            ds.DialogShow4();
            System.exit(0);
            }
    }

