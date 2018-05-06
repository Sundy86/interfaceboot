package com.sc.springmvc.listener;

import com.sc.springmvc.model.CaseSystem;
import com.sc.springmvc.model.Role;

import java.util.*;

/**
 * Created by Suncy on 2018/4/21 0021.
 */
public class ObjectCache {
    private static Map<String,Object> map =new HashMap<String,Object>();
    public static void addCache(String key,Object value){
        map.put(key,value);
    }
    public static Object get(String key){
        if(map.containsKey(key)){
          return   map.get(key);
        }
        return null;
    }
    public static void deleteCache(String key){
        if(map.containsKey(key)){
            map.remove(key);
        }
    }

    public static List getAllObject(){
        List  list = new ArrayList();
        Set<String> set = map.keySet();
        for(String s : set){
            Object object = map.get(s);
            if(object instanceof CaseSystem){
                list.add((CaseSystem) object);
            }
            if( object instanceof Role){
                list.add((Role) object);
            }
        }
        return list;
    }

    public static List<CaseSystem> getAllSystem(){
        List<CaseSystem> list = new ArrayList<CaseSystem>();
        Set<String> set = map.keySet();
        for(String s : set){
           Object object = map.get(s);
           if(object instanceof CaseSystem){
               list.add((CaseSystem) object);
           }
        }
        return list;
    }

    public static String getName(String id){
        Object object = get(id);
        if(object!=null && object instanceof CaseSystem){
             return  ((CaseSystem) object).getSystemname();
        }
        if(object!=null && object instanceof Role){
            return  ((Role) object).getRolename();
        }
        return "";
    }
}
