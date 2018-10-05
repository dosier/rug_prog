package genetica.nodes.eater.supervisor

import genetica.Square
import genetica.World
import genetica.nodes.eater.orientation.FacingDirection
import java.util.*

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
class Chromosome {

    private val rules = arrayOfNulls<Rule>(64)
    private val random = Random()

    fun update(cur: Square){
        var index = 0
        // 16 possible states
        for (state in 0 until 16)
            for (dir in 0 until 4){
                val direction = FacingDirection.values()[dir]
                rules[index++] = Rule(state, World.getSquare(cur.x + direction.deltaX, cur.y + direction.deltaY).node)
            }

    }

    fun randomize(cur: Square){

        rules.forEach {

            if(hit())
                it!!.state = random.nextInt(16)

            if(hit()){
                val randomDir = FacingDirection.values()[random.nextInt(4)]
                it!!.nodeInView = World.getSquare(cur.x + randomDir.deltaX, cur.y + randomDir.deltaY).node
            }

        }

    }

    private fun hit() : Boolean{
        return random.nextInt(100) == mutationProbability
    }

    fun swap(with: Chromosome, nextInt: Int) {
        var position = 0
        var index = -1
        rules.forEach {

            index++

            if(position >= nextInt){
                rules[index] = with.rules[index]
                return
            }
            it!!.state
            position++

            if(position >= nextInt){
                rules[index] = with.rules[index]
                return
            }
            it.nodeInView
            position++


        }
    }

    companion object {
        var mutationProbability = 1
    }
}