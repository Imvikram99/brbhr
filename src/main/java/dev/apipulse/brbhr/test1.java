package dev.apipulse.brbhr;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.*;


public class test1 {
    private static class point {
        int x,y;
    }
    //list of x,y how many squares we can make,
    //x,y 1,1
    //1,1
    //1,2
    //2,1
    //2,2
    //
    //output = 1

//    1,1
//            1,2
//            2,1
//            2,2
//            3,1
//            3,2
//
//    output = 2

    public int countSquares(List<point> points){

        int count =0;
        point[] combination = new point[4];
        for(int i=0;i< points.size();i++){
            for(int j=0;i+1< points.size();j++){
                for(int k=j+1;k< points.size();k++){
                    for(int l=k+1;l< points.size();l++){
                        combination[0]=points.get(i);
                        combination[1]=points.get(j);
                        combination[2]=points.get(k);
                        combination[3]=points.get(l);
                        if(isSquare(combination)){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean isSquare(point[] points) {
        int[] distances = new int[6];
        int index=0;
        for(int i =0;i<points.length;i++){
            for(int j=i+1;j< points.length;j++){
                distances[index++]=squaredDistanceTo(points[i],points[j]);
            }
        }
        Set<Integer> ud= new HashSet<>();
        for(int distance: distances){
            ud.add(distance);
        }
        if(ud.size()!=2){
            return false;
        }
        int side = Collections.min(ud);
        int dia = Collections.max(ud);
        return dia == 2*side;
    }

    private int squaredDistanceTo(point point, point point1) {
        return (point.x-point1.x)*(point.x-point1.x) +(point.y-point1.y)*(point.y-point1.y);
    }

    public static void main(String args[]){


    }

    public static void p(String str){
        System.out.println(str);
    }
}
