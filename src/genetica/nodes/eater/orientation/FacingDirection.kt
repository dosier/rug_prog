package genetica.nodes.eater.orientation

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
enum class FacingDirection(val deltaX : Int, val deltaY : Int) {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    fun rotate(rotation: AngularDirection) : FacingDirection {
        if(rotation == AngularDirection.CLOCK_WISE){
            val newOrdinal = ordinal+1

            if(newOrdinal > values().size)
                return NORTH

            return values()[newOrdinal]
        } else {
            val newOrdinal = ordinal-1

            if(newOrdinal < 0)
                return WEST

            return values()[newOrdinal]
        }
    }

}