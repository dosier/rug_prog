package genetica

import genetica.nodes.BlankSpace

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/icebrick/)
 * @since   05/10/2018
 * @version 1.0
 */
class Square(var x : Int, var y : Int) {

    var node : Node = BlankSpace()

    fun removeNode(){
        node = BlankSpace()
    }
}