
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class List<T> {
    public int size;
    private static final Logger logger= (Logger) LogManager.getLogger(System.in);
    private Node<T> head;
    private Node<T> tail;
    private int i=0;

    public List(){
        this.size=10;

        for(int i=0;i<this.size;i++){
            if(i == 0){
                head=new Node<T>();
                tail=head;
            }
            else{
                Node<T> temp=new Node();
                Node<T> ptr=tail;
                tail.next=temp;
                tail=temp;
                tail.prev=ptr;
            }
        }
    }

    public void add(T data){
        if(i < this.size){
            Node<T> ptr=getNode(i);
            ptr.data=data;
        }
        else{
            this.size+=10;
            for(int i=0;i<10;i++){
                Node<T> temp=new Node();
                Node<T> ptr=tail;
                tail.next=temp;
                tail=temp;
                tail.prev=ptr;
            }

            Node<T> ptr=getNode(i);
            ptr.data=data;
        }
        i++;
    }

    private Node<T> getNode(int index){
        Node<T> ptr=head;
        for(int i=0;i<index;i++){
            ptr=ptr.next;
        }

        return ptr;
    }

    public void display(){
        Node<T> ptr=head;

        while (ptr != null){
            logger.info(ptr.data);
            ptr=ptr.next;
        }
    }

    public T get(int index){
        if(index > i){
            logger.error("Out of range Index : {}",index);

            return null;
        }
        else{
            Node<T> ptr=head;
            for(int i=0;i<index;i++){
                ptr=ptr.next;
            }

            return ptr.data;
        }
    }

    public T remove(int index){
        if(index < i){
            Node<T> ptr=head;

            if(index == 0){
                head=head.next;
                head.prev=null;

                return ptr.data;
            }
            else if(index == i-1){
                Node<T> temp=tail.prev;
                tail.prev=tail;
                tail.next=null;

                return temp.data;
            }
            else{
                for(int i=0;i<index-1;i++){
                    ptr=ptr.next;
                }
                Node<T> itemRemoved=ptr.next;
                Node<T> temp=ptr.next.next;
                ptr.next=temp;
                temp.prev=ptr;

                return itemRemoved.data;
            }
        }
        else{
            logger.error("Index {} not present", index );

            return null;
        }
    }

}
class Node<T>{
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(){
        //do nothing
    }

    public Node(T data){
        this.data=data;
    }
}
