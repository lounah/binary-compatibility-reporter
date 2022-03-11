package com.lounah.ktbinaryreporter.descriptor

/**
 * Считаем разность между двумя коллекциями с учетом того,
 * что каждая коллекция может содержать повторяющиеся (одинаковые) элементы
 *
 * val collectionA = listOf(a, a, a, b)
 * val collectionB: listOf(a, b, c)
 * val diff = DiffUtil.difference(collectionA, collectionB)
 * println(diff.added) // [c]
 * println(diff.removed) // [a, a]
 */
internal object DiffUtil {

    data class Patch<out T>(val added: Collection<T>, val removed: Collection<T>)

    fun difference(original: Collection<String>, compareTo: Collection<String>): Patch<String> {
        return difference(original, compareTo) { it }
    }

    inline fun <T, K> difference(
        original: Collection<T>,
        compareTo: Collection<T>,
        crossinline keySelector: (T) -> K
    ): Patch<K> {
        val added = compareTo.calcDiff(original, keySelector)
        val removed = original.calcDiff(compareTo, keySelector)
        return Patch(added, removed)
    }

    private inline fun <T, K> Collection<T>.calcDiff(
        compareTo: Collection<T>,
        crossinline keySelector: (T) -> K
    ): Collection<K> {
        val diff = mutableListOf<K>()
        val comparison = compareTo.groupingBy(keySelector).eachCount()

        groupingBy(keySelector)
            .eachCount()
            .forEach { (key, count) ->
                val occurrence = comparison[key] ?: 0
                val diffOccurrence = count - occurrence
                repeat(diffOccurrence) {
                    diff += key
                }
            }

        return diff
    }
}