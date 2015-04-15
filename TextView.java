import java.awt.Color;

/**
 * The TextView provides a view of the populations of actors in the field as text.
 * In its current version, it can only plot exactly two different classes of 
 * animals. If further animals are introduced, they will not currently be displayed.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class TextView implements SimulatorView
{
    private final String STEP_PREFIX = "Step: ";
    private static final String POPULATION_PREFIX = "Population: ";
    
    // A statistics object computing and storing simulation information
    private FieldStats stats;

    /**
     * Constructor for objects of class TextView
     */
    public TextView()
    {
        stats = new FieldStats();
    }

    /**
     * Define a color to be used for a given class of animal.
     * Not used in this view.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    public void setColor(Class animalClass, Color color)
    {
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        stats.reset();
        
        int depth = field.getDepth();
        int width = field.getWidth();
        
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                Object actor = field.getObjectAt(row, col);
                if(actor != null) {
                    stats.incrementCount(actor.getClass());
                }
            }
        }
        stats.countFinished();

        System.out.println(STEP_PREFIX + step);
        System.out.println(POPULATION_PREFIX + stats.getPopulationDetails(field));
    }
    
    /**
     * Prepare for a new run.
     */
    public void reset()
    {
        stats.reset();
    }
}
