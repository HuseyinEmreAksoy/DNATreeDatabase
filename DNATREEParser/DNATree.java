package DNATREEParser;
import java.util.ArrayList;
import java.util.List;

public class DNATree<E> extends AbstractTree<String> {
    boolean isUser = true; //for method or user
    boolean isLength = false;//display-length method or normal method
    boolean isIn = false; // is in tree

    //---------------- nested Node class ----------------
    protected static class Node<E> implements Position<E> {
        private E element;          // an element stored at this node
        private Node<E> parent;     // a reference to the parent node (if any)
        private Node<E> A;       // a reference to the A child (if any)
        private Node<E> C;      // a reference to the C child (if any)
        private Node<E> G;       // a reference to the G child (if any)
        private Node<E> T;      // a reference to the T child (if any)
        private Node<E> $;       // a reference to the $ child (if any)


        public Node(E e, Node<E> above, Node<E> AChild, Node<E> CChild, Node<E> GChild, Node<E> TChild, Node<E> $Child) {
            element = e;
            parent = above;
            A = AChild;
            C = CChild;
            G = GChild;
            T = TChild;
            $ = $Child;
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getA() {
            return A;
        }

        public Node<E> getC() {
            return C;
        }

        public Node<E> getG() {
            return G;
        }

        public Node<E> getT() {
            return T;
        }

        public Node<E> get$() {
            return $;
        }

        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setA(Node<E> AChild) {
            A = AChild;
        }

        public void setC(Node<E> CChild) {
            C = CChild;
        }

        public void setG(Node<E> GChild) {
            G = GChild;
        }

        public void setT(Node<E> TChild) {
            T = TChild;
        }

        public void set$(Node<E> $Child) {
            $ = $Child;
        }

    } //----------- end of nested Node class -----------

    protected Node<String> createNode(String string, Node<String> parent, Node<String> A, Node<String> C, Node<String> G, Node<String> T, Node<String> $) {
        return new Node<String>(string, parent, A, C, G, T, $);
    }

    protected Node<String> root = null;     // root of the tree
    private int size = 0;              // number of nodes in the tree


    public DNATree() {
    }      // constructs an empty DNA tree


    protected Node<String> validate(Position<String> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<String> node = (Node<String>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }
    // accessor methods (not already implemented in AbstractTree)

    @Override
    public int size() {
        return size;
    }


    @Override
    public Position<String> root() {
        return root;
    }

    @Override
    public Position<String> parent(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.getParent();
    }

    @Override
    public Iterable<Position<String>> children(Position<String> p) {
        List<Position<String>> snapshot = new ArrayList<>(5);    // max capacity of 5
        if (A(p) != null)
            snapshot.add(A(p));
        if (C(p) != null)
            snapshot.add(C(p));
        if (G(p) != null)
            snapshot.add(G(p));
        if (T(p) != null)
            snapshot.add(T(p));
        if ($(p) != null)
            snapshot.add($(p));
        return snapshot;
    }

    public int numChildren(Position<String> p) {// count of children
        int count = 0;
        if (A(p) != null)
            count++;
        if (C(p) != null)
            count++;
        if (G(p) != null)
            count++;
        if (T(p) != null)
            count++;
        if ($(p) != null)
            count++;
        return count;
    }

    public Position<String> A(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.getA();
    }

    public Position<String> C(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.getC();
    }

    public Position<String> G(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.getG();
    }

    public Position<String> T(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.getT();
    }

    public Position<String> $(Position<String> p) throws IllegalArgumentException {
        Node<String> node = validate(p);
        return node.get$();
    }

    public Position<String> addRoot(String string) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(string, null, null, null, null, null, null);
        size = 1;
        return root;
    }

    public Position<String> addA(Position<String> p, String string)
            throws IllegalArgumentException {
        Node<String> parent = validate(p);
        if (parent.getA() != null)
            throw new IllegalArgumentException("p already has a A child");

        Node<String> child = createNode(string, parent, null, null, null, null, null);
        parent.setA(child);
        size++;
        return child;
    }

