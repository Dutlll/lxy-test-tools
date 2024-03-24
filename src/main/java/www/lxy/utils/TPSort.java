package www.lxy.utils;
import org.springframework.util.Assert;

import java.util.*;

public class TPSort<T> {
    /**
     * 有向无环图 拓扑排序实现
     */
    public List<T> sort(List<T[]> pointer){
        Map<T,List<T>> out = new HashMap<>();
        Map<T,List<T>> in = new HashMap<>();
        Map<T,Integer> needNum = new HashMap<>();
        final Set<T> ts = new HashSet<>();
        for (T[] cur: pointer){
            T t = cur[0];
            T value = cur[1];
            if (!out.containsKey(value)){
                out.put(value,new ArrayList<>());
            }
            if (!out.containsKey(t)){
                out.put(t,new ArrayList<>());
            }
            if (!in.containsKey(value)){
                in.put(value,new ArrayList<>());
            }
            if (!in.containsKey(t)){
                in.put(t,new ArrayList<>());
            }
            out.get(t).add(value);
            in.get(value).add(t);
            // add all dot
            ts.add(t);
            ts.add(value);
        }
        for (T t: ts){
            if (!in.containsKey(t)){
                needNum.put(t,0);
            }
            needNum.put(t,in.get(t).size());
        }
        List<T> ans = new ArrayList<>();
        ArrayDeque<T> executeList = new ArrayDeque<>();
        for (T t: ts){
            if (needNum.get(t)==0){
                executeList.addLast(t);
                ans.add(t);
            }
        }
        Set<T> hasVisit = new HashSet<>();
        hasVisit.addAll(ans);
        while (!executeList.isEmpty()){
            T solveCur =executeList.removeFirst();
            // 依赖自己的节点
            List<T> needMine = out.get(solveCur);
            for (T t: needMine){
                needNum.put(t,needNum.get(t) - 1);
                if (needNum.get(t) == 0){
                    if (hasVisit.contains(t)){
                        throw new RuntimeException("存在间接依赖回环");
                    }
                    hasVisit.add(t);
                    executeList.addLast(t);
                    ans.add(t);
                }
            }
        }
        if (ans.size() != ts.size()){
            throw new RuntimeException("存在直接依赖回环");
        }
        return ans;
    }

    public static void main(String[] args) {
        final TPSort<String> stringTPSort = new TPSort<>();
        List<String[]> pointers = new ArrayList<>();
        pointers.add(new String[]{"4","3"});
        pointers.add(new String[]{"4","6"});
        pointers.add(new String[]{"3","2"});
        pointers.add(new String[]{"3","6"});
        pointers.add(new String[]{"6","5"});
        pointers.add(new String[]{"6","1"});
        pointers.add(new String[]{"1","2"});
        pointers.add(new String[]{"2","1"});
        pointers.add(new String[]{"2","6"});
        System.out.println(stringTPSort.sort(pointers));
    }
}
