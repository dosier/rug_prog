package genetica.nodes.eater.supervisor.evaluation

import genetica.nodes.eater.Eater
import java.util.*

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
class DecisionModel(private val eater: Eater, private val threshHold : Int) {

    fun reproduce() : Boolean{

        val fitness = eater.calculateFitness()

        if(fitness > threshHold)
            return Random().nextInt(100) <= 50

        return fitness > Random().nextInt(threshHold)
    }

}