    public Position<String> addC(Position<String> p, String string)
            throws IllegalArgumentException {
        Node<String> parent = validate(p);
        if (parent.getC() != null)
            throw new IllegalArgumentException("p already has a C child");
        Node<String> child = createNode(string, parent, null, null, null, null, null);
        parent.setC(child);
        size++;
        return child;
    }

    public Position<String> addG(Position<String> p, String string)
            throws IllegalArgumentException {
        Node<String> parent = validate(p);
        if (parent.getG() != null)
            throw new IllegalArgumentException("p already has a G child");
        Node<String> child = createNode(string, parent, null, null, null, null, null);
        parent.setG(child);
        size++;
        return child;
    }

    public Position<String> addT(Position<String> p, String string)
            throws IllegalArgumentException {
        Node<String> parent = validate(p);
        if (parent.getT() != null)
            throw new IllegalArgumentException("p already has a T child");
        Node<String> child = createNode(string, parent, null, null, null, null, null);

        parent.setT(child);
        size++;
        return child;
    }

    public Position<String> add$(Position<String> p, String string)
            throws IllegalArgumentException {
        Node<String> parent = validate(p);
        if (parent.get$() != null)
            throw new IllegalArgumentException("p already has $ left child");
        Node<String> child = createNode(string, parent, null, null, null, null, null);
        parent.set$(child);
        size++;
        return child;
    }

    public String set(Position<String> p, String string) throws IllegalArgumentException {
        Node<String> node = validate(p);
        String temp = node.getElement();
        node.setElement(string);
        return temp;
    }

    public void insert(String string) { //insert the tree with calling recursive method insertAux
        if (isEmpty())
            addRoot("");
        isUser = false;//our method is working
        if (size() > 1 && search(string + "$") != null) // if we have sequence we can not duplicate
            System.out.println("Error this " + string + " is already added in tree");
        else {
            insertAux(root(), string, 0);
            System.out.println(string + " is successfully added in " + depth(search(string + "$")) + "th level");
        }
        size++;
        isUser = true;//method is not working
    }

    private void insertAux(Position<String> p, String string, int index) {// we have position because we want to add correct place
                                                                // we have also index this is for check which letter in DNA
        char nucleotide;
        if (index + 1 > string.length())//our lvl is bigger than DNA
            add$(p, string);
        else {
            nucleotide = string.charAt(index);// found nucleotide
            if (nucleotide == 'A')// what is our nucleotide
            {
                if (A(p) == null || isExternal(A(p)))//if it is external we are in leaf we can add our value
                {
                    if (A(p) != null)//if is not empty create new child and set null this node
                    {
                        Position<String> temp = A(p);//replace the old DNA sequence
                        if (index + 1 > string.length() - 1)
                            add$(A(p), string);
                        else {
                            char x = string.charAt(index + 1);
                            if (x == 'A')
                                addA(A(p), string);
                            else if (x == 'C')
                                addC(A(p), string);
                            else if (x == 'T')
                                addT(A(p), string);
                            else
                                addG(A(p), string);
                        }
                        insertAux(temp, temp.getElement(), depth(temp));//because of we want to find new place
                        set(A(p), null);
                    } else // it is empty directly add DNA
                        addA(p, string);
                } else// it is internal keep going find true index
                    insertAux(A(p), string, index + 1);
            } else if (nucleotide == 'C')// what is our nucleotide
            {
                if (C(p) == null || isExternal(C(p)))//if it is external we are in leaf we can add our value
                {
                    if (C(p) != null)//if is not empty create new child and get null this node
                    {
                        Position<String> temp = C(p);//replace the old DNA sequence
                        if (index + 1 > string.length() - 1)
                            add$(C(p), string);
                        else {
                            char x = string.charAt(index + 1);
                            if (x == 'A')
                                addA(C(p), string);
                            else if (x == 'C')
                                addC(C(p), string);
                            else if (x == 'T')
                                addT(C(p), string);
                            else
                                addG(C(p), string);
                        }
                        insertAux(temp, temp.getElement(), depth(temp));//because of we want to find new place
                        set(C(p), null);
                    } else// it is empty directly add DNA
                        addC(p, string);
                } else// it is internal keep going find true index
                    insertAux(C(p), string, index + 1);
            } else if (nucleotide == 'G')// what is our nucleotide
            {
                if (G(p) == null || isExternal(G(p)))//if it is external we are in leaf we can add our value
                {
                    if (G(p) != null)//if is not empty create new child and get null this node
                    {
                        Position<String> temp = G(p);//replace the old DNA sequence
                        if (index + 1 > string.length() - 1)
                            add$(G(p), string);
                        else {
                            char x = string.charAt(index + 1);
                            if (x == 'A')
                                addA(G(p), string);
                            else if (x == 'C')
                                addC(G(p), string);
                            else if (x == 'T')
                                addT(G(p), string);
                            else
                                addG(G(p), string);
                        }
                        insertAux(temp, temp.getElement(), depth(temp));//because of we want to find new place
                        set(G(p), null);

                    } else// it is empty directly add DNA
                        addG(p, string);
                } else// it is internal keep going find true index
                    insertAux(G(p), string, index + 1);
            } else // what is our nucleotide
            {
                if (T(p) == null || isExternal(T(p)))//if it is external we are in leaf we can add our value
                {
                    if (T(p) != null)//if is not empty create new child and get null this node
                    {
                        Position<String> temp = T(p);//replace the old DNA sequence
                        if (index + 1 > string.length() - 1)
                            add$(T(p), string);
                        else {
                            char x = string.charAt(index + 1);
                            if (x == 'A')
                                addA(T(p), string);
                            else if (x == 'C')
                                addC(T(p), string);
                            else if (x == 'T')
                                addT(T(p), string);
                            else
                                addG(T(p), string);
                        }
                        insertAux(temp, temp.getElement(), depth(temp));//because of we want to find new place
                        set(T(p), null);
                    } else// it is empty directly add DNA
                        addT(p, string);
                } else// it is internal keep going find true index
                    insertAux(T(p), string, index + 1);
            }
        }
    }

