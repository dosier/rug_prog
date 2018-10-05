package genetica

import genetica.nodes.Plant
import genetica.nodes.eater.Eater
import genetica.nodes.eater.supervisor.evaluation.DecisionModel
import java.util.*
import kotlin.math.roundToInt

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
object World {

    var day = 0
    var year = 0

    val HEIGHT = 256
    val WIDTH = 512

    val START_PLANT = 250
    val START_EATER = 25

    val squares = emptyArray<Square>()
    val population = ArrayList<Eater>()
    val random = Random()
    var crossoverProbability = 75

    fun getSquare(x : Int, y : Int) : Square {
        return Square(x, y)
    }

    fun sequence(){

        if(day == 365){
            day = 0
            year++
        }

        val nextPopulation = ArrayList<Eater>()
        population.stream()
                .mapToInt { it.calculateFitness() }
                .average()
                .ifPresent {averageFitness ->
                    population.removeIf{ !DecisionModel(it, averageFitness.roundToInt()).reproduce() }
        }

        val missing = population.size - nextPopulation.size

        nextPopulation.addAll(IntRange(1, missing).map { Eater(randomSquare()) })
        nextPopulation.forEach {
            val randomIndex = random.nextInt(population.indexOf(it))

            if(random.nextInt(100) <= 75 )
                it.crossover(population[randomIndex])

        }

        population.clear()

        day++
    }

    private fun randomSquare() : Square {
        return Square(Random().nextInt(WIDTH), Random().nextInt(HEIGHT))
    }

    fun createPopulation(size : Int){
        IntRange(1, size).map { _ -> Eater(randomSquare()) }
    }

    fun respawnPlants(){
        IntRange(1, START_PLANT).forEach { _ -> Plant() }
    }

}