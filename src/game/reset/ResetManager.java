package game.reset;

import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the Resettable instances.
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implement Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the instances by traversing through the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
        for (Resettable resettable: this.resettableList) {
            resettable.resetInstance();
        }
    }

    /**
     * Add the Resettable instance to the list
     * @param resettable the interface instance
     */
    public void appendResetInstance(Resettable resettable){
        this.resettableList.add(resettable);
    }

    /**
     * clean up instances (actor, item, or ground) that doesn't exist anymore in the map
     * FIXME: it does nothing, you need to implement it :)
     */
    private void cleanUp(){

    }
}
