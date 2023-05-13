
/**
 * 
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 *  Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * 
 * 
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Map<Integer, Integer> mapped = new HashMap();
    
        ListNode current1 = l1;
        ListNode current2 = l2;
        int key =-1;
       //int carryOver =0;

        while(current1 != null || current2 != null){
            key +=1;
            if(current1 != null){
                Integer exist = mapped.get(key) ==null ? 0: mapped.get(key);
                Integer sum = current1.val + exist;

                if(sum > 9){
                    int carryOver = sum/10;
                    int leave = sum% 10;
                    mapped.put(key, leave);
                    mapped.put(key+1, carryOver);
                }else{
                    mapped.put(key, sum) ;
                }
            current1 = current1.next;
                }
            if(current2 != null){
                Integer exist = mapped.get(key);
                Integer value = current2.val + (exist ==null ?0: exist);
                //int leave =0;
                if(value > 9){
                    int carryOver = value/10;
                    int leave = value% 10;
                    mapped.put(key, leave);
                    mapped.put(key+1, carryOver);
                }else{
                    mapped.put(key, value);
                }
            current2 = current2.next;
            }
            
        }

        ListNode result= new ListNode(mapped.get(0));

        for(int k=1; k < mapped.size(); k++){
            ListNode order =new ListNode(mapped.get(k));
            add(result, order);
        }

    return result;
    }

    private void add(ListNode l1, ListNode nNode){
        ListNode head = l1;
        boolean isUpdated = false;

        ListNode node = new ListNode(-1);

        while(head != null){
            if(head.next ==null && !isUpdated){
                ListNode newNode = new ListNode();
                newNode.val = nNode.val;
                head.next = newNode;
                isUpdated = true;
            }
            
            node.next = head.next;
            head = head.next;
        }

        l1 = node;
    }
}