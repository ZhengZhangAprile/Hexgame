package Hexgame;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aprile on 22/09/15.
 */
public class TestNooks {
    @Test
    public void testNooksOK(){
        String nook[] ={"093D038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D",
                        "014A051D110E012B073C108A010D098C101A008C062C065D018A123C126D016C120A083C",
                        "030A077B080C026C105A075D023A067C070A062C064A021C018A123C125A085C117A055C"
        };
        assertTrue(HexGame.legitimateNooks(nook[0]));
        assertTrue(HexGame.legitimateNooks(nook[1]));
        assertTrue("Incorrectly rejected correct crannies: " + nook[2],HexGame.legitimateNooks(nook[2]));
    }

    @Test
    public void testNooksnoOrientation(){
        String nook[]={"093D038D064E070C100D043D106A108F072080A051D112F082B016C118D060D125B122D",
                        "030077B080C026C105A075D023A067C070A062C064A021C018A123C125A085C117A055C"
        };
        assertFalse("Incorrectly accepted nonOrientation nook" + nook[0], HexGame.legitimateNooks(nook[0]));
        assertFalse("Incorrectly accepted nonOrientation nook" + nook[1], HexGame.legitimateNooks(nook[1]));

    }
    @Test
    public void testNooksassgningDifferentOrientation(){
        String nook[] ={"093DA038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D",
                "014A051D110EC012B073C108A010D098C101A008C062C065D018A123C126D016C120A083C",
                "030A077B080CA026C105A075D023A067C070A062C064A021C018A123C125A085C117A055C"
        };
        assertFalse("Incorrectly accepted differentOrientation nook" + nook[0], HexGame.legitimateNooks(nook[0]));
        assertFalse("Incorrectly accepted differentOrientation nook" + nook[1], HexGame.legitimateNooks(nook[1]));
        assertFalse("Incorrectly accepted differentOrientation nook" + nook[2], HexGame.legitimateNooks(nook[2]));
    }

}
