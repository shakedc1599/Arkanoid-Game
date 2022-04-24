package game.listeners;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Interface game.listeners.HitNotifier with: addHitListener and removeHitListener methods.
 */
public interface HitNotifier {

    /**
     * Add game.listeners.HitListener as a listener to hit events.
     *
     * @param hl type game.listeners.HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove game.listeners.HitListener from the list of listener to hit events.
     *
     * @param hl type game.listeners.HitListener.
     */
    void removeHitListener(HitListener hl);
}