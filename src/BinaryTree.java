public class BinaryTree {

    Node root;

    public String predecessor (int key) {
        Node focusNode = root;
        Node parent = root;

        while(focusNode.key != key) {
            parent = focusNode;
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else if (key > focusNode.key){
                focusNode = focusNode.rightChild;
            }
            else {
                return "Predecessor: doesn't exist";
            }
        }
        return "Predecessor of " + key + ": " + parent.key;
    }

    public String successor (int key) { //successor not functionable
        Node focusNode = root;

        while(focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else if (key > focusNode.key){
                focusNode = focusNode.rightChild;
            }
            else {
                return "Key: doesn't exist";
            }
        }
        if (focusNode.leftChild != null && focusNode.rightChild != null) {
            return "Successor of " + key + " fleft: " + focusNode.leftChild.key + " right: " + focusNode.rightChild.key;
        } else if (focusNode.leftChild == null && focusNode.rightChild != null) {
            return "Successor of " + key + " gleft: doesn't exist" + " right: " + focusNode.rightChild.key;
        } else if (focusNode.leftChild != null && focusNode.rightChild == null) {
            return "Successor of " + key + " sleft: " + focusNode.leftChild.key + " right: doesn't exist";
        } else {
            return "Successor doesn't exist";
        }
    }

    public int minimum() {
        Node focusNode = root;
        Node parent = root;

        while (focusNode != null) {
            parent = focusNode;
            focusNode = focusNode.leftChild;

        }
        return parent.key;
    }

    public int maximum() {
        Node focusNode = root;
        Node parent = root;

        while (focusNode != null) {
            parent = focusNode;
            focusNode = focusNode.rightChild;

        }
        return parent.key;
    }

    public Boolean remove(int key) {
        Node focusNode = root;
        Node parent = root;

        Boolean isItALeftChild = true;

        while (focusNode.key != key) {

            parent = focusNode;

            if (key < focusNode.key) {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.rightChild;
            }
        }
        if (focusNode == null) {
            return false;
        }
        // If Node doesn't have children delete it

        if (focusNode.leftChild == null && focusNode.rightChild == null) {

            // If root delete it

            if (focusNode == root)
                root = null;

                // If it was marked as a left child
                // of the parent delete it in its parent

            else if (isItALeftChild)
                parent.leftChild = null;

                // Vice versa for the right child

            else
                parent.rightChild = null;

        }

        // If no right child

        else if (focusNode.rightChild == null) {

            if (focusNode == root)
                root = focusNode.leftChild;

                // If focus Node was on the left of parent
                // move the focus Nodes left child up to the
                // parent node

            else if (isItALeftChild)
                parent.leftChild = focusNode.leftChild;

                // Vice versa for the right child

            else
                parent.rightChild = focusNode.leftChild;

        }

        // If no left child

        else if (focusNode.leftChild == null) {

            if (focusNode == root)
                root = focusNode.rightChild;

                // If focus Node was on the left of parent
                // move the focus Nodes right child up to the
                // parent node

            else if (isItALeftChild)
                parent.leftChild = focusNode.rightChild;

                // Vice versa for the left child

            else
                parent.rightChild = focusNode.rightChild;

        }

        // Two children so I need to find the deleted nodes
        // replacement

        else {

            Node replacement = getReplacementNode(focusNode);

            // If the focusNode is root replace root
            // with the replacement

            if (focusNode == root)
                root = replacement;

                // If the deleted node was a left child
                // make the replacement the left child

            else if (isItALeftChild)
                parent.leftChild = replacement;

                // Vice versa if it was a right child

            else
                parent.rightChild = replacement;

            replacement.leftChild = focusNode.leftChild;

        }

        return true;

    }

    public Node getReplacementNode(Node replacedNode) {

        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.rightChild;

        // While there are no more left children

        while (focusNode != null) {

            replacementParent = replacement;

            replacement = focusNode;

            focusNode = focusNode.leftChild;

        }
        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild

        if (replacement != replacedNode.rightChild) {

            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;

        }

        return replacement;

    }


    public String searchNode (int key) {
        Node focusNode = root;

        while(focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                return "No node with " + key + " value";
            }
        }
        return "Node " + key + " exists";
    }

    public void insertNode(int key) {
        Node newNode = new Node (key);

        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;

            Node parent;

            while (true) {
                parent = focusNode;

                if (key < focusNode.key) {

                    focusNode = focusNode.leftChild;


                    if (focusNode == null) {

                        // then place the new node on the left of it

                        parent.leftChild = newNode;
                        return; // All Done

                    }

                } else { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;

                    // If the right child has no children

                    if (focusNode == null) {

                        // then place the new node on the right of it

                        parent.rightChild = newNode;
                        return; // All Done
                    }
                }
            }

        }
    }

    public static void main(String[] args){

        BinaryTree tree = new BinaryTree();

        tree.insertNode(50);
        tree.insertNode(45);
        tree.insertNode(60);
        tree.insertNode(10);
        tree.insertNode(75);
        tree.remove(75);

        System.out.println(tree.searchNode(60));
        System.out.println("Min value: " + tree.minimum());
        System.out.println("Max value: " + tree.maximum());
        System.out.println(tree.predecessor(45));
        System.out.println(tree.successor(10));
        System.out.println(tree.successor(45));

    }
    class Node{
        int key;


        Node leftChild;
        Node rightChild;

        public Node(int key) {
            this.key = key;
        }



    }
}