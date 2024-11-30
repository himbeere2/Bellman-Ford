fun main() {
    var nodes = listOf(
        Node(0, mapOf(Pair(0,0), Pair(1,2), Pair(2,2), Pair(3,3), Pair(4,300))),
        Node(1, mapOf(Pair(0,12), Pair(1,0), Pair(2,2), Pair(3,3), Pair(4,300))),
        Node(2, mapOf(Pair(0,4), Pair(1,2), Pair(2,0), Pair(3,3), Pair(4,300))),
        Node(3, mapOf(Pair(0,2), Pair(1,2), Pair(2,2), Pair(3,0), Pair(4,300))),
        Node(4, mapOf(Pair(0,4), Pair(1,2), Pair(2,2), Pair(3,3), Pair(4,0))),
    )
    val matrix = nodeToMatrix(nodes);
    bellmanFord(matrix);
}


/**
 * Must follow
 * 0...1...2...3
 * 1...0...x...x
 * 2...x...0...x
 * 3...x...x...0
 */

// fixme: Matrix has same value throughout whole column
fun nodeToMatrix(nodes: List<Node>) : Array<Array<Int>> {
    // note: Connections must always have the same order, so that node 0 is always followed by 1
    var filteredNodes : ArrayList<Node> = ArrayList(nodes.stream().filter{ n : Node -> nodes.stream().filter{ n2: Node -> n2.identifier == n.identifier}.count().toInt() == 1}.toList());
    val matrix : Array<Array<Int>> = kotlin.Array(filteredNodes.size) { Array<Int>(nodes.size){0} }
    for ((index, column) in matrix.withIndex()) {
        for (row: Int in column.indices) {
            column[row] = filteredNodes[index].connections[row] ?: Int.MAX_VALUE
        }
    }
    return matrix;
}

fun bellmanFord(matrix: Array<Array<Int>>) {
    var changes = false;
    for ((index, origin) in matrix.withIndex()) {
        for (target:Int in origin.indices) {
            print("$target  ");
            for (altRoute:Int in matrix.indices.filterNot { f -> f == index}) {
                if (target > altRoute + origin[altRoute]) {
                    origin[target] = altRoute + origin[altRoute];
                    changes = true;
                }
            }
        }
        println("")
    }
    println("--------------")
    if (changes) {
        bellmanFord(matrix)
    };
}