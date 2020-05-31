package com.itheima;


import java.lang.reflect.Array;
import java.util.*;

public class Sunfa {
    public int subarraysDivByK(int[] A, int K) {
        HashMap<Integer,Integer> hs=new HashMap<>();
        int[] preSum = new int[A.length + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + A[i - 1];
        }
        for (int i = 1; i <preSum.length ; i++) {
            int mod=(K+preSum[i])%K;
            hs.put(mod,hs.getOrDefault(mod,0)+1);
        }
        int count=0;
        for (int key:hs.keySet()) {
            int a=hs.get(key);
            count+=a/2*(a-1);
        }
        if(hs.containsKey(0)){
            count+=hs.get(0);
        }
        return count;
        /*int count = 0;
        for (int i = 0; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == K) {
                    count++;
                }
            }
        }*/

    }


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res=new ArrayList<>();
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <candies.length ; i++) {
            max=Math.max(max,candies[i]);
        }
        for (int i = 0; i <candies.length ; i++) {
            boolean flag=(candies[i]+extraCandies)>=max?true:false;
            res.add(flag);
        }
        return res;
    }
    public int subtractProductAndSum(int n) {
        int a=1;
        int b=0;
        while (n!=0){
            int num=n%10;
            a*=num;
            b+=num;
            n/=10;
        }
        return a-b;
    }
    public String getHint(String secret, String guess) {
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0; i <secret.length() ; i++) {
            map.put(secret.charAt(i),map.getOrDefault(secret.charAt(i),0)+1);
        }
        int ps=0;
        int pg=0;
        int a=0;
        int b=0;
        while (ps<secret.length()){
            if(secret.charAt(ps)==guess.charAt(pg)){
                map.put(secret.charAt(ps),map.get(secret.charAt(ps))-1);
                a++;
            }
            ps++;
            pg++;
        }
        ps=0;
        pg=0;
        while (ps<secret.length()){
            if(map.containsKey(guess.charAt(pg))&&map.get(guess.charAt(pg))>0){
                map.put(guess.charAt(pg),map.get(guess.charAt(pg))-1);
                b++;
            }
            ps++;
            pg++;
        }
        String s=a+"A"+b+"B";
        return s;
    }
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hs=new HashMap();
        if(nums1.length>nums2.length){
            for (int i = 0; i <nums1.length ; i++) {
                hs.put(nums1[i],hs.getOrDefault(nums1[i],0)+1);
            }
            return judge(nums2,hs);
        }else{
            for (int i = 0; i <nums2.length ; i++) {
                hs.put(nums2[i],hs.getOrDefault(nums2[i],0)+1);
            }
            return judge(nums1,hs);
        }
    }

    private int[] judge(int[] nums, HashMap<Integer, Integer> hs) {
        ArrayList<Integer> list=new ArrayList();
        for (int i = 0; i <nums.length ; i++) {
            if(hs.containsKey(nums[i])&&hs.get(nums[i])>0){
                int index=hs.get(nums[i]);
                list.add(nums[i]);
                hs.put(nums[i],index--);
            }
        }
        int[] a=new int[list.size()];
        for (int i = 0; i <a.length ; i++) {
            a[i]=list.get(i);
        }
        return a;
    }
    public int firstUniqChar(String s) {
        int len=s.length();
        boolean[] flag = new boolean[len];
        HashMap<Character,Integer> hs=new HashMap<>();
        for (int i = 0; i <len ; i++) {
            char c=s.charAt(i);
            if(hs.containsKey(c)){
                flag[i]=true;
                flag[hs.get(c)]=true;
            }else{
                hs.put(s.charAt(i),i);
            }
        }
        for (int i = 0; i <len ; i++) {
            if(!flag[i]) return i;
        }
        return -1;
    }
    public int longestPalindrome(String s) {
        HashMap<Character,Integer> hs=new HashMap<>();
        for (int i = 0; i <s.length() ; i++) {
            hs.put(s.charAt(i),hs.getOrDefault(s.charAt(i),0)+1);
        }
        int[] nums=new int[hs.size()];
        int count=0;
        int i=0;
        for (char c:hs.keySet()) {
            int index=hs.get(c);
            nums[i]=index%2;
            count+=(index/2*2);
            i++;
        }
        for (int j = 0; j <nums.length ; j++) {
            if(nums[j]==1) return count+1;
        }
        return count;
    }

    public List<Integer> findAnagrams(String s, String p) {
        // 用于返回字母异位词的起始索引
        List<Integer> res = new ArrayList<>();
        // 用 map 存储目标值中各个单词出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : p.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        // 用另外一个 map 存储滑动窗口中有效字符出现的次数
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0; // 左指针
        int right = 0; // 右指针
        int valid = p.length(); // 只有当 valid == 0 时，才说明 window 中包含了目标子串
        while (right < s.length()) {
            // 如果目标子串中包含了该字符，才存入 window 中
            if (map.containsKey(s.charAt(right))) {
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
                // 只有当 window 中该有效字符数量不大于map中该字符数量，才能算一次有效包含
                if (window.get(s.charAt(right)) <= map.get(s.charAt(right))) {
                    valid--;
                }
            }
            // 如果 window 符合要求，即两个 map 存储的有效字符相同，就可以移动左指针了
            // 但是只有二个map存储的数据完全相同，才可以记录当前的起始索引，也就是left指针所在位置
            while (valid == 0) {
                if (right - left + 1 == p.length()) res.add(left);
                // 如果左指针指的是有效字符,需要更改 window 中的 key 对应的 value
                // 如果 有效字符对应的数量比目标子串少，说明无法匹配了
                if (map.containsKey(s.charAt(left))) {
                    window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                    if (window.get(s.charAt(left)) < map.get(s.charAt(left))) {
                        valid++;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String a="cbaebabacd";
        String b="abc";
        new Sunfa().findAnagrams(a,b);
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res=new ArrayList<>();
        int[] inDegree=new int[n];
        List<List<Integer>> list=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        boolean[] used=new boolean[n];
        for (int i = 0; i <n ; i++) {
            list.add(new ArrayList<Integer>());
        }
        for (int i = 0; i <edges.length ; i++) {
            inDegree[edges[i][0]]++;
            inDegree[edges[i][1]]++;
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);
        }
        for (int i = 0; i <inDegree.length ; i++) {
            if(inDegree[i]==1){
                queue.add(i);
            }
        }
        while (n>2){
            n-=queue.size();
            int len=queue.size();
            for (int i = 0; i <len ; i++) {
                int a=queue.poll();
                used[a]=true;
                for (int b: list.get(a)) {
                    if(--inDegree[b]==1){
                        queue.add(b);
                    }
                }
            }
        }
        for (int i = 0; i <used.length ; i++) {
            if(!used[i]){
                res.add(i);
            }
        }
        return res;
    }

}
