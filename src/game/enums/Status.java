package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author Afrida Jahin, Lee Jia Yi, Tan Hong Yi
 * @version 1.0.0
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    WEAK_TO_STORM_RULER,
    ABLE_TO_ENTER_VALLEY,
    ABLE_TO_STEP_ON_FLOOR,
    DISARM,
    STUN,
    CHARGED,
    IS_LIT
}
