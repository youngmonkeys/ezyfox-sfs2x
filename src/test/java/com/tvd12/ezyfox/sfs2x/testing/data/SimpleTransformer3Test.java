package com.tvd12.ezyfox.sfs2x.testing.data;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;
import com.tvd12.test.base.BaseTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import static org.testng.Assert.*;

import java.util.Collection;

import org.testng.annotations.Test;

/**
 * @author tavandung12
 * Created on Aug 29, 2016
 *
 */
public class SimpleTransformer3Test extends BaseTest {

    @Test
    public void test() {
        SFSDataWrapper wrapper = new SimpleTransformer().transform(new Result());
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_OBJECT);
        
        ISFSObject result = (ISFSObject) wrapper.getObject();
        
        ISFSArray cardIds = result.getSFSArray("1");
        assertEquals(cardIds.size(), 2);
        
        Collection<Integer> cardIds0 = cardIds.getIntArray(0);
        assertEquals(cardIds0.size(), 2);
        assertEquals(cardIds0.toArray()[0], 1);
        assertEquals(cardIds0.toArray()[1], 2);
        
        Collection<Integer> cardIds1 = cardIds.getIntArray(1);
        assertEquals(cardIds1.size(), 2);
        assertEquals(cardIds1.toArray()[0], 3);
        assertEquals(cardIds1.toArray()[1], 4);
        
        ISFSArray people = result.getSFSArray("2");
        assertEquals(people.size(), 2);
        
        ISFSArray people0 = people.getSFSArray(0);
        assertEquals(people0.size(), 2);
        
        ISFSObject people01 = people0.getSFSObject(0);
        assertEquals(people01.getUtfString("name"), "a");
        ISFSObject people02 = people0.getSFSObject(1);
        assertEquals(people02.getUtfString("name"), "b");
        
        ISFSArray people1 = people.getSFSArray(1);
        assertEquals(people1.size(), 2);
        
        ISFSObject people11 = people1.getSFSObject(0);
        assertEquals(people11.getUtfString("name"), "c");
        ISFSObject people12 = people1.getSFSObject(1);
        assertEquals(people12.getUtfString("name"), "d");
    }
    
    @Test
    public void test1() {
        SFSDataWrapper wrapper = new SimpleTransformer().transform(new Person[][] {
            {new Person("a"), new Person("b")},
            {new Person("c"), new Person("d")}
        });
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_ARRAY);
        
        ISFSArray people = (ISFSArray) wrapper.getObject();
        assertEquals(people.size(), 2);
        
        ISFSArray people0 = people.getSFSArray(0);
        assertEquals(people0.size(), 2);
        
        ISFSObject people01 = people0.getSFSObject(0);
        assertEquals(people01.getUtfString("name"), "a");
        ISFSObject people02 = people0.getSFSObject(1);
        assertEquals(people02.getUtfString("name"), "b");
        
        ISFSArray people1 = people.getSFSArray(1);
        assertEquals(people1.size(), 2);
        
        ISFSObject people11 = people1.getSFSObject(0);
        assertEquals(people11.getUtfString("name"), "c");
        ISFSObject people12 = people1.getSFSObject(1);
        assertEquals(people12.getUtfString("name"), "d");
    }
    
    @Data
    public static class Result {
        @ResponseParam("1")
        private int[][] cardIds = {{1, 2}, {3, 4}};
        @ResponseParam("2")
        private Person[][] persons = new Person[][] {
            {new Person("a"), new Person("b")},
            {new Person("c"), new Person("d")}
        };
    }
    
    @Data
    @AllArgsConstructor
    public static class Person {
        
        @ResponseParam
        private String name;
        
    }
    
}
