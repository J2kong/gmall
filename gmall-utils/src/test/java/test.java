

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;


/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/13 20:12
 **/
public class test {


    /*  public static void main(String[] args) {
          int [] nums = {2,3,4};
          int target = 6 ;
              int[] temp = new int[2];
              Map<Integer,Integer> map = new HashMap<>();
              //遍历查找
              for(int i = 0; i < nums.length; i++){
                  int a = nums[i];
                  if(map.containsKey(target - a)){
                      temp[0] = map.get(target - a);
                      temp[1] = i;
                      System.out.println(temp[0]+"&"+temp[1]+"&"+(target-a));
                  }else {//如果找不到则存进去
                      map.put(nums[i], i);
                  }
              }

      }*/
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static class A

    {
      int id;
      String name;
      String key;

    }

    public static class B

    {
        int id;
        String name;
        String value;

        @Override
        public String toString() {
            return "B{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
    }
}