    public String remove(String str) {//remove the tree with calling recursive method removeAux
        isUser = false;//method is working
        if (search(str+"$") == null) {// check DNA in this tree or not
            System.out.println(str +" is not found");
            isUser = true;//method is not working
            return null;
        }
        Position<String> temp = search(str + "$");
        isUser = true;//method is not working
        size--;
        System.out.println(str + " is removed from tree");
        return removeAux(temp);
    }

    public String removeAux(Position<String> p) {

        Position<String> temp = p;
        if (numChildren(parent(p)) == 2 && nullChecker(p) == 2) {// if we have 2 children if we remove one of them other one is go up side.
            if (A(parent(p)) != null && A(parent(p)) != p) {//if it is not empty and not equal to itself (removal element)
                set(parent(p), A(parent(p)).getElement());
                set(A(parent(p)), null);
            } else if (C(parent(p)) != null && C(parent(p)) != p) {//if it is not empty and not equal to itself (removal element)
                set(parent(p), C(parent(p)).getElement());
                set(C(parent(p)), null);
            } else if (G(parent(p)) != null && G(parent(p)) != p) {//if it is not empty and not equal to itself (removal element)
                set(parent(p), G(parent(p)).getElement());
                set(G(parent(p)), null);
            } else if (T(parent(p)) != null && T(parent(p)) != p) {//if it is not empty and not equal to itself (removal element)
                set(parent(p), T(parent(p)).getElement());
                set(T(parent(p)), null);
            } else if ($(parent(p)) != null && $(parent(p)) != p) {//if it is not empty and not equal to itself (removal element)
                set(parent(p), $(parent(p)).getElement());
                set($(parent(p)), null);
            }
            set(p, null);//remove from DNA tree
        } else
            set(p, null);//remove from DNA tree
        return temp.getElement();
    }

    public void display() {//display the tree with calling recursive method displayAux
        displayAux(root());
    }

