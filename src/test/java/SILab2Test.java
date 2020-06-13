import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    public static SILab2 silab2 = new SILab2();

    @Test
    void everyBranchTest(){
        RuntimeException ex;

        //A, B, T
        ex = assertThrows(RuntimeException.class , () -> silab2.function(null, Arrays.asList("usr1","usr2")));
        assertTrue(ex.getMessage().contains("The user is not instantiated"));

        //A, C, D, T
        ex = assertThrows(RuntimeException.class , () -> silab2.function(new User(null,null,"mail"),Arrays.asList("usr1","usr2")));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //A, C, E, F, T
        assertEquals(false, silab2.function(new User("usr1","usr1","mail"),Arrays.asList("usr1","usr2")));

        //A, C, E, G, H, T
        assertEquals(false, silab2.function(new User("usr1","abcd","mail"),Arrays.asList("usr1","usr2")));

        //A, C, E, G, I, J, K, Q, K, M, O, J.1, R, T
        assertEquals(false, silab2.function(new User("usr1","abcdabcd","mail"),Arrays.asList("usr1","usr2")));


        //A, C, E, G, I, J, K, Q, L, M, N, O, P, J.1, S, T
        assertEquals(false, silab2.function(new User("usr1","abcdabcd.","mail"),Arrays.asList("usr1","usr2")));

        //mixed
        assertEquals(false, silab2.function(new User("Username22","Password&^$","mail"),Arrays.asList("usr1","usr2")));
        assertEquals(false, silab2.function(new User("usr1","Abc2.%","mail"),Arrays.asList("usr1","usr2")));
        assertEquals(true, silab2.function(new User("usr1","Password96.","mail"),Arrays.asList("usr1","usr2")));
    }

    @Test
    void multipleConditionsTest(){
        // (user.getUsername()==null || user.getPassword()==null)
        //T||F
        //F||T
        //F||F
        //T||T
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class , () -> silab2.function(new User("abv",null,"mail"),Arrays.asList("usr1","usr2")));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        ex = assertThrows(RuntimeException.class , () -> silab2.function(new User(null,"abvg","mail"),Arrays.asList("usr1","usr2")));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        ex = assertThrows(RuntimeException.class , () -> silab2.function(new User(null,null,"mail"),Arrays.asList("usr1","usr2")));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        assertEquals(false, silab2.function(new User("usr1","abcd","mail"),Arrays.asList("usr1","usr2")));

        // (!digit || !upper || !special)
        //F || F || F
        assertEquals(false, silab2.function(new User("usr1","abcdabcd","mail"),Arrays.asList("usr1","usr2")));

        //T || F || F
        assertEquals(false, silab2.function(new User("usr1","abcdabcd1","mail"),Arrays.asList("usr1","usr2")));

        //F || T || F
        assertEquals(false, silab2.function(new User("usr1","Abcdabcd","mail"),Arrays.asList("usr1","usr2")));

        //F || F || T
        assertEquals(false, silab2.function(new User("usr1","abcdabcd.","mail"),Arrays.asList("usr1","usr2")));

        // T || T || F
        assertEquals(false, silab2.function(new User("usr1","Abcdabcd1","mail"),Arrays.asList("usr1","usr2")));

        // T || F || T
        assertEquals(false, silab2.function(new User("usr1","Abcdabcd.","mail"),Arrays.asList("usr1","usr2")));

        // F || T || T
        assertEquals(false, silab2.function(new User("usr1","abcdab1.","mail"),Arrays.asList("usr1","usr2")));

        // T || T || T
        assertEquals(true, silab2.function(new User("usr1","Password$1.","mail"),Arrays.asList("usr1","usr2")));

    }

}