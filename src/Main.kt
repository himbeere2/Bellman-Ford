fun main() {
    println("Hello World!")
}

fun node_to_matrix(nodes: List<Node>) {
    // note: Connections must always have the same order, so that node 0 is always followed by 1
    var filteredNodes = nodes.stream().filter{ n : Node -> nodes.stream().filter{ n2: Node -> n2.identifier == n.identifier}.count().toInt() == 1}.toList();
    val matrix : Array<Array<Int>> = kotlin.Array(filteredNodes.size) { Array<Int>(nodes.size){0} }
    for ((index, node) in matrix.withIndex()) {
        for (i: Int in node.indices) {
            node[i] = filteredNodes[index].connections[i] ?: Int.MAX_VALUE
        }
    }
}

fun bellman_ford(matrix: Array<Array<Int>>) {
    for ((index, timeToNodeAtIndex) in matrix.withIndex()) {

    }
}