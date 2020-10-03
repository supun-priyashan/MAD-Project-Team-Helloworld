package com.example.daytoday;

import com.example.daytoday.Model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    ListActivity ls;

    @Before
    public void setup(){
        ls = new ListActivity();
    }

    @Test
    public void testTot(){
        String a = ls.formatDecimal((float) 12.000);

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

}