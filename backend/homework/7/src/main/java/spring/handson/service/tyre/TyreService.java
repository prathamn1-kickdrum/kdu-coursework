package spring.handson.service.tyre;

import org.springframework.stereotype.Service;
import spring.handson.model.tyre.BridgestoneTyre;
import spring.handson.model.tyre.MrfTyre;
import spring.handson.model.tyre.AbstractTyre;

import java.util.Random;

/**
 * class for generating tyre objects
 */
@Service
public class TyreService {
    private final MrfTyre mrf;
    private final BridgestoneTyre bridgestone;
    Random rand;

    public TyreService(MrfTyre mrf, BridgestoneTyre bridgestone) {
        this.mrf=mrf;
        this.bridgestone=bridgestone;
        rand=new Random();
    }

    public MrfTyre generateMrfTyre() {
        return mrf;
    }
    public BridgestoneTyre generateBridgestoneTyre() {
        return bridgestone;
    }

    /**
     * generates random tyre
     * @return bridgestone or mrf tyre
     */
    public AbstractTyre generateRandomTyre() {
        if(rand.nextInt()%2==0) {
            return mrf;
        }else {
            return bridgestone;
        }
    }
}
