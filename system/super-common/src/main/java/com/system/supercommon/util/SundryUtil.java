package com.system.supercommon.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description: 杂项工具
 *
 * @author Mr. Dai
 * @date 2023/3/29 16:17
 */
public class SundryUtil {

    @Getter
    @Setter
    public static class Tree{
        private String name;
        private List<Tree> child;
    }

    @Setter
    @Getter
    public static class Num{
        public Num(int i){
            this.index=i;
        }
        private int index;

        public void increment(){
            increment(1);
        }

        public void increment(int i){
            this.index+=i;
        }

        public int getIndex() {
            return index;
        }
    }

    /*树形遍历*/
    public static void eachTree(Tree tree){
        System.out.println("+"+tree.getName());
        List<Tree> child = tree.getChild();
        if(!CollectionUtils.isEmpty(child)){
            for (Tree tree1 : child) {
                eachTree(new Num(4),tree1);
            }
        }

    }


    private static void eachTree(Num num,Tree tree){
        StringBuffer buffer=new StringBuffer("|");

        for(int i=0;i<num.getIndex();i++){
            if (i==num.getIndex()/2) {
                if((num.getIndex()/4)%2!=0){
                    buffer.append("|");
                }
                buffer.append(" ");
            }else{
                buffer.append(" ");
            }

        }
        List<Tree> child = tree.getChild();
        if (!CollectionUtils.isEmpty(child)) {
            buffer.append("+");
            buffer.append(tree.getName());
            buffer.append("qi");
            System.out.println(buffer);
            for (Tree tree1 : child) {
                eachTree(new Num(num.getIndex()+4),tree1);
            }
        }else{
            buffer.append("-");
            buffer.append(tree.getName());
            System.out.println(buffer);
        }
    }
}