    public void displayAux(Position<String> p) {
        if (p ==  root())//internal
            System.out.println("I");

        if (p != root() && p.getElement() != null) {
            for (int i = 0; i < depth(p); i++)
                System.out.print("." + " ");
            if (isLength)//if it is display-length
                System.out.println(p.getElement() + " sequence length is " + p.getElement().length());
            else
                System.out.println(p.getElement());
        }
        if (p.getElement() == null) {
            if (isInternal(p)) {
                for (int i = 0; i < depth(p); i++)
                    System.out.print("." + " ");
                System.out.println("I");
            }
            else {//if it is external
                if (parent(p).getElement() == null){
                    for (int i = 0; i < depth(p); i++)
                        System.out.print("." + " ");
                    System.out.println("E");
                }
            }
        }
        //preorder sequence
        if (A(p) != null)
            displayAux(A(p));
        if (C(p) != null)
            displayAux(C(p));
        if (G(p) != null)
            displayAux(G(p));
        if (T(p) != null)
            displayAux(T(p));
        if ($(p) != null)
            displayAux($(p));
    }

    public void displayLengths() {//display-length the tree with calling recursive method displayAux
        isLength = true;
        displayAux(root());
        isLength = false;
    }

    public int nullChecker(Position<String> p) {//how many children is not empty
        int count = 0;
        if (A(parent(p)) != null && A(parent(p)).getElement() != null)
            count++;
        if (C(parent(p)) != null && C(parent(p)).getElement() != null)
            count++;
        if (T(parent(p)) != null && T(parent(p)).getElement() != null)
            count++;
        if (G(parent(p)) != null && G(parent(p)).getElement() != null)
            count++;
        if ($(parent(p)) != null && $(parent(p)).getElement() != null)
            count++;
        return count;
    }

    public Position<String> search(String str) {//search the tree with calling recursive method searchAux
        Position<String> result = searchAux(root(), str, 0);
        if (!isIn)
            System.out.println(str + " is not found");
        isIn = false;
        return result;
    }

    public Position<String> searchAux(Position<String> p, String str, int index) {// we have position because we want to find correct place
                                                    // we have also index this is for check which letter in DNA and find number of nodes visited in the tree during the search
        if (str.contains("$")) {
            isIn = true;
            if (p != null && p.getElement() != null && p.getElement().equals(str.substring(0, str.length() - 1))) {//str.length() - 1 because last element is $
                if (isUser)
                    System.out.println(str.substring(0,str.length()-1) + " number of nodes visited in the tree during the search: " + index);
                return p;
            }
            if (index + 1 > str.length() - 1) {
                if ($(p) != null)
                    return searchAux($(p), str, index + 1);
                else {// if our length is smaller than index and $ is empty it means sequence is not in tree
                    if (isUser)
                        System.out.println(str + " is not found");
                    return null;
                }
            } else {
                if (!isUser) {//this is for help method it is find in this tree or not
                    if (str.charAt(index) == 'A') {
                        if (A(p) == null)
                            return null;
                        return searchAux(A(p), str, index + 1);
                    } else if (str.charAt(index) == 'C') {
                        if (C(p) == null)
                            return null;
                        return searchAux(C(p), str, index + 1);
                    } else if (str.charAt(index) == 'G') {
                        if (G(p) == null)
                            return null;
                        return searchAux(G(p), str, index + 1);
                    } else {
                        if (T(p) == null)
                            return null;
                        return searchAux(T(p), str, index + 1);
                    }
                } else {//find exact sequence
                    if (str.charAt(index) == 'A')
                        return searchAux(A(p), str, index + 1);
                    else if (str.charAt(index) == 'C')
                        return searchAux(C(p), str, index + 1);
                    else if (str.charAt(index) == 'G')
                        return searchAux(G(p), str, index + 1);
                    else
                        return searchAux(T(p), str, index + 1);
                }
            }
        } else {//print all contain str like prefix and str
            if (index + 1 > str.length())
                if ($(p) != null)
                    searchAux($(p), str, index + 1);

            if (p.getElement() != null && p.getElement().length() >= str.length() && p.getElement().substring(0, str.length()).equals(str)) //it can be prefix or not
            {
                isIn = true;
                System.out.println("Prefix sequence: " + p.getElement());
            }
            if (A(p) != null)
                searchAux(A(p), str, index + 1);
            if (C(p) != null)
                searchAux(C(p), str, index + 1);
            if (G(p) != null)
                searchAux(G(p), str, index + 1);
            if (T(p) != null)
                searchAux(T(p), str, index + 1);
            return null;
        }

    }
}


