    //SEVGİ NUR KARA  => 211450004
    
    
    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Queue;
    
public class App {
    
    static class Node {
        int data;     //node un içerisindeki değer
        Node left;    //node un left ve right değerlerini tutmak için
        Node right;
    
        public  Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
     static class BinarySearchTree {
        private Node root;
    
        public BinarySearchTree() {
            this.root = null;      // İlk başta rootumuzun boş bırakıyoruz çünkü henüz bir değer eklemedim
        }
            /*İnsert: tree ye eleman eklemek için yazdığım recursive olan metod
             *Her metodtan sonra recursive kullanmaktaki amacım tree üzerinde gezinmek veya düğüm silmek için bir düğümden diğer düğüme geçerken
             *aynı işlemi tekrarlamada kolaylık sağlamasıdır.
             * getPreOrderTraversal metodu: ağacın düğümlerini kök-sol-sağ sırasında ziyaret etmeyi sağlar.
             *  İlk olarak kök düğüm ziyaret edilir, ardından sol alt ağaç pre-order gezinmesi yapılır ve en son olarak sağ alt ağaç pre-order gezinmesi yapılır.
             * findLowestCommonAncestor metodu: en küçük ortak atayı bulmak için
             * printLevelOrder: queue yapısı kullandım
              */



        public void insert(int data) {     //tree ye eleman eklemek için yazdığım metod
            root = insertHelper(root, data);
        }
    
        //Recursive
        private Node insertHelper(Node root, int data) {
            if (root == null) {
                root = new Node(data);
                return root;
            }
    
            if (data < root.data) {
                root.left = insertHelper(root.left, data);
            } else if (data > root.data) {
                root.right = insertHelper(root.right, data);
            }
    
            return root;
        }
    
        public List<Integer> getPreOrderTraversal() {
            List<Integer> traversal = new ArrayList<>();      //düğümleri bir diziye ekledim ve bu diziyi döndürdüm.
            preOrderTraversalHelper(root, traversal);
            return traversal;
        }
    
         //Recursive
        private void preOrderTraversalHelper(Node root, List<Integer> traversal) {
            if (root == null) {
                return;
            }
    
            traversal.add(root.data);
            preOrderTraversalHelper(root.left, traversal);
            preOrderTraversalHelper(root.right, traversal);
        }
    
        public  Node findLowestCommonAncestor(int data1, int data2) {         //yakın akraba için yazdıpım public metod
            return findLowestCommonAncestorHelper(root, data1, data2);    //recursive
        }
    
         //Recursive
        private Node findLowestCommonAncestorHelper(Node root, int data1, int data2) {
            if (root == null) {
                return null;     //root kontrol edilir.
            }
    
            if (root.data > data1 && root.data > data2) {
                return findLowestCommonAncestorHelper(root.left, data1, data2);
            } else if (root.data < data1 && root.data < data2) {
                return findLowestCommonAncestorHelper(root.right, data1, data2);
            } else {   //root verilen düğümün her ikisinide içeriyosa root olarak döndürülür
                return root;
            }
        }
    
        public void deleteNode(int data) {
            root = deleteNodeHelper(root, data);
        }
    
         //Recursive
        private Node deleteNodeHelper(Node root, int data) {
            if (root == null) {
                return null;      //root düğümü kontrol edilir
            }
    
            if (data < root.data) {         //silinmesi istenen değer rootdaki değerden küçük
                root.left = deleteNodeHelper(root.left, data);
            } else if (data > root.data) {
                root.right = deleteNodeHelper(root.right, data);
            } else {
                if (root.left == null) {  //silinecek düğümün sol subtree si yoksa silinecek düğümün sağ subtree ile yer değişitirilir böylece düğüm silinmiş olur.
                    return root.right;
                } else if (root.right == null) {//silinecek düğümün sağ subtree si yoksa silinecek düğümün sol subtree ile yer değişitirilir böylece düğüm silinmiş olur.
                    return root.left;
                }
    
                root.data = left_right_subtree(root.right);
                root.right = deleteNodeHelper(root.right, root.data);    //istenen düğüm silinene kadar devam etmesi için
            }
    
            return root;
        }
    
        private int left_right_subtree(Node root) {    // silinecek düğümün hem sağ hem sol subtreesi varsa kullanmak üzere yazdığım metod
            int subtree = root.data;
            while (root.left != null) {
                subtree = root.left.data;
                root = root.left;
            }
            return subtree;
        }
    
        
       
        public void printLevelOrder() {                         //LevelOrderda queue yapısı kullandım
            System.out.print("Level Order output: ");
            if (root == null) {
                return;
            }
    
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
    
            while (!queue.isEmpty()) {    //queue nin boş olup olmadığına bakılır
                Node node = queue.poll();
                System.out.print(node.data + " ");
    
                if (node.left != null) {
                    queue.add(node.left);         //sol düğüm boş değilse queue eklenir
                }
    
                if (node.right != null) {        //sağ düğüm boş değilse queue eklenir
                    queue.add(node.right);
                }
            }
    
            System.out.println();
        }
    
        public static void main(String[] args) {
            BinarySearchTree bst = new BinarySearchTree();
    
            // Tree yapısını oluşturma
            bst.insert(70);
            bst.insert(45);
            bst.insert(32);
            bst.insert(21);
            bst.insert(13);
            bst.insert(27);
            bst.insert(40);
            bst.insert(56);
            bst.insert(46);
            bst.insert(68);
            bst.insert(87);
            bst.insert(77);
            bst.insert(73);
            bst.insert(80);
            bst.insert(79);
            bst.insert(82);
            bst.insert(92);
            bst.insert(89);
            bst.insert(99);
    
            // Pre-order gezinme
            List<Integer> preOrderTraversal = bst.getPreOrderTraversal();
            System.out.println("Pre-order output: " + preOrderTraversal);
    
            // En küçük ortak atası bulma
            Node ancestor1 = bst.findLowestCommonAncestor(27, 46);
            System.out.println("Lowest Common Ancestor of 27 and 46: " + ancestor1.data);
    
            Node ancestor2 = bst.findLowestCommonAncestor(77, 13);
            System.out.println("Lowest Common Ancestor of 77 and 13: " + ancestor2.data);

            
    
            // Düğüm silme
            bst.deleteNode(32);
            System.out.println("Binary Search Tree after deleting node 32");
            bst.printLevelOrder();
        }
    }
    


}    
   
  
