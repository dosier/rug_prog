package genetica.nodes.eater

import genetica.*
import genetica.nodes.Plant
import genetica.nodes.eater.supervisor.Chromosome
import genetica.nodes.eater.orientation.AngularDirection
import genetica.nodes.eater.orientation.FacingDirection
import genetica.nodes.eater.orientation.LinearDirection
import java.util.*

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
class Eater(var square: Square) {

    private val chromosome = Chromosome()

    var plantsEaten = 0
    var compass = FacingDirection.values()[Random().nextInt(FacingDirection.values().size)]

    fun crossover(paired : Eater){
        chromosome.swap(paired.chromosome, Random().nextInt(129))
    }

    fun move(direction: LinearDirection){

        val square = World.getSquare(square.x + compass.deltaX * direction.delta, square.y + compass.deltaY * direction.delta)

        if(square.node is Plant) {
            square.removeNode()
            plantsEaten++
        }

        this.square = square

        chromosome.update(square)
    }

    fun turn(direction: AngularDirection){
        compass = compass.rotate(direction)
    }

    /**
     * Calculate the fitness of this [Eater].
     *
     * We add 1 to [plantsEaten] to avoid a fitness of 0.
     */
    internal fun calculateFitness() : Int{
        return plantsEaten + 1
    }

}