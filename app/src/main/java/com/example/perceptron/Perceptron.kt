package com.example.perceptron

typealias Data = List<Pair<List<Double>, Boolean>>

fun delta(expected: Double, actual: Double) = expected - actual

fun predict(actual: Double, threshold: Double) = actual > threshold

fun output(equation: List<Pair<Double, Double>>) = equation.map{it.first * it.second}.sum()

fun train(data: Data, weights: List<Double>, rate: Double, threshold: Double, iterations: Int): List<Double> {
    if (iterations == 0)
        return weights
    else {
        val newWeights = trainTest(data, weights, rate, threshold)
        return train(data, newWeights, rate, threshold, iterations - 1)
    }
}

fun trainTest(data: Data, weights: List<Double>, rate: Double, threshold: Double): List<Double> {
    if (data.isEmpty()) {
        return weights
    } else {
        val xs = data.get(0).first
        val y = output(xs zip weights)
        val expected = data.get(0).second
        val prediction = predict(y, threshold)
        val err = delta(threshold, y)
        val newWeights = if (expected != prediction)
            weights.zip(xs){w, x -> w + x * rate * err}
        else
            weights
        return trainTest(data.drop(1), newWeights, rate, threshold)
    }
}

fun solve(data: Data, rate: Double, threshold: Double, iterations: Int): Pair<List<Boolean>, List<Double>> {
    val startingWeights = data.map{0.0}
    val weights = train(data, startingWeights, rate, threshold, iterations)
    val results = data.map{predict(output(it.first zip weights), threshold)}
    return results to weights
}
